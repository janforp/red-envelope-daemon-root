package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-19
 */
public class ReWithdrawSort implements java.io.Serializable {

    // Fields

    // 提现分类ID
    private Integer withdrawId;
    // 提现类型
    private String withdrawType;
    // 名字
    private String withdrawName;
    // 图标
    private String withdrawImg;
    // 副标题
    private String withdrawExplain;
    // 跳转链接
    private String withdrawUrl;
    // 提现金额,如:10&30&50
    private String withdrawMoney;
    // 到账金额,如:9&29.5&49.9
    private String toAccountMoney;
    // 提现说明
    private String withdrawDesc;
    // 提现次数
    private Integer withdrawTimes;
    // 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
    private Integer withdrawStatus;
    // 排序,值较小者排在较前
    private Integer withdrawOrder;

    // Constructors

    /**
     * default constructor
     */
    public ReWithdrawSort() {
    }

    /**
     * full constructor
     */
    public ReWithdrawSort(String withdrawType, String withdrawName, String withdrawImg, String withdrawExplain, String withdrawUrl, String withdrawMoney, String toAccountMoney, String withdrawDesc, Integer withdrawTimes, Integer withdrawStatus, Integer withdrawOrder) {
        this.withdrawType = withdrawType;
        this.withdrawName = withdrawName;
        this.withdrawImg = withdrawImg;
        this.withdrawExplain = withdrawExplain;
        this.withdrawUrl = withdrawUrl;
        this.withdrawMoney = withdrawMoney;
        this.toAccountMoney = toAccountMoney;
        this.withdrawDesc = withdrawDesc;
        this.withdrawTimes = withdrawTimes;
        this.withdrawStatus = withdrawStatus;
        this.withdrawOrder = withdrawOrder;
    }

    // Property accessors

    /**
     * 提现分类ID
     */
    public Integer getWithdrawId() {
        return this.withdrawId;
    }

    /**
     * 提现分类ID
     */
    public void setWithdrawId(Integer withdrawId) {
        this.withdrawId = withdrawId;
    }

    /**
     * 提现类型
     */
    public String getWithdrawType() {
        return this.withdrawType;
    }

    /**
     * 提现类型
     */
    public void setWithdrawType(String withdrawType) {
        this.withdrawType = withdrawType;
    }

    /**
     * 名字
     */
    public String getWithdrawName() {
        return this.withdrawName;
    }

    /**
     * 名字
     */
    public void setWithdrawName(String withdrawName) {
        this.withdrawName = withdrawName;
    }

    /**
     * 图标
     */
    public String getWithdrawImg() {
        return this.withdrawImg;
    }

    /**
     * 图标
     */
    public void setWithdrawImg(String withdrawImg) {
        this.withdrawImg = withdrawImg;
    }

    /**
     * 副标题
     */
    public String getWithdrawExplain() {
        return this.withdrawExplain;
    }

    /**
     * 副标题
     */
    public void setWithdrawExplain(String withdrawExplain) {
        this.withdrawExplain = withdrawExplain;
    }

    /**
     * 跳转链接
     */
    public String getWithdrawUrl() {
        return this.withdrawUrl;
    }

    /**
     * 跳转链接
     */
    public void setWithdrawUrl(String withdrawUrl) {
        this.withdrawUrl = withdrawUrl;
    }

    /**
     * 提现金额,如:10&30&50
     */
    public String getWithdrawMoney() {
        return this.withdrawMoney;
    }

    /**
     * 提现金额,如:10&30&50
     */
    public void setWithdrawMoney(String withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }

    /**
     * 到账金额,如:9&29.5&49.9
     */
    public String getToAccountMoney() {
        return this.toAccountMoney;
    }

    /**
     * 到账金额,如:9&29.5&49.9
     */
    public void setToAccountMoney(String toAccountMoney) {
        this.toAccountMoney = toAccountMoney;
    }

    /**
     * 提现说明
     */
    public String getWithdrawDesc() {
        return this.withdrawDesc;
    }

    /**
     * 提现说明
     */
    public void setWithdrawDesc(String withdrawDesc) {
        this.withdrawDesc = withdrawDesc;
    }

    /**
     * 提现次数
     */
    public Integer getWithdrawTimes() {
        return this.withdrawTimes;
    }

    /**
     * 提现次数
     */
    public void setWithdrawTimes(Integer withdrawTimes) {
        this.withdrawTimes = withdrawTimes;
    }

    /**
     * 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getWithdrawStatus() {
        return this.withdrawStatus;
    }

    /**
     * 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
     */
    public void setWithdrawStatus(Integer withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }

    /**
     * 排序,值较小者排在较前
     */
    public Integer getWithdrawOrder() {
        return this.withdrawOrder;
    }

    /**
     * 排序,值较小者排在较前
     */
    public void setWithdrawOrder(Integer withdrawOrder) {
        this.withdrawOrder = withdrawOrder;
    }

}