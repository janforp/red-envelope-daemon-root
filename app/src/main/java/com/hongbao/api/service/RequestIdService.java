package com.hongbao.api.service;

import com.wujie.common.redis.StringKeyRedisTemplate;
import org.craigq.common.logger.LogMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Summer on 16/6/28.
 */
@Service
public class RequestIdService {

    @Autowired
    private StringKeyRedisTemplate requestIdCacheRedisTemplate;

    public void setRequestId(String key, long seconds) {
        requestIdCacheRedisTemplate.setex(key, "", seconds);
    }

    public boolean getRequestId(String key) {
        String result = null;
        try {
            result = requestIdCacheRedisTemplate.getObj(key);
        } catch (Exception e) {
            LogMgr.getLogger().error("RequestIdService.getRequestId", e);
        }
        if(result != null) {
            return true;
        }
        return false;
    }

}
