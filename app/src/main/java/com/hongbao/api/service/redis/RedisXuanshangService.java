package com.hongbao.api.service.redis;

import com.hongbao.api.consts.BaseConsts;
import com.hongbao.api.consts.CacheConsts;
import com.hongbao.api.dao.ReAccountDetailDAO;
import com.hongbao.api.dao.ReUserDAO;
import com.hongbao.api.dao.ReXuanshangDAO;
import com.hongbao.api.dao.ReXuanshangDetailDAO;
import com.hongbao.api.enums.MissionSubtype;
import com.hongbao.api.enums.MissionType;
import com.hongbao.api.model.ReAccountDetail;
import com.hongbao.api.model.ReUser;
import com.hongbao.api.model.ReXuanshang;
import com.hongbao.api.model.ReXuanshangDetail;
import com.hongbao.api.model.cache.UserInfo;
import com.hongbao.api.model.dto.ReBannerInfo;
import com.hongbao.api.model.vo.*;
import com.hongbao.api.service.user.UserCacheService;
import com.hongbao.api.service.user.UserService;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.util.StringUtil;
import com.hongbao.api.util.redis.RedisUtil;
import com.hongbao.api.util.redis.StringKeyRedisTemplateUtil;
import com.wujie.common.utils.FastJsonUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Janita on 2017/1/9.
 */
@Service
public class RedisXuanshangService {

    @Autowired
    private StringKeyRedisTemplateUtil cacheRedisTemplate_xuanshang;
    @Autowired
    private UserCacheService userCacheService;
    @Autowired
    private ReXuanshangDAO reXuanshangDAO;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReXuanshangDetailDAO reXuanshangDetailDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisBannerService redisBannerService;


    /**
     * 获取bann列表
     *
     * @param platform
     * @param versioncode
     * @param packageName
     * @param channelName
     * @param flag
     * @return
     */
    public List<ReBannerInfo> selectXuanshangBanner(int platform, int versioncode, String packageName, String channelName, boolean flag) {
        return redisBannerService.selectXuanshangBanner(platform, flag, versioncode, packageName, channelName);
    }

    /**
     * 获取发布悬赏任务的机会
     *
     * @param userId
     * @return
     */
    public int missionTimes(Integer userId) {
        if (!StringUtil.isEmpty(userId)) {
            UserInfo user = userCacheService.getUser(userId);
            return user.getMissionTimes();
        } else {
            return 0;
        }
    }

    /**
     * 悬赏任务首页
     * 悬赏任务列表
     * 悬赏任务列表不需要登录
     * 且发布人与做任务的人看见的都是一样的
     * 具体的区别在详情页下面的按钮区别
     *
     * @param xuanshangId 当前页面最后一个悬赏任务id
     * @param userId      当前登录用户id，没有登录就是null
     * @return
     */
    public List<XuanshangVo> getXuanshangList(Integer userId, Long xuanshangId) {
        List<String> listId;
        if (xuanshangId == null){
            listId = RedisUtil.zGetMembersByScoreRange(cacheRedisTemplate_xuanshang,CacheConsts.Z_XUANSHANG_ID_LIST,1, BaseConsts.PAGE_SIZE);
        }else {
            Double lastScore = RedisUtil.zGetScoreByMember(cacheRedisTemplate_xuanshang,CacheConsts.Z_XUANSHANG_ID_LIST,xuanshangId.toString());
            listId = RedisUtil.zGetMembersByScoreRange(cacheRedisTemplate_xuanshang,CacheConsts.Z_XUANSHANG_ID_LIST,lastScore+1,lastScore+BaseConsts.PAGE_SIZE);
        }
        if (listId == null || listId.size() ==0){
            reSortIdListInRedis();
            if (xuanshangId == null){
                listId = RedisUtil.zGetMembersByScoreRange(cacheRedisTemplate_xuanshang,CacheConsts.Z_XUANSHANG_ID_LIST,1, BaseConsts.PAGE_SIZE);
            }else {
                Double lastScore = RedisUtil.zGetScoreByMember(cacheRedisTemplate_xuanshang,CacheConsts.Z_XUANSHANG_ID_LIST,xuanshangId.toString());
                listId = RedisUtil.zGetMembersByScoreRange(cacheRedisTemplate_xuanshang,CacheConsts.Z_XUANSHANG_ID_LIST,lastScore+1,lastScore+BaseConsts.PAGE_SIZE);
            }
        }
        List<Long> xuanshangIdList = turnStringListToLongList(listId);
        List<ReXuanshang> xuanshangList = getXuanshangMetaList(xuanshangIdList);
        List<XuanshangVo> vos = new ArrayList<>();
        for (ReXuanshang xuan : xuanshangList){
            XuanshangVo vo = turnMetaXuanshangToIndexListXuanshangVo(userId,xuan);
            vos.add(vo);
        }
        Collections.reverse(vos);
        return vos;
    }

