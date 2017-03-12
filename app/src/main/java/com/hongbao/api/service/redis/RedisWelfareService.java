package com.hongbao.api.service.redis;

import com.hongbao.api.config.Config;
import com.hongbao.api.consts.BaseConsts;
import com.hongbao.api.consts.CacheConsts;
import com.hongbao.api.dao.ReWelfareDAO;
import com.hongbao.api.dao.ReWelfareTypeDAO;
import com.hongbao.api.model.ReWelfare;
import com.hongbao.api.model.ReWelfareType;
import com.hongbao.api.model.dto.MissionInfo;
import com.hongbao.api.model.vo.MissionVo;
import com.hongbao.api.model.vo.NavigationVo;
import com.hongbao.api.util.redis.RedisUtil;
import com.hongbao.api.util.redis.StringKeyRedisTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Janita on 2017/1/3.
 * 福利
 */
@Service
public class RedisWelfareService {

    @Autowired
    private ReWelfareDAO reWelfareDAO;
    @Autowired
    private ReWelfareTypeDAO reWelfareTypeDAO;
    @Autowired
    private StringKeyRedisTemplateUtil cacheRedisTemplate_welfare;

    /**
     * 首页福利精选
     * 精选标题为图片
     *
     * @param platform  平台
     * @return
     */
    public MissionVo indexSelection(int platform) {
        List<MissionInfo> data = getSelectedWelfareFromRedis(platform);
        MissionVo vo = new MissionVo();
        vo.setTitle("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/selection.png");
        vo.setData(data);
        return vo;
    }

    /**
     * 首页精选福利
     * 精选福利标题现在为文字
     * @return
     */
    public MissionVo indexSelectionV5_1(int platform,boolean flag){
        MissionVo vo = new MissionVo();
        if(!flag) {
            List<MissionInfo> data = getSelectedWelfareFromRedis(platform);
            vo.setTitle("精选福利");
            vo.setData(data);
        }
        return vo;
    }

    /**
     * 从redis中获取精选福利列表
     * @param platform
     * @return
     */
    public List<MissionInfo> getSelectedWelfareFromRedis(int platform){
        List<MissionInfo> data;
        if (platform == 0){
            data = RedisUtil.getObj(cacheRedisTemplate_welfare,CacheConsts.SELECTED_WELFARE_IOS);
        }else {
            data = RedisUtil.getObj(cacheRedisTemplate_welfare,CacheConsts.SELECTED_WELFARE_ANDROID);
        }
        if (data == null || data.size() == 0){
            data = getSelectedWelfareFromMySql(platform);
            setSelectedWelfareToRedis(platform,data);
        }
        return data;
    }

    /**
     * 把精选福利列表存入redis
     * @param platform
     */
    public void setSelectedWelfareToRedis(int platform,List<MissionInfo> data){
        if (platform == 0){
            RedisUtil.setObj(cacheRedisTemplate_welfare,CacheConsts.SELECTED_WELFARE_IOS,data,CacheConsts.SECONDS_OF_ONE_MONTH);
        }else {
            RedisUtil.setObj(cacheRedisTemplate_welfare,CacheConsts.SELECTED_WELFARE_ANDROID,data,CacheConsts.SECONDS_OF_ONE_MONTH);
        }
    }

    /**
     * 从mysql中获取精选福利列表
     * @param platform
     * @return
     */
    public List<MissionInfo> getSelectedWelfareFromMySql(int platform){
        List<MissionInfo> data = new ArrayList<>();
        List<ReWelfare> list = getMetaSelectedWelfareListFromMySql(platform);
        for (ReWelfare reWelfare : list) {
            MissionInfo info = turnMetaWelfareToMissionInfo(reWelfare);
            data.add(info);
        }
        return data;
    }

    /**
     *
     * @param reWelfare
     * @return
     */
    public MissionInfo turnMetaWelfareToMissionInfo(ReWelfare reWelfare){
        MissionInfo info = new MissionInfo();
        info.setMissionId(reWelfare.getWelfareId());
        info.setMissionIcon(reWelfare.getWelfareIcon());
        info.setMissionTitle(reWelfare.getWelfareTitle());
        info.setMerchantIcon(reWelfare.getMerchantIcon());
        info.setMerchantName(reWelfare.getMerchantName());
        info.setMissionDesc(reWelfare.getWelfareReward());
        info.setParticipantsNum("参与人数: "+reWelfare.getParticipantsNum());
        info.setMissionUrl(Config.getRedBaseUrl()+"/c/p/welfare/detail/"+reWelfare.getWelfareId());

        return info;
    }

