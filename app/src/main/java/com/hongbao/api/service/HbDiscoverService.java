package com.hongbao.api.service;

import com.hongbao.api.dao.ReDiscoverBannerDAO;
import com.hongbao.api.dao.ReDiscoverDAO;
import com.hongbao.api.dao.ReIntegralWallDAO;
import com.hongbao.api.model.dto.*;
import com.hongbao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 16/8/12.
 */
@Service
public class HbDiscoverService {

    @Autowired
    private ReDiscoverDAO reDiscoverDAO;
    @Autowired
    private ReDiscoverBannerDAO reDiscoverBannerDAO;
    @Autowired
    private ReIntegralWallDAO reIntegralWallDAO;

    /**
     * 发现banner
     *
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @param flag
     * @return
     */
    public List<ReBannerInfo> selectDiscoverBanner(int platform, String version, String packageName, String channelName, boolean flag) {
        return reDiscoverBannerDAO.selectDiscoverBannerByPlatform(platform, version, packageName, channelName, flag);
    }

    /**
     * 发现列表
     *
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @return
     */
    public List<ReDiscoverInfo> selectDiscoverList(int platform, String version, String packageName, String channelName) {
        return reDiscoverDAO.selectDiscoverByPlatform(platform, version, packageName, channelName);
    }

    /**
     * 小游戏
     *
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @return
     */
    public ReDiscoverModel selectDiscover(int platform, String version, String packageName, String channelName) {

        List<ReDiscoverInfo> selectDiscoverList = reDiscoverDAO.selectDiscoverByPlatform(platform, version, packageName, channelName);

        ReDiscoverModel model = new ReDiscoverModel();
        model.setOrderId(0);
        model.setTitle("小游戏");
        model.setDesc("更多精彩敬请期待");
        model.setList(selectDiscoverList);

        return model;
    }

    /**
     * 任务中心
     *
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @param flag
     * @return
     */
    public List<ReIntegralWallInfo> selectWallList(int platform, String version, String packageName, String channelName, boolean flag) {

        List<ReIntegralWallInfo> list = new ArrayList<>();

        if(flag) {
            list = reIntegralWallDAO.selectWallListByPlatform(platform, version, packageName, channelName);
        }

        return list;

    }


    /**
     * 任务中心
     *
     * 版本 3.0.0
     *
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @param flag
     * @return
     */
    public List<ReIntegralWallInfo3> selectWallList3(int platform, String version, String packageName, String channelName, boolean flag) {

        List<ReIntegralWallInfo3> infoList = new ArrayList<>();

        if(flag) {

            List<ReIntegralWallInfo> list = reIntegralWallDAO.selectWallListByPlatform(platform, version, packageName, channelName);

            for (ReIntegralWallInfo wallInfo : list) {

                ReIntegralWallInfo3 info = new ReIntegralWallInfo3();
                info.setWallTitle(wallInfo.getWallTitle());
                info.setWallImg(wallInfo.getWallImg());
                info.setWallUrl(wallInfo.getWallUrl());

                List<String> labelList = new ArrayList<>();
                String wallLabels = wallInfo.getWallDesc();
                if(!StringUtil.isEmpty(wallLabels)){
                    String[] labels = wallLabels.split(",");
                    for (String label : labels) {
                        labelList.add(label);
                    }
                }

                info.setWallLabel(labelList);

                infoList.add(info);

            }

        }

        return infoList;

    }


    /**
     * 任务中心
     *
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @param flag
     * @return
     */
    public ReIntegralWallModel selectWallModel(int platform, String version, String packageName, String channelName, boolean flag) {

        ReIntegralWallModel wallModel = new ReIntegralWallModel();
        wallModel.setOrderId(1);
        wallModel.setTitle("联盟任务");
        wallModel.setDesc("任务已更新");

        List<ReIntegralWallInfo3> infoList = new ArrayList<>();

        if(flag) {

            List<ReIntegralWallInfo> list = reIntegralWallDAO.selectWallListByPlatform(platform, version, packageName, channelName);

            for (ReIntegralWallInfo wallInfo : list) {

                List<String> labelList = new ArrayList<>();
                String wallLabels = wallInfo.getWallDesc();
                if(!StringUtil.isEmpty(wallLabels)){
                    String[] labels = wallLabels.split(",");
                    for (String label : labels) {
                        labelList.add(label);
                    }
                }

                ReIntegralWallInfo3 info = new ReIntegralWallInfo3();
                info.setWallTitle(wallInfo.getWallTitle());
                info.setWallImg(wallInfo.getWallImg());
                info.setWallUrl(wallInfo.getWallUrl());
                info.setWallLabel(labelList);

                infoList.add(info);

            }

        }

        wallModel.setList(infoList);

        return wallModel;

    }

}
