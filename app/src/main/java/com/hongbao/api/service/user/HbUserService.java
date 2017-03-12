package com.hongbao.api.service.user;

import com.hongbao.api.config.Config;
import com.hongbao.api.dao.*;
import com.hongbao.api.model.ReAccountDetail;
import com.hongbao.api.model.ReScoreDetail;
import com.hongbao.api.model.ReUmengPush;
import com.hongbao.api.model.ReUser;
import com.hongbao.api.model.cache.UserInfo;
import com.hongbao.api.model.dto.*;
import com.hongbao.api.model.vo.AliyunOSSVo;
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
 * Created by Summer on 16/8/22.
 */
@Service
public class HbUserService {

    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReScoreDetailDAO reScoreDetailDAO;
    @Autowired
    private ReUmengPushDAO reUmengPushDAO;
    @Autowired
    private SessionCacheService sessionCacheService;
    @Autowired
    private ReRecommendTaskDAO reRecommendTaskDAO;
    @Autowired
    private UserCacheService userCacheService;

    /**
     * 我的任务列表
     *
     * @param userId
     * @param taskStatus
     * @param taskId
     * @return
     */
    public List<MyMissionInfo> selectMissionList(Integer userId, Integer taskStatus, Long taskId) {

        List<MyMissionInfo> missionList = new ArrayList<>();

        List<ReTaskInfo> taskInfoList = reRecommendTaskDAO.selectUserTask(userId, taskStatus, taskId);

        for (ReTaskInfo task : taskInfoList ) {

            MyMissionInfo info = new MyMissionInfo();
            info.setTaskId(task.getTaskId());
            info.setMissionIcon(task.getMissionIcon());
            info.setMissionTitle(task.getMissionTitle());

            int status = task.getTaskStatus();
            info.setTaskStatus(status);

            if(status == 0) {
                info.setStatusContent("进行中");
            }else if(status == 1) {
                info.setStatusContent("审核中");
            }else if(status == 2) {
                info.setStatusContent("审核通过");
            }else if(status == 3) {
                info.setStatusContent("未通过");
            }else if(status == 4) {
                info.setStatusContent("已过期");
            }

            info.setVerifyRemarks(task.getVerifyRemarks());
            info.setMissionUrl(Config.getRedBaseUrl()+"/c/p/recommend/detail/"+task.getMissionId());

            missionList.add(info);
        }

        return missionList;

    }


    /**
     * 资金明细
     *
     * @param userId
     * @return
     */
    public ReUserMoneyInfo getUserMoney(int userId) {

        ReUserMoneyInfo info = new ReUserMoneyInfo();

        BigDecimal defaultMoney = new BigDecimal("0.00");

        if(userId == 0) {
            info.setTodayMoney(defaultMoney);
            info.setTotalMoney(defaultMoney);
        }else {

            UserInfo userInfo = userCacheService.getUser(userId);
            info.setTodayMoney(userInfo.getTodayMoney());
            info.setTotalMoney(userInfo.getUserMoney());


//            // 今日
//            String dayTime = CommonMethod.fmtDay(System.currentTimeMillis());
//            BigDecimal money = reAccountDetailDAO.selectByUserIdAndDay(userId, dayTime);
//            if(money == null) {
//                money = defaultMoney;
//            }
//            info.setTodayMoney(money);
//
//            // 总共
//            ReUserMoneyAndScore reUserMoneyAndScore = getUserMoneyAndScore(userId);
//            info.setTotalMoney(reUserMoneyAndScore.getUserMoney());
        }

        return info;

    }