    /**
     * 从mysql中获取精选福利列表(ReWelfare)
     * @param platform
     * @return
     */
    public List<ReWelfare> getMetaSelectedWelfareListFromMySql(int platform){
        return reWelfareDAO.selectSelectionListByPlatform(platform);
    }

    /**
     * 福利导航
     * 福利列表页面的不同福利
     * 类型的入口
     *
     * @param platform
     * @param flag          是否审核通过
     * @return
     */
    public List<NavigationVo> navigationList(int platform, boolean flag) {
        List<NavigationVo> list = null;
        if (flag){
            list = getNavigationListFromRedis(platform);
            if (list == null || list.size() == 0){
                list = getNavigationListFromMySql(platform);
                setNavigationListToRedis(platform,list);
            }
        }
        return list;
    }

    /**
     * 从redis中获取福利页面导航列表
     * @param platform
     * @return
     */
    public List<NavigationVo> getNavigationListFromRedis(int platform){
        if (platform == 0){
            return RedisUtil.getObj(cacheRedisTemplate_welfare,CacheConsts.WELFARE_NAVIGATION_IOS);
        }else {
            return RedisUtil.getObj(cacheRedisTemplate_welfare,CacheConsts.WELFARE_NAVIGATION_ANDROID);
        }
    }

    /**
     * 把导航列表存入redis
     * @param platform
     * @param data
     */
    public void setNavigationListToRedis(int platform,List<NavigationVo> data){
        if (platform == 0){
            RedisUtil.setObj(cacheRedisTemplate_welfare,CacheConsts.WELFARE_NAVIGATION_IOS,data,CacheConsts.SECONDS_OF_ONE_MONTH);
        }else {
            RedisUtil.setObj(cacheRedisTemplate_welfare,CacheConsts.WELFARE_NAVIGATION_ANDROID,data,CacheConsts.SECONDS_OF_ONE_MONTH);
        }
    }

    /**
     * 从mysql中获取导航列表
     * @param platform
     * @return
     */
    public List<NavigationVo> getNavigationListFromMySql(int platform){
        List<NavigationVo> list = new ArrayList<>();
        List<ReWelfareType> typeList = getMetaNavigationListFromMySql(platform);
        for (ReWelfareType type : typeList) {
            NavigationVo vo = turnMetaNavigationToNavigationVo(type,platform);
            list.add(vo);
        }
        return list;
    }

    /**
     * 把一个NavigationType转换成一个NavigationVo
     * @param type
     * @param platform
     * @return
     */
    public NavigationVo turnMetaNavigationToNavigationVo(ReWelfareType type,int platform){
        NavigationVo vo = new NavigationVo();
        vo.setNavigationName(type.getTypeName());
        vo.setNavigationImg(type.getTypeImg());
        if(platform == 0) { // ios
            vo.setNavigationUrl("hongbao://FRWelfareViewController?type="+type.getTypeId()+"&title="+type.getTypeName());
        }else { // android
            vo.setNavigationUrl("hongbao://WelfareActivity?type="+type.getTypeId()+"&title="+type.getTypeName());
        }
        return vo;
    }

    /**
     * 从mysql中获取福利类型列表
     * @param platform
     * @return
     */
    public List<ReWelfareType> getMetaNavigationListFromMySql(int platform){
        return reWelfareTypeDAO.selectByPlatform(platform);
    }




    /**
     * 最新福利
     * '最新福利'4个字用图片的形式
     *
     * @param platform
     * @param flag          是否审核通过
     * @return
     */
    public MissionVo selectListByPlatform(int platform, boolean flag) {

        List<MissionInfo> data;
        String key;
        if (!flag){
            //审核时显示精选福利即可
            data = getSelectedWelfareFromRedis(platform);
        }else {
            key = getSortedSetKeyByWelfareType(null,platform);
            data = getWelfareListBySortedSetKeyAndTypeFromRedis(key,1,BaseConsts.PAGE_SIZE);
        }

        MissionVo vo = new MissionVo();
        vo.setData(data);
        vo.setTitle("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/new-welfare.png");
        return vo;
    }

    /**
     * 获取score是start到end的福利对象
     * 其实存入的是福利的id
     * score-id:二者一一对应，均是唯一的
     * @param key       存id列表的键
     * @param start     从score是start开始取
     * @param end       到score未end为止
     * @return
     */
    public List<MissionInfo> getWelfareListBySortedSetKeyAndTypeFromRedis(String key,double start,double end){

        List<Long> idList =getIdListFromRedis(key,start,end);
        if (idList == null || idList.size() == 0){
            resetAllIdListInRedis();
            idList =getIdListFromRedis(key,start,end);
        }
        List<MissionInfo> infoList = new ArrayList<>();
        if (idList != null && idList.size() >= 0){
            infoList = getMissionInfoListByIdList(idList);
        }
        return infoList;
    }

