package com.hongbao.api.service;

import com.hongbao.api.config.Config;
import com.hongbao.api.dao.ReCodeExchangeDetailDAO;
import com.hongbao.api.dao.ReRecommendMissionDAO;
import com.hongbao.api.dao.ReRecommendTaskDAO;
import com.hongbao.api.model.ReRecommendMission;
import com.hongbao.api.model.ReRecommendTask;
import com.hongbao.api.model.dto.*;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2016/10/26.
 */
@Service
public class HbRecommendService {

    @Autowired
    private ReRecommendTaskDAO reRecommendTaskDAO;
    @Autowired
    private ReRecommendMissionDAO reRecommendMissionDAO;
    @Autowired
    private ReCodeExchangeDetailDAO reCodeExchangeDetailDAO;


    /**
     * 查询高额任务列表
     *
     * @param platform
     * @param userId
     * @return
     */
    public List<GreatInfo> selectGreatList(int platform, Integer userId) {

        List<GreatInfo> greatList = new ArrayList<>();

        String today = CommonMethod.fmtDay(System.currentTimeMillis());

        List<ReTaskInfo> underwayList = new ArrayList<>();
        List<ReTaskInfo> otherList = new ArrayList<>();

        if(userId != null) {
            // 查询已领取的任务
            List<ReTaskInfo> receiveList = reRecommendTaskDAO.selectByUserId(userId);
            for (ReTaskInfo task : receiveList) {
                // 状态; 0-进行中 1-审核中 2-审核通过 3-未通过
                int status = task.getTaskStatus();
                if(status == 0) {
                    underwayList.add(task);
                }else {
                    otherList.add(task);
                }
            }

            for (ReTaskInfo task : underwayList) { // 正在进行中的任务
                greatList.add(greatInfo(task));
            }
        }


        // 查询可做列表
        List<ReRecommendMission> list = reRecommendMissionDAO.selectUnderwayGreatList(platform, userId, today);
        for (ReRecommendMission mission : list) {
            greatList.add(missionInfo(mission, 0));
        }


        if(userId != null) {
            for (ReTaskInfo task : otherList) { // 正在审核中 已完成 的任务
                greatList.add(greatInfo(task));
            }
        }


        // 查询已结束列表
        List<ReRecommendMission> overList = reRecommendMissionDAO.selectOverGreatList(platform, userId, today);
        for (ReRecommendMission mission : overList) {
            greatList.add(missionInfo(mission, 2));
        }

        return greatList;

    }


    /**
     * 推荐任务
     *
     * @param platform
     * @param flag
     * @param userId
     * @return
     */
    public List<RecommendInfo> selectRecommendList(int platform, boolean flag, int userId) {

        List<RecommendInfo> infoList = new ArrayList<>();

        if(flag) { // 审核通过版本

            List<String> list = null;

            Integer user = null;
            if(userId > 0){
                user = userId;
            }

            String today = CommonMethod.fmtDay(System.currentTimeMillis());

            // 推荐任务列表
            List<ReRecommendMission> missionList = reRecommendMissionDAO.selectByPlatformAndUserId(platform, user, today);

            if(platform == 0) { //ios

                list = new ArrayList<>();
                list.add("每日更新");
                list.add("可赚50元");

                // 积分墙入口
                RecommendInfo score = new RecommendInfo();
                score.setRecommendUrl("hongbao://FRJifenWallController");
                score.setRecommendImg("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/category/lianmeng.png");
                score.setRecommendTitle("联盟任务");
                score.setRecommendLabel(list);
                infoList.add(score);

            }else { //andriod

                list = new ArrayList<>();
                list.add("安卓专享");
                list.add("新任务");
                list.add("单价高");

                // 安卓专享任务入口
                RecommendInfo mission = new RecommendInfo();
                mission.setRecommendImg("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/category/shiwan.png");
                mission.setRecommendTitle("下载试玩");
                mission.setRecommendUrl(Config.getRedBaseUrl()+"/c/p/a/task/timedList");
                mission.setRecommendLabel(list);
                infoList.add(mission);


                list = new ArrayList<>();
                list.add("每日更新");
                list.add("可赚50元");

                // 积分墙入口
                RecommendInfo score = new RecommendInfo();
                score.setRecommendImg("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/category/lianmeng.png");
                score.setRecommendTitle("联盟任务");
                score.setRecommendUrl("hongbao://JifenWallActivity");
                score.setRecommendLabel(list);
                infoList.add(score);

            }

            for (ReRecommendMission recommendMission : missionList) {

                RecommendInfo info = new RecommendInfo();
                info.setRecommendUrl(Config.getRedBaseUrl()+"/c/p/recommend/detail/"+recommendMission.getMissionId());
                info.setRecommendTitle(recommendMission.getMissionTitle());
                info.setRecommendImg(recommendMission.getMissionIcon());

                list = new ArrayList<>();
                list.add("+"+recommendMission.getMinMoney()+"元");

                String missionLabel = recommendMission.getMissionLabel();
                if(!StringUtil.isEmpty(missionLabel)) {
                    String[] labels = missionLabel.split(",");
                    for (String label :labels) {
                        list.add(label);
                    }
                }
                list.add("剩"+recommendMission.getLeftNum()+"份");
                info.setRecommendLabel(list);

                infoList.add(info);

            }

        }

        return infoList;

    }

