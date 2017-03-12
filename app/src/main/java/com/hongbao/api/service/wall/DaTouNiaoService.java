package com.hongbao.api.service.wall;

import com.hongbao.api.dao.ReAccountDetailDAO;
import com.hongbao.api.dao.ReDtnNotifyDAO;
import com.hongbao.api.dao.ReUserDAO;
import com.hongbao.api.enums.MissionSubtype;
import com.hongbao.api.enums.MissionType;
import com.hongbao.api.model.ReAccountDetail;
import com.hongbao.api.model.ReDtnNotify;
import com.hongbao.api.model.ReUser;
import com.hongbao.api.service.user.UserCacheService;
import com.hongbao.api.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Summer on 2016/9/27.
 */
@Service
public class DaTouNiaoService {

    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReDtnNotifyDAO reDtnNotifyDAO;
    @Autowired
    private UserCacheService userCacheService;


    public void callback(String appId, String udid, Integer userId, double currency, String orderId, String adName) {

        // 去重
        ReDtnNotify notify = reDtnNotifyDAO.selectByPrimaryKey(orderId);

        if(notify == null) {

            // 查询账户
            ReUser reUser = reUserDAO.selectLockByUserId(userId);

            if(reUser != null) {

                long nowTime = System.currentTimeMillis();
                String now = CommonMethod.fmtTime(nowTime);

                // 回调金额(元)
                double total_money = currency * 0.01;
                // 80% 给用户
                double user_money = total_money * 0.8;
                DecimalFormat df = new DecimalFormat("0.00");
                String money_s = df.format(user_money);
                BigDecimal money = new BigDecimal(money_s);

                // 记录回调明细
                notify = new ReDtnNotify();
                notify.setOrderId(orderId);
                notify.setAppId(appId);
                notify.setUdid(udid);
                notify.setUserId(userId);
                notify.setCurrency(new BigDecimal(df.format(currency)));
                notify.setAdName(adName);
                notify.setNotifyTime(now);
                reDtnNotifyDAO.insert(notify);


                // 记录用户账户明细
                ReAccountDetail reAccountDetail = new ReAccountDetail();
                reAccountDetail.setUserId(userId);
                reAccountDetail.setAppId(reUser.getAppId());
                reAccountDetail.setAccountMoney(money);
                reAccountDetail.setDetailType(1);
                reAccountDetail.setMissionType(MissionType.alliance_mission.val);
                reAccountDetail.setMissionSubtype(MissionSubtype.other.val);
                reAccountDetail.setDetailContent("试玩大头鸟任务["+adName+"]");
                reAccountDetail.setDetailTime(now);
                reAccountDetailDAO.insert(reAccountDetail);

                // 更新账户
                ReUser user = new ReUser();
                user.setUserId(userId);
                user.setUserMoney(reUser.getUserMoney().add(money));
                user.setTodayMoney(reUser.getTodayMoney().add(money));
                user.setUpdateTime(nowTime);
                reUserDAO.updateByPrimaryKeySelective(user);

                // 删除缓存
                userCacheService.removeUser(userId);

            }


        }

    }


}