    /**
     * 把悬赏任务元数据转换成列表对象
     * @param userId        当前登录用户的id
     * @param xuanshang     列表对象
     * @return
     */
    public XuanshangVo turnMetaXuanshangToIndexListXuanshangVo(Integer userId,ReXuanshang xuanshang){

        XuanshangVo vo = null;
        if (xuanshang != null){
            vo = new XuanshangVo();
            Long id = xuanshang.getId();
            vo.setXuanshangId(id);
            UserInfo user = userCacheService.getUserIncludeBlack(xuanshang.getUserId());
            vo.setPortrait(user.getPortrait());
            vo.setNickname(user.getNickname());
            String createTime = xuanshang.getCreateTime();
            vo.setCreateTime(getShowTime(createTime));
            vo.setMissionDesc(xuanshang.getMissionDesc());
            vo.setTotalMoney(xuanshang.getSingleMoney().multiply(new BigDecimal(xuanshang.getTotalNum())));
            String missionContent = "总悬赏" + vo.getTotalMoney() + "元，简单几步就能赚钱，快来交作业～";
            vo.setMissionContent(missionContent);
            vo.setTotalNum(xuanshang.getTotalNum());
            Integer submitNum = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_SUBMIT_NUM,id.toString());
            if (submitNum == null || submitNum == 0 || submitNum == 0.0){
                submitNum = reXuanshangDetailDAO.getSubmitNum(id);
                RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_SUBMIT_NUM,id.toString(),submitNum);
            }
            if (submitNum == 0.0 || submitNum == 0){
                vo.setSubmitNum(0);
            }else {
                vo.setSubmitNum(Integer.parseInt(submitNum+""));
            }
            vo.setPassNum(xuanshang.getPassNum());
            String statusContent = "共有" + vo.getTotalNum() + "个任务，" + submitNum + "人已提交";
            vo.setStatusContent(statusContent);
            // 发布时间
            if (userId == null) {
                vo.setIsSelf(0);
            } else {
                if (userId .equals(xuanshang.getUserId())) {
                    vo.setIsSelf(1);
                }
            }
        }
        return vo;
    }

    /**
     * 获取悬赏任务原数据列表
     * @param xuanshangIdList   需要获取的数据的id
     * @return
     */
    public List<ReXuanshang> getXuanshangMetaList(List<Long> xuanshangIdList){
        List<ReXuanshang> xuanshangs = new ArrayList<>();
        if (xuanshangIdList != null && xuanshangIdList.size()>=0){
            for (Long id : xuanshangIdList){
                ReXuanshang xuanshang = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_META_OBJECT,id.toString());
                if (xuanshang == null){
                    xuanshang = reXuanshangDAO.selectByPrimaryKey(id);
                    if (xuanshang != null){
                        RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_META_OBJECT,id.toString(),xuanshang);
                    }
                }
                if (xuanshang != null){
                    xuanshangs.add(xuanshang);
                }
            }
        }
        return xuanshangs;
    }

    /**
     * 把从sortedSet中取出的string类型的id转换成long类型
     * @param data
     * @return
     */
    public List<Long> turnStringListToLongList(List<String> data){
        List<Long> ids = new ArrayList<>();
        if (data == null || data.size() == 0){
            return ids;
        }
        for (String strId:data){
            ids.add(Long.parseLong(strId));
        }
        return ids;
    }
    /**
     * 把发布时间转换未
     * 类似微信的显示方式
     * 45分钟前
     * 1小时前
     * 。。。。
     *
     * @param createTime
     * @return
     */
    public String getShowTime(String createTime) {

        long now = System.currentTimeMillis();
        long createTimeMs = CommonMethod.get13Timestamp(createTime);
        long publishTime = now - createTimeMs;
        long time;
        if (publishTime < 60 * 1000) { // 60秒以内
            time = publishTime / 1000 + 1;
            createTime = time + "秒前";
        } else if (publishTime < 60 * 60 * 1000) { // 60分钟以内
            time = publishTime / (1000 * 60) + 1;
            createTime = time + "分钟前";
        } else if (publishTime < 24 * 60 * 60 * 1000) { // 24小时以内
            time = publishTime / (1000 * 60 * 60) + 1;
            createTime = time + "小时前";
        } else { // 超过1天
            createTime = CommonMethod.fmtMd(createTimeMs);
        }
        return createTime;
    }

    /**
     * 我参与的任务
     *
     * @param id
     * @return
     */
    public List<XuanshangVo> getMyPartInMissionList(Integer userId, Long id) {
        List<String> listId;
        String key = CacheConsts.Z_PARTIN_ID_LIST_+userId;
        if (id == null){
            listId = RedisUtil.zGetMembersByScoreRange(cacheRedisTemplate_xuanshang,key,1, BaseConsts.PAGE_SIZE);
        }else {
            Double lastScore = RedisUtil.zGetScoreByMember(cacheRedisTemplate_xuanshang,key,id.toString());
            listId = RedisUtil.zGetMembersByScoreRange(cacheRedisTemplate_xuanshang,key,lastScore+1,lastScore+BaseConsts.PAGE_SIZE);
        }
        if (listId == null || listId.size() ==0){
            reSortPartInIdListInRedis(userId);
            if (id == null){
                listId = RedisUtil.zGetMembersByScoreRange(cacheRedisTemplate_xuanshang,key,1, BaseConsts.PAGE_SIZE);
            }else {
                Double lastScore = RedisUtil.zGetScoreByMember(cacheRedisTemplate_xuanshang,key,id.toString());
                listId = RedisUtil.zGetMembersByScoreRange(cacheRedisTemplate_xuanshang,key,lastScore+1,lastScore+BaseConsts.PAGE_SIZE);
            }
        }
        List<Long> detailIdList = turnStringListToLongList(listId);
        List<ReXuanshangDetail> detailList = getDetailMetaList(detailIdList);
        List<XuanshangVo> vos = new ArrayList<>();
        for (ReXuanshangDetail detail : detailList){
            Long xuanshangId = detail.getXuanshangId();
            ReXuanshang xuanshang = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_META_OBJECT,xuanshangId.toString());
            if (xuanshang == null){
                xuanshang = reXuanshangDAO.selectByPrimaryKey(xuanshangId);
                if (xuanshang != null){
                    RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_META_OBJECT,xuanshangId.toString(),xuanshang);
                }
            }
            if (xuanshang != null){
                XuanshangVo vo = new XuanshangVo();
                vo.setId(detail.getId());
                vo.setXuanshangId(xuanshang.getId());
                String missionContent = "总悬赏"+xuanshang.getSingleMoney().multiply(new BigDecimal(xuanshang.getTotalNum()))+"元，简单几步就能赚钱，快来交作业～";
                vo.setMissionContent(missionContent);

                String statusContent = "共有"+xuanshang.getTotalNum()+"个任务，"+xuanshang.getPassNum()+"人已通过";
                vo.setStatusContent(statusContent);

                // 状态; 0：待审核,1:已通过,2:未通过,3:来晚了，
                Integer detailStatus = detail.getDetailStatus();
                String  statusText;
                if (detailStatus == 1){
                    statusText = "已通过";
                }else if (detailStatus == 3){
                    statusText = "来晚了";
                }else if ((detailStatus == 2)){
                    statusText = "未通过";
                }else {
                    statusText="待审核";
                }
                vo.setDetailStatus(detailStatus);
                vo.setStatusText(statusText);
                vo.setMissionDesc(xuanshang.getMissionDesc());

                Integer xuanshangUserId =  xuanshang.getUserId();
                UserInfo userInfo = userCacheService.getUser(xuanshangUserId);
                vo.setPortrait(userInfo.getPortrait());
                vo.setNickname(userInfo.getNickname());
                vos.add(vo);
            }
        }
        Collections.reverse(vos);
        return vos;
    }

    /**
     * 获取悬赏提交详情列表
     * @param detailIdList
     * @return
     */
    public List<ReXuanshangDetail> getDetailMetaList(List<Long> detailIdList){
        List<ReXuanshangDetail> detailList = new ArrayList<>();
        if (detailIdList != null && detailIdList.size()>0){
            for (Long id:detailIdList){
                ReXuanshangDetail detail = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_DETAIL_OBJECT,id.toString());
                if (detail == null){
                    detail = reXuanshangDetailDAO.selectByPrimaryKey(id);
                    if (detail != null){
                        RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_DETAIL_OBJECT,id.toString(),detail);
                    }
                }
                if (detail != null){
                    detailList.add(detail);
                }
            }
        }
        return detailList;
    }


    /**
     * 我发布的悬赏任务列表
     *
     * @param xuanshangId 当前页面最后一个我发布的悬赏任务的id
     * @return
     */
    public List<XuanshangVo> getMySendMissionList(Integer userId, Long xuanshangId) {

        List<String> listId;
        String key = CacheConsts.Z_SEND_ID_LIST_+userId;
        if (xuanshangId == null){
            listId = RedisUtil.zGetMembersByScoreRange(cacheRedisTemplate_xuanshang,key,1, BaseConsts.PAGE_SIZE);
        }else {
            Double lastScore = RedisUtil.zGetScoreByMember(cacheRedisTemplate_xuanshang,key,xuanshangId.toString());
            listId = RedisUtil.zGetMembersByScoreRange(cacheRedisTemplate_xuanshang,key,lastScore+1,lastScore+BaseConsts.PAGE_SIZE);
        }
        if (listId == null || listId.size() ==0){
            reSortSendIdListInRedis(userId);
            if (xuanshangId == null){
                listId = RedisUtil.zGetMembersByScoreRange(cacheRedisTemplate_xuanshang,key,1, BaseConsts.PAGE_SIZE);
            }else {
                Double lastScore = RedisUtil.zGetScoreByMember(cacheRedisTemplate_xuanshang,key,xuanshangId.toString());
                listId = RedisUtil.zGetMembersByScoreRange(cacheRedisTemplate_xuanshang,key,lastScore+1,lastScore+BaseConsts.PAGE_SIZE);
            }
        }
        List<Long> detailIdList = turnStringListToLongList(listId);
        List<ReXuanshang> xuanshangList = getXuanshangMetaList(detailIdList);
        List<XuanshangVo> vos = new ArrayList<>();
        for (ReXuanshang xuanshang : xuanshangList) {
            XuanshangVo vo = new XuanshangVo();

            Long xuanId = xuanshang.getId();
            vo.setXuanshangId(xuanId);

            String missionContent = "总悬赏"+xuanshang.getSingleMoney().multiply(new BigDecimal(xuanshang.getTotalNum()))+"元，简单几步就能赚钱，快来交作业～";
            vo.setMissionContent(missionContent);

            String statusContent = "共有"+xuanshang.getTotalNum()+"个任务，"+xuanshang.getPassNum()+"人已通过";
            vo.setStatusContent(statusContent);
            Integer totalNum = xuanshang.getTotalNum();
            Integer passNum = xuanshang.getPassNum();
            // 状态; 1:待审核：10，2：已结束
            Integer detailStatus;
            String statusText;
            if (passNum >= totalNum) {
                detailStatus = 4;
                statusText = "已结束";
            } else {
                //找到待审核的数
                Integer toVerifyNum = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_TOVERIFY_NUM,xuanId.toString());
                if (toVerifyNum == null || toVerifyNum == 0){
                    toVerifyNum = reXuanshangDetailDAO.getToVerifyNum(vo.getXuanshangId());
                    RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_TOVERIFY_NUM,xuanId.toString(),toVerifyNum);
                }
                detailStatus = 0;
                statusText = "待审核:" + toVerifyNum;

            }
            vo.setDetailStatus(detailStatus);
            vo.setStatusText(statusText);

            vo.setMissionDesc(xuanshang.getMissionDesc());

            Integer xuanshangUserId = xuanshang.getUserId();
            UserInfo userInfo = userCacheService.getUser(xuanshangUserId);
            vo.setPortrait(userInfo.getPortrait());
            vo.setNickname(userInfo.getNickname());

            vos.add(vo);
        }
        Collections.reverse(vos);
        return vos;
    }


    /**
     * 用户发布悬赏任务
     *
     * @param totalNum    总个数
     * @param singleMoney 单个金额
     * @param missionDesc 描述／要求
     * @param imgs        图片，多张用分号隔开
     * @return
     */
    public String send(Integer userId, Integer totalNum, BigDecimal singleMoney, String missionDesc, String imgs) {

        Map<String, Object> dataResult = new HashMap<>(2);

        ReUser user = reUserDAO.selectLockByUserId(userId);

        BigDecimal totalMoney = singleMoney.multiply(new BigDecimal(totalNum));
        BigDecimal userMoney = user.getUserMoney();
        if (userMoney.compareTo(totalMoney) < 0) {
            dataResult.put("code", 0);
            dataResult.put("msg", "余额不足");
            return JsonUtil.buildSuccessDataJson(dataResult);
        }
        if (user.getMissionTimes() < 1) {
            dataResult.put("code", 1);
            dataResult.put("msg", "发布悬赏任务机会已用完");
            return JsonUtil.buildSuccessDataJson(dataResult);
        }

        user.setMissionTimes(user.getMissionTimes() - 1);
        user.setUpdateTime(System.currentTimeMillis());
        user.setUserMoney(userMoney.subtract(totalMoney));
        reUserDAO.updateByPrimaryKeySelective(user);

        ReAccountDetail detail = new ReAccountDetail();
        detail.setUserId(userId);
        detail.setAppId(user.getAppId());
        detail.setAccountMoney(totalMoney);
        detail.setDetailType(0);
        detail.setMissionType(MissionType.reward_mission.val);
        detail.setMissionSubtype(MissionSubtype.send_reward.val);
        detail.setDetailContent("发布悬赏任务");
        detail.setDetailTime(CommonMethod.fmtTime(System.currentTimeMillis()));
        reAccountDetailDAO.insertSelective(detail);

        ReXuanshang xuanshang = new ReXuanshang();
        xuanshang.setUserId(userId);
        xuanshang.setTotalNum(totalNum);
        xuanshang.setSingleMoney(singleMoney);
        xuanshang.setPassNum(0);
        xuanshang.setMissionDesc(missionDesc);
        xuanshang.setMissionImg(imgs);
        xuanshang.setCreateTime(CommonMethod.fmtTime(System.currentTimeMillis()));
        xuanshang.setUpdateTime(CommonMethod.fmtTime(System.currentTimeMillis()));
        reXuanshangDAO.insertSelective(xuanshang);

        dataResult.put("code", 2);
        dataResult.put("msg", "发布成功");

        {
            //修改userInfo缓存
            UserInfo userInfo = userCacheService.getUser(userId);
            userInfo.setMissionTimes(user.getMissionTimes());
            userInfo.setUserMoney(user.getUserMoney());
            userInfo.setUpdateTime(System.currentTimeMillis());
            userCacheService.updateUser(userInfo);
        }
        {
            //发布成功之后，重新给id排序
            reSortIdListInRedis();
            //把该对象存入redis：xuanshang（悬赏任务首页列表每个对象）
            RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_META_OBJECT,xuanshang.getId()+"",xuanshang);
        }
        {
            //该任务已通过数
            RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_PASS_NUM,xuanshang.getId().toString(),0);
            //未通过数
            RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_NOTPASS_NUM,xuanshang.getId().toString(),0);
            //待审核数
            RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_TOVERIFY_NUM,xuanshang.getId().toString(),0);
        }
        {
            //重新给我发布的排序
            reSortSendIdListInRedis(userId);
        }

        // 删除缓存
        userCacheService.removeUser(userId);

        return JsonUtil.buildSuccessDataJson(dataResult);
    }


    /**
     * 调用此方法的情景：
     * 1：用户发布了新的悬赏任务
     * 2：
     * 重新给redis中的悬赏任务排序
     */
    public void reSortIdListInRedis(){
        //按时间倒序取出所有的idList
        List<Long> idList = getIdListFromMySql();
        //重新在redis中排序
        doReSortIdListInRedis(CacheConsts.Z_XUANSHANG_ID_LIST,idList);
    }

    /**
     * 给某个用户参与的悬赏任务详情的id排序
     * @param userId
     */
    public void reSortPartInIdListInRedis(Integer userId){
        List<Long> idList = reXuanshangDetailDAO.getMyPartInIdList(userId);
        String key = CacheConsts.Z_PARTIN_ID_LIST_+userId;
        doReSortIdListInRedis(key,idList);
    }

    /**
     * 给某个用户发布的悬赏任务id排序
     * @param userId
     */
    public void reSortSendIdListInRedis(Integer userId){
        List<Long> idList = reXuanshangDAO.getMySendIdList(userId);
        String key = CacheConsts.Z_SEND_ID_LIST_+userId;
        doReSortIdListInRedis(key,idList);
    }

    /**
     * 从mysql中获取所有的悬赏任务id列表
     * @return
     */
    public List<Long> getIdListFromMySql(){
        return reXuanshangDAO.getIdList();
    }

    /**
     * 重新排序id列表
     * @param key
     * @param idList
     */
    public void doReSortIdListInRedis(String key,List<Long> idList){
        RedisUtil.deleteKey(cacheRedisTemplate_xuanshang,key);
        double score = 1;
        for (Long id : idList){
            RedisUtil.zAddMemberScorePair(cacheRedisTemplate_xuanshang,key,score,id.toString());
            score++;
        }
    }


    /**
     * 交作业
     * 提交悬赏任务给发布者审核
     *
     * @param xuanshangId   所做的悬赏任务的id
     * @param missionText   用户输入的备注
     * @param xuanshangImgs 用户上传的图片，用分号隔开
     *                      格式：["https://ag-aw-test.oss-cn-hangzhou.aliyuncs.com/i/10/1482893568000499.jpg","https://ag-aw-test.oss-cn-hangzhou.aliyuncs.com/i/10/1482893571000430.jpg"]
     * @return
     */
    public String submitXuanshang(Integer userId, Long xuanshangId, String missionText, String xuanshangImgs) {

        Map<String, Object> dataResult = new HashMap<>(2);
        //先从存放提交的缓存中查
        Boolean isAlreadySubmit = RedisUtil.sIsExistInSet(cacheRedisTemplate_xuanshang,CacheConsts.S_USER_ALREADY_SUBMIT,xuanshangId+"&"+userId);
        if (isAlreadySubmit){
            dataResult.put("code", 0);
            dataResult.put("msg", "已经做过该任务了");
            return JsonUtil.buildSuccessDataJson(dataResult);
        }else {
            ReXuanshangDetail oldDetail = reXuanshangDetailDAO.getDetailByUserId(xuanshangId, userId);
            if (oldDetail != null) {
                dataResult.put("code", 0);
                dataResult.put("msg", "已经做过该任务了");
                RedisUtil.sAdd(cacheRedisTemplate_xuanshang,CacheConsts.S_USER_ALREADY_SUBMIT,xuanshangId+"&"+userId);
                return JsonUtil.buildSuccessDataJson(dataResult);
            }
        }

        ReXuanshang xuanshang = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_META_OBJECT,xuanshangId.toString());
        if (xuanshang == null){
            xuanshang = reXuanshangDAO.selectByPrimaryKey(xuanshangId);
            RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_META_OBJECT,xuanshangId.toString(),xuanshang);
        }
        if (xuanshang != null){
            if (xuanshang.getTotalNum() <= xuanshang.getPassNum()){
                dataResult.put("code", 1);
                dataResult.put("msg", "该任务已经结束");
                return JsonUtil.buildSuccessDataJson(dataResult);
            }else {
                ReXuanshangDetail detail = new ReXuanshangDetail();
                detail.setXuanshangId(xuanshangId);
                detail.setUserId(userId);
                detail.setMissionImg(xuanshangImgs);
                detail.setMissionText(missionText);
                detail.setDetailStatus(0);
                detail.setCreateTime(CommonMethod.fmtTime(System.currentTimeMillis()));
                detail.setUpdateTime(CommonMethod.fmtTime(System.currentTimeMillis()));

                reXuanshangDetailDAO.insertSelective(detail);
                dataResult.put("code", 2);
                dataResult.put("msg", "交作业成功");

                {
                    //提交详情
                    RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_DETAIL_OBJECT,detail.getId().toString(),detail);
                    //标志该用户已经做过该任务，下次不能再提交
                    RedisUtil.sAdd(cacheRedisTemplate_xuanshang,CacheConsts.S_USER_ALREADY_SUBMIT,xuanshangId+"&"+userId);
                }
                {
                    addSubmitNun(xuanshangId,1);
                    addToVerifyNum(xuanshangId,1);
                    reSortPartInIdListInRedis(userId);
                }
                {
                    //记录此人做该任务的详情id，详情页面需要用
                    RedisUtil.zAddMemberScorePair(cacheRedisTemplate_xuanshang,CacheConsts.Z_XUANSHANG_USER_DETAILID,detail.getId(),xuanshangId+"&"+userId);
                }
                return JsonUtil.buildSuccessDataJson(dataResult);
            }
        }
        return null;
    }

    /**
     * 审核通过（悬赏任务）
     *
     * @param xuanshangId 悬赏任务id
     * @param id          用户做的详情id
     * @return
     */
    public String pass(Long xuanshangId, Long id) {

        Map<String, Object> dataResult = new HashMap<>(2);
        ReXuanshang xuanshang = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_META_OBJECT,xuanshangId.toString());
        if (xuanshang == null){
            xuanshang = reXuanshangDAO.selectByPrimaryKeyLock(xuanshangId);
            RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_META_OBJECT,xuanshangId.toString(),xuanshang);
        }
        if (xuanshang != null){
            Integer totalNum = xuanshang.getTotalNum();
            Integer passNum = xuanshang.getPassNum();
            if (totalNum <= passNum) {//该任务已经结束了
                dataResult.put("code", 0);
                dataResult.put("msg", "该任务已经结束");
                return JsonUtil.buildSuccessDataJson(dataResult);
            }else {
                //找到该详情记录
                ReXuanshangDetail detail = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_DETAIL_OBJECT,id.toString());
                if (detail == null){
                    detail = reXuanshangDetailDAO.selectByPrimaryKey(id);
                    RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_DETAIL_OBJECT,id.toString(),detail);
                }
                if (detail != null) {
                    Integer status = detail.getDetailStatus();
                    //状态; 0:已提交,待审核,1:已通过,2:未通过
                    if (status != 0) {
                        dataResult.put("code", 1);
                        dataResult.put("msg", "该任务已经处理过了");
                        return JsonUtil.buildSuccessDataJson(dataResult);

                    } else {//审核通过逻辑
                        detail.setDetailStatus(1);
                        detail.setUpdateTime(CommonMethod.fmtTime(System.currentTimeMillis()));
                        reXuanshangDetailDAO.updateByPrimaryKeySelective(detail);
                        RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_DETAIL_OBJECT,id.toString(),detail);

                        xuanshang.setUpdateTime(CommonMethod.fmtTime(System.currentTimeMillis()));
                        xuanshang.setPassNum(xuanshang.getPassNum() + 1);
                        reXuanshangDAO.updateByPrimaryKeySelective(xuanshang);
                        RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_META_OBJECT,xuanshangId.toString(),xuanshang);

                        //金额相关表更新
                        BigDecimal money = xuanshang.getSingleMoney();
                        Integer userId = detail.getUserId();

                        ReUser user = reUserDAO.selectLockByUserId(userId);
                        user.setUserMoney(user.getUserMoney().add(money));
                        user.setTodayMoney(user.getTodayMoney().add(money));
                        user.setUpdateTime(System.currentTimeMillis());
                        reUserDAO.updateByPrimaryKeySelective(user);

                        // 删除缓存
                        userCacheService.removeUser(userId);


                        ReAccountDetail accountDetail = new ReAccountDetail();

                        accountDetail.setUserId(userId);
                        accountDetail.setAppId(user.getAppId());
                        accountDetail.setAccountMoney(money);
                        accountDetail.setDetailType(1);
                        accountDetail.setMissionType(MissionType.reward_mission.val);
                        accountDetail.setMissionSubtype(MissionSubtype.finish_reward.val);
                        accountDetail.setDetailContent("完成悬赏任务");
                        accountDetail.setDetailTime(CommonMethod.fmtTime(System.currentTimeMillis()));
                        reAccountDetailDAO.insert(accountDetail);

                        {
                            //审核通过+1
                            RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_PASS_NUM,xuanshangId.toString(),passNum+1);
                            minusToVerifyNum(xuanshangId,1);
                        }
                        {
                            //添加到XUANSHANG_REWARD_STRING_+xuanshangId中
                            List<String> rewardUserList = RedisUtil.getObj(cacheRedisTemplate_xuanshang,CacheConsts.S_XUANSHANG_REWARD_STRING_+xuanshangId);
                            if (rewardUserList == null || rewardUserList .size() ==0){
                                rewardUserList = new ArrayList<>();
                                //查出该任务，已经审核通过的数据(轮播)
                                List<ReXuanshangDetail> passDetails = reXuanshangDetailDAO.getPassList(xuanshangId);
                                for (ReXuanshangDetail reXuanshangDetail : passDetails) {
                                    UserPassXuanshangVo vo = turnReXuanshangDetailToUserPassXuanshagnVo(reXuanshangDetail);
                                    rewardUserList.add(vo.getNickname() + "完成任务,赚到" + vo.getMoney() + "元");
                                }
                            }else {
                                //vo.getNickname() + "完成任务,赚到" + vo.getMoney() + "元"
                                String thisReward = user.getNickname()+"完成任务,赚到" + xuanshang.getSingleMoney() + "元";
                                rewardUserList.add(thisReward);
                                RedisUtil.setObj(cacheRedisTemplate_xuanshang,CacheConsts.S_XUANSHANG_REWARD_STRING_+xuanshangId,rewardUserList,CacheConsts.SECONDS_OF_ONE_WEEK);
                            }
                        }

                        //若该用户通过之后,达到了最大任务数,则,把其余未处理的都置为来晚了.
                        if (totalNum == (passNum + 1)) {
                            //找到该xuanshangId所有提交的,状态是0的记录,统一改为3
                            List<ReXuanshangDetail> detailList = reXuanshangDetailDAO.getAllListNotVerify(xuanshangId, id);
                            for (ReXuanshangDetail oneDetail : detailList) {
                                oneDetail.setDetailStatus(3);
                                oneDetail.setReason("来晚了");
                                oneDetail.setUpdateTime(CommonMethod.fmtTime(System.currentTimeMillis()));
                                reXuanshangDetailDAO.updateByPrimaryKeySelective(oneDetail);
                                RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_DETAIL_OBJECT,id.toString(),oneDetail);
                            }
                            dataResult.put("code", 3);
                            dataResult.put("msg", "操作成功,且该悬赏任务已经完成");
                            return JsonUtil.buildSuccessDataJson(dataResult);
                        } else {
                            dataResult.put("code", 2);
                            dataResult.put("msg", "操作成功");
                            return JsonUtil.buildSuccessDataJson(dataResult);
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 审核不通过
     *
     * @param id     用户做的详情id
     * @param reason 未通过理由
     * @return
     */
    public String notPass(Long id, String reason) {

        Map<String, Object> dataResult = new HashedMap(2);

        ReXuanshangDetail detail = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_DETAIL_OBJECT,id.toString());
        if (detail == null){
            detail = reXuanshangDetailDAO.selectByPrimaryKey(id);
        }
        detail.setReason(reason);
        detail.setUpdateTime(CommonMethod.fmtTime(System.currentTimeMillis()));
        detail.setDetailStatus(2);
        reXuanshangDetailDAO.updateByPrimaryKeySelective(detail);
        RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_DETAIL_OBJECT,id.toString(),detail);

        Long xuanshangId = detail.getXuanshangId();
        {
            addNotPassNum(xuanshangId,1);
            minusToVerifyNum(xuanshangId,1);
        }

        dataResult.put("code", 1);
        dataResult.put("msg", "操作成功");

        return JsonUtil.buildSuccessDataJson(dataResult);
    }

    /**
     * 我的审核列表
     * 发布任务者可以看到此列表
     * 该任务所有已经审核过的列表
     *
     * @param xuanshangId 悬赏任务id
     * @param id          当前页面的最后一个id
     * @return
     */
    public List<MyXuanshangListVo> getMyVerifiedList(Long xuanshangId, Long id) {

        List<MyXuanshangListVo> vos = reXuanshangDetailDAO.getMyVerifiedList(xuanshangId, id);
        for (MyXuanshangListVo vo : vos) {
            String missionImg = vo.getMissionImg();
            if (!StringUtil.isEmpty(missionImg)) {
                List<String> list = FastJsonUtil.parseArray(missionImg, String.class);
                vo.setImgList(list);
            }
            Integer status = vo.getDetailStatus();
            String statusText = "";
            if (status == 1) {
                statusText = "已通过";
            }
            if (status == 3) {
                statusText = "来晚了";
            }
            if (status == 2) {
                statusText = "未通过";
            }
            vo.setStatusText(statusText);
            ReXuanshangDetail detail = reXuanshangDetailDAO.selectByPrimaryKey(vo.getId());
            String submitTime = detail.getCreateTime();
            submitTime = getShowTime(submitTime);
            vo.setSubmitTime(submitTime);

        }
        return vos;
    }

    /**
     * 待审核用户列表（翻页）
     *(这个数据暂时不放到缓存中)
     * @param xuanshangId 悬赏任务id
     * @param id          当前页面的最后一个待审核记录待id
     * @return
     */
    public List<UserToVerifyVo> getToVerifyList(Long xuanshangId, Long id) {

        List<ReXuanshangDetail> verifyVos = reXuanshangDetailDAO.getToVerifyList(xuanshangId, id);
        List<UserToVerifyVo> toVerifyVos = new ArrayList<>(verifyVos.size());
        for (ReXuanshangDetail detail : verifyVos) {
            toVerifyVos.add(turnReXuanshangDetailToUserToVerifyVo(detail));
        }
        return toVerifyVos;
    }

    /**
     * 悬赏任务id
     *
     * @param userId      登录者id，未登录未null
     * @param xuanshangId 悬赏任务的id
     * @returnce
     */
    public XuanshangDetailVo getXuanshangDetail(Integer userId, Long xuanshangId) {

        XuanshangDetailVo detailVo = new XuanshangDetailVo();

        ReXuanshang xuanshang = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_META_OBJECT,xuanshangId.toString());
        if (xuanshang == null){
            xuanshang = reXuanshangDAO.selectByPrimaryKey(xuanshangId);
        }
        UserInfo user = userCacheService.getUserIncludeBlack(xuanshang.getUserId());
        detailVo.setXuanshangId(xuanshangId);
        detailVo.setPortrait(user.getPortrait());
        detailVo.setNickname(user.getNickname());
        detailVo.setMissionDesc(xuanshang.getMissionDesc());
        detailVo.setSingleMoney(xuanshang.getSingleMoney());
        detailVo.setCreateTime(getShowTime(xuanshang.getCreateTime()));
        String imgs = xuanshang.getMissionImg();
        if (!StringUtil.isEmpty(imgs)) {
            List<String> imgList = FastJsonUtil.parseArray(imgs, String.class);
            detailVo.setImgList(imgList);
        }

        List<String> rewardUserList = RedisUtil.getObj(cacheRedisTemplate_xuanshang,CacheConsts.S_XUANSHANG_REWARD_STRING_+xuanshangId);
        if (rewardUserList == null || rewardUserList.size() == 0){
            rewardUserList = new ArrayList<>();
            //查出该任务，已经审核通过的数据(轮播)
            List<ReXuanshangDetail> passDetails = reXuanshangDetailDAO.getPassList(xuanshangId);
            for (ReXuanshangDetail detail : passDetails) {
                UserPassXuanshangVo vo = turnReXuanshangDetailToUserPassXuanshagnVo(detail);
                rewardUserList.add(vo.getNickname() + "完成任务,赚到" + vo.getMoney() + "元");
            }
        }

        detailVo.setRewardUserList(rewardUserList);

        boolean isEnd = isEnd(xuanshangId);
        if (userId == null) {//未登录，不是自己发布的详情页面
            if (isEnd) {
                detailVo.setButtonTitle("已结束");
                detailVo.setButtonStatus(1);
            } else {
                detailVo.setButtonTitle("交作业");
                detailVo.setButtonStatus(0);
            }
        } else {//登录
            if (userId.equals(xuanshang.getUserId())) {//自己发布的
                Integer totalNum = xuanshang.getTotalNum();
                Integer passNum = xuanshang.getPassNum();
                if (passNum >= totalNum) {
                    detailVo.setIsComplete(1);
                } else {
                    detailVo.setIsComplete(0);
                }
                detailVo.setPassNum(passNum);
                Integer notPassNum = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_NOTPASS_NUM,xuanshangId.toString());
                if (notPassNum == null || notPassNum == 0){
                    notPassNum = reXuanshangDetailDAO.getNotPassNum(xuanshangId);
                    if (notPassNum != null){
                        RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_NOTPASS_NUM,xuanshangId.toString(),notPassNum);
                    }
                }
                detailVo.setNotPassNum(notPassNum);

                Integer toVerifyNum = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_TOVERIFY_NUM,xuanshangId.toString());
                if (toVerifyNum == null || toVerifyNum == 0){
                    toVerifyNum = reXuanshangDetailDAO.getToVerifyNum(xuanshangId);
                    if (toVerifyNum != null){
                        RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_TOVERIFY_NUM,xuanshangId.toString(),toVerifyNum);
                    }
                }
                detailVo.setToVerifyNum(toVerifyNum);
                detailVo.setNumDesc("已通过: " + xuanshang.getPassNum() + " 未通过: " + notPassNum + " 待审核: " + toVerifyNum);

                //TODO: 2017/1/10  列出待我审核的列表(这个数据暂时不放到缓存中)
                List<ReXuanshangDetail> verifyVos = reXuanshangDetailDAO.getToVerifyList(xuanshangId, null);
                List<UserToVerifyVo> toVerifyVos = new ArrayList<>(verifyVos.size());
                for (ReXuanshangDetail detail : verifyVos) {
                    toVerifyVos.add(turnReXuanshangDetailToUserToVerifyVo(detail));
                }
                detailVo.setToVerifyList(toVerifyVos);

            } else {//不是自己发布的

                boolean isPartIn = isPartIn(userId, xuanshangId);
                if (isPartIn) {//此用户已经参与过该悬赏任务
                    ReXuanshangDetail detail;
                    Double detailId = RedisUtil.zGetScoreByMember(cacheRedisTemplate_xuanshang,CacheConsts.Z_XUANSHANG_USER_DETAILID,xuanshangId+"&"+userId);
                    if (detailId == null){
                        detail = reXuanshangDetailDAO.getDetailByUserId(xuanshangId, userId);
                        RedisUtil.zAddMemberScorePair(cacheRedisTemplate_xuanshang,CacheConsts.Z_XUANSHANG_USER_DETAILID,detail.getId(),xuanshangId+"&"+userId);
                    }else {
                        int detailIdint = detailId.intValue();
                        detail = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_DETAIL_OBJECT,Integer.valueOf(detailIdint).toString());
                        if (detail == null){
                            detail = reXuanshangDetailDAO.getDetailByUserId(xuanshangId, userId);
                            RedisUtil.zAddMemberScorePair(cacheRedisTemplate_xuanshang,CacheConsts.Z_XUANSHANG_USER_DETAILID,detail.getId(),xuanshangId+"&"+userId);
                        }
                    }

                    MyXuanshangListVo vo = new MyXuanshangListVo();
                    vo.setId(detail.getId());
                    vo.setMissionText(detail.getMissionText());
                    ReUser submitUser = reUserDAO.selectByPrimaryKey(detail.getUserId());
                    vo.setPortrait(submitUser.getPortrait());
                    vo.setNickname(submitUser.getNickname());
                    String submitTime = detail.getCreateTime();
                    submitTime = getShowTime(submitTime);
                    vo.setSubmitTime(submitTime);
                    String submitImgs = detail.getMissionImg();
                    if (!StringUtil.isEmpty(submitImgs)) {
                        List<String> imgList = FastJsonUtil.parseArray(submitImgs, String.class);
                        vo.setImgList(imgList);
                    }
                    Integer status = detail.getDetailStatus();
                    String statusText = "";
                    if (status == 1) {
                        statusText = "已通过";
                    }
                    if (status == 2) {
                        statusText = "未通过";
                    }
                    if (status == 3) {
                        statusText = "来晚了";
                    }
                    if (status == 0) {
                        statusText = "待审核";
                    }
                    vo.setStatusText(statusText);
                    vo.setReason(detail.getReason());
                    detailVo.setMySubmit(vo);
                    vo.setDetailStatus(status);

                    detailVo.setButtonStatus(2);
                    detailVo.setButtonTitle("已提交,请去我的任务查看");
                } else {
                    if (isEnd) {
                        detailVo.setButtonTitle("已结束");
                        detailVo.setButtonStatus(1);
                    } else {
                        detailVo.setButtonStatus(0);
                        detailVo.setButtonTitle("交作业");
                    }
                }
            }
        }
        return detailVo;
    }

    /**
     * 实体类转换
     *
     * @param detail
     * @return
     */
    public UserPassXuanshangVo turnReXuanshangDetailToUserPassXuanshagnVo(ReXuanshangDetail detail) {
        UserPassXuanshangVo vo = new UserPassXuanshangVo();
        Integer userId = detail.getUserId();
        UserInfo user = userCacheService.getUser(userId);
        vo.setNickname(user.getNickname());
        ReXuanshang xuanshang = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_META_OBJECT,detail.getXuanshangId().toString());
        if (xuanshang == null){
            xuanshang = reXuanshangDAO.selectByPrimaryKey(detail.getXuanshangId());
        }
        vo.setMoney(xuanshang.getSingleMoney());

        return vo;
    }

    /**
     * 实体类转换
     *
     * @param detail
     * @return
     */
    public UserToVerifyVo turnReXuanshangDetailToUserToVerifyVo(ReXuanshangDetail detail) {

        UserToVerifyVo vo = new UserToVerifyVo();
        vo.setId(detail.getId());
        vo.setMissionText(detail.getMissionText());
        ReUser user = reUserDAO.selectByPrimaryKey(detail.getUserId());
        vo.setPortrait(user.getPortrait());
        String submitImgs = detail.getMissionImg();
        vo.setNickname(user.getNickname());
        if (!StringUtil.isEmpty(submitImgs)) {
            List<String> imgList = FastJsonUtil.parseArray(submitImgs, String.class);
            vo.setImgList(imgList);
        }
        String submitTime = detail.getCreateTime();
        submitTime = getShowTime(submitTime);
        vo.setSubmitTime(submitTime);

        return vo;
    }

    /**
     * 该任务是否结束
     *
     * @param xuanshangId
     * @return
     */
    public Boolean isEnd(Long xuanshangId) {
        ReXuanshang xuanshang = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_META_OBJECT,xuanshangId.toString());
        if (xuanshang == null){
            xuanshang = reXuanshangDAO.selectByPrimaryKey(xuanshangId);
            RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_META_OBJECT,xuanshangId.toString(),xuanshang);
        }
        if (xuanshang.getPassNum().equals(xuanshang.getTotalNum())) {
            return true;
        }
        return false;
    }

    /**
     * 判断此人是是否参与过此悬赏任务
     *
     * @param userId      参与人id
     * @param xuanshangId 悬赏任务id
     * @return
     */
    public Boolean isPartIn(Integer userId, Long xuanshangId) {
        String redisMember = xuanshangId+"&"+userId;
        Boolean isAlreadySubmit = RedisUtil.sIsExistInSet(cacheRedisTemplate_xuanshang,CacheConsts.S_USER_ALREADY_SUBMIT,redisMember);
        if (isAlreadySubmit == null || isAlreadySubmit == false){
            ReXuanshangDetail detail = reXuanshangDetailDAO.getDetailByUserId(xuanshangId, userId);
            if (detail == null) {
                isAlreadySubmit = false;
            }else {
                isAlreadySubmit = true;
                RedisUtil.sAdd(cacheRedisTemplate_xuanshang,CacheConsts.S_USER_ALREADY_SUBMIT,redisMember);
            }
        }
        return isAlreadySubmit;
    }


    /**
     * 删除涉及违规的
     * 管理员删除
     *
     * @param xuanshangId 悬赏任务
     * @param userId      管理员id
     * @return
     */
    public String deleteXuanshang(Long xuanshangId, Integer userId) {

        Map<String, Object> dataResult = new HashMap<>(2);
        UserInfo userInfo = userCacheService.getUser(userId);
        Integer userType= userInfo.getUserType();
        if (userType == 1) {
            ReXuanshang xuanshang = reXuanshangDAO.selectByPrimaryKeyLock(xuanshangId);
            if (xuanshang != null) {
                deleteXuanshangAndDetails(xuanshangId);
                dataResult.put("code", 1);
                dataResult.put("msg", "删除成功");
                reSortIdListInRedis();
            }
        } else {
            dataResult.put("code", 0);
            dataResult.put("msg", "你无权删除");
        }

        return JsonUtil.buildSuccessDataJson(dataResult);
    }

    /**
     * 删除该用户发布的悬赏任务及提交的记录
     *
     * @param xuanshangId
     */
    public void deleteXuanshangAndDetails(Long xuanshangId) {
        reXuanshangDAO.removeByXuanshangId(xuanshangId);
        RedisUtil.hRemoveKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_META_OBJECT,xuanshangId.toString());
        List<Long> detailIdList = reXuanshangDetailDAO.getAllDetailIdList(xuanshangId);
        for (Long detailId : detailIdList){
            RedisUtil.hRemoveKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_DETAIL_OBJECT,detailId.toString());
        }
        reXuanshangDetailDAO.removeAllDetailsByXuanshangId(xuanshangId);
    }


    /**
     * 把该用户拉入黑名单
     *
     * 并且把
     *
     * @param xuanshangId 悬赏任务
     * @param userId      管理员id
     * @return
     */
    public String pushToBlack(Long xuanshangId, Integer userId) {

        Map<String, Object> dataResult = new HashMap<>(2);
        UserInfo userInfo = userCacheService.getUser(userId);
        Integer userType = userInfo.getUserType();
        if (userType == 1) {
            ReXuanshang xuanshang = reXuanshangDAO.selectByPrimaryKeyLock(xuanshangId);
            if (xuanshang != null) {
                Integer hisUserId = xuanshang.getUserId();
                //该用户的其他的任务也不应该展示出来，因为该用户被拉黑，无法登录，无法审核，倒不如看不到
                List<Long> hisXuanshangList = reXuanshangDAO.getHisXuanshangIdList(hisUserId);
                for (Long id : hisXuanshangList){
                    deleteXuanshangAndDetails(id);
                }
                userService.pushToBlack(hisUserId);
                userCacheService.removeUser(hisUserId);
                dataResult.put("code", 1);
                dataResult.put("msg", "拉黑成功");
                reSortIdListInRedis();
            }
        } else {
            dataResult.put("code", 0);
            dataResult.put("msg", "你无权操作");
        }

        return JsonUtil.buildSuccessDataJson(dataResult);
    }

    /**
     * 审核未通过数量+num
     * 并设置到缓存中
     * @param xuanshangId
     * @param num
     */
    public void addNotPassNum(Long xuanshangId,int num){
        Integer notPassNum = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_NOTPASS_NUM,xuanshangId.toString());
        if (notPassNum == null || notPassNum <= 0 ){
            notPassNum = reXuanshangDetailDAO.getNotPassNum(xuanshangId);
            RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_NOTPASS_NUM,xuanshangId.toString(),notPassNum);
        }else {
            RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_NOTPASS_NUM,xuanshangId.toString(),notPassNum+num);
        }
    }

    /**
     * 待审核数量-num
     * 并设置到缓存中
     * @param xuanshangId
     * @param num
     */
    public void minusToVerifyNum(Long xuanshangId,int num){
        Integer toVerifyNum = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_TOVERIFY_NUM,xuanshangId.toString());
        if (toVerifyNum == null || toVerifyNum == 0){
            toVerifyNum = reXuanshangDetailDAO.getToVerifyNum(xuanshangId);
            RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_TOVERIFY_NUM,xuanshangId.toString(),toVerifyNum);
        }else {
            RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_TOVERIFY_NUM,xuanshangId.toString(),toVerifyNum-num);
        }
    }

    /**
     * 待审核数量+num
     * @param xuanshangId
     * @param num
     */
    public void addToVerifyNum(Long xuanshangId,int num){
        Integer toVerifyNum = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_TOVERIFY_NUM,xuanshangId.toString());
        if (toVerifyNum == null || toVerifyNum == 0){
            toVerifyNum = reXuanshangDetailDAO.getToVerifyNum(xuanshangId);
            RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_TOVERIFY_NUM,xuanshangId.toString(),toVerifyNum);
        }else {
            RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_TOVERIFY_NUM,xuanshangId.toString(),toVerifyNum+num);
        }
    }

    /**
     * 提交数+num
     * @param xuanshangId
     * @param num
     */
    public void addSubmitNun(Long xuanshangId,int num){
        Integer submitNum = RedisUtil.hGetValueByKey(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_SUBMIT_NUM,xuanshangId.toString());
        if (submitNum == null || submitNum == 0){
            submitNum = reXuanshangDetailDAO.getSubmitNum(xuanshangId);
            RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_SUBMIT_NUM,xuanshangId.toString(),submitNum);
        }else {
            RedisUtil.hSetKeyValuePair(cacheRedisTemplate_xuanshang,CacheConsts.H_XUANSHANG_SUBMIT_NUM,xuanshangId.toString(),submitNum+num);
        }
    }
}

