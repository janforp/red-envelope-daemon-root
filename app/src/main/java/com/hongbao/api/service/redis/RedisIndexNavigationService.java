package com.hongbao.api.service.redis;

import com.hongbao.api.consts.CacheConsts;
import com.hongbao.api.dao.ReIndexMissionNavigationDAO;
import com.hongbao.api.model.ReIndexMissionNavigation;
import com.hongbao.api.model.vo.MissionNavigationVo;
import com.hongbao.api.model.vo.NavigationVo;
import com.hongbao.api.util.redis.RedisUtil;
import com.hongbao.api.util.redis.StringKeyRedisTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janita on 2017/1/5.
 */
@Service
public class RedisIndexNavigationService {

    @Autowired
    private StringKeyRedisTemplateUtil cacheRedisTemplate_welfare;
    @Autowired
    private ReIndexMissionNavigationDAO reIndexMissionNavigationDAO;

    /**
     * 首页任务导航
     *
     * @param flag
     * @param platform
     * @return
     */
    public MissionNavigationVo indexMissionNavigationList(boolean flag, int platform) {

        MissionNavigationVo missionNavigationVo = new MissionNavigationVo();
        if(flag) { // 正常状态
            List<NavigationVo> list = getIndexNavigationVoFromRedis(platform);
            int num = list.size();
            if(num % 2 == 1) {
                NavigationVo vo = new NavigationVo();
                vo.setNavigationImg("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/wait.png");
                list.add(vo);
            }
            missionNavigationVo.setTitle("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/mission.png");
            missionNavigationVo.setData(list);

        }
        return missionNavigationVo;
    }

    /**
     * 从redis中取出首页入口导航
     * @return
     */
    public List<NavigationVo> getIndexNavigationVoFromRedis(int platform){
        List<NavigationVo> navigationVoList;
        if (platform == 0){
            navigationVoList = RedisUtil.getObj(cacheRedisTemplate_welfare, CacheConsts.INDEX_MISSION_NAVIGATION_IOS);
        }else {
            navigationVoList = RedisUtil.getObj(cacheRedisTemplate_welfare, CacheConsts.INDEX_MISSION_NAVIGATION_ANDROID);
        }
        if (navigationVoList == null || navigationVoList.size() == 0){
            navigationVoList = getIndexNavigationVoFromMySql(platform);
            if (navigationVoList != null && navigationVoList.size() >0){
                setIndexNavigationVoToRedis(platform,navigationVoList);
            }
        }

        return navigationVoList;
    }

    /**
     * 从mysql中取出首页入口导航
     * @return
     */
    public List<NavigationVo> getIndexNavigationVoFromMySql(int platform){
        List<NavigationVo> list = new ArrayList<>();
        List<ReIndexMissionNavigation> navigationList = reIndexMissionNavigationDAO.selectByPlatform(platform);
        for (ReIndexMissionNavigation navigation : navigationList) {
            NavigationVo vo = new NavigationVo();
            vo.setNavigationImg(navigation.getNavigationImg());
            vo.setNavigationUrl(navigation.getNavigationUrl());
            vo.setNavigationName(navigation.getNavigationName());
            list.add(vo);
        }
        return list;
    }

    /**
     * 把最新首页导航放如redis
     * @param platform
     */
    public void setIndexNavigationVoToRedis(int platform,List<NavigationVo> data){
        if (platform == 0){
            RedisUtil.setObj(cacheRedisTemplate_welfare,CacheConsts.INDEX_MISSION_NAVIGATION_IOS,data,CacheConsts.SECONDS_OF_ONE_MONTH);
        }else {
            RedisUtil.setObj(cacheRedisTemplate_welfare,CacheConsts.INDEX_MISSION_NAVIGATION_ANDROID,data,CacheConsts.SECONDS_OF_ONE_MONTH);
        }
    }
}
