package com.hongbao.api.service.user;

import com.hongbao.api.dao.*;
import com.hongbao.api.enums.MissionSubtype;
import com.hongbao.api.enums.MissionType;
import com.hongbao.api.model.ReAccountDetail;
import com.hongbao.api.model.ReUser;
import com.hongbao.api.model.ReUserRed;
import com.hongbao.api.model.ReUserRedDetail;
import com.hongbao.api.model.dto.ReBannerInfo;
import com.hongbao.api.model.dto.ReUserRedInfo;
import com.hongbao.api.model.dto.RedDetailInfo;
import com.hongbao.api.model.vo.RedDetailVo;
import com.hongbao.api.model.vo.RedVo;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Summer on 2016/12/23.
 */
@Service
public class UserRedService {

    @Autowired
    private ReRedBannerDAO reRedBannerDAO;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReUserRedDAO reUserRedDAO;
    @Autowired
    private ReUserRedDetailDAO reUserRedDetailDAO;
    @Autowired
    private UserCacheService userCacheService;

    /**
     * 红包池banner
     *
     * @param platform
     * @param versioncode
     * @param packageName
     * @param channelName
     * @param flag
     * @return
     */
    public List<ReBannerInfo> bannerList(int platform, int versioncode, String packageName, String channelName, boolean flag) {
        return reRedBannerDAO.bannerList(platform, versioncode, packageName, channelName, flag);
    }

    /**
     * 今日可抢红包次数
     *
     * @param userId
     * @return
     */
    public int redTimes(Integer userId) {
        int times = 0;
        if(userId != null) {
            ReUser reUser = reUserDAO.selectByPrimaryKey(userId);
            times = reUser.getFreeTimes() + reUser.getGainTimes();
        }
        return times;
    }

    /**
     * 红包列表
     *
     * @param userId        当前登录用户id并不是发红包人的id
     * @param redId         分页时用的最后一个红包池下的红包id
     * @return
     */
    public List<RedVo> redVoList(Integer userId, Long redId) {
        List<RedVo> redVoList = new ArrayList<>();
        List<ReUserRedInfo> list = reUserRedDAO.selectRedList(userId, redId);
        for (ReUserRedInfo info : list) {
            RedVo vo = redVo(info, userId, System.currentTimeMillis());
            redVoList.add(vo);
        }
        return redVoList;
    }


    /**
     * 用户发红包
     * @param redNum        红包总数量
     * @param singleMoney   单个红包金额
     * @param redContent    文字描述
     * @return
     */
    public String send(Integer userId, Integer redNum, BigDecimal singleMoney, String redContent) {

        Map<String, Object> dataMap = new HashMap<>(2);

        // 红包总金额
        BigDecimal totalMoney = singleMoney.multiply(new BigDecimal(redNum));

        ReUser reUser = reUserDAO.selectLockByUserId(userId);
        BigDecimal userMoney = reUser.getUserMoney();

        // 判断余额是否充足
        if(userMoney.compareTo(totalMoney) < 0) { // 余额不足
            dataMap.put("code", 0);
            dataMap.put("msg", "余额不足");
            return JsonUtil.buildSuccessDataJson(dataMap);
        }

        long nowTime = System.currentTimeMillis();
        String now = CommonMethod.fmtTime(nowTime);

        // 发送红包
        ReUserRed reUserRed = new ReUserRed();
        reUserRed.setUserId(userId);
        reUserRed.setRedTotalNum(redNum);
        reUserRed.setRedLeftNum(redNum);
        reUserRed.setSingleMoney(singleMoney);
        reUserRed.setTotalMoney(totalMoney);
        reUserRed.setRedContent(redContent);
        reUserRed.setCreateTime(now);
        reUserRed.setCreateTimeMs(nowTime);
        reUserRed.setUpdateTime(now);
        reUserRed.setRefundTime(nowTime + 24 * 60 * 60 * 1000);
        reUserRedDAO.insertSelective(reUserRed);

        // 更新账户
        ReUser user = new ReUser();
        user.setUserId(userId);
        user.setUserMoney(userMoney.subtract(totalMoney));
        user.setMissionTimes(reUser.getMissionTimes() + 1);
        user.setGainTimes(reUser.getGainTimes() + 1);
        user.setUpdateTime(nowTime);
        reUserDAO.updateByPrimaryKeySelective(user);

        // 记录明细
        ReAccountDetail reAccountDetail = new ReAccountDetail();
        reAccountDetail.setUserId(userId);
        reAccountDetail.setAppId(reUser.getAppId());
        reAccountDetail.setAccountMoney(totalMoney);
        reAccountDetail.setDetailType(0);
        reAccountDetail.setMissionType(MissionType.red_pool.val);
        reAccountDetail.setMissionSubtype(MissionSubtype.send_red.val);
        reAccountDetail.setDetailContent("发红包");
        reAccountDetail.setDetailTime(now);
        reAccountDetailDAO.insertSelective(reAccountDetail);

        // 删除缓存
        userCacheService.removeUser(userId);

        dataMap.put("code", 1);
        dataMap.put("msg", "发布成功");
        return JsonUtil.buildSuccessDataJson(dataMap);
    }

