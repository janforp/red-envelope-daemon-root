package com.hongbao.api.dao;

import com.hongbao.api.model.ReXuanshangDetail;
import com.hongbao.api.model.vo.MyXuanshangListVo;
import com.hongbao.api.model.vo.XuanshangVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-12-26
 */
public interface ReXuanshangDetailDAO {
    int deleteByPrimaryKey(Long id);

    void insert(ReXuanshangDetail record);

    void insertSelective(ReXuanshangDetail record);

    void insertBatch(List<ReXuanshangDetail> records);

    ReXuanshangDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReXuanshangDetail record);

    int updateByPrimaryKey(ReXuanshangDetail record);

    /**
     * 该悬赏任务有多少人提交
     * @param xuanshangId
     * @return
     */
    Integer getSubmitNum(Long xuanshangId);

    /**
     * 我参与的悬赏任务列表
     * @param id    当前页面最后一个id
     * @return
     */
    List<XuanshangVo> getMyPartInMissionList(Integer userId,Long id);

    /**
     * 该悬赏待审核待数量
     * @param xuanshangId
     * @return
     */
    int getToVerifyNum(Long xuanshangId);

    /**
     * 该悬赏任务审核未通过的数量
     * @param xuanshangId
     * @return
     */
    int getNotPassNum(Long xuanshangId);

    /**
     * 找出该用户做的该悬赏任务的详情
     * @param xuanshangId
     * @param userId
     * @return
     */
    ReXuanshangDetail getDetailByUserId(Long xuanshangId,Integer userId);

    /**
     * 发布者审核任务时,
     * 当审核通过的数量=总数量的时候
     * 把xuanshangId对应的任务详情(id:当前审核的最后一个记录除外)
     * 状态是未审核的置成3(来晚了)
     * @param xuanshangId       当前任务id
     * @param id                当前任务的审核的记录id
     * @return
     */
    List<ReXuanshangDetail> getAllListNotVerify(Long xuanshangId,Long id);

    /**
     * 我的审核列表
     * 发布任务者可以看到此列表
     * 该任务所有已经审核过的列表
     * @param xuanshangId   悬赏任务id
     * @param  id           当前页面的最后一个id
     * @return
     */
    List<MyXuanshangListVo> getMyVerifiedList(Long xuanshangId,Long id);

    /**
     * 找到该悬赏任务已经审核通过的列表
     * @param xuanshangId
     * @return
     */
    List<ReXuanshangDetail> getPassList(Long xuanshangId);

    /**
     * 该悬赏任务待审核的列表（分页）
     * @param xuanshangId       悬赏任务id
     * @param id                该悬赏任务待审核待页面傻最后一个待审核记录待id
     * @return
     */
    List<ReXuanshangDetail> getToVerifyList(Long xuanshangId,Long id);

    /**
     * 删除该悬赏任务的所有提交记录
     * @param xuanshangId   悬赏任务id
     */
    void deleteAllDetailsByXuanshangId(Long xuanshangId);

    /**
     * 获取某个悬赏任务的提交详情的id列表
     * @param xuanshangId
     * @return
     */
    List<Long> getAllDetailIdList(Long xuanshangId);

    /**
     * 我参与的悬赏任务的详情id列表
     * @param userId
     * @return
     */
    List<Long> getMyPartInIdList(Integer userId);

    /**
     * 删除
     * @param xuanshangId
     */
    void removeAllDetailsByXuanshangId(Long xuanshangId);
}