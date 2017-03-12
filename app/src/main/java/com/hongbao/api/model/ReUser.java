package com.hongbao.api.model;

import java.math.*;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2017-01-16
 */
public class ReUser implements java.io.Serializable {

    // Fields

    // user_id，自增长
    private Integer userId;
    // re_id，对外展示的id，非空，唯一，只能修改一次
    private String reId;
    // 昵称，非空
    private String nickname;
    // 真实姓名
    private String realName;
    // 头像，可空
    private String portrait;
    // user_key，非空，唯一（UUID），不可修改
    private String userKey;
    // user_secret，非空；用于用户与服务端通信时，采用的对称加密秘钥
    private String userSecret;
    // 用户手机
    private String mobile;
    // 密码
    private String password;
    // 性别，非空，默认0；0：未知；1：男；2：女；
    private Integer gender;
    // 生日，可空
    private String birthday;
    // 职业
    private String profession;
    // 爱好
    private String hobby;
    // 地址
    private String address;
    // 账户余额
    private BigDecimal userMoney;
    // 今日已赚
    private BigDecimal todayMoney;
    // 账户积分
    private Integer userScore;
    // 签到时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
    private Long signTime;
    // 连续签到次数
    private Integer signCount;
    // 绑定类型；0:无, 1：微信，2：QQ，3：微博
    private Integer bindType;
    // 用户状态，非空，默认1（有效）;0：封号；1：有效
    private Integer userStatus;
    // 用户类型，非空，默认0（普通用户）;0：普通用户；1：内部用户；
    private Integer userType;
    // 邀请码
    private String invitationCode;
    private Integer appId;
    // 免费抢红包机会
    private Integer freeTimes;
    // 获得的抢红包机会
    private Integer gainTimes;
    // 发布悬赏任务的机会
    private Integer missionTimes;
    // 注册时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
    private Long createTime;
    // 更新时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
    private Long updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReUser() {
    }

    /**
     * full constructor
     */
    public ReUser(String reId, String nickname, String realName, String portrait, String userKey, String userSecret, String mobile, String password, Integer gender, String birthday, String profession, String hobby, String address, BigDecimal userMoney, BigDecimal todayMoney, Integer userScore, Long signTime, Integer signCount, Integer bindType, Integer userStatus, Integer userType, String invitationCode, Integer appId, Integer freeTimes, Integer gainTimes, Integer missionTimes, Long createTime, Long updateTime) {
        this.reId = reId;
        this.nickname = nickname;
        this.realName = realName;
        this.portrait = portrait;
        this.userKey = userKey;
        this.userSecret = userSecret;
        this.mobile = mobile;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.profession = profession;
        this.hobby = hobby;
        this.address = address;
        this.userMoney = userMoney;
        this.todayMoney = todayMoney;
        this.userScore = userScore;
        this.signTime = signTime;
        this.signCount = signCount;
        this.bindType = bindType;
        this.userStatus = userStatus;
        this.userType = userType;
        this.invitationCode = invitationCode;
        this.appId = appId;
        this.freeTimes = freeTimes;
        this.gainTimes = gainTimes;
        this.missionTimes = missionTimes;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Property accessors

    /**
     * user_id，自增长
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * user_id，自增长
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * re_id，对外展示的id，非空，唯一，只能修改一次
     */
    public String getReId() {
        return this.reId;
    }

    /**
     * re_id，对外展示的id，非空，唯一，只能修改一次
     */
    public void setReId(String reId) {
        this.reId = reId;
    }

    /**
     * 昵称，非空
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * 昵称，非空
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 真实姓名
     */
    public String getRealName() {
        return this.realName;
    }

    /**
     * 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 头像，可空
     */
    public String getPortrait() {
        return this.portrait;
    }

    /**
     * 头像，可空
     */
    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    /**
     * user_key，非空，唯一（UUID），不可修改
     */
    public String getUserKey() {
        return this.userKey;
    }

    /**
     * user_key，非空，唯一（UUID），不可修改
     */
    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    /**
     * user_secret，非空；用于用户与服务端通信时，采用的对称加密秘钥
     */
    public String getUserSecret() {
        return this.userSecret;
    }

    /**
     * user_secret，非空；用于用户与服务端通信时，采用的对称加密秘钥
     */
    public void setUserSecret(String userSecret) {
        this.userSecret = userSecret;
    }

    /**
     * 用户手机
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * 用户手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 性别，非空，默认0；0：未知；1：男；2：女；
     */
    public Integer getGender() {
        return this.gender;
    }

    /**
     * 性别，非空，默认0；0：未知；1：男；2：女；
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 生日，可空
     */
    public String getBirthday() {
        return this.birthday;
    }

    /**
     * 生日，可空
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 职业
     */
    public String getProfession() {
        return this.profession;
    }

    /**
     * 职业
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * 爱好
     */
    public String getHobby() {
        return this.hobby;
    }

    /**
     * 爱好
     */
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    /**
     * 地址
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 账户余额
     */
    public BigDecimal getUserMoney() {
        return this.userMoney;
    }

    /**
     * 账户余额
     */
    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    /**
     * 今日已赚
     */
    public BigDecimal getTodayMoney() {
        return this.todayMoney;
    }

    /**
     * 今日已赚
     */
    public void setTodayMoney(BigDecimal todayMoney) {
        this.todayMoney = todayMoney;
    }

    /**
     * 账户积分
     */
    public Integer getUserScore() {
        return this.userScore;
    }

    /**
     * 账户积分
     */
    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    /**
     * 签到时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
     */
    public Long getSignTime() {
        return this.signTime;
    }

    /**
     * 签到时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
     */
    public void setSignTime(Long signTime) {
        this.signTime = signTime;
    }

    /**
     * 连续签到次数
     */
    public Integer getSignCount() {
        return this.signCount;
    }

    /**
     * 连续签到次数
     */
    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }

