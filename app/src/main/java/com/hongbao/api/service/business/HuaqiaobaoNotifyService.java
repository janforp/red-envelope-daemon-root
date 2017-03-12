package com.hongbao.api.service.business;

import com.hongbao.api.dao.CallbackHuaqiaobaoDAO;
import com.hongbao.api.dao.ReAccountDetailDAO;
import com.hongbao.api.dao.ReUserDAO;
import com.hongbao.api.enums.MissionSubtype;
import com.hongbao.api.enums.MissionType;
import com.hongbao.api.model.CallbackHuaqiaobao;
import com.hongbao.api.model.ReAccountDetail;
import com.hongbao.api.model.ReUser;
import com.hongbao.api.service.user.UserCacheService;
import com.hongbao.api.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by Summer on 2016/10/31.
 */
@Service
public class HuaqiaobaoNotifyService {

    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private CallbackHuaqiaobaoDAO callbackHuaqiaobaoDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private UserCacheService userCacheService;


    /**
     * 回调业务
     * @param type
     * @param userKey
     * @param mobile
     * @return
     */
    public int callback(String type, String userKey, String mobile) {

        int callType = Integer.valueOf(type);
        Long now = System.currentTimeMillis();
        String time = CommonMethod.fmtTime(now);

        // 查询用户信息
        ReUser reUser = reUserDAO.selectUserLockByUserKey(userKey);
        if(reUser != null) {

            // 去重
            Integer userId = reUser.getUserId();
            CallbackHuaqiaobao callbackHuaqiaobao = callbackHuaqiaobaoDAO.selectRepeat(callType, userId, mobile);

            if(callbackHuaqiaobao == null) {

                BigDecimal money = new BigDecimal("0.50");

                // 记录
                callbackHuaqiaobao = new CallbackHuaqiaobao();
                callbackHuaqiaobao.setCallType(callType);
                callbackHuaqiaobao.setMobile(mobile);
                callbackHuaqiaobao.setUserId(userId);
                callbackHuaqiaobao.setCreateTime(time);
                callbackHuaqiaobaoDAO.insert(callbackHuaqiaobao);

                // 修改账户
                ReUser user = new ReUser();
                user.setUserId(userId);
                user.setUserMoney(reUser.getUserMoney().add(money));
                user.setTodayMoney(reUser.getTodayMoney().add(money));
                user.setUpdateTime(now);
                reUserDAO.updateByPrimaryKeySelective(user);

                // 添加明细
                ReAccountDetail reAccountDetail = new ReAccountDetail();
                reAccountDetail.setUserId(userId);
                reAccountDetail.setAppId(reUser.getAppId());
                reAccountDetail.setAccountMoney(money);
                reAccountDetail.setDetailType(1);
                reAccountDetail.setMissionType(MissionType.great_mission.val);
                reAccountDetail.setMissionSubtype(MissionSubtype.other.val);
                reAccountDetail.setDetailTime(time);
                reAccountDetail.setDetailContent("完成高额任务[华侨宝注册]");
                reAccountDetailDAO.insert(reAccountDetail);

                // 删除缓存
                userCacheService.removeUser(userId);

                return 0;

            }

        }else {

            // 记录
            CallbackHuaqiaobao callbackHuaqiaobao = new CallbackHuaqiaobao();
            callbackHuaqiaobao.setCallType(callType);
            callbackHuaqiaobao.setMobile(mobile);
            callbackHuaqiaobao.setUserId(0);
            callbackHuaqiaobao.setCreateTime(time);
            callbackHuaqiaobaoDAO.insert(callbackHuaqiaobao);

        }

        return 0;

    }


}
