package com.hongbao.api.dao.impl;

import com.hongbao.api.dao.ReUserInvitationDAO;
import com.hongbao.api.dao.BaseSqlSessionDaoSupport;
import com.hongbao.api.model.ReUserInvitation;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-20
 */
@Repository
public class ReUserInvitationDAOImpl extends BaseSqlSessionDaoSupport
        implements ReUserInvitationDAO {
    public int deleteByPrimaryKey(Long id) {
        ReUserInvitation _key = new ReUserInvitation();
        _key.setId(id);
        return getSqlSession().delete("re_user_invitation.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserInvitation record) {
        getSqlSession().insert("re_user_invitation.insert", record);
    }

    public void insertSelective(ReUserInvitation record) {
        getSqlSession().insert("re_user_invitation.insertSelective", record);
    }

    public void insertBatch(List<ReUserInvitation> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_invitation.insertBatch", records);
    }

    public ReUserInvitation selectByPrimaryKey(Long id) {
        ReUserInvitation _key = new ReUserInvitation();
        _key.setId(id);
        return getSqlSession().selectOne("re_user_invitation.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserInvitation record) {
        return getSqlSession().update("re_user_invitation.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserInvitation record) {
        return getSqlSession().update("re_user_invitation.updateByPrimaryKey", record);
    }

    /**
     * 是否已经被邀请
     * @param invitedUserId
     * @return
     */
    public ReUserInvitation selectIsInvited(Integer invitedUserId) {
        return getSqlSession().selectOne("re_user_invitation.selectIsInvited", invitedUserId);
    }

    /**
     * 查询邀请人id
     * @param invitedUserId
     * @return
     */
    public Integer selectUserId(Integer invitedUserId) {
        return getSqlSession().selectOne("re_user_invitation.selectUserId", invitedUserId);
    }

}