    /**
     * 绑定类型；0:无, 1：微信，2：QQ，3：微博
     */
    public Integer getBindType() {
        return this.bindType;
    }

    /**
     * 绑定类型；0:无, 1：微信，2：QQ，3：微博
     */
    public void setBindType(Integer bindType) {
        this.bindType = bindType;
    }

    /**
     * 用户状态，非空，默认1（有效）;0：封号；1：有效
     */
    public Integer getUserStatus() {
        return this.userStatus;
    }

    /**
     * 用户状态，非空，默认1（有效）;0：封号；1：有效
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 用户类型，非空，默认0（普通用户）;0：普通用户；1：内部用户；
     */
    public Integer getUserType() {
        return this.userType;
    }

    /**
     * 用户类型，非空，默认0（普通用户）;0：普通用户；1：内部用户；
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 邀请码
     */
    public String getInvitationCode() {
        return this.invitationCode;
    }

    /**
     * 邀请码
     */
    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public Integer getAppId() {
        return this.appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * 免费抢红包机会
     */
    public Integer getFreeTimes() {
        return this.freeTimes;
    }

    /**
     * 免费抢红包机会
     */
    public void setFreeTimes(Integer freeTimes) {
        this.freeTimes = freeTimes;
    }

    /**
     * 获得的抢红包机会
     */
    public Integer getGainTimes() {
        return this.gainTimes;
    }

    /**
     * 获得的抢红包机会
     */
    public void setGainTimes(Integer gainTimes) {
        this.gainTimes = gainTimes;
    }

    /**
     * 发布悬赏任务的机会
     */
    public Integer getMissionTimes() {
        return this.missionTimes;
    }

    /**
     * 发布悬赏任务的机会
     */
    public void setMissionTimes(Integer missionTimes) {
        this.missionTimes = missionTimes;
    }

    /**
     * 注册时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
     */
    public Long getCreateTime() {
        return this.createTime;
    }

    /**
     * 注册时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
     */
    public Long getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

}