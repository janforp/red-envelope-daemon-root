package com.hongbao.api.service;

import com.hongbao.api.consts.CacheConsts;
import com.hongbao.api.model.ReFixedRed;
import com.hongbao.api.model.dto.ReFixedRed4Info;
import com.hongbao.api.model.dto.ReFixedRedInfo;
import com.hongbao.api.service.redis.RedisFixedRedService;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Summer on 16/8/18.
 */
@Service
public class HbFixedRedService {

    @Autowired
    private RedisFixedRedService redisFixedRedService;

    /**
     * 定时红包
     * @param platform 平台
     * @return
     */
    public List<ReFixedRedInfo> getFixedRedList(int platform) {

        List<ReFixedRedInfo> list = new ArrayList<>();

//        List<ReFixedRed> redList = reFixedRedDAO.selectFixedRedByPlatform(platform);
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        long now = System.currentTimeMillis();
//
//        for (ReFixedRed red : redList) {
//
//            ReFixedRedInfo info = new ReFixedRedInfo();
//
//            info.setFixedId(red.getFixedId());
//            info.setFixedRemainder(red.getFixedRemainder());
//            info.setFixedTitle(red.getFixedTitle());
//            info.setFixedUrl(red.getFixedUrl()+red.getFixedId());
//            String fixedTime = CommonMethod.fmtDay(now) + " " + red.getFixedHour() + ":" + red.getFixedMinute() + ":" + red.getFixedSecond();
//            info.setFixedTime(fixedTime);
//
//            Date date = null;
//            try {
//                date = simpleDateFormat.parse(fixedTime);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            long countdown = date.getTime() - now;
//            info.setCountdown(countdown);
//
//            list.add(info);
//
//        }

        return list;

    }

    /**
     * 定时红包列表数据 v4.0
     * @param userId
     * @return
     */
    public List<ReFixedRed4Info> getFixRed4List(Integer userId){

        List<ReFixedRed4Info> list = new ArrayList<>();

        List<Integer> fixIdList = new ArrayList<>(4);
        fixIdList.add(1);
        fixIdList.add(2);
        fixIdList.add(3);
        fixIdList.add(4);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long now = System.currentTimeMillis();

        for (Integer fixId : fixIdList) {

            ReFixedRed red = redisFixedRedService.selectRedById(fixId);

            ReFixedRed4Info info = new ReFixedRed4Info();
            info.setFixedId(fixId);
            info.setFixedRemainder(red.getFixedRemainder());
            info.setFixedTitle(red.getFixedTitle());
            info.setFixedUrl(red.getFixedUrl()+red.getFixedId());
            String fixedTime = CommonMethod.fmtDay(now) + " " + red.getFixedHour() + ":" + red.getFixedMinute() + ":" + red.getFixedSecond();
            info.setFixedTime(fixedTime);

            Date date = null;
            try {
                date = simpleDateFormat.parse(fixedTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long countdown = date.getTime() - now;
            info.setCountdown(countdown);

            // 若没有登录, userId不需要传0
            if (userId == null){
                info.setIsGet(0);
            }else {
                if (redisFixedRedService.isReceiveRed(CacheConsts.USER_FIXED + fixId + "_" + userId)){ // 已抢过
                    info.setIsGet(1);
                }else {
                    info.setIsGet(0);
                }
            }
            list.add(info);
        }
        return list;
    }


    /**
     * 点击定时红包 查询用户是否领取
     * @param userId
     * @param redId
     * @return
     */
    public String getUserIsReceived(Integer userId, int redId) {

        String key = CacheConsts.USER_FIXED + redId + "_" + userId;

        // 0-跳转,1-不跳转
        int status = 1;

        int remainder = redisFixedRedService.selectRedById(redId).getFixedRemainder();
        if(remainder == 0) {
            status = 0;
        }else {
            if(redisFixedRedService.isReceiveRed(key)) {
                status = 0;
            }
        }

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("status", status);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 查询定时红包倒计时
     * @param redId
     * @return
     */
    public String getFixedRedCountdown(int redId) {

        ReFixedRed red = redisFixedRedService.selectRedById(redId);
        long now = System.currentTimeMillis();
        String fixedTime = CommonMethod.fmtDay(now) + " " + red.getFixedHour() + ":" + red.getFixedMinute() + ":" + red.getFixedSecond();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(fixedTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long countdown = date.getTime() - now;

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("countdown", countdown);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }

    /**
     * 随机生成定时红包金额
     * @return
     */
    public BigDecimal getRandomMoney() {

        double randomNumber = Math.random();

        if(randomNumber >= 0.0 && randomNumber < 0.01) {

            return new BigDecimal("0.10");

        }else if(randomNumber >= 0.01 && randomNumber < 0.03) {

            return new BigDecimal("0.09");

        }else if(randomNumber >= 0.03 && randomNumber < 0.06) {

            return new BigDecimal("0.08");

        }else if(randomNumber >= 0.06 && randomNumber < 0.10) {

            return new BigDecimal("0.06");

        }else if(randomNumber >= 0.10 && randomNumber < 0.15) {

            return new BigDecimal("0.05");

        }else if(randomNumber >= 0.15 && randomNumber < 0.25) {

            return new BigDecimal("0.04");

        }else if(randomNumber >= 0.25 && randomNumber < 0.40) {

            return new BigDecimal("0.03");

        }else if(randomNumber >= 0.40 && randomNumber < 0.60) {

            return new BigDecimal("0.02");

        }else {

            return new BigDecimal("0.01");

        }
    }
}
