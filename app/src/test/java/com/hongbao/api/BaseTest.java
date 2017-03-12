package com.hongbao.api;

import com.hongbao.api.consts.CacheConsts;
import com.hongbao.api.dao.ReWelfareDAO;
import com.hongbao.api.model.ReWelfare;
import com.hongbao.api.util.redis.StringKeyRedisTemplateUtil;
import com.wujie.common.redis.StringKeyRedisTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @author wwg
 *         测试基类
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/spring/spring-profile.xml"})
@ActiveProfiles(profiles = "development")
public class BaseTest {

    @Autowired
    private StringKeyRedisTemplateUtil cacheRedisTemplate_welfare;
    @Autowired
    private ReWelfareDAO reWelfareDAO;

    private RedisSerializer<Object> jdkSerializationSerializer = new JdkSerializationRedisSerializer();
    private RedisSerializer<String> stringSerializer = new StringRedisSerializer();

    private Jedis jedis = new Jedis("127.0.0.1",6379,5000);


    @Test
    public void testString(){

        jedis.set("a","janita");
        System.out.println("\n*****"+jedis.get("a"));
    }

    @Test
    public void testList(){

        List<String> chars = new ArrayList<>();
        chars.add("A");
        chars.add("B");
        chars.add("C");

        jedis.del("list");

        for (String char_1 : chars){
            jedis.lpush("list",char_1);
        }

        List<String> vals = jedis.lrange("list",0,-1);
        System.out.println("\n*****"+vals);
    }

    @Test//无序的
    public void testSet(){

        String key = "set";
        jedis.del(key);

        Set<String> sets = new HashSet<>();
        sets.add("A");
        sets.add("B");
        sets.add("C");
        sets.add("D");

        Iterator<String> iterator = sets.iterator();
        while (iterator.hasNext()){
            System.out.println("\n******"+iterator.next()+"*******\n");
        }
    }


    @Test
    public void testSortedSet(){

        String key = "sortedSet";
        cacheRedisTemplate_welfare.delete(key);

        cacheRedisTemplate_welfare.zadd(key,"1",1);
        cacheRedisTemplate_welfare.zadd(key,"2",2);
        cacheRedisTemplate_welfare.zadd(key,"3",3);
        List<String> m = cacheRedisTemplate_welfare.zRevRangeByScore(key,1,3);
        System.out.println("\n*****"+m);
    }

    @Test
    public void testHash(){

        String key = "hash";
        jedis.del(key);

        ReWelfare welfare = reWelfareDAO.selectByPrimaryKey(11L);
        jedis.hset(stringSerializer.serialize(key),stringSerializer.serialize(11+""),jdkSerializationSerializer.serialize(welfare));

        byte[] val = jedis.hget(stringSerializer.serialize(key),stringSerializer.serialize(11+""));

        Object o = jdkSerializationSerializer.deserialize(val);

        ReWelfare res = (ReWelfare) o;

        System.out.println("\n*****"+res.getWelfareId());
    }


    @Test
    public void testRedistemplate(){

        String key = "hashMap";
        cacheRedisTemplate_welfare.delete(key);

        ReWelfare welfare = reWelfareDAO.selectByPrimaryKey(12L);
        cacheRedisTemplate_welfare.hashSet(key,"12",welfare);

        ReWelfare welfare1 = cacheRedisTemplate_welfare.hashGet(key,"12");
        System.out.println("\n*****"+welfare1.getWelfareId());

    }

    @Test
    public void testRedisTemplateZ(){
        String key = "sortedSet";
        cacheRedisTemplate_welfare.delete(key);

        cacheRedisTemplate_welfare.zadd(key,"12",12);

        Double d = cacheRedisTemplate_welfare.zGetScoreByMember(key,"12");
    }

    @Test
    public void testDb(){
        jedis.select(10);
        jedis.zadd("db2",12L,"db");
    }
}
