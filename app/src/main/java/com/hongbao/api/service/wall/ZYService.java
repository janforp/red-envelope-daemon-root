package com.hongbao.api.service.wall;

import com.hongbao.api.dao.ReAccountDetailDAO;
import com.hongbao.api.dao.ReUserDAO;
import com.hongbao.api.dao.ReZyNotifyDAO;
import com.hongbao.api.enums.MissionSubtype;
import com.hongbao.api.enums.MissionType;
import com.hongbao.api.model.ReAccountDetail;
import com.hongbao.api.model.ReUser;
import com.hongbao.api.model.ReZyNotify;
import com.hongbao.api.service.user.UserCacheService;
import com.hongbao.api.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Summer on 2016/10/9.
 */
@Service
public class ZYService {

    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReZyNotifyDAO reZyNotifyDAO;
    @Autowired
    private UserCacheService userCacheService;

    /**
     * 回调逻辑
     * @param order
     * @param app
     * @param ad
     * @param integral
     * @param adid
     * @param pkg
     * @param device
     * @param price
     * @param day
     * @param other
     */
    public void callback(String order, String app, String ad, String integral, String adid, String pkg,
                         String device, String price, String day, String other) {

        // 去重
        ReZyNotify notify = reZyNotifyDAO.selectByPrimaryKey(order);

        if(notify == null) {

            Integer userId = Integer.valueOf(other);

            // 查询账户
            ReUser reUser = reUserDAO.selectLockByUserId(userId);

            if(reUser != null) {

                long nowTime = System.currentTimeMillis();
                String now = CommonMethod.fmtTime(nowTime);

                // 80% 给用户
                float user_money = (float) (Float.valueOf(price) * 0.8);
                DecimalFormat df = new DecimalFormat("0.00");
                String money_s = df.format(user_money);
                BigDecimal money = new BigDecimal(money_s);

                // 记录回调明细
                notify = new ReZyNotify();
                notify.setOrderId(order);
                notify.setUserId(userId);
                notify.setAppId(app);
                notify.setAdId(Integer.valueOf(adid));
                notify.setAdName(ad);
                notify.setDayNum(Integer.valueOf(day));
                notify.setPackageName(pkg);
                notify.setIntegral(Integer.valueOf(integral));
                notify.setDeviceId(device);
                notify.setNotifyTime(now);
                notify.setPrice(money);
                reZyNotifyDAO.insert(notify);

                // 记录用户账户明细
                ReAccountDetail reAccountDetail = new ReAccountDetail();
                reAccountDetail.setUserId(userId);
                reAccountDetail.setAppId(reUser.getAppId());
                reAccountDetail.setAccountMoney(money);
                reAccountDetail.setDetailType(1);
                reAccountDetail.setMissionType(MissionType.alliance_mission.val);
                reAccountDetail.setMissionSubtype(MissionSubtype.other.val);
                reAccountDetail.setDetailContent("试玩中亿任务["+ad+"]");
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
