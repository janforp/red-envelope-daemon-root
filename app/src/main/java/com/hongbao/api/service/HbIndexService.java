package com.hongbao.api.service;

import com.hongbao.api.config.Config;
import com.hongbao.api.dao.ReAccountDetailDAO;
import com.hongbao.api.dao.ReMissionDAO;
import com.hongbao.api.enums.MissionSubtype;
import com.hongbao.api.enums.MissionType;
import com.hongbao.api.model.dto.*;
import com.hongbao.api.service.redis.RedisBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Summer on 2016/10/8.
 */
@Service
public class HbIndexService {

    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReMissionDAO reMissionDAO;
    @Autowired
    private AppClickShareService appClickShareService;
    @Autowired
    private HbRecommendService hbRecommendService;
    @Autowired
    private RedisBannerService redisBannerService;

    /**
     * 首页滚动列表
     *
     * @return
     */
    public List<String> indexRollRedis() {
        return redisBannerService.indexRollList();
    }



    /**
     * 首页滚动列表
     * 版本 1.0
     *
     * @return
     */
    public List<RollInfo> selectRollInfoList(){
        return reAccountDetailDAO.selectRollList();
    }

    /**
     * 首页滚动列表
     * 版本 2.0
     *
     * @return
     */
    public List<RollContent> selectRollList(){

        List<RollContent> rollList = new ArrayList<>();

        List<RollInfo> rollInfoList = reAccountDetailDAO.selectRollList();

        for (int i = 0; i < rollInfoList.size(); i++) {

            RollContent rollContent = new RollContent();

            rollContent.setNickname(rollInfoList.get(i).getNickname());

            if(i == 0) {
                rollContent.setContent("提现了10元!");
            }else if(i == 1) {
                rollContent.setContent("充值了10元话费!");
            }else if(i == 2) {
                rollContent.setContent("获得了1.01元!");
            }else {
                rollContent.setContent("获得了"+rollInfoList.get(i).getMoney()+"元!");
            }

            rollList.add(rollContent);

        }

        Collections.shuffle(rollList);

        return rollList;

    }

    /**
     * 首页滚动列表
     * 版本 4.0
     *
     * @return
     */
    public List<String> indexRollList() {

        List<String> rollList = new ArrayList<>();

        List<IndexRollInfo> rollInfoList = reAccountDetailDAO.selectIndexRollList();

        for (IndexRollInfo info : rollInfoList) {

            String content = null;

            String nickName = info.getNickname();
            int detailType = info.getDetailType();
            int missionType = info.getMissionType();
            int missionSubtype = info.getMissionSubtype();
            BigDecimal money = info.getMoney();
            if(detailType == 0) { // 支出
                if(missionType == MissionType.other_mission.val) { // 其他
                    if(missionSubtype == MissionSubtype.zhifubao.val) {
                        content = "恭喜" + nickName + "提现了" + money + "元到支付宝";
                    }else if(missionSubtype == MissionSubtype.weixin.val) {
                        content = "恭喜" + nickName + "提现了" + money + "元到微信";
                    }else if(missionSubtype == MissionSubtype.huafei.val) {
                        content = "恭喜" + nickName + "充值了" + money + "元话费";
                    }
                }
            }else { // 收入
                if(missionType == MissionType.other_mission.val) { // 其他
                    if(missionSubtype == MissionSubtype.fix_red.val) {
                        content = "恭喜" + nickName + "领取了定时红包获得" + money + "元现金";
                    }else if(missionSubtype == MissionSubtype.word_red.val) {
                        content = "恭喜" + nickName + "领取了口令红包获得" + money + "元现金";
                    }else if(missionSubtype == MissionSubtype.newcomer.val) {
                        content = "恭喜" + nickName + "完成新手任务获得" + money + "元现金";
                    }else if(missionSubtype == MissionSubtype.register.val) {
                        content = "恭喜" + nickName + "完成注册任务获得" + money + "元现金";
                    }
                }else if (missionType == MissionType.demo_mission.val) { // 试玩任务
                    content = "恭喜" + nickName + "完成试玩任务获得" + money + "元现金";
                }else if (missionType == MissionType.share_mission.val) { // 分享任务
                    content = "恭喜" + nickName + "完成分享任务获得" + money + "元现金";
                }else if (missionType == MissionType.attention_mission.val) { // 关注任务
                    content = "恭喜" + nickName + "完成关注任务获得" + money + "元现金";
                }else if (missionType == MissionType.alliance_mission.val) { // 联盟任务
                    content = "恭喜" + nickName + "完成联盟任务获得" + money + "元现金";
                }else if (missionType == MissionType.great_mission.val) { // 高额任务
                    content = "恭喜" + nickName + "完成高额任务获得" + money + "元现金";
                }else if (missionType == MissionType.deepness_mission.val) { // 深度任务
                    content = "恭喜" + nickName + "完成深度任务获得" + money + "元现金";
                }
            }

            if(content != null) {
                rollList.add(content);
            }

        }

        return rollList;

    }