    /**
     * 修改保存福利列表分页的sortedSet
     * WELFARE_ID_LIST_IOS_TYPE_1/WELFARE_ID_LIST_IOS_TYPE_2/WELFARE_ID_LIST_IOS_TYPE_3....WELFARE_ID_LIST_IOS_TYPE_8   分类型的排序
     * WELFARE_ID_LIST_ANDROID_TYPE_/WELFARE_ID_LIST_ANDROID_TYPE_2/WELFARE_ID_LIST_ANDROID_TYPE_3....WELFARE_ID_LIST_ANDROID_TYPE_8
     * WELFARE_ID_LIST_ANDROID_TYPE_    不分类时
     * WELFARE_ID_LIST_IOS_TYPE_
     *
     * sortedSet存入格式是：member=welfareId,score=1,2,3,.....
     */
    public void resetAllIdListInRedis(){
        List<Integer> types = getWelfareTypeList();
        for(Integer type : types){
            reSetIdListByTypePlatformToRedis(0,type);
            reSetIdListByTypePlatformToRedis(1,type);
        }
        reSetTotalIdListByPlatformToRedis(1);
        reSetTotalIdListByPlatformToRedis(0);
    }

    /**
     * 更新缓存中
     * WELFARE_ID_LIST_IOS_TYPE_
     * WELFARE_ID_LIST_ANDROID_TYPE_
     * 两个键的值
     * 这2个是存所有福利的id列表的
     * @param platform
     */
    public void  reSetTotalIdListByPlatformToRedis(int platform){
        String key;
        List<Long> idList;
        if (platform == 0){
            key = CacheConsts.WELFARE_ID_LIST_IOS_TYPE_;
            idList = getIosIdListByType(null);
        }else {
            key = CacheConsts.WELFARE_ID_LIST_ANDROID_TYPE_;
            idList = getAndroidIdListByType(null);
        }
        RedisUtil.deleteKey(cacheRedisTemplate_welfare,key);
        setIdListByKeyToRedis(key,idList);
    }

    /**
     * 获取福利的所有类型的列表
     * @return
     */
    public List<Integer> getWelfareTypeList(){
        return reWelfareTypeDAO.getWelfareTypeList();
    }


    /**
     * 由id列表获取福利列表
     * @param idList    id列表
     * @return
     */
    public List<MissionInfo> getMissionInfoListByIdList(List<Long> idList){
        List<MissionInfo> infoList = new ArrayList<>();
        if (idList != null && idList.size()>0){
            for (Long id : idList){
                MissionInfo missionInfo = getMissionInfoFromRedis(id);
                if (missionInfo != null){
                    infoList.add(missionInfo);
                }
            }
        }
        Collections.reverse(infoList);
        return infoList;
    }

    /**
     * 从redis中取MissionInfo
     * 若redis中没有
     * ，则到mysql中取
     * @param id
     * @return
     */
    public MissionInfo getMissionInfoFromRedis(Long id){
        MissionInfo info = RedisUtil.hGetValueByKey(cacheRedisTemplate_welfare,CacheConsts.WELFARE_OBJECT,id.toString());
        if (info == null){
            info = getMissionInfoFromMySql(id);
            if (info != null){
                setMissionInfoToRedis(id,info);
            }
        }
        return info;
    }

    public void setMissionInfoToRedis(Long id,MissionInfo missionInfo){
        RedisUtil.hSetKeyValuePair(cacheRedisTemplate_welfare,CacheConsts.WELFARE_OBJECT,id.toString(),missionInfo);
    }

    /**
     * 从mysql中取MissionInfo
     * @param id
     * @return
     */
    public MissionInfo getMissionInfoFromMySql(Long id){
        ReWelfare welfare = getMetaWelfareFromMySql(id);
        if (welfare == null){
            removeMissionInfoFromRedis(id);
            return null;
        }
        return turnMetaWelfareToMissionInfo(welfare);
    }

    /**
     * 若数据库中没有此数据，则删除hash表中存入的key-value
     * @param id
     */
    public void removeMissionInfoFromRedis(Long id){
        RedisUtil.hRemoveKeyValuePair(cacheRedisTemplate_welfare,CacheConsts.WELFARE_OBJECT,id.toString());
    }

    /**
     * 从mysql中查询该福利
     * @param id
     * @return
     */
    public ReWelfare getMetaWelfareFromMySql(Long id){
        return reWelfareDAO.selectByPrimaryKey(id);
    }

