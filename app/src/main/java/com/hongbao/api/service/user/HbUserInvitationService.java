package com.hongbao.api.service.user;

import com.hongbao.api.dao.ReUserCommissionDAO;
import com.hongbao.api.dao.ReUserCommissionDetailDAO;
import com.hongbao.api.dao.ReUserDAO;
import com.hongbao.api.dao.ReUserInvitationDAO;
import com.hongbao.api.model.ReUserCommission;
import com.hongbao.api.model.ReUserCommissionDetail;
import com.hongbao.api.model.ReUserInvitation;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by Summer on 16/9/20.
 */
@Service
public class HbUserInvitationService {

    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReUserInvitationDAO reUserInvitationDAO;
    @Autowired
    private ReUserCommissionDAO reUserCommissionDAO;
    @Autowired
    private ReUserCommissionDetailDAO reUserCommissionDetailDAO;

    /**
     * 绑定邀请关系以及奖励佣金
     * @param invitedUserId
     * @param invitationCode
     */
    public void invited(Integer invitedUserId, String invitationCode) {

//        if(!StringUtil.isEmpty(invitationCode) && invitationCode.trim().length() == 5) { // 邀请码不为空 长度为5
//
//            // 判断用户是否已经被邀请
//            ReUserInvitation reUserInvitation = reUserInvitationDAO.selectIsInvited(invitedUserId);
//
//            if(reUserInvitation == null) { // 未被邀请
//
//                // 查询邀请人ID
//                Integer userId = reUserDAO.selectUserIdByInvitationCode(invitationCode);
//
//                if(userId != null) { // 邀请码存在
//
//                    String now = CommonMethod.fmtTime(System.currentTimeMillis());
//                    BigDecimal commission = new BigDecimal("1.00");
//
//                    // 绑定邀请关系
//                    reUserInvitation = new ReUserInvitation();
//                    reUserInvitation.setUserId(userId);
//                    reUserInvitation.setInvitedUserId(invitedUserId);
//                    reUserInvitation.setInvitedTime(now);
//                    reUserInvitationDAO.insert(reUserInvitation);
//
//                    // 更新佣金
//                    ReUserCommission reUserCommission = reUserCommissionDAO.selectByPrimaryKey(userId);
//                    reUserCommission.setCurrentMoney(reUserCommission.getCurrentMoney().add(commission));
//                    reUserCommission.setTotalMoney(reUserCommission.getTotalMoney().add(commission));
//                    reUserCommissionDAO.updateByPrimaryKey(reUserCommission);
//
//
//                    // 插入明细
//                    ReUserCommissionDetail reUserCommissionDetail = new ReUserCommissionDetail();
//                    reUserCommissionDetail.setUserId(userId);
//                    reUserCommissionDetail.setAccountMoney(commission);
//                    reUserCommissionDetail.setDetailType(0);
//                    reUserCommissionDetail.setDetailContent("邀请好友注册成功奖励");
//                    reUserCommissionDetail.setDetailTime(now);
//                    reUserCommissionDetailDAO.insert(reUserCommissionDetail);
//
//
//                    // 被邀请者 更新佣金
//                    ReUserCommission invitedCommission = reUserCommissionDAO.selectByPrimaryKey(invitedUserId);
//                    invitedCommission.setCurrentMoney(invitedCommission.getCurrentMoney().add(commission));
//                    invitedCommission.setTotalMoney(invitedCommission.getTotalMoney().add(commission));
//                    reUserCommissionDAO.updateByPrimaryKey(invitedCommission);
//
//
//                    // 被邀请者 插入明细
//                    ReUserCommissionDetail invitedCommissionDetail = new ReUserCommissionDetail();
//                    invitedCommissionDetail.setUserId(invitedUserId);
//                    invitedCommissionDetail.setAccountMoney(commission);
//                    invitedCommissionDetail.setDetailType(0);
//                    invitedCommissionDetail.setDetailContent("邀请注册成功奖励");
//                    invitedCommissionDetail.setDetailTime(now);
//                    reUserCommissionDetailDAO.insert(invitedCommissionDetail);
//
//
//                }
//
//            }
//
//
//        }


    }



}