    /**
     * 首页理财红包
     *
     * @param platform
     * @param flag
     * @return
     */
    public List<ReCodeRedInfo> selectMissionList(int platform, boolean flag) {

        List<ReCodeRedInfo> missionList = new ArrayList<>();

        if(flag) {

            List<ReMissionInfo> list = reMissionDAO.selectByPlatform(platform);

            for (ReMissionInfo missionInfo : list) {
                ReCodeRedInfo info = new ReCodeRedInfo();
                info.setRedStatus(missionInfo.getMissionStatus());
                info.setCustomerName(missionInfo.getMerchantName());
                info.setCustomerImg(missionInfo.getMissionImg());
                info.setAwardDesc(missionInfo.getMissionReward());
                info.setCustomerDesc(missionInfo.getMerchantDetail());
                info.setRedUrl(Config.getRedBaseUrl() + "/c/p/mission/missionDetail/" + missionInfo.getMissionId());
                missionList.add(info);
            }

        }

        return missionList;

    }

    /**
     * 优惠生活
     *
     * @param platform
     * @return
     */
    public List<ReCodeRedInfo> selectLifeList(int platform) {

        List<ReCodeRedInfo> missionList = new ArrayList<>();

        List<ReMissionInfo> list = reMissionDAO.selectByPlatform(platform);

        for (ReMissionInfo missionInfo : list) {
            ReCodeRedInfo info = new ReCodeRedInfo();
            info.setRedStatus(missionInfo.getMissionStatus());
            info.setCustomerDesc(missionInfo.getMerchantDetail());
            info.setCustomerName(missionInfo.getMerchantName());
            info.setCustomerImg(missionInfo.getMissionImg());
            info.setAwardDesc(missionInfo.getMissionReward());
            info.setRedUrl(Config.getRedBaseUrl() + "/c/p/mission/missionDetail/" + missionInfo.getMissionId());
            missionList.add(info);
        }

        return missionList;

    }


    /**
     * 首页任务入口
     *
     * @param platform
     * @param flag
     * @param userId
     * @return
     */
    public List<IndexMissionInfo> indexMissionList(int platform, boolean flag, Integer userId) {

        List<IndexMissionInfo> list = new ArrayList<>();

        if(userId.intValue() == 0) {
            userId = null;
        }

        if(platform == 1) { // andriod

            if(flag) { // 正常

                // 新手任务
                IndexMissionInfo newcomer = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/newcomer.png",
                        "新手任务",
                        "+3.1元",
                        "新",
                        "hongbao://NewcomerActivity");
                list.add(newcomer);

                // 试玩任务
                IndexMissionInfo trial = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/trial.png",
                        "试玩任务",
                        "今日已更新",
                        "新",
                        "hongbao://TrialAccountActivity");
                list.add(trial);

                // 分享任务
                IndexMissionInfo share = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/share.png",
                        "分享任务",
                        "分享领奖励",
                        appClickShareService.selectShareCount(),
                        "hongbao://ForwardingTaskActivity");
                list.add(share);

