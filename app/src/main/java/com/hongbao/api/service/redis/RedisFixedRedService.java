package com.hongbao.api.service.redis;

import com.hongbao.api.consts.CacheConsts;
import com.hongbao.api.dao.ReFixedRedDAO;
import com.hongbao.api.model.ReFixedRed;
import com.hongbao.api.util.redis.StringKeyRedisTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by Summer on 2017/1/18.
 */
@Service
public class RedisFixedRedService {

    @Autowired
    private StringKeyRedisTemplateUtil cacheRedisTemplate_hongbao;
    @Autowired
    private ReFixedRedDAO reFixedRedDAO;

    /**
     * 查询定时红包
     *
     * @param fixId
     * @return
     */
    public ReFixedRed selectRedById(int fixId) {
        String key = CacheConsts.FIXED_RED + fixId;
        ReFixedRed red = cacheRedisTemplate_hongbao.getObj(key);
        if(red == null) {
            red = reFixedRedDAO.selectByPrimaryKey(fixId);
            if(red != null) {
                updateFixed(key, red);
            }
        }
        return red;
    }

    /**
     * 设置定时红包 缓存1天
     *
     * @param key
     * @param red
     */
    public void updateFixed(String key, ReFixedRed red) {
        cacheRedisTemplate_hongbao.setex(key, red, CacheConsts.SECONDS_OF_ONE_DAY);
    }


    /**
     * 设置用户抢到红包 缓存1天
     *
     * @param key
     * @param userId
     */
    public void userGetFixed(String key, Integer userId) {
        cacheRedisTemplate_hongbao.setex(key, userId, CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 判断用户是否领取过红包
     *
     * @param key
     * @return
     */
    public boolean isReceiveRed(String key) {
        Integer userId = cacheRedisTemplate_hongbao.getObj(key);
        if(userId == null) { // 未抢过
            return false;
        }
        return true;
    }

    /**
     * 设置用户抢到红包金额 缓存1天
     *
     * @param key
     * @param money
     */
    public void userFixedMoney(String key, BigDecimal money) {
        cacheRedisTemplate_hongbao.setex(key, money, CacheConsts.SECONDS_OF_ONE_DAY);
    }

}
