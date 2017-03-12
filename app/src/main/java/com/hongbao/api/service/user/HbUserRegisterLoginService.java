package com.hongbao.api.service.user;

import com.hongbao.api.config.Config;
import com.hongbao.api.consts.JsonConsts;
import com.hongbao.api.consts.MsgConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.consts.SessionConsts;
import com.hongbao.api.dao.*;
import com.hongbao.api.enums.HbUserStatus;
import com.hongbao.api.enums.VerifyCodeStatus;
import com.hongbao.api.model.*;
import com.hongbao.api.model.cache.UserInfo;
import com.hongbao.api.model.dto.ServiceResult;
import com.hongbao.api.model.vo.AliyunOSSVo;
import com.hongbao.api.model.vo.UserLoginResultVo;
import com.hongbao.api.service.SmsService;
import com.hongbao.api.util.*;
import com.wujie.common.beanutil.BeanUtils;
import com.wujie.common.security.base64.Base64Encoder;
import org.craigq.common.logger.ILogger;
import org.craigq.common.logger.LogMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Service
public class HbUserRegisterLoginService {

    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private RePackageChannelDAO rePackageChannelDAO;
    @Autowired
    private ReUserInvitationDAO reUserInvitationDAO;
    @Autowired
    private ReUserCommissionDetailDAO reUserCommissionDetailDAO;
    @Autowired
    private ReVerifyCodeDAO reVerifyCodeDAO;
    @Autowired
    private UserCacheService userCacheService;
    @Autowired
    private ReUserChannelDAO reUserChannelDAO;
    @Autowired
    private ReUserPortraitDAO reUserPortraitDAO;
    @Autowired
    private ReUserCommissionDAO reUserCommissionDAO;
    @Autowired
    private ReUserLoginDetailDAO reUserLoginDetailDAO;
    @Autowired
    private ReWhiteListDAO reWhiteListDAO;
    @Autowired
    private ReParameterConfigureDAO reParameterConfigureDAO;
    @Autowired
    private SmsService smsService;
    @Autowired
    private SessionCacheService sessionCacheService;
    @Autowired
    private HbUserService hbUserService;



    public Integer selectStatusByUserId(Integer userId) {
        return reUserDAO.selectStatusByUserId(userId);
    }