                // 关注任务 个数 总金额
                MoneyAndCountInfo attentionInfo = hbRecommendService.selectAttention(platform, userId);

                // 关注任务
                IndexMissionInfo attention = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/attention.png",
                        "关注任务",
                        attentionInfo.getMoney(),
                        attentionInfo.getCount(),
                        "hongbao://AttentionTaskActivity");
                list.add(attention);

                // 高额任务 个数 总金额
                MoneyAndCountInfo greatInfo = hbRecommendService.selectGreat(platform, userId);

                // 高额任务
                IndexMissionInfo great = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/great.png",
                        "高额任务",
                        greatInfo.getMoney(),
                        greatInfo.getCount(),
                        "hongbao://HighMissionActivity");
                list.add(great);

                // 联盟任务
                IndexMissionInfo alliance = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/alliance.png",
                        "联盟任务",
                        "每日更新",
                        "",
                        "hongbao://JifenWallActivity");
                list.add(alliance);

                // 每日福利
                IndexMissionInfo welfare = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/welfare.png",
                        "每日福利",
                        "每日更新",
                        "新",
                        "hongbao://DiscountLifeActivity");
                list.add(welfare);

                // 一元夺宝
                IndexMissionInfo duobao = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/duobao.png",
                        "一元夺宝",
                        "1元抢iPhone",
                        "新",
                        Config.getRedBaseUrl()+"/page/yiyuanduobao.html");
                list.add(duobao);

            }

//            // 定时红包
//            IndexMissionInfo fix = new IndexMissionInfo(
//                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/fix.png",
//                    "定时红包",
//                    "准时开抢",
//                    "",
////                    "hongbao://FixRedActivity");
//                    Config.getRedBaseUrl() + "/page/fixed.html");
//            list.add(fix);

            // 签到任务
            IndexMissionInfo sign = new IndexMissionInfo(
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/sign.png",
                    "签到任务",
                    "签到领金币",
                    "",
                    Config.getRedBaseUrl()+"/c/p/a/sign/index");
            list.add(sign);

            // 口令红包
            IndexMissionInfo word = new IndexMissionInfo(
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/word.png",
                    "口令红包",
                    "口令兑现金",
                    "新",
                    "hongbao://CommandRedActivity");
            list.add(word);

            if(flag) {

                // 理财大厅
                IndexMissionInfo financial = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/financial.png",
                        "理财大厅",
                        "全民理财",
                        "新",
                        Config.getRedBaseUrl()+"/c/p/financial/mall");
                list.add(financial);

            }

        }else { // ios

            if(flag) { // 正常

                // 新手任务
                IndexMissionInfo newcomer = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/newcomer.png",
                        "新手任务",
                        "+2.9元",
                        "新",
                        "hongbao://FRYoungLingViewController");
                list.add(newcomer);

                // 分享任务
                IndexMissionInfo share = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/share.png",
                        "分享任务",
                        "分享领奖励",
                        appClickShareService.selectArticleCount(),
                        "hongbao://FRShareMissionViewController");
                list.add(share);

                // 关注任务 个数 总金额
                MoneyAndCountInfo attentionInfo = hbRecommendService.selectAttention(platform, userId);

                // 关注任务
                IndexMissionInfo attention = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/attention.png",
                        "关注任务",
                        attentionInfo.getMoney(),
                        attentionInfo.getCount(),
                        "hongbao://FRAttentionMissionViewController");
                list.add(attention);

                // 高额任务 个数 总金额
                MoneyAndCountInfo greatInfo = hbRecommendService.selectGreat(platform, userId);

                // 高额任务
                IndexMissionInfo great = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/great.png",
                        "高额任务",
                        greatInfo.getMoney(),
                        greatInfo.getCount(),
                        "hongbao://FRHighMissionViewController");
                list.add(great);

                // 联盟任务
                IndexMissionInfo alliance = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/alliance.png",
                        "联盟任务",
                        "每日更新",
                        "",
                        "hongbao://FRAllianceMissionViewController");
                list.add(alliance);


                // 每日福利
                IndexMissionInfo welfare = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/welfare.png",
                        "每日福利",
                        "每日更新",
                        "新",
                        "hongbao://FRDiscountLifeViewController");
                list.add(welfare);

                // 一元夺宝
                IndexMissionInfo duobao = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/duobao.png",
                        "一元夺宝",
                        "1元抢iPhone",
                        "新",
                        Config.getRedBaseUrl()+"/page/yiyuanduobao.html");
                list.add(duobao);

            }

