package com.hongbao.api.service;

import com.hongbao.api.consts.CacheConsts;
import com.hongbao.api.dao.ReAccountDetailDAO;
import com.hongbao.api.dao.ReFixedRedDAO;
import com.hongbao.api.dao.ReReceiveDetailDAO;
import com.hongbao.api.dao.ReUserDAO;
import com.hongbao.api.enums.MissionSubtype;
import com.hongbao.api.enums.MissionType;
import com.hongbao.api.model.ReAccountDetail;
import com.hongbao.api.model.ReFixedRed;
import com.hongbao.api.model.ReReceiveDetail;
import com.hongbao.api.model.ReUser;
import com.hongbao.api.model.dto.ServiceResult;
import com.hongbao.api.service.redis.RedisFixedRedService;
import com.hongbao.api.service.user.UserCacheService;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Summer on 16/8/18.
 */
@Service
public class HbReceiveService {

    @Autowired
    private ReFixedRedDAO reFixedRedDAO;
    @Autowired
    private ReReceiveDetailDAO reReceiveDetailDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private HbFixedRedService hbFixedRedService;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private HongBaoCacheService hongBaoCacheService;
    @Autowired
    private UserCacheService userCacheService;
    @Autowired
    private RedisFixedRedService redisFixedRedService;


    /**
     * 是否开始
     * @param redType
     * @param redId
     * @return
     */
    public boolean isStart(int redType, int redId) {

        boolean flag = false;

        long now = System.currentTimeMillis();
        String todayTime = CommonMethod.fmtTime(now);
        String time;

        if(redId == 1) {
            time = CommonMethod.fmtDay(now) + " 09:00:00";
        }else if(redId == 2) {
            time = CommonMethod.fmtDay(now) + " 12:00:00";
        }else if(redId == 3) {
            time = CommonMethod.fmtDay(now) + " 19:00:00";
        }else{
            time = CommonMethod.fmtDay(now) + " 22:00:00";
        }

        if(todayTime.compareTo(time) >= 0) {
            flag = true;
        }

        return flag;
    }


    /**
     * 是否抢过红包
     * @param userId
     * @param redType
     * @param redId
     * @return
     */
    public boolean isReceiveRed(int userId, int redType, int redId) {
        boolean flag = false;
        long now = System.currentTimeMillis();
        ReReceiveDetail detail = reReceiveDetailDAO.selectIsReceiveFixedRed(userId, redId, CommonMethod.fmtDay(now));
        if(detail != null) { // 今天已领取
            flag = true;
        }
        return flag;
    }