    /**
     * 提交审核
     *
     * @param taskId
     * @param requires
     * @param text
     * @param imgs
     * @return
     */
    public String submitMission(Long taskId, String requires, String text, String imgs) {

        ReRecommendTask reRecommendTask = reRecommendTaskDAO.selectByIdAndStatus(taskId);

        if(reRecommendTask == null) {
            return JsonUtil.buildErrorJson("请勿重复提交");
        }

        reRecommendTask.setCommitText(text);
        reRecommendTask.setCommitRequire(requires);
        reRecommendTask.setCommitImg(imgs);
        reRecommendTask.setUpdateTime(System.currentTimeMillis());
        reRecommendTask.setTaskStatus(1);
        reRecommendTaskDAO.updateByPrimaryKeySelective(reRecommendTask);

        return JsonUtil.buildSuccessMsgJson("提交成功");

    }

    /**
     * 查询关注任务列表
     *
     * @param platform
     * @param userId
     * @return
     */
    public List<AttentionInfo> selectAttentionList(int platform, Integer userId) {

        List<AttentionInfo> attentionInfoList = new ArrayList<>();

        String today = CommonMethod.fmtDay(System.currentTimeMillis());

        // 查询可做列表
        List<ReRecommendMission> underwayList = reRecommendMissionDAO.selectUnderwayAttentionList(platform, userId, today);
        for (ReRecommendMission mission : underwayList) {
            attentionInfoList.add(attentionInfo(mission, 0));
        }

        if(userId != null) {
            // 查询已完成列表
            List<ReRecommendMission> finishList = reCodeExchangeDetailDAO.selectFinishAttentionList(userId);
            for (ReRecommendMission mission : finishList) {
                attentionInfoList.add(attentionInfo(mission, 1));
            }

        }

        return attentionInfoList;

    }

    /**
     * 生成 AttentionInfo 关注任务
     *
     * @param mission
     * @param status  状态 0-未完成 1-已完成
     * @return
     */
    public AttentionInfo attentionInfo(ReRecommendMission mission, int status) {

        AttentionInfo info = new AttentionInfo();

        info.setTitle(mission.getMissionTitle());
        info.setImg(mission.getMissionIcon());

        List<String> labelList = new ArrayList<>();
        String missionLabel = mission.getMissionLabel();
        if(!StringUtil.isEmpty(missionLabel)) {
            String[] labels = missionLabel.split(",");
            for (String label :labels) {
                labelList.add(label);
            }
        }
        labelList.add("剩"+mission.getLeftNum()+"份");
        info.setLabel(labelList);

        info.setMoney(mission.getMinMoney());
        info.setStatus(status);
        info.setUrl(Config.getRedBaseUrl()+"/c/p/recommend/detail/"+mission.getMissionId());

        return info;

    }


