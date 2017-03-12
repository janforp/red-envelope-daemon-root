package com.hongbao.api.service;

import com.hongbao.api.util.redis.StringKeyRedisTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janita on 2016/12/22.
 * 定时红包的redis存取
 */
@Service
public class HongBaoCacheService {

    @Autowired
    private StringKeyRedisTemplateUtil cacheRedisTemplate_hongbao;
    @Autowired
    RedisScript<String> getHongbao;


    /**
     * 去redis队列中取红包
     *
     * @param redName       红包队列
     * @param distinctName  去重队列
     * @param userId        用户id
     * @return
     */
    public String getRed(String redName, String distinctName, Integer userId){

        RedisSerializer<String> stringSerializer = new StringRedisSerializer();

        List<String> keys = new ArrayList<>(3);
        keys.add(redName);
        keys.add(distinctName);
        keys.add(userId+"");

        String object = cacheRedisTemplate_hongbao.execute(getHongbao, stringSerializer, stringSerializer, keys);

        if (object != null) {
            return object;
        }else {
            //已经取完了
            if(cacheRedisTemplate_hongbao.llen(redName) == 0){
                return null;
            }
        }
        return null;

    }

}
