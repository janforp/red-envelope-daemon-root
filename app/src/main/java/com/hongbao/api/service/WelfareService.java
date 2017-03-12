package com.hongbao.api.service;

import com.hongbao.api.config.Config;
import com.hongbao.api.dao.ReDiscoverBannerDAO;
import com.hongbao.api.dao.ReWelfareDAO;
import com.hongbao.api.dao.ReWelfareTypeDAO;
import com.hongbao.api.model.ReWelfare;
import com.hongbao.api.model.ReWelfareType;
import com.hongbao.api.model.dto.MissionInfo;
import com.hongbao.api.model.dto.ReBannerInfo;
import com.hongbao.api.model.vo.MissionVo;
import com.hongbao.api.model.vo.NavigationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2016/12/12.
 */
@Service
public class WelfareService {

    @Autowired
    private ReWelfareDAO reWelfareDAO;
    @Autowired
    private ReDiscoverBannerDAO reDiscoverBannerDAO;
    @Autowired
    private ReWelfareTypeDAO reWelfareTypeDAO;

    /**
     * 首页福利精选
     * 精选标题为图片
     *
     * @param platform  平台
     * @return
     */
    public MissionVo indexSelection(int platform) {

        List<MissionInfo> data = new ArrayList<>();

        List<ReWelfare> list = reWelfareDAO.selectSelectionListByPlatform(platform);
        for (ReWelfare reWelfare : list) {
            MissionInfo info = new MissionInfo();
            info.setMissionId(reWelfare.getWelfareId());
            info.setMissionIcon(reWelfare.getWelfareIcon());
            info.setMissionTitle(reWelfare.getWelfareTitle());
            info.setMissionDesc(reWelfare.getWelfareReward());
            info.setMerchantIcon(reWelfare.getMerchantIcon());
            info.setMerchantName(reWelfare.getMerchantName());
            info.setParticipantsNum("参与人数: "+reWelfare.getParticipantsNum());
            info.setMissionUrl(Config.getRedBaseUrl()+"/c/p/welfare/detail/"+reWelfare.getWelfareId());
            data.add(info);
        }

        MissionVo vo = new MissionVo();
        vo.setTitle("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/selection.png");
        vo.setData(data);
        return vo;

    }

    /**
     * 福利banner
     * 5.0版本的福利详情页头的banner
     * 使用的table是re_discover_banner
     *
     * @param platform      平台
     * @param version       版本
     * @param packageName   包名
     * @param channelName   渠道
     * @param flag          是否审核通过
     * @return
     */
    public List<ReBannerInfo> bannerList(int platform, String version, String packageName, String channelName, boolean flag) {
        return reDiscoverBannerDAO.selectDiscoverBannerByPlatform(platform, version, packageName, channelName, flag);
    }

    /**
     * 福利导航
     * 福利列表页面的不同福利
     * 类型的入口
     *
     * @param platform
     * @param flag          是否审核通过
     * @return
     */
    public List<NavigationVo> navigationList(int platform, boolean flag) {
        List<NavigationVo> list = new ArrayList<>();
        if(flag) {
            List<ReWelfareType> typeList = reWelfareTypeDAO.selectByPlatform(platform);
            for (ReWelfareType type : typeList) {
                NavigationVo vo = new NavigationVo();
                vo.setNavigationImg(type.getTypeImg());
                vo.setNavigationName(type.getTypeName());
                if(platform == 0) { // ios
                    vo.setNavigationUrl("hongbao://FRWelfareViewController?type="+type.getTypeId()+"&title="+type.getTypeName());
                }else { // android
                    vo.setNavigationUrl("hongbao://WelfareActivity?type="+type.getTypeId()+"&title="+type.getTypeName());
                }
                list.add(vo);
            }
        }
        return list;
    }

    /**
     * 最新福利
     * '最新福利'4个字用图片的形式
     *
     * @param platform
     * @param flag          是否审核通过
     * @return
     */
    public MissionVo selectListByPlatform(int platform, boolean flag) {

        List<MissionInfo> data = new ArrayList<>();

        if(flag) {
            List<ReWelfare> list = reWelfareDAO.selectListByPlatform(platform, null, null);
            for (ReWelfare reWelfare : list) {
                MissionInfo info = new MissionInfo();
                info.setParticipantsNum("参与人数: "+reWelfare.getParticipantsNum());
                info.setMissionId(reWelfare.getWelfareId());
                info.setMissionTitle(reWelfare.getWelfareTitle());
                info.setMissionDesc(reWelfare.getWelfareReward());
                info.setMerchantIcon(reWelfare.getMerchantIcon());
                info.setMerchantName(reWelfare.getMerchantName());
                info.setMissionIcon(reWelfare.getWelfareIcon());
                info.setMissionUrl(Config.getRedBaseUrl()+"/c/p/welfare/detail/"+reWelfare.getWelfareId());
                data.add(info);
            }
        }else {
            List<ReWelfare> list = reWelfareDAO.selectSelectionListByPlatform(platform);
            for (ReWelfare reWelfare : list) {
                MissionInfo info = new MissionInfo();
                info.setMissionId(reWelfare.getWelfareId());
                info.setMissionDesc(reWelfare.getWelfareReward());
                info.setMissionIcon(reWelfare.getWelfareIcon());
                info.setMissionTitle(reWelfare.getWelfareTitle());
                info.setMerchantIcon(reWelfare.getMerchantIcon());
                info.setMerchantName(reWelfare.getMerchantName());
                info.setParticipantsNum("参与人数: "+reWelfare.getParticipantsNum());
                info.setMissionUrl(Config.getRedBaseUrl()+"/c/p/welfare/detail/"+reWelfare.getWelfareId());
                data.add(info);
            }
        }

        MissionVo vo = new MissionVo();
        vo.setData(data);
        vo.setTitle("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/new-welfare.png");
        return vo;

    }


    /**
     * 福利 (分页)
     *
     * @param platform
     * @param welfareId     翻页之前最后一个福利的id
     * @param welfareType   福利的类型
     * @param flag          是否审核通过
     * @return
     */
    public List<MissionInfo> selectListByPlatform(int platform, Long welfareId, Integer welfareType, boolean flag) {

        List<MissionInfo> infoList = new ArrayList<>();

        if(flag) {
            List<ReWelfare> list = reWelfareDAO.selectListByPlatform(platform, welfareId, welfareType);
            for (ReWelfare reWelfare : list) {
                MissionInfo info = new MissionInfo();
                info.setMissionId(reWelfare.getWelfareId());
                info.setMissionTitle(reWelfare.getWelfareTitle());
                info.setMerchantIcon(reWelfare.getMerchantIcon());
                info.setMissionDesc(reWelfare.getWelfareReward());
                info.setMerchantName(reWelfare.getMerchantName());
                info.setMissionIcon(reWelfare.getWelfareIcon());
                info.setParticipantsNum("参与人数: "+reWelfare.getParticipantsNum());
                info.setMissionUrl(Config.getRedBaseUrl()+"/c/p/welfare/detail/"+reWelfare.getWelfareId());
                infoList.add(info);
            }
        }

        return infoList;

    }



}