//            // 定时红包
//            IndexMissionInfo fix = new IndexMissionInfo(
//                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/fix.png",
//                    "定时红包",
//                    "准时开抢",
//                    "",
////                    "hongbao://FRFixedRedViewController");
//                    Config.getRedBaseUrl() + "/page/fixed.html");
//            list.add(fix);

            // 签到任务
            IndexMissionInfo sign = new IndexMissionInfo(
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/sign.png",
                    "签到任务",
                    "签到领金币",
                    "",
                    Config.getRedBaseUrl()+"/c/p/a/sign/index");
            list.add(sign);

            if(flag) {

                // 理财大厅
                IndexMissionInfo financial = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/financial.png",
                        "理财大厅",
                        "全民理财",
                        "新",
                        Config.getRedBaseUrl()+"/c/p/financial/mall");
                list.add(financial);

            }

            // 口令红包
            IndexMissionInfo word = new IndexMissionInfo(
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/word.png",
                    "口令红包",
                    "口令兑现金",
                    "新",
                    "hongbao://FRCommandRedViewController");
            list.add(word);

            // 微信热文
            IndexMissionInfo weixin = new IndexMissionInfo(
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/weixin.png",
                    "微信热文",
                    "精选微信好文",
                    "",
                    "http://weixinrewen.com/");
            list.add(weixin);

        }

        return list;

    }


    /**
     * 首页任务入口2
     *
     * @param platform
     * @param flag
     * @param userId
     * @return
     */
    public List<IndexMissionInfo> indexMissionList2(int platform, boolean flag, Integer userId) {

        List<IndexMissionInfo> list = new ArrayList<>();

        if(platform == 1) { // andriod

            if(flag) { // 正常

                // 新手任务
                IndexMissionInfo newcomer = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/newcomer.png",
                        "新手任务",
                        "+3.1元",
                        "新",
                        "hongbao://NewcomerActivity");
                list.add(newcomer);

                // 试玩任务
                IndexMissionInfo trial = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/trial.png",
                        "试玩任务",
                        "今日已更新",
                        "新",
                        "hongbao://NewTrialsActivity");
                list.add(trial);

                // 分享任务
                IndexMissionInfo share = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/share.png",
                        "分享任务",
                        "分享领奖励",
                        appClickShareService.selectShareCount(),
                        "hongbao://ForwardingTaskActivity");
                list.add(share);

                // 关注任务 个数 总金额
                MoneyAndCountInfo attentionInfo = hbRecommendService.selectAttention(platform, userId);

                // 关注任务
                IndexMissionInfo attention = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/attention.png",
                        "关注任务",
                        attentionInfo.getMoney(),
                        attentionInfo.getCount(),
                        "hongbao://AttentionTaskActivity");
                list.add(attention);

                // 高额任务 个数 总金额
                MoneyAndCountInfo greatInfo = hbRecommendService.selectGreat(platform, userId);

                // 高额任务
                IndexMissionInfo great = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/great.png",
                        "高额任务",
                        greatInfo.getMoney(),
                        greatInfo.getCount(),
                        "hongbao://HighMissionActivity");
                list.add(great);

                // 联盟任务
                IndexMissionInfo alliance = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/alliance.png",
                        "联盟任务",
                        "每日更新",
                        "",
                        "hongbao://JifenWallActivity");
                list.add(alliance);


                // 每日福利
                IndexMissionInfo welfare = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/welfare.png",
                        "每日福利",
                        "每日更新",
                        "新",
                        "hongbao://DiscountLifeActivity");
                list.add(welfare);

                // 一元夺宝
                IndexMissionInfo duobao = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/duobao.png",
                        "一元夺宝",
                        "1元抢iPhone",
                        "新",
                        Config.getRedBaseUrl()+"/page/yiyuanduobao.html");
                list.add(duobao);


            }

