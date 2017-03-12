package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReXuanshangDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReXuanshang;

import com.hongbao.api.model.ReXuanshangBanner;
import com.hongbao.api.model.vo.XuanshangVo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-26
 */
@Repository
public class ReXuanshangDAOImpl extends BaseSqlSessionDaoSupport
        implements ReXuanshangDAO {
    public int deleteByPrimaryKey(Long id) {
        ReXuanshang _key = new ReXuanshang();
        _key.setId(id);
        return getSqlSession().delete("re_xuanshang.deleteByPrimaryKey", _key);
    }

    public void insert(ReXuanshang record) {
        getSqlSession().insert("re_xuanshang.insert", record);
    }

    public void insertSelective(ReXuanshang record) {
        getSqlSession().insert("re_xuanshang.insertSelective", record);
    }

    public void insertBatch(List<ReXuanshang> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_xuanshang.insertBatch", records);
    }

    public ReXuanshang selectByPrimaryKey(Long id) {
        ReXuanshang _key = new ReXuanshang();
        _key.setId(id);
        return getSqlSession().selectOne("re_xuanshang.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReXuanshang record) {
        return getSqlSession().update("re_xuanshang.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReXuanshang record) {
        return getSqlSession().update("re_xuanshang.updateByPrimaryKey", record);
    }

    /**
     * 获取首页悬赏任务列表
     * @return
     */
    @Override
    public List<XuanshangVo> getXuanshangList(Long xuanshangId) {

        Map<String,Object> map = new HashMap<>(1);
        map.put("xuanshangId",xuanshangId);

        return getSqlSession().selectList("re_xuanshang.getXuanshangList",map);
    }

    /**
     * 获取某个悬赏任务的XuanshangVo
     * @param xuanshangId
     * @return
     */
    @Override
    public XuanshangVo getXuanshangVoById(Long xuanshangId) {
        Map<String,Object> map = new HashMap<>(1);
        map.put("xuanshangId",xuanshangId);

        return getSqlSession().selectOne("re_xuanshang.getXuanshangVoById",map);
    }

    /**
     * 我发布的悬赏任务列表
     * @param xuanshangId 当前页面最后一个我发布的悬赏任务的id
     * @return
     */
    public List<XuanshangVo> getMySendMissionList(Integer userId,Long xuanshangId){

        Map<String,Object> map = new HashMap<>(2);
        map.put("xuanshangId",xuanshangId);
        map.put("userId",userId);

        return getSqlSession().selectList("re_xuanshang.getMySendMissionList",map);
    }

    @Override
    public ReXuanshang selectByPrimaryKeyLock(Long xuanshangId) {
        Map<String,Object> map = new HashMap<>(1);
        map.put("xuanshagnId",xuanshangId);
        return getSqlSession().selectOne("re_xuanshang.selectByPrimaryKeyLock",map);
    }

    /**
     * 从mysql中获取所有的悬赏任务id列表
     * @return
     */
    @Override
    public List<Long> getIdList() {
        return getSqlSession().selectList("re_xuanshang.getIdList");
    }

    /**
     * 给某个用户发布的悬赏任务id排序
     * @param userId
     * @return
     */
    @Override
    public List<Long> getMySendIdList(Integer userId) {
        Map<String,Object> map = new HashMap<>(1);
        map.put("userId",userId);

        return getSqlSession().selectList("re_xuanshang.getMySendIdList",map);
    }

    /**
     * 删除
     * @param xuanshangId
     */
    @Override
    public void removeByXuanshangId(Long xuanshangId) {
        Map<String,Object> map = new HashMap<>(1);
        map.put("xuanshangId",xuanshangId);

        getSqlSession().update("re_xuanshang.removeByXuanshangId",map);
    }

    /**
     * 找到某个人发布的悬赏任务列表
     * @param userId
     * @return
     */
    @Override
    public List<Long> getHisXuanshangIdList(Integer userId) {
        Map<String,Object> map = new HashMap<>(1);
        map.put("userId",userId);

        return getSqlSession().selectList("re_xuanshang.getHisXuanshangIdList",map);
    }
}