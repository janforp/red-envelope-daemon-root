package com.hongbao.api.service;


import com.hongbao.api.config.Config;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.dao.ReRecommendMissionDAO;
import com.hongbao.api.dao.ReRecommendTaskDAO;
import com.hongbao.api.model.dto.*;
import com.hongbao.api.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2016/12/15.
 */
@Service
public class GreatMissionService {

    @Autowired
    private ReRecommendMissionDAO reRecommendMissionDAO;
    @Autowired
    private ReRecommendTaskDAO reRecommendTaskDAO;

    /**
     * 高额任务列表
     *
     * @param platform
     * @param userId
     * @param missionId
     * @return
     */
    public List<MissionInfo> greatList(int platform, Integer userId, Long missionId) {

        String today = CommonMethod.fmtDay(System.currentTimeMillis());

        List<MissionInfo> greatList = new ArrayList<>();

        List<GreatMissionInfo> list = reRecommendMissionDAO.selectGreatMissionList(missionId, userId, platform, today);
        for (GreatMissionInfo info : list) {
            MissionInfo vo = greatInfo(info);
            greatList.add(vo);
        }

        return greatList;

    }


    /**
     * 我的任务列表
     *
     * @param request
     * @param missionType
     * @param missionId
     * @return
     */
    public List<MissionInfo> myMissionList(HttpServletRequest request, Integer missionType, Long missionId) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        int platform = (int) request.getAttribute(RequestConsts.ATTR_PLATFORM);

        List<MissionInfo> greatList = new ArrayList<>();

        List<GreatMissionInfo> list = reRecommendTaskDAO.selectMyMissionList(missionId, userId, platform, missionType);
        for (GreatMissionInfo info : list) {
            MissionInfo vo = greatInfo(info);
            greatList.add(vo);
        }

        return greatList;

    }


    /**
     * 转换成 MissionInfo
     *
     * @param mission
     * @return
     */
    public MissionInfo greatInfo(GreatMissionInfo mission) {

        MissionInfo info = new MissionInfo();

        info.setMissionId(mission.getMissionId());
        info.setMissionIcon(mission.getMissionIcon());
        info.setMissionTitle(mission.getMissionTitle());
        info.setMissionDesc(mission.getMissionReward());
        info.setMerchantIcon(mission.getMerchantIcon());
        info.setMerchantName(mission.getMerchantName());
        info.setParticipantsNum("参与人数: "+mission.getParticipantsNum());

        // 状态; 0-进行中 1-审核中 2-审核通过 3-未通过 4-已结束 5-未领取
        int missionStatus = 5;
        // 状态内容
        String statusContent = "";
        // 状态; 0-进行中 1-审核中 2-审核通过 3-未通过
        Integer taskStatus = mission.getTaskStatus();
        if(taskStatus == null) {
            taskStatus = 5;
        }

        if(taskStatus == 0) {
            missionStatus = 0;
            statusContent = "进行中";
        }else if(taskStatus == 1) {
            missionStatus = 1;
            statusContent = "审核中";
        }else if(taskStatus == 2) {
            missionStatus = 2;
            statusContent = "已通过";
        }else if(taskStatus == 3) {
            missionStatus = 3;
            statusContent = "未通过";
        }else {

            // 进行中  0-否  1-是
            int isEnd = mission.getIsEnd();
            if(isEnd == 0) {
                missionStatus = 4;
                statusContent = "已结束";
            }else {
                // 剩余数量
                int leftNum = mission.getLeftNum();
                if(leftNum == 0) {
                    missionStatus = 4;
                    statusContent = "已结束";
                }else {
                    // 结束时间
                    String now = CommonMethod.fmtDay(System.currentTimeMillis());
                    String endTime = mission.getEndTime();
                    int compare = now.compareTo(endTime);
                    if(compare > 0) {
                        missionStatus = 4;
                        statusContent = "已结束";
                    }

                }
            }

        }

        info.setMissionStatus(missionStatus);
        info.setStatusContent(statusContent);
        info.setMissionUrl(Config.getRedBaseUrl()+"/c/p/recommend/detail/"+mission.getMissionId());

        return info;

    }

}
