package com.hongbao.api.service;

import com.wujie.common.redis.StringKeyRedisTemplate;
import org.craigq.common.logger.LogMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Summer on 16/6/28.
 */
@Service
public class HbCaptchaService {

    @Autowired
    private StringKeyRedisTemplate captchaCacheRedisTemplate;

    /**
     * 设置验证码到缓存
     * @param key
     * @param captcha
     * @param seconds
     */
    public void setCaptcha(String key, String captcha, long seconds) {
        captchaCacheRedisTemplate.setex(key, captcha, seconds);
    }

    /**
     * 获取缓存中的验证码
     * @param key
     * @return
     */
    public String getCaptcha(String key) {
        String result = null;
        try {
            result = captchaCacheRedisTemplate.getObj(key);
        } catch (Exception e) {
            LogMgr.getLogger().error("HbCaptchaService.getCaptcha", e);
        }
        return result;
    }

}
