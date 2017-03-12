package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReXuanshangDetailDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReXuanshangDetail;

import com.hongbao.api.model.vo.MyXuanshangListVo;
import com.hongbao.api.model.vo.XuanshangVo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-26
 */
@Repository
public class ReXuanshangDetailDAOImpl extends BaseSqlSessionDaoSupport
        implements ReXuanshangDetailDAO {
    public int deleteByPrimaryKey(Long id) {
        ReXuanshangDetail _key = new ReXuanshangDetail();
        _key.setId(id);
        return getSqlSession().delete("re_xuanshang_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReXuanshangDetail record) {
        getSqlSession().insert("re_xuanshang_detail.insert", record);
    }

    public void insertSelective(ReXuanshangDetail record) {
        getSqlSession().insert("re_xuanshang_detail.insertSelective", record);
    }

    public void insertBatch(List<ReXuanshangDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_xuanshang_detail.insertBatch", records);
    }

    public ReXuanshangDetail selectByPrimaryKey(Long id) {
        ReXuanshangDetail _key = new ReXuanshangDetail();
        _key.setId(id);
        return getSqlSession().selectOne("re_xuanshang_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReXuanshangDetail record) {
        return getSqlSession().update("re_xuanshang_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReXuanshangDetail record) {
        return getSqlSession().update("re_xuanshang_detail.updateByPrimaryKey", record);
    }

    /**
     * 该悬赏任务有多少人提交
     * @param xuanshangId
     * @return
     */
    @Override
    public Integer getSubmitNum(Long xuanshangId) {
        return getSqlSession().selectOne("re_xuanshang_detail.getSubmitNum",xuanshangId);
    }

    /**
     * 我参与的悬赏任务列表
     * @param userId    登录用户id
     * @param id        当前页面最后一个id
     * @return
     */
    @Override
    public List<XuanshangVo> getMyPartInMissionList(Integer userId,Long id) {

        Map<String ,Object> map = new HashMap<>(2);
        map.put("userId",userId);
        map.put("id",id);
        return getSqlSession().selectList("re_xuanshang_detail.getMyPartInMissionList",map);
    }

    /**
     * 该悬赏待审核待数量
     * @param xuanshangId
     * @return
     */
    @Override
    public int getToVerifyNum(Long xuanshangId) {

        Map<String ,Object> map = new HashMap<>(1);
        map.put("xuanshangId",xuanshangId);
        return getSqlSession().selectOne("re_xuanshang_detail.getToVerifyNum",map);
    }

    /**
     * 该悬赏任务审核未通过的数量
     * @param xuanshangId
     * @return
     */
    @Override
    public int getNotPassNum(Long xuanshangId) {

        Map<String ,Object> map = new HashMap<>(1);
        map.put("xuanshangId",xuanshangId);
        return getSqlSession().selectOne("re_xuanshang_detail.getNotPassNum",map);
    }

    /**
     * 找出该用户做的该悬赏任务的详情
     * @param xuanshangId
     * @param userId
     * @return
     */
    @Override
    public ReXuanshangDetail getDetailByUserId(Long xuanshangId, Integer userId) {

        Map<String ,Object> map = new HashMap<>(2);
        map.put("xuanshangId",xuanshangId);
        map.put("userId",userId);

        return getSqlSession().selectOne("re_xuanshang_detail.getDetailByUserId",map);
    }

    /**
     * 发布者审核任务时,
     * 当审核通过的数量=总数量的时候
     * 把xuanshangId对应的任务详情(id:当前审核的最后一个记录除外)
     * 状态是未审核的置成3(来晚了)
     * @param xuanshangId       当前任务id
     * @param id                当前任务的审核的记录id
     * @return
     */
    @Override
    public List<ReXuanshangDetail> getAllListNotVerify(Long xuanshangId, Long id) {
        Map<String ,Object> map = new HashMap<>(2);
        map.put("xuanshangId",xuanshangId);
        map.put("id",id);

        return getSqlSession().selectList("re_xuanshang_detail.getAllListNotVerify",map);
    }

    /**
     * 我的审核列表
     * 发布任务者可以看到此列表
     * 该任务所有已经审核过的列表
     * @param xuanshangId   悬赏任务id
     * @param  id           当前页面的最后一个id
     * @return
     */
    @Override
    public List<MyXuanshangListVo> getMyVerifiedList(Long xuanshangId, Long id) {

        Map<String ,Object> map = new HashMap<>(2);
        map.put("xuanshangId",xuanshangId);
        map.put("id",id);

        return getSqlSession().selectList("re_xuanshang_detail.getMyVerifiedList",map);
    }

    /**
     * 找到该悬赏任务已经审核通过的列表
     * @param xuanshangId
     * @return
     */
    @Override
    public List<ReXuanshangDetail> getPassList(Long xuanshangId) {

        Map<String ,Object> map = new HashMap<>(1);
        map.put("xuanshangId",xuanshangId);
        return getSqlSession().selectList("re_xuanshang_detail.getPassList",map);
    }

    /**
     * 该悬赏任务待审核的列表（分页）
     * @param xuanshangId       悬赏任务id
     * @param id                该悬赏任务待审核待页面傻最后一个待审核记录待id
     * @return
     */
    @Override
    public List<ReXuanshangDetail> getToVerifyList(Long xuanshangId,Long id) {
        Map<String ,Object> map = new HashMap<>(2);
        map.put("xuanshangId",xuanshangId);
        map.put("id",id);
        return getSqlSession().selectList("re_xuanshang_detail.getToVerifyList",map);
    }

    /**
     * 删除该悬赏任务的所有提交记录
     * @param xuanshangId   悬赏任务id
     */
    @Override
    public void deleteAllDetailsByXuanshangId(Long xuanshangId) {

        Map<String ,Object> map = new HashMap<>(1);
        map.put("xuanshangId",xuanshangId);
        getSqlSession().delete("re_xuanshang_detail.deleteAllDetailsByXuanshangId",map);
    }

    /**
     * 获取某个悬赏任务的提交详情的id列表
     * @param xuanshangId
     * @return
     */
    @Override
    public List<Long> getAllDetailIdList(Long xuanshangId) {
        Map<String ,Object> map = new HashMap<>(1);
        map.put("xuanshangId",xuanshangId);
        return getSqlSession().selectList("re_xuanshang_detail.getAllDetailIdList",map);
    }

    /**
     * 我参与的悬赏任务的详情id列表
     * @param userId
     * @return
     */
    public List<Long> getMyPartInIdList(Integer userId){
        Map<String ,Object> map = new HashMap<>(1);
        map.put("userId",userId);
        return getSqlSession().selectList("re_xuanshang_detail.getMyPartInIdList",map);
    }

    @Override
    public void removeAllDetailsByXuanshangId(Long xuanshangId) {
        Map<String ,Object> map = new HashMap<>(1);
        map.put("xuanshangId",xuanshangId);
        getSqlSession().selectList("re_xuanshang_detail.removeAllDetailsByXuanshangId",map);
    }


}