    /**
     * ReUserRedInfo 转换成 RedVo
     *
     * @param info          info内有一个userId，此用户id是发红包的人的id，用于判断是否是该用户自己发的红包
     * @param userId        登录用户的id
     * @param now
     * @return
     */
    private RedVo redVo(ReUserRedInfo info, Integer userId, long now) {

        RedVo vo = new RedVo();

        vo.setRedId(info.getRedId());
        vo.setPortrait(info.getPortrait());
        vo.setNickname(info.getNickname());
        vo.setRedContent(info.getRedContent());

        // 是否自己发布 (0-不是, 1-是)
        int isSelf = 0;
        if(userId != null) {
            if(userId.equals(info.getUserId())) {
                isSelf = 1;
            }
        }
        vo.setIsSelf(isSelf);

        // 发布时间
        long createTimeMs = info.getCreateTimeMs();
        long publishTime = now - createTimeMs;
        String createTime = "";
        long time = 0;
        if(publishTime < 60 * 1000) { // 60秒以内
            time = publishTime / 1000 + 1;
            createTime = time + "秒前";
        }else if(publishTime < 60 * 60 * 1000) { // 60分钟以内
            time = publishTime / (1000 * 60) + 1;
            createTime = time + "分钟前";
        }else if(publishTime < 24 * 60 * 60 * 1000) { // 24小时以内
            time = publishTime / (1000 * 60 * 60) + 1;
            createTime = time + "小时前";
        }else { // 超过1天
            createTime = CommonMethod.fmtMd(createTimeMs);
        }
        vo.setCreateTime(createTime);

        // 红包状态 (0-可抢 1-抢光 2-已抢过 3-已过期)
        int status = 0;
        String content = "";

        int totalNum = info.getRedTotalNum();
        int leftNum = info.getRedLeftNum();
        BigDecimal money = info.getRedMoney();
        long refundTime = info.getRefundTime();

        if(money == null) { // 未抢过，1是该红包被抢光，2是该红包过期了，3是可以抢红包
            if(leftNum == 0) { // 已抢光
                status = 1;
                content = "抢光啦,共有"+totalNum+"人领取";
            }else { // 未抢光
                if(now > refundTime) { // 已过期
                    status = 3;
                    if(isSelf == 0) { // 不是自己发布
                        content = "已过期";
                    }else { // 是自己发布
                        content = totalNum + "个红包，" + leftNum + "个已退回";
                    }
                }else { // 未过期
                    status = 0;
                    int gainNum = totalNum - leftNum;
                    if(isSelf == 0) { // 不是自己发布
                        content = gainNum + "人已领取,赶快来抢";
                    }else { // 是自己发布
                        content = totalNum + "个红包，" + gainNum + "个已领取";
                    }
                }
            }
        }else { // 已抢过
            status = 2;
            content = "已抢到"+money+"元";
        }

        vo.setRedStatus(status);
        vo.setStatusContent(content);

        return vo;

    }