    /**
     * 资金明细2
     *
     * @param userId
     * @return
     */
    public ReUserMoneyInfo getUserMoney2(Integer userId) {

        ReUserMoneyInfo info = new ReUserMoneyInfo();

        BigDecimal defaultMoney = new BigDecimal("0.00");

        if(userId == null) {
            info.setTodayMoney(defaultMoney);
            info.setTotalMoney(defaultMoney);
        }else {

            UserInfo userInfo = userCacheService.getUser(userId);
            info.setTodayMoney(userInfo.getTodayMoney());
            info.setTotalMoney(userInfo.getUserMoney());

//            // 今日
//            String dayTime = CommonMethod.fmtDay(System.currentTimeMillis());
//            BigDecimal money = reAccountDetailDAO.selectByUserIdAndDay(userId, dayTime);
//            if(money == null) {
//                money = defaultMoney;
//            }
//            info.setTodayMoney(money);
//
//            // 总共
//            ReUserMoneyAndScore reUserMoneyAndScore = getUserMoneyAndScore(userId);
//            info.setTotalMoney(reUserMoneyAndScore.getUserMoney());
        }

        return info;

    }


    /**
     * 金币明细
     *
     * @param userId
     * @return
     */
    public ReUserScoreInfo getUserScore(int userId) {

        ReUserScoreInfo info = new ReUserScoreInfo();

        // 今日
        String dayTime = CommonMethod.fmtDay(System.currentTimeMillis());
        Integer score = reScoreDetailDAO.selectByUserIdAndDay(userId, dayTime);
        if(score == null) {
            score = 0;
        }
        info.setTodayScore(score);

        // 总共
        ReUserMoneyAndScore reUserMoneyAndScore = getUserMoneyAndScore(userId);
        info.setTotalScore(reUserMoneyAndScore.getUserScore());

        return info;

    }


    /**
     * 获取今日收益
     *
     * @param userId
     * @return
     */
    public ReUserMoneyAndScore getTodayIncome(int userId) {

        ReUserMoneyAndScore income = new ReUserMoneyAndScore();

        if(userId == 0) {
            income.setUserMoney(new BigDecimal("0.00"));
            income.setUserScore(0);
        }else {

            UserInfo userInfo = userCacheService.getUser(userId);
            income.setUserMoney(userInfo.getTodayMoney());
            income.setUserScore(0);

//            String dayTime = CommonMethod.fmtDay(System.currentTimeMillis());
//
//            // 今日现金收入
//            BigDecimal money = reAccountDetailDAO.selectByUserIdAndDay(userId, dayTime);
//            if(money == null) {
//                money = new BigDecimal("0.00");
//            }
//            income.setUserMoney(money);
//
//            // 今日金币收入
//            Integer score = reScoreDetailDAO.selectByUserIdAndDay(userId, dayTime);
//            if(score == null) {
//                score = 0;
//            }
//            income.setUserScore(score);

        }

        return income;
    }



    /**
     * 查询账户明细
     * @param userId
     * @param detailId
     * @return
     */
    public List<ReAccountDetail> getAccountDetailList(Integer userId, Long detailId) {
        return reAccountDetailDAO.selectAccountDetailList(userId, detailId);
    }


    /**
     * 查询金币明细
     * @param userId
     * @param scoreId
     * @return
     */
    public List<ReScoreDetail> getScoreDetailList(Integer userId, Integer scoreId) {
        return reScoreDetailDAO.selectScoreDetailList(userId, scoreId);
    }


    /**
     * 获取用户余额积分
     * @param userId
     * @return
     */
    public ReUserMoneyAndScore getUserMoneyAndScore(int userId) {
        return reUserDAO.selectUserMoneyAndScore(userId);
    }

    /**
     * 阿里云oss配置
     * @param userId
     * @return
     */
    public AliyunOSSVo getAliyun(Integer userId) {

        AliyunOSSVo oss = new AliyunOSSVo();

        oss.setBucketName(Config.getAliyunOss().getBucketName());
        oss.setCdnName(Config.getAliyunOss().getImgCdnName());

        String userImgPath = Config.getAliyunOss().getUserImgPath();
        String imgPath = String.format(userImgPath, String.valueOf(userId));
        oss.setImgPath(imgPath);


        oss.setImgHost(Config.getAliyunOss().getImgHost());
        oss.setNewCdnName(Config.getAliyunOss().getNewCdnName());

        return oss;

    }