    /**
     * 生成 GreatInfo 高额任务(已领取)
     *
     * @param taskInfo
     * @return
     */
    public GreatInfo greatInfo(ReTaskInfo taskInfo) {

        GreatInfo info = new GreatInfo();

        info.setTitle(taskInfo.getMissionTitle());
        info.setImg(taskInfo.getMissionIcon());

        List<String> labelList = new ArrayList<>();
        String missionLabel = taskInfo.getMissionLabel();
        if(!StringUtil.isEmpty(missionLabel)) {
            String[] labels = missionLabel.split(",");
            for (String label :labels) {
                labelList.add(label);
            }
        }
        info.setLabel(labelList);

        // 状态; 0-进行中 1-审核中 2-审核通过 3-未通过
        int status = taskInfo.getTaskStatus();
        if(status == 0) {
            info.setDesc("进行中");
            info.setStatus(1);
        }else if (status == 1) {
            info.setDesc("审核中");
            info.setStatus(1);
        }else if (status == 2) {
            info.setDesc("已完成");
            info.setStatus(2);
        }else if(status == 3) {
            info.setDesc("未通过");
            info.setStatus(2);
        }

        info.setUrl(Config.getRedBaseUrl()+"/c/p/recommend/detail/"+taskInfo.getMissionId());

        return info;

    }

    /**
     * 生成 GreatInfo 高额任务
     *
     * @param mission
     * @param status 状态 0-显示金额 2-已结束
     * @return
     */
    public GreatInfo missionInfo(ReRecommendMission mission, int status) {

        GreatInfo info = new GreatInfo();

        info.setTitle(mission.getMissionTitle());
        info.setImg(mission.getMissionIcon());

        List<String> labelList = new ArrayList<>();
        String missionLabel = mission.getMissionLabel();
        if(!StringUtil.isEmpty(missionLabel)) {
            String[] labels = missionLabel.split(",");
            for (String label :labels) {
                labelList.add(label);
            }
        }
        info.setLabel(labelList);

        if(status == 0) {
            info.setDesc(""+mission.getMinMoney());
        }else if(status == 2) {
            info.setDesc("已结束");
        }

        info.setStatus(status);
        info.setUrl(Config.getRedBaseUrl()+"/c/p/recommend/detail/"+mission.getMissionId());

        return info;

    }


    /**
     * 查询关注任务 个数 金额
     *
     * @param platform
     * @param userId
     * @return
     */
    public MoneyAndCountInfo selectAttention(int platform, Integer userId) {

        MoneyAndCountInfo info = new MoneyAndCountInfo();

        String today = CommonMethod.fmtDay(System.currentTimeMillis());

        // 查询可做列表
        List<ReRecommendMission> underwayList = reRecommendMissionDAO.selectUnderwayAttentionList(platform, userId, today);

        int count = 0;
        BigDecimal money = new BigDecimal("0.00");

        if(underwayList != null) {
            count = underwayList.size();
            for (ReRecommendMission mission : underwayList) {
                money = money.add(mission.getMinMoney());
            }
        }

        if(count == 0) {
            info.setCount("");
            info.setMoney("关注领红包");
        }else {
            info.setCount("" + count);
            info.setMoney("+" + money + "元");
        }

        return info;

    }

    /**
     * 查询高额任务 个数 金额
     *
     * @param platform
     * @param userId
     * @return
     */
    public MoneyAndCountInfo selectGreat(int platform, Integer userId) {

        MoneyAndCountInfo info = new MoneyAndCountInfo();

        String today = CommonMethod.fmtDay(System.currentTimeMillis());

        // 查询可做列表
        List<ReRecommendMission> underwayList = reRecommendMissionDAO.selectUnderwayGreatList(platform, userId, today);

        int count = 0;
        BigDecimal money = new BigDecimal("0.00");

        if(underwayList != null) {
            count = underwayList.size();
            for (ReRecommendMission mission : underwayList) {
                money = money.add(mission.getMinMoney());
            }
        }

        if(count == 0) {
            info.setMoney("高额返现");
            info.setCount("");
        }else {
            info.setMoney("+" + money + "元");
            info.setCount("" + count);
        }

        return info;

    }

}