    /**
     * 注册登录
     * @param request
     * @param response
     * @param cellphone
     * @param code
     * @param invitation
     * @param address
     * @param longitude
     * @param latitude
     * @return
     */
    public ServiceResult registerLogin(HttpServletRequest request, HttpServletResponse response, String cellphone,
                                       String code, String invitation, String address, String longitude,
                                       String latitude, String imsi, String imei) {

        // 设备号
        String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);
        // 设备名
        String deviceName = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_NAME);
        // 版本
        String appVersion = (String) request.getAttribute(RequestConsts.ATTR_VERSION);
        // 渠道
        String channelName = (String) request.getAttribute(RequestConsts.HEADER_CHANNEL_NAME);
        // 包名
        String packageName = (String) request.getAttribute(RequestConsts.HEADER_PACKAGE_NAME);
        // 平台
        int platform = (Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM);
        // ip
        String ip = RequestUtil.getClientIp(request);

        ReWhiteList reWhiteList = reWhiteListDAO.selectByPrimaryKey(cellphone);

        if(reWhiteList == null) {

            if(StringUtil.isEmpty(deviceId)) {
                return ServiceResult.build(JsonUtil.buildErrorJson("请换一台设备登录"));
            }

            // 查询设备对应的用户id
            Integer deviceUserId = reUserChannelDAO.selectUserIdByDeviceId(deviceId);
            if(deviceUserId != null) {
                ReUser deviceUser = reUserDAO.selectByPrimaryKey(deviceUserId);
                String mobile = deviceUser.getMobile();
                if(!mobile.equals(cellphone)) {
                    return ServiceResult.build(JsonUtil.buildErrorJson("该设备已被绑定,请用手机号"+mobile+"登录!"));
                }
            }

            String errResult = verifySmsCode(cellphone, code);
            if (errResult != null) {
                // 验证码 验证失败
                return ServiceResult.build(errResult);
            }
        }

        ReUser reUser = reUserDAO.selectUserByMobile(cellphone);

        long now = System.currentTimeMillis();
        String nowString = CommonMethod.fmtTime(now);

        Integer userId = null;

        if(reUser == null) { // 未注册

            // 每日免费领取红包机会
            ReParameterConfigure reParameterConfigure = reParameterConfigureDAO.selectByPrimaryKey("free_times");
            int freeTimes = Integer.valueOf(reParameterConfigure.getConfigureOne());

            // 查询渠道版本
            RePackageChannel rePackageChannel = rePackageChannelDAO.selectByPrimaryKey(channelName, packageName);
            int appId = rePackageChannel.getAppId();

            // 进行注册登录
            ReUser registerUser = new ReUser();

            // 随便获取一个头像
            String img = reUserPortraitDAO.selectRandom();

            // 生成邀请码
            String invitationCode = RandomUtil.getRandomStringLow(5);
            boolean flag = true;
            while (flag){
                int num = reUserDAO.selectInvitationCode(invitationCode);
                if(num == 0) {
                    flag = false;
                }else {
                    invitationCode = RandomUtil.getRandomStringLow(5);
                }
            }

            BigDecimal money = new BigDecimal("0.00");

            String nickName = cellphone.substring(0,3) + "****" + cellphone.substring(7,11);

            registerUser.setReId("hb"+cellphone);
            registerUser.setNickname(nickName);
            registerUser.setPortrait(img);
            registerUser.setUserKey(UUID.randomUUID().toString().toLowerCase());
            registerUser.setUserSecret(CommonMethod.generateUserSecret());
            registerUser.setMobile(cellphone);
            registerUser.setPassword(MD5Encryption.encodeMD5(cellphone));
            registerUser.setGender(0);
            registerUser.setMissionTimes(1);
            registerUser.setUserMoney(money);
            registerUser.setUserScore(0);
            registerUser.setSignCount(0);
            registerUser.setBindType(0);
            registerUser.setUserStatus(1);
            registerUser.setUserType(0);
            registerUser.setAppId(appId);
            registerUser.setFreeTimes(freeTimes);
            registerUser.setGainTimes(0);
            registerUser.setMissionTimes(999);
            registerUser.setCreateTime(now);
            registerUser.setUpdateTime(now);
            registerUser.setInvitationCode(invitationCode);

            reUserDAO.insertSelective(registerUser);

            userId = registerUser.getUserId();

            // 渠道表
            ReUserChannel userChannel = new ReUserChannel();
            userChannel.setUserId(userId);
            userChannel.setChannelName(channelName);
            userChannel.setPackageName(packageName);
            userChannel.setAppId(appId);
            userChannel.setPlatform(platform);
            userChannel.setDeviceId(deviceId);
            userChannel.setDeviceName(deviceName);
            userChannel.setAppVersion(appVersion);
            userChannel.setUserIp(ip);
            userChannel.setUserImei(imei);
            userChannel.setUserImsi(imsi);
            userChannel.setCreateTime(CommonMethod.fmtTime(now));
            reUserChannelDAO.insert(userChannel);

            // 佣金表
            ReUserCommission reUserCommission = new ReUserCommission();
            reUserCommission.setUserId(userId);
            reUserCommission.setCurrentMoney(money);
            reUserCommission.setTotalMoney(money);
            reUserCommissionDAO.insert(reUserCommission);

            // 邀请佣金奖励
            {
                if(!StringUtil.isEmpty(invitation) && invitation.trim().length() == 5) { // 邀请码不为空 长度为5

                    // 查询邀请人ID
                    Integer user_Id = reUserDAO.selectUserIdByInvitationCode(invitation);

                    if(userId != null) { // 邀请码存在

                        BigDecimal commission = new BigDecimal("0.10");

                        { // 邀请人

                            // 绑定邀请关系
                            ReUserInvitation reUserInvitation = new ReUserInvitation();
                            reUserInvitation.setUserId(user_Id);
                            reUserInvitation.setInvitedUserId(userId);
                            reUserInvitation.setInvitedTime(nowString);
                            reUserInvitationDAO.insert(reUserInvitation);

                            // 更新佣金
                            ReUserCommission userCommission = reUserCommissionDAO.selectByPrimaryKey(user_Id);
                            userCommission.setCurrentMoney(userCommission.getCurrentMoney().add(commission));
                            userCommission.setTotalMoney(userCommission.getTotalMoney().add(commission));
                            reUserCommissionDAO.updateByPrimaryKey(userCommission);


                            // 插入明细
                            ReUserCommissionDetail reUserCommissionDetail = new ReUserCommissionDetail();
                            reUserCommissionDetail.setUserId(user_Id);
                            reUserCommissionDetail.setAccountMoney(commission);
                            reUserCommissionDetail.setDetailType(1);
                            reUserCommissionDetail.setDetailContent("邀请好友注册成功奖励");
                            reUserCommissionDetail.setDetailTime(nowString);
                            reUserCommissionDetailDAO.insert(reUserCommissionDetail);
                        }

                        { // 被邀请者

                            // 更新佣金
                            ReUserCommission invitedCommission = reUserCommissionDAO.selectByPrimaryKey(userId);
                            invitedCommission.setCurrentMoney(invitedCommission.getCurrentMoney().add(commission));
                            invitedCommission.setTotalMoney(invitedCommission.getTotalMoney().add(commission));
                            reUserCommissionDAO.updateByPrimaryKey(invitedCommission);


                            // 插入明细
                            ReUserCommissionDetail invitedCommissionDetail = new ReUserCommissionDetail();
                            invitedCommissionDetail.setUserId(userId);
                            invitedCommissionDetail.setAccountMoney(commission);
                            invitedCommissionDetail.setDetailType(1);
                            invitedCommissionDetail.setDetailContent("邀请注册成功奖励");
                            invitedCommissionDetail.setDetailTime(nowString);
                            reUserCommissionDetailDAO.insert(invitedCommissionDetail);
                        }

                    }

                }
            }

        }else { // 已注册

            if(!HbUserStatus.normal.val.equals(reUser.getUserStatus())) {
                // 用户状态不是正常状态
                return ServiceResult.build(JsonConsts.ERROR_user_permanent_blocked);
            }

            userId = reUser.getUserId();

            // 更新用户绑定的deviceId
            ReUserChannel channel = reUserChannelDAO.selectByUserId(userId);
            channel.setDeviceId(deviceId);
            channel.setDeviceName(deviceName);
            reUserChannelDAO.updateByPrimaryKeySelective(channel);

        }


        // 记录登录信息
        ReUserLoginDetail detail =  new ReUserLoginDetail();
        detail.setUserId(userId);
        detail.setPackageName(packageName);
        detail.setChannelName(channelName);
        detail.setVersion(appVersion);
        detail.setPlatfrom(platform);
        detail.setDeviceId(deviceId);
        detail.setDeviceName(deviceName);
        detail.setLoginAddress(address);
        detail.setLoginLatitude(latitude);
        detail.setLoginLongitude(longitude);
        detail.setLoginIp(ip);
        detail.setLoginTime(nowString);
        reUserLoginDetailDAO.insert(detail);


        ReUser user = reUserDAO.selectByUserIdAndStatus(userId);

        final UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user, userInfo);

        String userSecret = user.getUserSecret();

        ServiceResult serviceResult = new ServiceResult();
        serviceResult.addCallback(new Runnable() {
            @Override
            public void run() {
                userCacheService.updateUser(userInfo);
            }
        });
        serviceResult.setJson(buildRegisterLoginResult(request, response, user, userSecret));
        return serviceResult;

    }


    /**
     * 登录结果
     * @param request
     * @param response
     * @param reUser
     * @param userSecret
     * @return
     */
    private String buildRegisterLoginResult(HttpServletRequest request, HttpServletResponse response, ReUser reUser, String userSecret) {

        if (reUser == null) {
            return JsonConsts.ERROR_user_not_exist;
        }

        // 初始化一个session：产生新的sessionId并设置到request属性范围、setCookie到response；产生新的会话Map并设置到属性范围
        String cacheSessionId = sessionCacheService.initialize(request, response, reUser.getUserKey());
        Map<String, Serializable> sessionMap = new HashMap<>(1);
        sessionMap.put(SessionConsts.USER_KEY, reUser.getUserKey());
        sessionCacheService.updateCachedSession(cacheSessionId, sessionMap);


        Map<String, Object> dataMap = new HashMap<>(3);
        UserLoginResultVo loginVo = new UserLoginResultVo();
        reUser.setUserSecret(null);
        BeanUtils.copyProperties(reUser, loginVo);

        AliyunOSSVo aliyun = hbUserService.getAliyun(reUser.getUserId());

        dataMap.put("aliyun", aliyun);
        dataMap.put("loginVo", loginVo);
        dataMap.put("userSecret", Base64Encoder.encode(userSecret.getBytes(), false));
        return JsonUtil.buildSuccessDataJson(dataMap);

    }


    /**
     * 发送登录验证码
     * @param cellphone
     * @param appName
     * @return
     */
    public String sendLoginCode(String cellphone, String appName){

        //查询最近发送给此手机,但还未验证的验证码
        long startTime = System.currentTimeMillis() - Config.getSecurity().getSmsVerifyCodeExpiredTime();
        String latestSendCode = reVerifyCodeDAO.selectLatestCodeByCellphoneAndNotVerifyWithinTime(cellphone,startTime);

        String code = null;
        if(latestSendCode != null){
            code = latestSendCode;
        }else {
            code = RandomUtil.getRandomNumberString(4);
        }

        ReVerifyCode reVerifyCode = new ReVerifyCode();
        //生产验证码对象,用于保存至数据库
        reVerifyCode.setCode(code);
        reVerifyCode.setCodeVerifyStatus(VerifyCodeStatus.not_verify.val);
        reVerifyCode.setGenerateTime(System.currentTimeMillis());
        reVerifyCode.setCellphone(cellphone);

        //判断验证码是否发送成功
        boolean smsSendSuccess = false;
        //是否是测试环境,还是线上环境
        boolean isRealSendSms = Config.getSecurity().isRealSms();
        if(isRealSendSms){
            //要真是发送信息,发送短信内容设置
            int result = smsService.sendLoginCode(cellphone, code, appName);
            if(result == 0) {
                smsSendSuccess = true;
            }
        }else {
            //测试环境,不用发送,直接设置true
            smsSendSuccess = true;
        }
        //短信发现成功之后,再添加此验证码信息至数据库,否则的话不用添加
        if (smsSendSuccess) {
            // 短信发送成功，也可能是测试环境，无须发送
            // 保存到数据库
            reVerifyCodeDAO.insertSelective(reVerifyCode);
            if (isRealSendSms) {
                // 要真实发送短信
                return JsonConsts.SUCCESS_verify_sms_send_success;
            } else {
                String msgWithCode = CommonMethod.formatArg(MsgConsts.SUCCESS_TEST_VERIFY_SMS_SEND, code);
                ILogger logger = LogMgr.getLogger();
                if (logger.isInfoEnabled()) {
                    logger.info(msgWithCode);
                }
                // 测试环境，直接告诉客户端，验证码内容
                return JsonUtil.buildSuccessMsgJson(msgWithCode);
            }
        } else {
            return JsonConsts.ERROR_verify_sms_send_fail;
        }

    }


    /**
     * 服务端验证验证码的有效性
     *
     * @param cellphone 用户手机号
     * @param verifyCode 用户输入的验证码
     */
    public String verifySmsCode(String cellphone, String verifyCode) {

        long startTime = System.currentTimeMillis() - Config.getSecurity().getSmsVerifyCodeExpiredTime();

        List<ReVerifyCode> verifyCodeList = reVerifyCodeDAO.selectByCellphoneAndCodeNotVerifyWithinTime(cellphone, startTime);
        if (verifyCodeList == null || verifyCodeList.size() == 0) {
            // 验证码过期，或者就没发送过
            return JsonConsts.ERROR_verify_code_expire;
        }

        // 用户填写的验证码匹配到在DB中的id
        Long matchedCodeId = null;
        for (ReVerifyCode vcode : verifyCodeList) {
            if (verifyCode.equals(vcode.getCode())) {
                // 找到匹配的验证码了
                matchedCodeId = vcode.getId();
                break;
            }
        }
        List<Long> verifyFailIds = new ArrayList<>(verifyCodeList.size());
        if (matchedCodeId == null) {
            // 没有匹配到，则把这个手机号已发送的注册验证码处理成验证失败
//            for (ReVerifyCode vcode : verifyCodeList) {
//                verifyFailIds.add(vcode.getId());
//            }
        } else {
            // 匹配到一条
            for (ReVerifyCode vcode : verifyCodeList) {
                if (matchedCodeId.longValue() != vcode.getId().longValue()) {
                    // 不等于匹配到的id，都是失败
                    verifyFailIds.add(vcode.getId());
                }
            }
        }
        if (verifyFailIds.size() > 0) {
            if (verifyFailIds.size() > 1) {
                // 失败的有多条
                reVerifyCodeDAO.updateCodeVerifyStatusByIds(verifyFailIds, VerifyCodeStatus.verify_fail.val);
            } else {
                // 失败的只有一条
                reVerifyCodeDAO.updateCodeVerifyStatusById(verifyFailIds.get(0), VerifyCodeStatus.verify_fail.val);
            }
        }
        if (matchedCodeId != null) {
            // 此条验证通过
            reVerifyCodeDAO.updateCodeVerifyStatusById(matchedCodeId, VerifyCodeStatus.verify_success.val);
            return null;
        } else {
            return JsonConsts.ERROR_verify_code_wrong;
        }

    }

}