    /**
     * 点击红包
     *
     * @param redId
     * @param userId
     * @return
     */
    public String click(Long redId, Integer userId) {

        Map<String, Object> dataMap = new HashMap<>();

        // 领取机会
        ReUser reUser = reUserDAO.selectLockByUserId(userId);
        int times = reUser.getFreeTimes() + reUser.getGainTimes();
        dataMap.put("times", times);

        ReUserRed userRed = reUserRedDAO.selectLockByRedId(redId);
        BigDecimal redMoney = userRed.getSingleMoney();
        long now = System.currentTimeMillis();

        // 判断是否抢过
        ReUserRedDetail reUserRedDetail = reUserRedDetailDAO.selectByRedIdAndUserId(redId, userId);
        if(reUserRedDetail != null) { // 已抢过
            dataMap.put("code", 0);
            dataMap.put("redStatus", 2);
            dataMap.put("statusContent", "已抢到"+redMoney+"元");
            return JsonUtil.buildSuccessDataJson(dataMap);
        }

        // 判断红包是否过期
        long refundTime = userRed.getRefundTime();
        if(now > refundTime){ // 已过期
            dataMap.put("code", 1);
            dataMap.put("redStatus", 3);
            dataMap.put("statusContent", "已过期");
            return JsonUtil.buildSuccessDataJson(dataMap);
        }

        // 判断是否有剩余
        int leftNum = userRed.getRedLeftNum();
        int totalNum = userRed.getRedTotalNum();
        if(leftNum == 0) { // 已抢完
            dataMap.put("code", 2);
            dataMap.put("redStatus", 1);
            dataMap.put("statusContent", "抢光啦,共有"+totalNum+"人领取");
            return JsonUtil.buildSuccessDataJson(dataMap);
        }

        int gainNum = totalNum - leftNum;
        if(times == 0) { // 机会已用完
            dataMap.put("code", 3);
            dataMap.put("redStatus", 0);
            dataMap.put("statusContent", gainNum + "人已领取,赶快来抢");
            return JsonUtil.buildSuccessDataJson(dataMap);
        }

        dataMap.put("code", 4);

        return JsonUtil.buildSuccessDataJson(dataMap);

    }

    /**
     * 领取红包
     *
     * @param redId
     * @param userId
     * @return
     */
    public String receive(Long redId, Integer userId) {

        Map<String, Object> dataMap = new HashMap<>(4);

        ReUser reUser = reUserDAO.selectLockByUserId(userId);
        int freeTimes = reUser.getFreeTimes();
        int gainTimes = reUser.getGainTimes();
        int times = freeTimes + gainTimes;

        ReUserRed userRed = reUserRedDAO.selectLockByRedId(redId);
        BigDecimal redMoney = userRed.getSingleMoney();
        long now = System.currentTimeMillis();
        String nowTime = CommonMethod.fmtTime(now);

        // 判断是否抢过
        ReUserRedDetail reUserRedDetail = reUserRedDetailDAO.selectByRedIdAndUserId(redId, userId);
        if(reUserRedDetail != null) { // 已抢过
            dataMap.put("code", 0);
            dataMap.put("redStatus", 2);
            dataMap.put("statusContent", "已抢到"+redMoney+"元");
            dataMap.put("times", times);
            return JsonUtil.buildSuccessDataJson(dataMap);
        }

        // 判断红包是否过期
        long refundTime = userRed.getRefundTime();
        if(now > refundTime){ // 已过期
            dataMap.put("code", 1);
            dataMap.put("redStatus", 3);
            dataMap.put("statusContent", "已过期");
            dataMap.put("times", times);
            return JsonUtil.buildSuccessDataJson(dataMap);
        }

        // 判断是否有剩余
        int leftNum = userRed.getRedLeftNum();
        int totalNum = userRed.getRedTotalNum();
        if(leftNum == 0) { // 已抢完
            dataMap.put("code", 2);
            dataMap.put("redStatus", 1);
            dataMap.put("statusContent", "抢光啦,共有"+totalNum+"人领取");
            dataMap.put("times", times);
            return JsonUtil.buildSuccessDataJson(dataMap);
        }

        // 判断是否有领取机会
        int gainNum = totalNum - leftNum;
        if((freeTimes + gainTimes) == 0) { // 机会已用完
            dataMap.put("code", 3);
            dataMap.put("redStatus", 0);
            dataMap.put("statusContent", gainNum + "人已领取,赶快来抢");
            dataMap.put("times", times);
            return JsonUtil.buildSuccessDataJson(dataMap);
        }

        // 记录红包明细
        reUserRedDetail = new ReUserRedDetail();
        reUserRedDetail.setRedId(redId);
        reUserRedDetail.setUserId(userId);
        reUserRedDetail.setRedMoney(redMoney);
        reUserRedDetail.setCreateTime(nowTime);
        reUserRedDetailDAO.insertSelective(reUserRedDetail);

        // 更新用户
        ReUser user = new ReUser();
        user.setUserId(userId);
        user.setUserMoney(reUser.getUserMoney().add(redMoney));
        user.setTodayMoney(reUser.getTodayMoney().add(redMoney));
        if(freeTimes > 0) {
            user.setFreeTimes(freeTimes - 1);
        }else {
            user.setGainTimes(gainTimes - 1);
        }
        user.setUpdateTime(now);
        reUserDAO.updateByPrimaryKeySelective(user);

        // 记录明细
        ReAccountDetail reAccountDetail = new ReAccountDetail();
        reAccountDetail.setUserId(userId);
        reAccountDetail.setAppId(reUser.getAppId());
        reAccountDetail.setAccountMoney(redMoney);
        reAccountDetail.setDetailType(1);
        reAccountDetail.setMissionType(MissionType.red_pool.val);
        reAccountDetail.setMissionSubtype(MissionSubtype.get_red.val);
        reAccountDetail.setDetailContent("抢红包");
        reAccountDetail.setDetailTime(nowTime);
        reAccountDetailDAO.insertSelective(reAccountDetail);

        //修改红包
        userRed.setRedLeftNum(userRed.getRedLeftNum()-1);
        userRed.setUpdateTime(CommonMethod.fmtTime(System.currentTimeMillis()));
        reUserRedDAO.updateByPrimaryKeySelective(userRed);

        // 删除缓存
        userCacheService.removeUser(userId);

        dataMap.put("code", 4);
        dataMap.put("redStatus", 2);
        dataMap.put("statusContent", "已抢到"+redMoney+"元");
        dataMap.put("times", times - 1);
        return JsonUtil.buildSuccessDataJson(dataMap);

    }