//            // 定时红包
//            IndexMissionInfo fix = new IndexMissionInfo(
//                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/fix.png",
//                    "定时红包",
//                    "准时开抢",
//                    "",
////                    "hongbao://FixRedActivity");
//                    Config.getRedBaseUrl() + "/page/fixed.html");
//            list.add(fix);

            // 签到任务
            IndexMissionInfo sign = new IndexMissionInfo(
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/sign.png",
                    "签到任务",
                    "签到领金币",
                    "",
                    Config.getRedBaseUrl()+"/c/p/a/sign/index");
            list.add(sign);

            // 口令红包
            IndexMissionInfo word = new IndexMissionInfo(
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/word.png",
                    "口令红包",
                    "口令兑现金",
                    "新",
                    "hongbao://CommandRedActivity");
            list.add(word);

            if(flag) {

                // 理财大厅
                IndexMissionInfo financial = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/financial.png",
                        "理财大厅",
                        "全民理财",
                        "新",
                        Config.getRedBaseUrl()+"/c/p/financial/mall");
                list.add(financial);

            }

        }else { // ios

            if(flag) { // 正常

                // 新手任务
                IndexMissionInfo newcomer = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/newcomer.png",
                        "新手任务",
                        "+2.9元",
                        "新",
                        "hongbao://FRYoungLingViewController");
                list.add(newcomer);

                // 分享任务
                IndexMissionInfo share = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/share.png",
                        "分享任务",
                        "分享领奖励",
                        appClickShareService.selectArticleCount(),
                        "hongbao://FRShareMissionViewController");
                list.add(share);

                // 关注任务 个数 总金额
                MoneyAndCountInfo attentionInfo = hbRecommendService.selectAttention(platform, userId);

                // 关注任务
                IndexMissionInfo attention = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/attention.png",
                        "关注任务",
                        attentionInfo.getMoney(),
                        attentionInfo.getCount(),
                        "hongbao://FRAttentionMissionViewController");
                list.add(attention);

                // 高额任务 个数 总金额
                MoneyAndCountInfo greatInfo = hbRecommendService.selectGreat(platform, userId);

                // 高额任务
                IndexMissionInfo great = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/great.png",
                        "高额任务",
                        greatInfo.getMoney(),
                        greatInfo.getCount(),
                        "hongbao://FRHighMissionViewController");
                list.add(great);

                // 联盟任务
                IndexMissionInfo alliance = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/alliance.png",
                        "联盟任务",
                        "每日更新",
                        "",
                        "hongbao://FRAllianceMissionViewController");
                list.add(alliance);


                // 每日福利
                IndexMissionInfo welfare = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/welfare.png",
                        "每日福利",
                        "每日更新",
                        "新",
                        "hongbao://FRDiscountLifeViewController");
                list.add(welfare);

                // 一元夺宝
                IndexMissionInfo duobao = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/duobao.png",
                        "一元夺宝",
                        "1元抢iPhone",
                        "新",
                        Config.getRedBaseUrl()+"/page/yiyuanduobao.html");
                list.add(duobao);

            }

