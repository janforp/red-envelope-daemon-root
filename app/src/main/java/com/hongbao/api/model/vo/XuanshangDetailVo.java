package com.hongbao.api.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Janita on 2016/12/28.
 * 悬赏任务详情页
 * 根据是否登录／是否是自己发布的任务传递的数据有所不同
 * 可以共用此模型
 */
public class XuanshangDetailVo implements Serializable {
    //悬赏任务id
    private Long        xuanshangId;
    //发布者头像
    private String      portrait;
    //发布者昵称
    private String      nickname;
    //发布时间
    private String      createTime;
    //任务单价
    private BigDecimal  singleMoney;
    //任务描述
    private String      missionDesc;
    //任务图片
    private List<String>    imgList;
    //该任务完成情况列表（轮播）
    private List<UserPassXuanshangVo>   passList;
    //153****1451完成任务，获得0.1元
    private List<String>  rewardUserList;
    //已通过
    private Integer     passNum;
    //未通过
    private Integer     notPassNum;
    //待审核
    private Integer     toVerifyNum;
    //已通过： 10 未通过： 5 待审核： 2
    private String      numDesc;
    //该是否已完成    1:完成了，0：未完成
    private Integer     isComplete;
    //待审核列表
    private List<UserToVerifyVo> toVerifyList;
    //按钮状态  0:交作业 1：已结束，2：已提交
    private Integer     buttonStatus;
    //按钮标题   0:交作业 1：已结束,2:已提交
    private String      buttonTitle;
    //若该用户参与了该悬赏任务，则显示该记录
    private MyXuanshangListVo mySubmit;


    public Long getXuanshangId() {
        return xuanshangId;
    }

    public void setXuanshangId(Long xuanshangId) {
        this.xuanshangId = xuanshangId;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getSingleMoney() {
        return singleMoney;
    }

    public void setSingleMoney(BigDecimal singleMoney) {
        this.singleMoney = singleMoney;
    }

    public String getMissionDesc() {
        return missionDesc;
    }

    public void setMissionDesc(String missionDesc) {
        this.missionDesc = missionDesc;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public List<UserPassXuanshangVo> getPassList() {
        return passList;
    }

    public void setPassList(List<UserPassXuanshangVo> passList) {
        this.passList = passList;
    }

    public Integer getPassNum() {
        return passNum;
    }

    public void setPassNum(Integer passNum) {
        this.passNum = passNum;
    }

    public Integer getNotPassNum() {
        return notPassNum;
    }

    public void setNotPassNum(Integer notPassNum) {
        this.notPassNum = notPassNum;
    }

    public Integer getToVerifyNum() {
        return toVerifyNum;
    }

    public void setToVerifyNum(Integer toVerifyNum) {
        this.toVerifyNum = toVerifyNum;
    }

    public List<UserToVerifyVo> getToVerifyList() {
        return toVerifyList;
    }

    public void setToVerifyList(List<UserToVerifyVo> toVerifyList) {
        this.toVerifyList = toVerifyList;
    }

    public Integer getButtonStatus() {
        return buttonStatus;
    }

    public void setButtonStatus(Integer buttonStatus) {
        this.buttonStatus = buttonStatus;
    }

    public String getButtonTitle() {
        return buttonTitle;
    }

    public void setButtonTitle(String buttonTitle) {
        this.buttonTitle = buttonTitle;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    public MyXuanshangListVo getMySubmit() {
        return mySubmit;
    }

    public void setMySubmit(MyXuanshangListVo mySubmit) {
        this.mySubmit = mySubmit;
    }

    public List<String> getRewardUserList() {
        return rewardUserList;
    }

    public void setRewardUserList(List<String> rewardUserList) {
        this.rewardUserList = rewardUserList;
    }

    public String getNumDesc() {
        return numDesc;
    }

    public void setNumDesc(String numDesc) {
        this.numDesc = numDesc;
    }
}