    /**
     * 领取红包详情
     *
     * @param redId
     * @param userId
     * @return
     */
    public RedDetailVo detail(Long redId, Integer userId) {

        ReUserRedInfo info = reUserRedDAO.selectRedDetail(userId, redId);
        BigDecimal redMoney = info.getRedMoney();
        BigDecimal totalMoney = info.getTotalMoney();
        BigDecimal receiveMoney = new BigDecimal("0.00");

        List<RedDetailInfo> detailList = reUserRedDetailDAO.selectByRedId(redId);
        for (RedDetailInfo detailInfo : detailList) {
            detailInfo.setReceiveTime(detailInfo.getReceiveTime().substring(5, 16));
            receiveMoney = receiveMoney.add(detailInfo.getRedMoney());
        }

        String redStatus;

        int leftNum = info.getRedLeftNum();
        int totalNum = info.getRedTotalNum();
        BigDecimal leftMoney = totalMoney.subtract(receiveMoney);
        if(leftNum == 0) { // 已抢完
            redStatus = totalNum + "个红包,已抢光";
        }else {
            long now = System.currentTimeMillis();
            long refundTime = info.getRefundTime();
            if(now > refundTime){ // 已过期
                redStatus = "已领取" + detailList.size() + "/" + totalNum + "个,共" + receiveMoney + "/" +
                        totalMoney + "元,其余" + leftNum + "个因过期已退回, 退回金额" + leftMoney + "元";
            }else {
                redStatus = "已领取" + detailList.size() + "/" + totalNum + "个,共" + receiveMoney + "/" + totalMoney + "元";
            }
        }


        RedDetailVo vo = new RedDetailVo();
        vo.setPortrait(info.getPortrait());
        vo.setNickname(info.getNickname());
        vo.setRedContent(info.getRedContent());
        if(redMoney == null) { // 未抢到
            vo.setIsReceive(0);
        }else { // 抢到
            vo.setIsReceive(1);
        }
        vo.setRedMoney(redMoney + "");
        vo.setDetailList(detailList);
        vo.setRedStatus(redStatus);

        return vo;

    }

}