    /**
     * 从缓存中获取List<String> idList
     * 其实存入的是福利的id
     * @param key       存id列表的键
     * @param start     从score是start开始取
     * @param end       到score未end为止
     * @return
     */
    public List<Long> getIdListFromRedis(String key,double start,double end){
        List<String> idLists = RedisUtil.zGetMembersByScoreRange(cacheRedisTemplate_welfare,key,start,end);
        return turnStringListToLongList(idLists);
    }

    /**
     * 把从sortedSet中取出的string类型的id转换成long类型
     * @param data
     * @return
     */
    public List<Long> turnStringListToLongList(List<String> data){
        List<Long> ids = new ArrayList<>();
        if (data == null || data.size() == 0){
            return ids;
        }
        for (String strId:data){
            ids.add(Long.parseLong(strId));
        }
        return ids;
    }

    /**
     * 福利 (分页)
     *
     * @param platform
     * @param welfareId     翻页之前最后一个福利的id,在这就是：根据sortedSet中唯一的id
     * @param welfareType   福利的类型，若是最新福利，传的是null
     * @param flag          是否审核通过
     * @return
     */
    public List<MissionInfo> selectListByPlatform(int platform, Long welfareId, Integer welfareType, boolean flag) {
        List<MissionInfo> infoList = new ArrayList<>();
        if(flag) {
            String key = getSortedSetKeyByWelfareType(welfareType,platform);
            Double lastScore = getScoreById(key,welfareId);
            if (lastScore == null){
                lastScore = 0d;
            }
            infoList = getWelfareListBySortedSetKeyAndTypeFromRedis(key,lastScore+1,lastScore+BaseConsts.PAGE_SIZE);
            if (infoList == null || infoList.size() == 0){
                reSetIdListByTypePlatformToRedis(platform,welfareType);
                infoList = getWelfareListBySortedSetKeyAndTypeFromRedis(key,lastScore+1,lastScore+BaseConsts.PAGE_SIZE);
            }

        }
        return infoList;
    }

    /**
     * 更新缓存中某个类型的福利列表
     * @param platform      平台
     * @param type          类型
     */
    public void reSetIdListByTypePlatformToRedis(int platform,Integer type){
        String key;
        List<Long> idList;
        if (platform == 0){
            key = CacheConsts.WELFARE_ID_LIST_IOS_TYPE_+type;
            idList = getIosIdListByType(type);
        }else {
            key = CacheConsts.WELFARE_ID_LIST_ANDROID_TYPE_+type;
            idList = getAndroidIdListByType(type);
            RedisUtil.deleteKey(cacheRedisTemplate_welfare,CacheConsts.WELFARE_ID_LIST_ANDROID_TYPE_+type);
        }
        RedisUtil.deleteKey(cacheRedisTemplate_welfare,key);
        setIdListByKeyToRedis(key,idList);
    }

    /**
     * 获取ios的不同类的福利的id列表
     * @param type
     * @return
     */
    public List<Long> getIosIdListByType(Integer type){
        return reWelfareDAO.selectWelfareIdListOrderByUpdateTimeDesc(0,type);
    }

    /**
     * 获取android的不同类的福利的id列表
     * @param type
     * @return
     */
    public List<Long> getAndroidIdListByType(Integer type){
        return reWelfareDAO.selectWelfareIdListOrderByUpdateTimeDesc(1,type);
    }

    /**
     * 把某个id列表放入redis的sortedSet中
     * 福利分页用
     * @param key
     * @param idList
     */
    public void setIdListByKeyToRedis(String key,List<Long> idList){
        int score = 1;
        for (Long id : idList){
            RedisUtil.zAddMemberScorePair(cacheRedisTemplate_welfare,key,score,id.toString());
            score++;
        }
    }

    /**
     * 根据sortedSet中唯一的分数，获取唯一的id
     * 若传来的id是null，则取队列的第一个，即第一页
     * @param key       存id列表的键
     * @param welfareId 最后一个id，分页用，由此id获取到唯一的score
     *                  由score排序分页的
     * @return
     */
    public Double getScoreById(String key ,Long welfareId){
        Double score = 0d;
        if(welfareId != null){
            score = RedisUtil.zGetScoreByMember(cacheRedisTemplate_welfare,key,welfareId.toString());
        }
        return score;
    }

    public String getSortedSetKeyByWelfareType(Integer welfareType,int platform){
        String type;
        String key;
        if (welfareType == null){
            type = "";
        }else {
            type = welfareType+"";
        }
        if (platform == 0){
            key = CacheConsts.WELFARE_ID_LIST_IOS_TYPE_+type;

        }else {
            key = CacheConsts.WELFARE_ID_LIST_ANDROID_TYPE_+type;
        }
        return key;
    }
}