    /**
     * 修改头像
     * @param userId
     * @param portrait
     * @return
     */
    public String changePortrait(int userId, String portrait) {

        ReUser reUser = new ReUser();
        reUser.setUserId(userId);
        reUser.setPortrait(portrait);
        reUser.setUpdateTime(System.currentTimeMillis());
        reUserDAO.updateByPrimaryKeySelective(reUser);

        Map<String,Object> dataResult = new HashMap<>(1);
        dataResult.put("portrait", portrait);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 修改昵称
     * @param userId
     * @param nickname
     * @return
     */
    public String changeNickname(int userId, String nickname) {

        ReUser reUser = new ReUser();
        reUser.setUserId(userId);
        reUser.setNickname(nickname);
        reUser.setUpdateTime(System.currentTimeMillis());
        reUserDAO.updateByPrimaryKeySelective(reUser);

        Map<String,Object> dataResult = new HashMap<>(1);
        dataResult.put("nickname", nickname);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 修改姓名
     * @param userId
     * @param realName
     * @return
     */
    public String changeRealName(int userId, String realName) {

        ReUser reUser = new ReUser();
        reUser.setUserId(userId);
        reUser.setRealName(realName);
        reUser.setUpdateTime(System.currentTimeMillis());
        reUserDAO.updateByPrimaryKeySelective(reUser);

        Map<String,Object> dataResult = new HashMap<>(1);
        dataResult.put("realName", realName);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }

    /**
     * 修改性别
     * @param userId
     * @param gender
     * @return
     */
    public String changeGender(int userId, int gender) {

        ReUser reUser = new ReUser();
        reUser.setUserId(userId);
        reUser.setGender(gender);
        reUser.setUpdateTime(System.currentTimeMillis());
        reUserDAO.updateByPrimaryKeySelective(reUser);

        Map<String,Object> dataResult = new HashMap<>(1);
        dataResult.put("gender", gender);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 修改职业
     * @param userId
     * @param profession
     * @return
     */
    public String changeProfession(int userId, String profession) {

        ReUser reUser = new ReUser();
        reUser.setUserId(userId);
        reUser.setProfession(profession);
        reUser.setUpdateTime(System.currentTimeMillis());
        reUserDAO.updateByPrimaryKeySelective(reUser);

        Map<String,Object> dataResult = new HashMap<>(1);
        dataResult.put("profession", profession);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 修改地址
     * @param userId
     * @param address
     * @return
     */
    public String changeAddress(int userId, String address) {

        ReUser reUser = new ReUser();
        reUser.setUserId(userId);
        reUser.setAddress(address);
        reUser.setUpdateTime(System.currentTimeMillis());
        reUserDAO.updateByPrimaryKeySelective(reUser);

        Map<String,Object> dataResult = new HashMap<>(1);
        dataResult.put("address", address);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 存储友盟token
     * @param userId
     * @param platform
     * @param token
     * @return
     */
    public String saveToken(int userId, int platform, String token) {

        ReUmengPush umengPush = reUmengPushDAO.selectByPrimaryKey(userId);

        if(umengPush == null) { // 不存在 新增

            umengPush = new ReUmengPush();
            umengPush.setUserId(userId);
            umengPush.setDeviceToken(token);
            umengPush.setPlatform(platform);
            reUmengPushDAO.insert(umengPush);

        }else { // 已存在 修改

            umengPush.setDeviceToken(token);
            umengPush.setPlatform(platform);
            reUmengPushDAO.updateByPrimaryKey(umengPush);

        }

        return JsonUtil.buildSuccessJson();

    }


    /**
     * 退出登录
     * @param userId
     * @param cacheSessionId
     * @return
     */
    public String logout(int userId, String cacheSessionId) {

        // 清除缓存
        sessionCacheService.removeCachedSession(cacheSessionId);

        // 清除友盟推送token
        reUmengPushDAO.deleteByPrimaryKey(userId);

        return JsonUtil.buildSuccessJson();

    }

}
