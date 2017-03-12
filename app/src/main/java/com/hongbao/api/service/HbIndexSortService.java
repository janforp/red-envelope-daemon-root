package com.hongbao.api.service;

import com.hongbao.api.config.Config;
import com.hongbao.api.dao.ReCodeRedDAO;
import com.hongbao.api.dao.ReCustomerExtendDAO;
import com.hongbao.api.dao.ReIndexSortDAO;
import com.hongbao.api.dao.ReMissionDAO;
import com.hongbao.api.enums.HbIndexSort;
import com.hongbao.api.model.ReCodeRed;
import com.hongbao.api.model.ReIndexSort;
import com.hongbao.api.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 16/9/18.
 */
@Service
public class HbIndexSortService {

    @Autowired
    private ReIndexSortDAO reIndexSortDAO;
    @Autowired
    private ReCodeRedDAO reCodeRedDAO;
    @Autowired
    private ReCustomerExtendDAO reCustomerExtendDAO;
    @Autowired
    private ReMissionDAO reMissionDAO;

    /**
     * 获取首页数据列表
     * 版本 1.0
     *
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @return
     */
    public List<ReIndexSortInfo> getIndexSortList(int platform, String version, String packageName, String channelName) {

        List<ReIndexSortInfo> sortInfoList = new ArrayList<>();

        // 首页分类
        List<ReIndexSort> sortList = reIndexSortDAO.selectByPlatform(platform, version, packageName, channelName);

        for (ReIndexSort reIndexSort : sortList) {

            ReIndexSortInfo indexSortInfo = new ReIndexSortInfo();
            indexSortInfo.setSortName(reIndexSort.getSortName());

            List<ReCodeRedInfo> infoList = new ArrayList<>();

            String[] desc = reIndexSort.getSortDesc().split(",");
            for (int i = 0; i < desc.length; i++) {

                int type = Integer.parseInt(desc[i]);

                if(HbIndexSort.code_red.val.equals(type)){ // 兑换码红包

                    List<ReCodeRed> redList = reCodeRedDAO.selectCodeRedByPlatform(platform);
                    for (ReCodeRed red : redList) {
                        ReCodeRedInfo info = new ReCodeRedInfo();
                        info.setCustomerImg(red.getCustomerImg());
                        info.setCustomerName(red.getCustomerName());
                        info.setCustomerDesc(red.getCustomerDesc());
                        info.setAwardDesc(red.getAwardDesc());
                        info.setRedUrl(Config.getRedBaseUrl()+"/c/p/codeRed/codeDetail/"+ red.getCodeId());
                        int left = red.getRedNumDayLeft();
                        if(left > 0) {
                            info.setRedStatus(1);
                        }else {
                            info.setRedStatus(0);
                        }
                        infoList.add(info);
                    }

                }else if(HbIndexSort.wx_red.val.equals(type)){ // 微信现金红包

                    List<ReExtendInfo> extendList = reCustomerExtendDAO.selectExtendByPlatform(platform);
                    for (ReExtendInfo extend : extendList) {
                        ReCodeRedInfo info = new ReCodeRedInfo();
                        info.setCustomerImg(extend.getCustomerImg());
                        info.setCustomerName(extend.getCustomerName());
                        info.setCustomerDesc(extend.getExtendDesc());
                        info.setAwardDesc(extend.getAwardDesc());
                        int left = extend.getRedNumDayLeft();
                        if(left > 0) {
                            info.setRedStatus(1);
                        }else {
                            info.setRedStatus(0);
                        }
                        info.setRedUrl(Config.getRedBaseUrl() + "/c/p/extend/extendDetail/" + extend.getId() );
                        infoList.add(info);
                    }

                }else if(HbIndexSort.mission_red.val.equals(type)){ // 任务

                    List<ReMissionInfo> missionList = reMissionDAO.selectByPlatform(platform);
                    for (ReMissionInfo missionInfo : missionList) {
                        ReCodeRedInfo info = new ReCodeRedInfo();
                        info.setCustomerName(missionInfo.getMerchantName());
                        info.setCustomerImg(missionInfo.getMissionImg());
                        info.setAwardDesc(missionInfo.getMissionReward());
                        info.setCustomerDesc(missionInfo.getMerchantDetail());
                        info.setRedStatus(missionInfo.getMissionStatus());
                        info.setRedUrl(Config.getRedBaseUrl() + "/c/p/mission/missionDetail/" + missionInfo.getMissionId());
                        infoList.add(info);
                    }

                }

            }

            if(infoList != null && infoList.size() > 0) {

                indexSortInfo.setList(infoList);
                sortInfoList.add(indexSortInfo);

            }

        }

        return sortInfoList;

    }


