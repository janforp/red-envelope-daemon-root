package com.hongbao.api.dao;

import com.hongbao.api.model.ReXuanshang;
import com.hongbao.api.model.ReXuanshangBanner;
import com.hongbao.api.model.vo.XuanshangVo;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-26
 */
public interface ReXuanshangDAO {
    int deleteByPrimaryKey(Long id);

    void insert(ReXuanshang record);

    void insertSelective(ReXuanshang record);

    void insertBatch(List<ReXuanshang> records);

    ReXuanshang selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReXuanshang record);

    int updateByPrimaryKey(ReXuanshang record);


    /**
     * 获取首页悬赏任务列表
     * @return
     */
    List<XuanshangVo> getXuanshangList(Long xuanshangId);

    /**
     * 获取某个悬赏任务的XuanshangVo
     * @param xuanshangId
     * @return
     */
    XuanshangVo getXuanshangVoById(Long xuanshangId);

    /**
     * 我发布的悬赏任务列表
     * @param xuanshangId 当前页面最后一个我发布的悬赏任务的id
     * @return
     */
    List<XuanshangVo> getMySendMissionList(Integer userId,Long xuanshangId);

    /**
     * 根据
     * @param xuanshangId
     * @return
     */
    ReXuanshang selectByPrimaryKeyLock(Long xuanshangId);

    /**
     * 从mysql中获取所有的悬赏任务id列表
     * @return
     */
    List<Long> getIdList();

    /**
     * 给某个用户发布的悬赏任务id排序
     * @param userId
     * @return
     */
    List<Long> getMySendIdList(Integer userId);

    /**
     * 删除
     * @param xuanshangId
     */
    void removeByXuanshangId(Long xuanshangId);

    /**
     * 找到某个人发布的悬赏任务列表
     * @param userId
     * @return
     */
    List<Long> getHisXuanshangIdList(Integer userId);

}