package com.hongbao.api.service.wall;

import com.hongbao.api.dao.ReAccountDetailDAO;
import com.hongbao.api.dao.ReBdNotifyDAO;
import com.hongbao.api.dao.ReUserDAO;
import com.hongbao.api.enums.MissionSubtype;
import com.hongbao.api.enums.MissionType;
import com.hongbao.api.model.ReAccountDetail;
import com.hongbao.api.model.ReBdNotify;
import com.hongbao.api.model.ReUser;
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
public class BDService {

    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReBdNotifyDAO reBdNotifyDAO;
    @Autowired
    private UserCacheService userCacheService;

    /**
     * 回调逻辑
     * @param userid
     * @param device_id
     * @param app_id
     * @param currency
     * @param ratio
     * @param time_stamp
     * @param ad_name
     * @param ad_packname
     * @param trade_type
     */
    public void callback(String userid, String device_id, String app_id, double currency, double ratio,
                         long time_stamp, String ad_name, String ad_packname, int trade_type) {

        // 去重
        ReBdNotify notify = reBdNotifyDAO.selectRepeat(device_id, ad_packname, trade_type, time_stamp);

        if(notify == null) {

            Integer userId = Integer.valueOf(userid);

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
                notify = new ReBdNotify();
                notify.setUserId(userId);
                notify.setDeviceId(device_id);
                notify.setAppId(app_id);
                notify.setCurrency(new BigDecimal(df.format(currency)));
                notify.setRatio(new BigDecimal(df.format(ratio)));
                notify.setTimeStamp(time_stamp);
                notify.setAdName(ad_name);
                notify.setAdPackname(ad_packname);
                notify.setNotifyTime(now);
                notify.setTradeType(trade_type);
                reBdNotifyDAO.insert(notify);

                // 记录用户账户明细
                ReAccountDetail reAccountDetail = new ReAccountDetail();
                reAccountDetail.setUserId(userId);
                reAccountDetail.setAppId(reUser.getAppId());
                reAccountDetail.setAccountMoney(money);
                reAccountDetail.setDetailType(1);
                reAccountDetail.setMissionType(MissionType.alliance_mission.val);
                reAccountDetail.setMissionSubtype(MissionSubtype.other.val);
                String detail = "";
                if(trade_type == 0) {
                    detail = "安装激活贝多任务["+ad_name+"]";
                }else {
                    detail = "签到贝多任务["+ad_name+"]";
                }
                reAccountDetail.setDetailContent(detail);
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
