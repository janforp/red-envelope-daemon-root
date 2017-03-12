package com.hongbao.api.util.redis;

import com.hongbao.api.consts.CacheConsts;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Janita on 2017/1/5.
 */
public class RedisUtil {

    /**
     * 添加一对 member-score
     * zset
     * @param redis
     * @param key
     * @param score
     * @param member
     */
    public static void zAddMemberScorePair(StringKeyRedisTemplateUtil redis,String key,double score,String member){
        redis.zadd(key,member,score);
        expire(redis,key,CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 删除某个键
     * @param redis
     * @param key
     */
    public static void deleteKey(StringKeyRedisTemplateUtil redis,String key){
        redis.delete(key);
    }

    /**
     * 根据给定的score的范围
     * 获取member的集合
     * zset
     * @param redis
     * @param key
     * @param min
     * @param max
     * @return
     */
    public static List<String> zGetMembersByScoreRange(StringKeyRedisTemplateUtil redis, String key, double min, double max){
        return redis.zRevRangeByScore(key,min,max);
    }

    /**
     * 通过member取得对应的score
     * @param member
     * @return
     */
    public static Double zGetScoreByMember(StringKeyRedisTemplateUtil redis,String key,String member){
        return redis.zGetScoreByMember(key,member);
    }

    /**
     * 在zset中把
     * 某个值的分数+1
     * @param redis
     * @param key
     * @param member
     */
    public static Double zIncrBy(StringKeyRedisTemplateUtil redis,String key,String member,double increment){
        expire(redis,key,CacheConsts.SECONDS_OF_ONE_DAY);
        return redis.zIncrBy(key,member,increment);
    }


    /**
     * 通过key取到value
     * hash
     * @param redis
     * @param key
     * @param field
     * @param <T>
     * @return
     */
    public static  <T> T hGetValueByKey(StringKeyRedisTemplateUtil redis,String key,String field){
        return redis.hashGet(key,field);
    }

    /**
     * hash
     * 设置一对简直对到hash表中
     * @param redis
     * @param key
     * @param field
     * @param value
     * @param <T>
     * @return
     */
    public  static <T> Boolean hSetKeyValuePair(StringKeyRedisTemplateUtil redis,String key,String field,T value){
        expire(redis,key,CacheConsts.SECONDS_OF_ONE_DAY);
        return redis.hashSet(key,field,value);
    }

    /**
     * 删除hash表中的某个键值对
     * @param redis
     * @param key
     * @param field
     * @param <T>
     * @return
     */
    public static <T> Long hRemoveKeyValuePair(StringKeyRedisTemplateUtil redis,String key,String field){
        return redis.hashDelete(key,field);
    }

    /**
     * 获取redis中存的对象
     * @param redis
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getObj(StringKeyRedisTemplateUtil redis,String key){
        return redis.getObj(key);
    }

    /**
     * 设置对象到redis中
     * 并设置有效时间
     * @param redis
     * @param key
     * @param value
     * @param <T>
     */
    public static  <T> void  setObj(StringKeyRedisTemplateUtil redis,String key,T value,long expire){
        redis.setex(key,value, expire);
    }


    /**
     * set
     * 判断此value是否已经在此set中
     * @param redis
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public static  <T> Boolean   sIsExistInSet(StringKeyRedisTemplateUtil redis,String key,T value){
        return redis.sIsExistInSet(key,value);
    }

    /**
     * 把一个value设置到set中
     * @param redis
     * @param key
     * @param value
     * @param <T>
     */
    public static <T> Long sAdd(StringKeyRedisTemplateUtil redis,String key,T value){
        expire(redis,key,CacheConsts.SECONDS_OF_ONE_DAY);
        return redis.sAdd(key,value);
    }

    /**
     * 给redis上乐观锁
     * @param redis
     */
    public static void  lock(StringKeyRedisTemplateUtil redis){
        redis.multi();
    }

    /**
     * 执行乐观锁锁定的命令
     * @param redis
     */
    public static void exec(StringKeyRedisTemplateUtil redis){
        redis.exec();
    }

    /**
     * 为某个键设置过期时间
     * 注意：一个hash中存有多个field-value对
     * 无法针对其中的某一个field设置过期时间
     * 只能对次hash设置过期时间
     * expire只对顶级key有效，你可以这样用
     * @param redis     redis某个库
     * @param key       redis中某个key
     * @param expire    过期时间
     */
    public static void expire(StringKeyRedisTemplateUtil redis,String key,Long expire){
        redis.expire(key,expire, TimeUnit.SECONDS);
    }
}
