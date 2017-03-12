package com.hongbao.api.service;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.dao.*;
import com.hongbao.api.enums.MissionSubtype;
import com.hongbao.api.enums.MissionType;
import com.hongbao.api.model.*;
import com.hongbao.api.model.vo.PasswordRedIntroduction;
import com.hongbao.api.service.user.UserCacheService;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jan on 16/11/16.
 * 口令红包
 */
@Service
public class PasswordRedService {

    @Autowired
    private ReParameterConfigureDAO reParameterConfigureDAO;
    @Autowired
    private RePasswordRedDAO rePasswordRedDAO;
    @Autowired
    private RePasswordRedDetailDAO rePasswordRedDetailDAO;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private UserCacheService userCacheService;

    /**
     * 获取口令红包介绍
     * @return
     */
    public PasswordRedIntroduction getIntroduction(){

        ReParameterConfigure configure = reParameterConfigureDAO.getPasswordRedIntroduction();
        PasswordRedIntroduction introduction = new PasswordRedIntroduction();
        introduction.setDesc(configure.getConfigureOne());
        introduction.setQq(configure.getConfigureTwo());

        return introduction;
    }

    /**
     * 领取口令红包
     * @param request
     * @param redPassword   红包口令
     * @return
     */
    public String doGetPasswordRed(HttpServletRequest request,String redPassword) {

        Map<String, Object> dataResult = new HashMap<>(2);

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        long nowTime = System.currentTimeMillis();
        String now = CommonMethod.fmtTime(nowTime);

        RePasswordRed red = rePasswordRedDAO.selectLockByPassword(redPassword);

        if (red == null){
            dataResult.put("code", 0);
            dataResult.put("msg", "口令错误");
            return JsonUtil.buildSuccessDataJson(dataResult);
        }

        String endTime = red.getEndTime();
        long endTimeMills = CommonMethod.get13Timestamp(endTime);
        if (nowTime > endTimeMills){
            dataResult.put("code", 0);
            dataResult.put("msg", "该口令红包已结束");
            return JsonUtil.buildSuccessDataJson(dataResult);
        }

        RePasswordRedDetail detail = rePasswordRedDetailDAO.selectByPrimaryKey(red.getId(), userId);
        if (detail != null){
            dataResult.put("code", 0);
            dataResult.put("msg", "您已经领过啦!");
            return JsonUtil.buildSuccessDataJson(dataResult);
        }

        int leftNum = red.getLeftNum();
        if (leftNum <= 0){
            dataResult.put("code", 0);
            dataResult.put("msg", "手慢了,红包已发完!");
            return JsonUtil.buildSuccessDataJson(dataResult);
        }

        red.setLeftNum(red.getLeftNum() - 1);
        rePasswordRedDAO.updateByPrimaryKeySelective(red);

        detail = new RePasswordRedDetail();
        detail.setPasswordId(red.getId());
        detail.setUserId(userId);
        detail.setReceiveTime(now);
        BigDecimal money = getMoney(red.getMaxMoney(), red.getMinMoney());
        detail.setMoney(money);
        rePasswordRedDetailDAO.insert(detail);

        ReUser user = reUserDAO.selectLockByUserId(userId);
        user.setUserMoney(user.getUserMoney().add(money));
        user.setTodayMoney(user.getTodayMoney().add(money));
        reUserDAO.updateByPrimaryKeySelective(user);

        ReAccountDetail accountDetail = new ReAccountDetail();
        accountDetail.setUserId(userId);
        accountDetail.setAppId(user.getAppId());
        accountDetail.setAccountMoney(money);
        accountDetail.setDetailType(1);
        accountDetail.setMissionType(MissionType.other_mission.val);
        accountDetail.setMissionSubtype(MissionSubtype.word_red.val);
        accountDetail.setDetailContent("口令红包");
        accountDetail.setDetailTime(now);
        reAccountDetailDAO.insert(accountDetail);

        dataResult.put("code", 1);
        dataResult.put("msg", "恭喜获得" + money + "元现金");

        // 删除缓存
        userCacheService.removeUser(userId);

        return JsonUtil.buildSuccessDataJson(dataResult);
    }

    /**
     * 随机一个红包金额
     * @param max
     * @param min
     * @return
     */
    public BigDecimal getMoney(BigDecimal max,BigDecimal min){

        BigDecimal multiply = new BigDecimal("100.00");

        int minMoney = min.multiply(multiply).intValue();
        int maxMoney = max.multiply(multiply).intValue();

        int randomMoney = minMoney;
        if (maxMoney > minMoney){
            randomMoney = RandomUtil.getRandomBetweenMaxAndMin(maxMoney, minMoney);
        }
        String money = String.format("%.2f",(double)(randomMoney / 100.0));

        return new BigDecimal(money);
    }
}
