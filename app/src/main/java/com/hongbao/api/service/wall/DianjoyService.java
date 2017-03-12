package com.hongbao.api.service.wall;

import com.hongbao.api.dao.ReAccountDetailDAO;
import com.hongbao.api.dao.ReDianjoyNotifyDAO;
import com.hongbao.api.dao.ReUserDAO;
import com.hongbao.api.enums.MissionSubtype;
import com.hongbao.api.enums.MissionType;
import com.hongbao.api.model.ReAccountDetail;
import com.hongbao.api.model.ReDianjoyNotify;
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
public class DianjoyService {

    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReDianjoyNotifyDAO reDianjoyNotifyDAO;
    @Autowired
    private UserCacheService userCacheService;


    /**
     * 回调逻辑
     * @param user_id
     * @param device_id
     * @param app_id
     * @param currency_s
     * @param appRatio
     * @param time_stamp
     * @param ad_name
     * @param pack_name
     * @param task_id
     * @param trade_type
     */
    public void callback(String user_id, String device_id, String app_id, String currency_s, String appRatio,
                         String time_stamp, String ad_name, String pack_name, String task_id, String trade_type) {

        // 去重
        ReDianjoyNotify notify = reDianjoyNotifyDAO.selectRepeat(device_id, pack_name, trade_type, task_id);

        if(notify == null) {

            Integer userId = Integer.valueOf(user_id);
            Integer currency = Integer.valueOf(currency_s);
            Integer app_ratio = Integer.valueOf(appRatio);

            // 查询账户
            ReUser reUser = reUserDAO.selectLockByUserId(userId);

            if(reUser != null) {

                long nowTime = System.currentTimeMillis();
                String now = CommonMethod.fmtTime(nowTime);

                // 记录回调明细
                notify = new ReDianjoyNotify();
                notify.setUserid(userId);
                notify.setDeviceId(device_id);
                notify.setAppId(app_id);
                notify.setCurrency(currency);
                notify.setAppRatio(app_ratio);
                notify.setTimeStamp(time_stamp);
                notify.setAdName(ad_name);
                notify.setPackName(pack_name);
                notify.setTaskId(task_id);
                notify.setTradeType(trade_type);
                notify.setNotifyTime(now);
                reDianjoyNotifyDAO.insert(notify);

                // 回调金额(元)
                float total_money = (float)currency/app_ratio/100;
                // 80% 给用户
                float user_money = (float) (total_money * 0.8);

                DecimalFormat df = new DecimalFormat("0.00");
                String money_s = df.format(user_money);
                BigDecimal money = new BigDecimal(money_s);

                // 记录用户账户明细
                ReAccountDetail reAccountDetail = new ReAccountDetail();
                reAccountDetail.setUserId(userId);
                reAccountDetail.setAppId(reUser.getAppId());
                reAccountDetail.setAccountMoney(money);
                reAccountDetail.setDetailType(1);
                reAccountDetail.setMissionType(MissionType.alliance_mission.val);
                reAccountDetail.setMissionSubtype(MissionSubtype.other.val);
                String detail = "";
                if("1".equals(trade_type)) {
                    detail = "激活并体验点乐任务[" + ad_name + "]";
                }else {
                    detail = "深度体验点乐任务[" + ad_name + "]";
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