    /**
     * 领取红包
     * @param userId
     * @param redType
     * @param redId
     * @return
     */
    public String receiveRed(int userId, int redType, int redId) {

        Map<String, Object> dataResult = new HashMap<>();

        // 判断是否开始
        boolean isStart = isStart(redType, redId);
        if(!isStart) {
            dataResult.put("code", 1);
            dataResult.put("msg", "未到开抢时间!");
            return JsonUtil.buildSuccessDataJson(dataResult);
        }

        String key = CacheConsts.USER_FIXED + redId + "_" + userId;

        // 判断是否已领取
        boolean isReceiveRed = redisFixedRedService.isReceiveRed(key);
        if(isReceiveRed) {
            dataResult.put("code", 2);
            dataResult.put("msg", "请勿重复点击!");
            return JsonUtil.buildSuccessDataJson(dataResult);
        }

        // 记录缓存
        redisFixedRedService.userGetFixed(key, userId);

        ReFixedRed reFixedRed = redisFixedRedService.selectRedById(redId);

        if(reFixedRed.getFixedRemainder() > 0) {

            ReFixedRed red = reFixedRedDAO.selectNumLockById(redId);
            int totalNum = red.getFixedAmount();
            int leftNum = red.getFixedRemainder();

            if(leftNum > 0) {

                BigDecimal money = hbFixedRedService.getRandomMoney();
                leftNum = leftNum - 1;
                int redOrder = totalNum - leftNum;

                // 更新缓存
                redisFixedRedService.userFixedMoney(CacheConsts.FIXED_MONEY + redId + "_" + userId, money);

                // 更新红包剩余数
                reFixedRedDAO.updateRemainder(redId, leftNum);

                // 更新缓存
                reFixedRed.setFixedRemainder(leftNum);
                redisFixedRedService.updateFixed(CacheConsts.FIXED_RED + redId, reFixedRed);

                // 用户余额
                BigDecimal userMoney = reUserDAO.selectUserMoneyAndScore(userId).getUserMoney();

                dataResult.put("code", 0);
                dataResult.put("msg", "领取成功!");
                dataResult.put("userMoney", userMoney.add(money));
                dataResult.put("money", money);

                ServiceResult result = saveDetail(redId, money, userId, redOrder);
                result.invokeCallbackGetJson();

            }else {

                dataResult.put("code", 3);
                dataResult.put("msg", "手慢了,红包派完了!");

                return JsonUtil.buildSuccessDataJson(dataResult);

            }


        }else { // 红包已经领完

            dataResult.put("code", 3);
            dataResult.put("msg", "手慢了,红包派完了!");

            return JsonUtil.buildSuccessDataJson(dataResult);

        }

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 记录红包明细
     * @return
     */
    public ServiceResult saveDetail(final int redId, final BigDecimal money, final Integer userId, final int redOrder){
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.addCallback(new Runnable() {
            @Override
            public void run() {

                String title;

                if(redId == 1) {
                    title = "早餐红包";
                }else if(redId == 2) {
                    title = "午餐红包";
                }else if(redId == 3) {
                    title = "晚餐红包";
                }else{
                    title = "夜宵红包";
                }

                long now = System.currentTimeMillis();
                String nowTime = CommonMethod.fmtTime(now);

                // 记录红包
                ReReceiveDetail reReceiveDetail = new ReReceiveDetail();
                reReceiveDetail.setUserId(userId);
                reReceiveDetail.setRedType(0);
                reReceiveDetail.setRedId(redId);
                reReceiveDetail.setRedMoney(money);
                reReceiveDetail.setDetailTime(nowTime);
                reReceiveDetail.setRedOrder(redOrder);
                reReceiveDetailDAO.insert(reReceiveDetail);

                ReUser reUser = reUserDAO.selectUserLockByUserId(userId);
                BigDecimal userMoney = reUser.getUserMoney().add(money);
                BigDecimal todayMoney = reUser.getTodayMoney().add(money);

                // 记录账户明细
                ReAccountDetail detail = new ReAccountDetail();
                detail.setUserId(userId);
                detail.setAppId(reUser.getAppId());
                detail.setAccountMoney(money);
                detail.setDetailType(1);
                detail.setMissionType(MissionType.other_mission.val);
                detail.setMissionSubtype(MissionSubtype.fix_red.val);
                detail.setDetailContent(title);
                detail.setDetailTime(nowTime);
                reAccountDetailDAO.insert(detail);

                if (redOrder == 1){
                    BigDecimal awardMoney = new BigDecimal("0.3");
                    userMoney = userMoney.add(awardMoney);
                    todayMoney = todayMoney.add(awardMoney);

                    ReAccountDetail detail1 = new ReAccountDetail();
                    detail1.setUserId(userId);
                    detail1.setAppId(reUser.getAppId());
                    detail1.setAccountMoney(awardMoney);
                    detail1.setDetailType(1);
                    detail1.setMissionType(MissionType.other_mission.val);
                    detail1.setMissionSubtype(MissionSubtype.fix_red.val);
                    detail1.setDetailContent("定时红包奖励");
                    detail1.setDetailTime(nowTime);
                    reAccountDetailDAO.insert(detail1);

                }else if (redOrder == 2){
                    BigDecimal awardMoney = new BigDecimal("0.2");
                    userMoney = userMoney.add(awardMoney);
                    todayMoney = todayMoney.add(awardMoney);

                    ReAccountDetail detail1 = new ReAccountDetail();
                    detail1.setUserId(userId);
                    detail1.setAccountMoney(awardMoney);
                    detail1.setAppId(reUser.getAppId());
                    detail1.setDetailType(1);
                    detail1.setMissionType(MissionType.other_mission.val);
                    detail1.setMissionSubtype(MissionSubtype.fix_red.val);
                    detail1.setDetailContent("定时红包奖励");
                    detail1.setDetailTime(nowTime);
                    reAccountDetailDAO.insert(detail1);
                } else if (redOrder == 3){
                    BigDecimal awardMoney = new BigDecimal("0.1");
                    userMoney = userMoney.add(awardMoney);
                    todayMoney = todayMoney.add(awardMoney);

                    ReAccountDetail detail1 = new ReAccountDetail();
                    detail1.setUserId(userId);
                    detail1.setAppId(reUser.getAppId());
                    detail1.setDetailType(1);
                    detail1.setAccountMoney(awardMoney);
                    detail1.setMissionType(MissionType.other_mission.val);
                    detail1.setMissionSubtype(MissionSubtype.fix_red.val);
                    detail1.setDetailContent("定时红包奖励");
                    detail1.setDetailTime(nowTime);
                    reAccountDetailDAO.insert(detail1);
                }


                // 更新账户余额
                reUser.setUserId(userId);
                reUser.setUserMoney(userMoney);
                reUser.setTodayMoney(todayMoney);
                reUser.setUpdateTime(now);
                reUserDAO.updateSelective(reUser);

                // 删除缓存
                userCacheService.removeUser(userId);

            }
        });
        return serviceResult;
    }


    /**
     * 奖励前3名
     * @return
     */
    public ServiceResult rewardTop3(final int redId){

        ServiceResult serviceResult = new ServiceResult();

        serviceResult.addCallback(new Runnable() {
            @Override
            public void run() {

                doRewardTop3(redId);

            }
        });
        return serviceResult ;
    }

    /**
     * 奖励
     * @param redId
     */
    public void doRewardTop3(Integer redId){

        String today = CommonMethod.fmtDay(System.currentTimeMillis());

        List<ReReceiveDetail> top3 = reReceiveDetailDAO.getTop3Detail(redId, today);

        //第一名:0.3元, 第二名:0.2元, 第三名:0.1元
        for (int i = 0; i < 3; i++){

            ReReceiveDetail detail1 = top3.get(i);
            Integer userId = detail1.getUserId();

            BigDecimal money = null;
            if (i == 0) {
                money = new BigDecimal("0.3");
            }
            if (i == 1) {
                money = new BigDecimal("0.2");
            }
            if (i == 2) {
                money = new BigDecimal("0.1");
            }

            ReUser user = reUserDAO.selectLockByUserId(userId);

            // 记录明细
            ReAccountDetail accountDetail = new ReAccountDetail();
            accountDetail.setUserId(userId);
            accountDetail.setAppId(user.getAppId());
            accountDetail.setAccountMoney(money);
            accountDetail.setDetailType(1);
            accountDetail.setMissionType(MissionType.other_mission.val);
            accountDetail.setMissionSubtype(MissionSubtype.fix_red.val);
            accountDetail.setDetailContent("定时红包奖励");
            accountDetail.setDetailTime(CommonMethod.fmtTime(System.currentTimeMillis()));
            reAccountDetailDAO.insertSelective(accountDetail);

            // 更新用户表
            ReUser reUser = new ReUser();
            reUser.setUserId(userId);
            reUser.setUserMoney(user.getUserMoney().add(money));
            reUser.setTodayMoney(user.getTodayMoney().add(money));
            reUser.setUpdateTime(System.currentTimeMillis());
            reUserDAO.updateByPrimaryKeySelective(reUser);

            // 删除缓存
            userCacheService.removeUser(userId);

        }

    }


    /**
     * v5.1
     * 抢定时红包
     * @param userId        用户的id
     * @param redType       定时红包类型：0
     * @param redId         定时红包id（ 1：早餐红包 2：午餐红包 3：晚餐红包 4：夜宵红包）
     * @return
     */
    public String receiveFixRed2(int userId,int redType,int redId){

        long now = System.currentTimeMillis();
        String nowTime = CommonMethod.fmtTime(now);

        Map<String, Object> dataResult = new HashMap<>();

        // 判断是否开始
        boolean isStart = isStart(redType, redId);
        if(!isStart) {
            dataResult.put("code", 1);
            dataResult.put("msg", "未到开抢时间!");
            return JsonUtil.buildSuccessDataJson(dataResult);
        }

        ReUser user = reUserDAO.selectLockByUserId(userId);

        // 记录用的是免费的机会还是做任务得到的机会
        int useFreeTime = user.getFreeTimes();
        int useGainTime = user.getGainTimes();
        int receiveTimes = useFreeTime + useGainTime;

        if (receiveTimes == 0){ // 机会用完了
            dataResult.put("code", 1);
            dataResult.put("msg", "抢红包次数已用完,快去做任务获取吧~");
            return JsonUtil.buildSuccessDataJson(dataResult);
        }

        String name = null;
        String distinctName = null;
        String redTitle = null;

        if (redId == 1){
            name = CacheConsts.MORNING_FIX_RED;
            distinctName = CacheConsts.MORNING_RED_GET_LIST;
            redTitle = "早餐红包";
        }else if (redId == 2){
            name = CacheConsts.LUNCH_FIX_RED;
            distinctName = CacheConsts.LUNCH_RED_GET_LIST;
            redTitle = "午餐红包";
        }else if (redId == 3){
            name = CacheConsts.DINNER_FIX_RED;
            distinctName = CacheConsts.DINNER_RED_GET_LIST;
            redTitle = "晚餐红包";
        }else if (redId == 4){
            name = CacheConsts.NIGHT_FIX_RED;
            distinctName = CacheConsts.NIGHT_RED_GET_LIST;
            redTitle = "夜宵红包";
        }

        // 去缓存中抢红包 {"1":0.03},{"2":0.01},{"3":0.04}
        String luaResult = hongBaoCacheService.getRed(name, distinctName, userId);

        if (luaResult == null){
            ReFixedRed red = reFixedRedDAO.selectByPrimaryKey(redId);
            red.setFixedRemainder(0);
            reFixedRedDAO.updateByPrimaryKeySelective(red);
            dataResult.put("code", 3);
            dataResult.put("msg", "手慢了,红包派完了!");
            return JsonUtil.buildSuccessDataJson(dataResult);
        }

        if (luaResult.contains("alreadyGetFixRed")){ // 判断是否已领取
            dataResult.put("code", 2);
            dataResult.put("msg", "您已经领取过红包了!");
            return JsonUtil.buildSuccessDataJson(dataResult);
        }

        BigDecimal totalMoney = new BigDecimal("0");

        //红包金额
        String fixRedMoney = luaResult.substring(luaResult.indexOf(":")+1, luaResult.length()-1);
        //红包序号
        String fixRedOrder = luaResult.substring(luaResult.indexOf("\"")+1, luaResult.lastIndexOf("\""));
        //红包金额
        BigDecimal redMoney = new BigDecimal(fixRedMoney);

        totalMoney = totalMoney.add(redMoney);

        // 记录账户明细
        ReAccountDetail detail = new ReAccountDetail();
        detail.setUserId(userId);
        detail.setAppId(user.getAppId());
        detail.setAccountMoney(redMoney);
        detail.setDetailType(1);
        detail.setMissionType(MissionType.other_mission.val);
        detail.setMissionSubtype(MissionSubtype.fix_red.val);
        detail.setDetailContent(redTitle);
        detail.setDetailTime(nowTime);
        reAccountDetailDAO.insertSelective(detail);

        // 记录红包
        ReReceiveDetail reReceiveDetail = new ReReceiveDetail();
        reReceiveDetail.setUserId(userId);
        reReceiveDetail.setRedOrder(Integer.parseInt(fixRedOrder));
        reReceiveDetail.setRedType(redType);
        reReceiveDetail.setRedId(redId);
        reReceiveDetail.setRedMoney(redMoney);
        reReceiveDetail.setDetailTime(nowTime);
        reReceiveDetailDAO.insertSelective(reReceiveDetail);

        if ("1".equals(fixRedOrder)){
            BigDecimal awardMoney = new BigDecimal("0.3");
            totalMoney = totalMoney.add(awardMoney);

            ReAccountDetail detail1 = new ReAccountDetail();
            detail1.setUserId(user.getUserId());
            detail1.setAppId(user.getAppId());
            detail1.setAccountMoney(awardMoney);
            detail1.setDetailType(1);
            detail1.setMissionType(MissionType.other_mission.val);
            detail1.setMissionSubtype(MissionSubtype.fix_red.val);
            detail1.setDetailContent("定时红包奖励");
            detail1.setDetailTime(CommonMethod.fmtTime(System.currentTimeMillis()));
            reAccountDetailDAO.insertSelective(detail);

        }
        if ("2".equals(fixRedOrder)){
            BigDecimal awardMoney = new BigDecimal("0.2");
            totalMoney = totalMoney.add(awardMoney);

            ReAccountDetail detail1 = new ReAccountDetail();
            detail1.setUserId(user.getUserId());
            detail1.setAccountMoney(awardMoney);
            detail1.setAppId(user.getAppId());
            detail1.setDetailType(1);
            detail1.setMissionType(MissionType.other_mission.val);
            detail1.setMissionSubtype(MissionSubtype.fix_red.val);
            detail1.setDetailContent("定时红包奖励");
            detail1.setDetailTime(CommonMethod.fmtTime(System.currentTimeMillis()));
            reAccountDetailDAO.insertSelective(detail);
        }
        if ("3".equals(fixRedOrder)){
            BigDecimal awardMoney = new BigDecimal("0.1");
            totalMoney = totalMoney.add(awardMoney);

            ReAccountDetail detail1 = new ReAccountDetail();
            detail1.setUserId(user.getUserId());
            detail1.setAppId(user.getAppId());
            detail1.setDetailType(1);
            detail1.setAccountMoney(awardMoney);
            detail1.setMissionType(MissionType.other_mission.val);
            detail1.setMissionSubtype(MissionSubtype.fix_red.val);
            detail1.setDetailContent("定时红包奖励");
            detail1.setDetailTime(CommonMethod.fmtTime(System.currentTimeMillis()));
            reAccountDetailDAO.insertSelective(detail);
        }

        // 更新账户
        if(useFreeTime > 0) {
            useFreeTime = useFreeTime - 1;
        }else {
            useGainTime = useGainTime - 1;
        }
        user.setUserMoney(user.getUserMoney().add(totalMoney));
        user.setTodayMoney(user.getTodayMoney().add(totalMoney));
        user.setUpdateTime(now);
        user.setGainTimes(useGainTime);
        user.setFreeTimes(useFreeTime);
        reUserDAO.updateByPrimaryKeySelective(user);

        dataResult.put("code", 0);
        dataResult.put("msg", "领取成功!");
        dataResult.put("money", redMoney);

        // 删除缓存
        userCacheService.removeUser(userId);

        return JsonUtil.buildSuccessDataJson(dataResult);
    }

}