//            // 定时红包
//            IndexMissionInfo fix = new IndexMissionInfo(
//                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/fix.png",
//                    "定时红包",
//                    "准时开抢",
//                    "",
////                    "hongbao://FRFixedRedViewController");
//                    Config.getRedBaseUrl() + "/page/fixed.html");
//            list.add(fix);

            // 签到任务
            IndexMissionInfo sign = new IndexMissionInfo(
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/sign.png",
                    "签到任务",
                    "签到领金币",
                    "",
                    Config.getRedBaseUrl()+"/c/p/a/sign/index");
            list.add(sign);

            if(flag) {

                // 理财大厅
                IndexMissionInfo financial = new IndexMissionInfo(
                        "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/financial.png",
                        "理财大厅",
                        "全民理财",
                        "新",
                        Config.getRedBaseUrl()+"/c/p/financial/mall");
                list.add(financial);

            }

            // 口令红包
            IndexMissionInfo word = new IndexMissionInfo(
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/word.png",
                    "口令红包",
                    "口令兑现金",
                    "新",
                    "hongbao://FRCommandRedViewController");
            list.add(word);

            // 微信热文
            IndexMissionInfo weixin = new IndexMissionInfo(
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/weixin.png",
                    "微信热文",
                    "精选微信好文",
                    "",
                    "http://weixinrewen.com/");
            list.add(weixin);

        }

        return list;

    }


    /**
     * 首页服务入口
     *
     * @return
     */
    public List<IndexServiceInfo> indexServiceList(int platform, boolean flag) {

        List<IndexServiceInfo> list = new ArrayList<>();

        if(platform == 1) { // andriod
            // 微信热文
            IndexServiceInfo weixin = new IndexServiceInfo(
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/weixin.png",
                    "微信热文",
                    "http://tag.html5.qq.com/#tab/6188615/0/0");
            list.add(weixin);
        }

        // 幸运转盘
        IndexServiceInfo luck = new IndexServiceInfo(
                "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/luck.png",
                "幸运转盘",
                Config.getRedBaseUrl() + "/c/p/rotate/index");
        list.add(luck);

        if(flag) { // 正常

            // 同城交友
            IndexServiceInfo friend = new IndexServiceInfo(
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/friend.png",
                    "同城交友",
                    Config.getRedBaseUrl()+"/page/make_friend.html");
            list.add(friend);

        }

        // 系统消息
        IndexServiceInfo system = new IndexServiceInfo(
                "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/system.png",
                "系统消息",
                Config.getRedBaseUrl() + "/c/p/sys/message");
        list.add(system);

        // 邀请好友
        IndexServiceInfo invite = new IndexServiceInfo(
                "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/invite.png",
                "邀请好友",
                Config.getRedBaseUrl() + "/c/p/invitation/invitationCommissionMenu");
        list.add(invite);

        // 淘抢购
        IndexServiceInfo buy = new IndexServiceInfo(
                "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/buy.png",
                "淘抢购",
                "https://s.click.taobao.com/fO9sTNx");
        list.add(buy);

        // 充值中心
        IndexServiceInfo recharge = new IndexServiceInfo(
                "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/recharge.png",
                "充值中心",
                "https://h5.m.taobao.com/app/cz/cost.html");
        list.add(recharge);

        // 闲置好物
        IndexServiceInfo transaction = new IndexServiceInfo(
                "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/transaction.png",
                "闲置好物",
                "http://hz.58.com/sale.shtml?PGTID=0d100000-0004-f5af-7a5c-e550eed243e4&ClickID=1");
        list.add(transaction);

        // 高薪招聘
        IndexServiceInfo employ = new IndexServiceInfo(
                "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/employ.png",
                "高薪招聘",
                "http://hz.58.com/job.shtml?PGTID=0d100000-0004-f37b-4241-15ddc1be290a&ClickID=1");
        list.add(employ);

        // 租好房
        IndexServiceInfo rentHouse = new IndexServiceInfo(
                "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/rentHouse.png",
                "租好房",
                "http://hz.58.com/chuzu/?PGTID=0d200001-0004-f8e6-6e84-9d6fbff68265&ClickID=2");
        list.add(rentHouse);

        // 查公交
        IndexServiceInfo bus = new IndexServiceInfo(
                "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/bus.png",
                "查公交",
                "https://web.chelaile.net.cn/ch5/index.html");
        list.add(bus);

        // 点外卖
        IndexServiceInfo takeOut = new IndexServiceInfo(
                "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/index/takeOut.png",
                "点外卖",
                "http://waimai.meituan.com/?utm_campaign=bing&utm_source=4204");
        list.add(takeOut);

        return list;

    }

}