    /**
     * 获取首页分类列表
     * 版本 2.0
     *
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @return
     */
    public List<ReIndexSortInfo2> getIndexSortList2(int platform, String version, String packageName, String channelName) {

        List<ReIndexSortInfo2> sortInfoList = new ArrayList<>();

        // 首页分类
        List<ReIndexSort> sortList = reIndexSortDAO.selectByPlatform(platform, version, packageName, channelName);

        int size = 0;

        for (ReIndexSort reIndexSort : sortList) {

            ReIndexSortInfo2 indexSortInfo2 = new ReIndexSortInfo2();
            indexSortInfo2.setSortId(reIndexSort.getSortId());
            indexSortInfo2.setSortImg(reIndexSort.getSortImg());
            indexSortInfo2.setSortName(reIndexSort.getSortName());

            List<ReCodeRedInfo> infoList = new ArrayList<>();

            String[] desc = reIndexSort.getSortDesc().split(",");
            for (int i = 0; i < desc.length; i++) {

                int type = Integer.parseInt(desc[i]);

                if(HbIndexSort.code_red.val.equals(type)){ // 兑换码红包

                    List<ReCodeRed> redList = reCodeRedDAO.selectIndexCodeRedByPlatform(platform);

                    if(redList != null) {
                        size = redList.size();
                    }

                    for (ReCodeRed red : redList) {
                        ReCodeRedInfo info = new ReCodeRedInfo();
                        info.setCustomerName(red.getCustomerName());
                        info.setCustomerDesc(red.getCustomerDesc());
                        info.setAwardDesc(red.getAwardDesc());
                        int left = red.getRedNumDayLeft();
                        if(left > 0) {
                            info.setRedStatus(1);
                        }else {
                            info.setRedStatus(0);
                        }
                        info.setCustomerImg(red.getCustomerImg());
                        info.setRedUrl(Config.getRedBaseUrl()+"/c/p/codeRed/codeDetail/"+ red.getCodeId());
                        infoList.add(info);
                    }

                }else if(HbIndexSort.wx_red.val.equals(type)){ // 微信现金红包

                    if(size < 6) {
                        int pageSize = 6 - size;
                        List<ReExtendInfo> extendList = reCustomerExtendDAO.selectIndexExtendByPlatform(platform, pageSize);
                        for (ReExtendInfo extend : extendList) {
                            ReCodeRedInfo info = new ReCodeRedInfo();
                            info.setCustomerName(extend.getCustomerName());
                            info.setCustomerDesc(extend.getExtendDesc());
                            info.setAwardDesc(extend.getAwardDesc());
                            int left = extend.getRedNumDayLeft();
                            if(left > 0) {
                                info.setRedStatus(1);
                            }else {
                                info.setRedStatus(0);
                            }
                            info.setCustomerImg(extend.getCustomerImg());
                            info.setRedUrl(Config.getRedBaseUrl() + "/c/p/extend/extendDetail/" + extend.getId() );
                            infoList.add(info);
                        }

                    }

                }else if(HbIndexSort.mission_red.val.equals(type)){ // 任务

                    List<ReMissionInfo> missionList = reMissionDAO.selectIndexByPlatform(platform);
                    for (ReMissionInfo missionInfo : missionList) {
                        ReCodeRedInfo info = new ReCodeRedInfo();
                        info.setCustomerName(missionInfo.getMerchantName());
                        info.setCustomerImg(missionInfo.getMissionImg());
                        info.setAwardDesc(missionInfo.getMissionReward());
                        info.setRedStatus(missionInfo.getMissionStatus());
                        info.setRedUrl(Config.getRedBaseUrl() + "/c/p/mission/missionDetail/" + missionInfo.getMissionId());
                        info.setCustomerDesc(missionInfo.getMerchantDetail());
                        infoList.add(info);
                    }

                }

            }

            if(infoList != null && infoList.size() > 0) {
                indexSortInfo2.setList(infoList);
                sortInfoList.add(indexSortInfo2);
            }

        }

        return sortInfoList;

    }

