package com.hongbao.api.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Summer on 16/8/11.
 */
public class UserLoginResultVo implements Serializable {

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
    // 注册时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
    private Long createTime;
    // 邀请码
    private String invitationCode;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReId() {
        return reId;
    }

    public void setReId(String reId) {
        this.reId = reId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public Long getSignTime() {
        return signTime;
    }

    public void setSignTime(Long signTime) {
        this.signTime = signTime;
    }

    public Integer getSignCount() {
        return signCount;
    }

    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }

    public Integer getBindType() {
        return bindType;
    }

    public void setBindType(Integer bindType) {
        this.bindType = bindType;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
