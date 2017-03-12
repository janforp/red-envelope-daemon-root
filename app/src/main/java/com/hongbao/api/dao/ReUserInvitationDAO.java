package com.hongbao.api.dao;

import com.hongbao.api.model.ReUserInvitation;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-20
 */
public interface ReUserInvitationDAO {
    int deleteByPrimaryKey(Long id);

    void insert(ReUserInvitation record);

    void insertSelective(ReUserInvitation record);

    void insertBatch(List<ReUserInvitation> records);

    ReUserInvitation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReUserInvitation record);

    int updateByPrimaryKey(ReUserInvitation record);

    /**
     * 是否已经被邀请
     * @param invitedUserId
     * @return
     */
    ReUserInvitation selectIsInvited(Integer invitedUserId);

    /**
     * 查询邀请人id
     * @param invitedUserId
     * @return
     */
    Integer selectUserId(Integer invitedUserId);

}