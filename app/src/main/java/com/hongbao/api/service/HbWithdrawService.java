package com.hongbao.api.service;

import com.hongbao.api.config.Config;
import com.hongbao.api.dao.*;
import com.hongbao.api.model.*;
import com.hongbao.api.model.dto.*;
import com.hongbao.api.service.user.HbUserService;
import com.hongbao.api.service.user.UserCacheService;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Summer on 16/8/19.
 */
@Service
public class HbWithdrawService {

    @Autowired
    private ReWithdrawSortDAO reWithdrawSortDAO;
    @Autowired
    private ReScoreExchangeDAO reScoreExchangeDAO;
    @Autowired
    private ReWithdrawBindDAO reWithdrawBindDAO;
    @Autowired
    private ReWithdrawBindZfbDAO reWithdrawBindZfbDAO;
    @Autowired
    private ReWithdrawDetailDAO reWithdrawDetailDAO;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private HbUserService hbUserService;
    @Autowired
    private UserCacheService userCacheService;


    /**
     * 获取提现页面列表
     *
     * 版本3.0.0+
     *
     * @param version
     * @param platform
     * @return
     */
    public List<WithdrawListInfo> getWithdrawList3(int platform, String version) {

        List<WithdrawListInfo> list = new ArrayList<>();

        // 现金提现
        List<ReWithdrawSortInfo> sortInfoList = reWithdrawSortDAO.selectWithdrawSortByPlatform(platform);

        if(CommonMethod.compareVersion(version, "3.0.0") >= 0) {
            for (ReWithdrawSortInfo info : sortInfoList) {
                String url = info.getWithdrawUrl() + "?id=" + info.getWithdrawId();
                info.setWithdrawUrl(url);
            }
        }
        if(sortInfoList != null && sortInfoList.size() > 0) {
            WithdrawListInfo info = new WithdrawListInfo();
            info.setListData(sortInfoList);
            info.setListName("现金提现");
            list.add(info);
        }
        return list;
    }

