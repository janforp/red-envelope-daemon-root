package com.hongbao.api.service.wall;

import com.hongbao.api.dao.*;
import com.hongbao.api.enums.MissionSubtype;
import com.hongbao.api.enums.MissionType;
import com.hongbao.api.model.*;
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
public class YMService {

    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReYmAndriodDAO reYmAndriodDAO;
    @Autowired
    private ReYmIosDAO reYmIosDAO;
    @Autowired
    private UserCacheService userCacheService;

    /**
     * andriod 回调逻辑
     *
     * @param order
     * @param app
     * @param ad
     * @param user
     * @param chn
     * @param points
     * @param adid
     * @param pkg
     * @param device
     * @param price
     * @param trade_type
     */
    public void androidCallback(String order, String app, String ad, String user, String chn, String points,
                         String adid, String pkg, String device, String price, String trade_type) {

        // 去重
        ReYmAndriod notify = reYmAndriodDAO.selectByPrimaryKey(order);

        if(notify == null) {

            Integer userId = Integer.valueOf(user);

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
                notify = new ReYmAndriod();
                notify.setOrderId(order);
                notify.setAppId(app);
                notify.setAdName(ad);
                notify.setUserId(userId);
                notify.setChannelName(chn);
                notify.setPoints(new BigDecimal(points));
                notify.setAdId(Integer.valueOf(adid));
                notify.setPackageName(pkg);
                notify.setDeviceId(device);
                notify.setPrice(money);
                notify.setTradeType(trade_type);
                notify.setNotifyTime(now);
                reYmAndriodDAO.insert(notify);

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
                    detail = "完成有米主任务[" + ad + "]";
                }else if("2".equals(trade_type)) {
                    detail = "完成有米附加任务[" + ad + "]";
                }else if("3".equals(trade_type)) {
                    detail = "完成有米分享主任务[" + ad + "]";
                }else if("4".equals(trade_type)) {
                    detail = "完成有米深度分享任务[" + ad + "]";
                }

                reAccountDetail.setDetailContent(detail);
                reAccountDetail.setDetailTime(now);
                reAccountDetailDAO.insert(reAccountDetail);

                // 更新账户
                ReUser user1 = new ReUser();
                user1.setUserId(userId);
                user1.setUserMoney(reUser.getUserMoney().add(money));
                user1.setTodayMoney(reUser.getTodayMoney().add(money));
                user1.setUpdateTime(nowTime);
                reUserDAO.updateByPrimaryKeySelective(user1);

                // 删除缓存
                userCacheService.removeUser(userId);

            }

        }


    }

    /**
     * ios 回调逻辑
     *
     * @param order
     * @param app
     * @param ad
     * @param adid
     * @param user
     * @param device
     * @param chn
     * @param price
     * @param points
     * @param storeid
     */
    public void iosCallback(String order, String app, String ad, String adid, String user, String device,
                                String chn, String price, String points, String storeid) {

        // 去重
        ReYmIos notify = reYmIosDAO.selectByPrimaryKey(order);

        if(notify == null) {

            Integer userId = Integer.valueOf(user);

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
                notify = new ReYmIos();
                notify.setOrderId(order);
                notify.setAppId(app);
                notify.setAdName(ad);
                notify.setAdId(Integer.valueOf(adid));
                notify.setUserId(userId);
                notify.setDeviceId(device);
                notify.setChannelName(chn);
                notify.setPrice(money);
                notify.setPoints(new BigDecimal(points));
                notify.setStoreId(storeid);
                notify.setNotifyTime(now);
                reYmIosDAO.insert(notify);

                // 记录用户账户明细
                ReAccountDetail reAccountDetail = new ReAccountDetail();
                reAccountDetail.setUserId(userId);
                reAccountDetail.setAppId(reUser.getAppId());
                reAccountDetail.setAccountMoney(money);
                reAccountDetail.setDetailType(1);
                reAccountDetail.setMissionType(MissionType.alliance_mission.val);
                reAccountDetail.setMissionSubtype(MissionSubtype.other.val);
                reAccountDetail.setDetailContent("完成有米任务[" + ad + "]");
                reAccountDetail.setDetailTime(now);
                reAccountDetailDAO.insert(reAccountDetail);

                // 更新账户
                ReUser user1 = new ReUser();
                user1.setUserId(userId);
                user1.setUserMoney(reUser.getUserMoney().add(money));
                user1.setTodayMoney(reUser.getTodayMoney().add(money));
                user1.setUpdateTime(nowTime);
                reUserDAO.updateByPrimaryKeySelective(user1);

                // 删除缓存
                userCacheService.removeUser(userId);

            }

        }


    }

}