    /**
     * 查询分类数据
     * @param platform
     * @param id
     * @return
     */
    public List<ReCodeRedInfo> getRedList(int platform, int id) {

        List<ReCodeRedInfo> infoList = new ArrayList<>();

        ReIndexSort reIndexSort = reIndexSortDAO.selectByPrimaryKey(id);

        if(reIndexSort != null) {

            String[] desc = reIndexSort.getSortDesc().split(",");

            for (int i = 0; i < desc.length; i++) {

                int type = Integer.parseInt(desc[i]);

                if(HbIndexSort.code_red.val.equals(type)){ // 兑换码红包

                    List<ReCodeRed> redList = reCodeRedDAO.selectCodeRedByPlatform(platform);

                    for (ReCodeRed red : redList) {
                        ReCodeRedInfo info = new ReCodeRedInfo();
                        info.setCustomerImg(red.getCustomerImg());
                        info.setCustomerDesc(red.getCustomerDesc());
                        info.setCustomerName(red.getCustomerName());
                        info.setAwardDesc(red.getAwardDesc());
                        int left = red.getRedNumDayLeft();
                        if(left > 0) {
                            info.setRedStatus(1);
                        }else {
                            info.setRedStatus(0);
                        }
                        info.setRedUrl(Config.getRedBaseUrl()+"/c/p/codeRed/codeDetail/"+ red.getCodeId());
                        infoList.add(info);
                    }

                }else if(HbIndexSort.wx_red.val.equals(type)){ // 微信现金红包

                    List<ReExtendInfo> extendList = reCustomerExtendDAO.selectExtendByPlatform(platform);
                    for (ReExtendInfo extend : extendList) {
                        ReCodeRedInfo info = new ReCodeRedInfo();
                        info.setCustomerName(extend.getCustomerName());
                        info.setCustomerImg(extend.getCustomerImg());
                        info.setCustomerDesc(extend.getExtendDesc());
                        info.setAwardDesc(extend.getAwardDesc());
                        int left = extend.getRedNumDayLeft();
                        if(left > 0) {
                            info.setRedStatus(1);
                        }else {
                            info.setRedStatus(0);
                        }
                        info.setRedUrl(Config.getRedBaseUrl() + "/c/p/extend/extendDetail/" + extend.getId() );
                        infoList.add(info);
                    }

                }else if(HbIndexSort.mission_red.val.equals(type)){ // 任务

                    List<ReMissionInfo> missionList = reMissionDAO.selectByPlatform(platform);
                    for (ReMissionInfo missionInfo : missionList) {
                        ReCodeRedInfo info = new ReCodeRedInfo();
                        info.setCustomerName(missionInfo.getMerchantName());
                        info.setCustomerImg(missionInfo.getMissionImg());
                        info.setCustomerDesc(missionInfo.getMerchantDetail());
                        info.setAwardDesc(missionInfo.getMissionReward());
                        info.setRedStatus(missionInfo.getMissionStatus());
                        info.setRedUrl(Config.getRedBaseUrl() + "/c/p/mission/missionDetail/" + missionInfo.getMissionId());
                        infoList.add(info);
                    }

                }

            }

        }

        return infoList;

    }



}