    /**
     * 提现详情
     *
     * 版本3.0.0+
     *
     * @param withdrawId
     * @param userId
     * @return
     */
    public String getWithdrawSortDetail3(int withdrawId, int userId) {

        ReWithdrawDetailInfo detail = new ReWithdrawDetailInfo();

        ReWithdrawSort sort = reWithdrawSortDAO.selectByPrimaryKey(withdrawId);

        String withdrawType = sort.getWithdrawType();
        detail.setWithdrawType(withdrawType);

        // 本月剩余提现次数
        int leftTimes = selectLeftWithdrawTimes(userId);
        detail.setWithdrawTimes(leftTimes);

        if("weixin".equals(withdrawType)) { // 提现到微信

            ReWithdrawBind bind = reWithdrawBindDAO.selectByPrimaryKey(userId);
            detail.setBindUrl(Config.getWeixinBindUrl());

            if(bind == null) { // 未绑定微信

                detail.setWithdrawTimes(sort.getWithdrawTimes());

            }else { // 已绑定微信

                detail.setNickname(bind.getNickname());
                detail.setOpenid(bind.getOpenId());

            }

        }else if("zhifubao".equals(withdrawType)){ // 提现到支付宝

            ReWithdrawBindZfb zfb = reWithdrawBindZfbDAO.selectByPrimaryKey(userId);
            if(zfb != null) { // 已绑定
                detail.setNickname(zfb.getFullName());
                detail.setOpenid(zfb.getAlipayAccount());
            }

        }else if("huafei".equals(withdrawType)){ // 提现话费

            ReUser reUser = reUserDAO.selectByPrimaryKey(userId);
            detail.setOpenid(reUser.getMobile());

        }

        detail.setWithdrawDesc(sort.getWithdrawDesc());

        String[] withdrawMoney = sort.getWithdrawMoney().split("&");
        String[] toAccountMoney = sort.getToAccountMoney().split("&");
        List<ReWithdrawMoney> reWithdrawMoneyList = new ArrayList<>();
        for (int i = 0; i < withdrawMoney.length; i++ ) {
            ReWithdrawMoney money = new ReWithdrawMoney();
            money.setWithdrawMoney(new BigDecimal(toAccountMoney[i]));
            money.setApplyMoney(new BigDecimal(withdrawMoney[i]));
            reWithdrawMoneyList.add(money);
        }
        detail.setReWithdrawMoneyList(reWithdrawMoneyList);


        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("detail", detail);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 查询提现记录
     *
     * @param userId
     * @param type
     * @param id
     * @returns
     */
    public List<WithdrawRecordInfo> getRecords(Integer userId, Integer type, Long id) {

        List<WithdrawRecordInfo> list = new ArrayList<>();

        List<ReWithdrawDetail> detailList = reWithdrawDetailDAO.selectWithdrawList(userId, type, id);

        for (ReWithdrawDetail detail : detailList) {

            WithdrawRecordInfo info = new WithdrawRecordInfo();
            info.setWithdrawId(detail.getWithdrawId());
            info.setTime(detail.getApplyTime());

            String details = "";
            String withdrawType = detail.getWithdrawType();
            BigDecimal money = detail.getApplyMoney();
            if("zhifubao".equals(withdrawType)) {
                details = "支付宝提现" + money + "元";
            }else if("huafei".equals(withdrawType)){
                details = "话费提现" + money + "元";
            }else if("weixin".equals(withdrawType)){
                details = "微信提现" + money + "元";
            }
            info.setDetail(details);

            // 0:审核中, 1:已完成, 2:未通过
            int status = detail.getWithdrawStatus();
            info.setStatus(status);
            if(status == 0) {
                info.setContent("审核中");
            }else if(status == 1) {
                info.setContent("提现成功");
            }else {
                info.setContent(detail.getRemarks());
            }

            list.add(info);

        }

        return list;

    }


    /**
     * 获取提现页面列表
     *
     * @param version
     * @param platform
     * @return
     */
    public List<WithdrawListInfo> getWithdrawList(int platform, String version) {

        List<WithdrawListInfo> list = new ArrayList<>();

        // 现金提现
        List<ReWithdrawSortInfo> sortInfoList = reWithdrawSortDAO.selectWithdrawSortByPlatform(platform);

        if((platform == 1 && CommonMethod.compareVersion(version, "2.1.0") >= 0)) {
            for (ReWithdrawSortInfo info : sortInfoList) {
                String url = info.getWithdrawUrl() + "?id=" + info.getWithdrawId();
                info.setWithdrawUrl(url);
            }
        }

        if(sortInfoList != null && sortInfoList.size() > 0) {
            WithdrawListInfo info = new WithdrawListInfo();
            info.setListName("现金提现");
            info.setListData(sortInfoList);
            list.add(info);
        }

        // 金币兑换
        List<ReScoreExchangeInfo> exchangeInfoList = reScoreExchangeDAO.selectScoreExchangeByPlatform(platform);
        if(exchangeInfoList != null && exchangeInfoList.size() > 0) {

            List<ReWithdrawSortInfo> infoList = new ArrayList<>();
            for (ReScoreExchangeInfo exchange : exchangeInfoList) {
                ReWithdrawSortInfo sort = new ReWithdrawSortInfo();
                sort.setWithdrawImg(exchange.getExchangeImg());
                sort.setWithdrawName(exchange.getExchangeName());
                sort.setWithdrawUrl(exchange.getExchangeUrl());
                sort.setWithdrawExplain(exchange.getExchangeDesc());
                infoList.add(sort);
            }

            WithdrawListInfo info = new WithdrawListInfo();
            info.setListName("金币兑换");
            info.setListData(infoList);
            list.add(info);
        }

        return list;

    }



    /**
     * 提现详情
     * @param withdrawId
     * @param userId
     * @return
     */
    public String getWithdrawSortDetail(int withdrawId, int userId) {

        ReWithdrawDetailInfo detail = new ReWithdrawDetailInfo();

        ReWithdrawSort sort = reWithdrawSortDAO.selectByPrimaryKey(withdrawId);

        String withdrawType = sort.getWithdrawType();
        detail.setWithdrawType(withdrawType);

        // 本月剩余提现次数
        int leftTimes = selectLeftWithdrawTimes(userId);
        detail.setWithdrawTimes(leftTimes);

        if("weixin".equals(withdrawType)) { // 提现到微信

            ReWithdrawBind bind = reWithdrawBindDAO.selectByPrimaryKey(userId);
            detail.setBindUrl(Config.getWeixinBindUrl());

            if(bind == null) { // 未绑定微信

                detail.setWithdrawTimes(sort.getWithdrawTimes());

            }else { // 已绑定微信

                detail.setNickname(bind.getNickname());
                detail.setOpenid(bind.getOpenId());

            }

        }

        detail.setWithdrawDesc(sort.getWithdrawDesc());

        String[] withdrawMoney = sort.getWithdrawMoney().split("&");
        String[] toAccountMoney = sort.getToAccountMoney().split("&");
        List<ReWithdrawMoney> reWithdrawMoneyList = new ArrayList<>();
        for (int i = 0; i < withdrawMoney.length; i++ ) {
            ReWithdrawMoney money = new ReWithdrawMoney();
            money.setApplyMoney(new BigDecimal(withdrawMoney[i]));
            money.setWithdrawMoney(new BigDecimal(toAccountMoney[i]));
            reWithdrawMoneyList.add(money);
        }
        detail.setReWithdrawMoneyList(reWithdrawMoneyList);


        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("detail", detail);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


    /**
     * 查询本月剩余提现次数
     *
     * @param userId
     * @return
     */
    public int selectLeftWithdrawTimes(int userId) {

        String monthTime = CommonMethod.formatYearMonth(System.currentTimeMillis());
        // 本月已提现次数
        int times =  reWithdrawDetailDAO.selectTimesByUserId(userId, monthTime);

        int leftTimes = 2 - times;
        if(leftTimes < 0) {
            leftTimes = 0;
        }
        return leftTimes;
    }


    /**
     * 申请提现
     * @param userId
     * @param withdrawType
     * @param account
     * @param applyMoney
     * @param withdrawMoney
     * @return
     */
    public String apply(int userId, String withdrawType, String account, BigDecimal applyMoney, BigDecimal withdrawMoney, String realName) {

        // 查询本月已提现次数
        int leftTimes = selectLeftWithdrawTimes(userId);
        if(leftTimes <= 0) {
            return JsonUtil.buildErrorJson("本月提现机会已用完!");
        }

        // 查询用户信息
        ReUser reUser = reUserDAO.selectLockByUserId(userId);

        // 账户余额
        BigDecimal money = reUser.getUserMoney();

        if(money.compareTo(applyMoney) < 0) { // 余额不足
            return JsonUtil.buildErrorJson("账户余额不足!");
        }

        long now = System.currentTimeMillis();

        // 修改账户信息
        ReUser user = new ReUser();
        user.setUserId(userId);
        user.setUserMoney(money.subtract(applyMoney));
        user.setUpdateTime(now);
        reUserDAO.updateByPrimaryKeySelective(user);

        // 记录申请
        ReWithdrawDetail detail = new ReWithdrawDetail();
        detail.setUserId(userId);
        detail.setWithdrawType(withdrawType);
        detail.setWithdrawAccount(account);
        detail.setWithdrawStatus(0);
        detail.setApplyMoney(applyMoney);
        detail.setWithdrawMoney(withdrawMoney);
        detail.setApplyTime(CommonMethod.fmtTime(now));
        detail.setAccountName(realName);
        reWithdrawDetailDAO.insertSelective(detail);

        // 用户余额积分
        ReUserMoneyAndScore moneyInfo = hbUserService.getUserMoneyAndScore(userId);
        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("moneyInfo", moneyInfo);

        // 删除缓存
        userCacheService.removeUser(userId);

        return JsonUtil.buildSuccessDataJson(dataResult, "申请成功!");

    }

}
