package com.hongbao.api.service.wall;

import com.hongbao.api.dao.ReAccountDetailDAO;
import com.hongbao.api.dao.ReDianruNotifyDAO;
import com.hongbao.api.dao.ReUserDAO;
import com.hongbao.api.enums.MissionSubtype;
import com.hongbao.api.enums.MissionType;
import com.hongbao.api.model.ReAccountDetail;
import com.hongbao.api.model.ReDianruNotify;
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
public class DianRuService {

    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReDianruNotifyDAO reDianruNotifyDAO;
    @Autowired
    private UserCacheService userCacheService;

    /**
     * andriod 回调
     * @param hashid
     * @param appid
     * @param adid
     * @param adname
     * @param userId
     * @param mac
     * @param deviceid
     * @param source
     * @param point
     */
    public void andriod_callback(String hashid, String appid, String adid, String adname, Integer userId, String mac,
                         String deviceid, String source, Integer point) {

        // 去重
        ReDianruNotify notify = reDianruNotifyDAO.selectByPrimaryKey(hashid);

        if(notify == null) {

            // 查询账户
            ReUser reUser = reUserDAO.selectLockByUserId(userId);

            if(reUser != null) {

                long nowTime = System.currentTimeMillis();
                String now = CommonMethod.fmtTime(nowTime);

                // 回调金额(元)
                double total_money = (double) point * 0.01;
                // 80% 给用户
                double user_money = total_money * 0.8;
                DecimalFormat df = new DecimalFormat("0.00");
                String money_s = df.format(user_money);
                BigDecimal money = new BigDecimal(money_s);

                // 记录回调明细
                notify = new ReDianruNotify();
                notify.setHashId(hashid);
                notify.setAppId(appid);
                notify.setAdId(adid);
                notify.setAdName(adname);
                notify.setUserId(userId);
                notify.setMacAdd(mac);
                notify.setDeviceId(deviceid);
                notify.setSource(source);
                notify.setPoint(point);
                notify.setNotifyTime(now);
                reDianruNotifyDAO.insertSelective(notify);


                // 记录用户账户明细
                ReAccountDetail reAccountDetail = new ReAccountDetail();
                reAccountDetail.setUserId(userId);
                reAccountDetail.setAppId(reUser.getAppId());
                reAccountDetail.setAccountMoney(money);
                reAccountDetail.setDetailType(1);
                reAccountDetail.setMissionType(MissionType.alliance_mission.val);
                reAccountDetail.setMissionSubtype(MissionSubtype.other.val);
                reAccountDetail.setDetailContent("试玩点入任务["+adname+"]");
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

    /**
     * ios 回调
     *
     * @param hashid
     * @param appid
     * @param adid
     * @param adname
     * @param userId
     * @param deviceid
     * @param source
     * @param point
     */
    public void ios_callback(String hashid, String appid, String adid, String adname, Integer userId,
                                 String deviceid, String source, Integer point) {

        // 去重
        ReDianruNotify notify = reDianruNotifyDAO.selectByPrimaryKey(hashid);

        if(notify == null) {

            // 查询账户
            ReUser reUser = reUserDAO.selectLockByUserId(userId);

            if(reUser != null) {

                long nowTime = System.currentTimeMillis();
                String now = CommonMethod.fmtTime(nowTime);

                // 回调金额(元)
                double total_money = (double) point * 0.01;
                // 80% 给用户
                double user_money = total_money * 0.8;
                DecimalFormat df = new DecimalFormat("0.00");
                String money_s = df.format(user_money);
                BigDecimal money = new BigDecimal(money_s);

                // 记录回调明细
                notify = new ReDianruNotify();
                notify.setHashId(hashid);
                notify.setAppId(appid);
                notify.setAdId(adid);
                notify.setAdName(adname);
                notify.setUserId(userId);
                notify.setDeviceId(deviceid);
                notify.setSource(source);
                notify.setPoint(point);
                notify.setNotifyTime(now);
                reDianruNotifyDAO.insertSelective(notify);


                // 记录用户账户明细
                ReAccountDetail reAccountDetail = new ReAccountDetail();
                reAccountDetail.setUserId(userId);
                reAccountDetail.setAppId(reUser.getAppId());
                reAccountDetail.setAccountMoney(money);
                reAccountDetail.setDetailType(1);
                reAccountDetail.setMissionType(MissionType.alliance_mission.val);
                reAccountDetail.setMissionSubtype(MissionSubtype.other.val);
                reAccountDetail.setDetailContent("试玩点入任务["+adname+"]");
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
