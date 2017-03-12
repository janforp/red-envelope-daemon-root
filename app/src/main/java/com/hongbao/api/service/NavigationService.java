package com.hongbao.api.service;

import com.hongbao.api.config.Config;
import com.hongbao.api.dao.ReIndexMissionNavigationDAO;
import com.hongbao.api.model.ReIndexMissionNavigation;
import com.hongbao.api.model.vo.MissionNavigationVo;
import com.hongbao.api.model.vo.NavigationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2016/12/10.
 */
@Service
public class NavigationService {

    @Autowired
    private ReIndexMissionNavigationDAO reIndexMissionNavigationDAO;

    /**
     * 入口导航
     * @return
     */
    public List<NavigationVo> indexNavigationList(int platform) {

        List<NavigationVo> list = new ArrayList<>();

        // 定时红包
        NavigationVo fixed = new NavigationVo(
                "定时红包",
                "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/fixed.png",
                Config.getRedBaseUrl() + "/page/fixed.html"
        );
        list.add(fixed);

        if(platform == 0) { // ios

            // 口令红包
            NavigationVo password = new NavigationVo(
                    "口令红包",
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/password.png",
                    "hongbao://FRCommandRedViewController"
            );
            list.add(password);

        }else { // andriod

            // 口令红包
            NavigationVo password = new NavigationVo(
                    "口令红包",
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/password.png",
                    "hongbao://CommandRedActivity"
            );
            list.add(password);

        }

        // 签到领奖
        NavigationVo sign = new NavigationVo(
                "签到领奖",
                "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/sign.png",
                Config.getRedBaseUrl()+"/c/p/a/sign/index"
        );
        list.add(sign);

        // 幸运转盘
        NavigationVo luck = new NavigationVo(
                "幸运转盘",
                "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/luck.png",
                Config.getRedBaseUrl() + "/c/p/rotate/index"
        );
        list.add(luck);

        return list;

    }


    /**
     * 入口导航 5.1版本之后
     *
     * @param platform
     * @return
     */
    public List<NavigationVo> indexNavigationList2(int platform, boolean flag) {

        List<NavigationVo> list = new ArrayList<>();

        if(platform == 0) { // ios

            // 定时红包
            NavigationVo fixed = new NavigationVo(
                    "定时红包",
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/fixed.png",
                    "hongbao://FRFixedRedViewController"
            );
            list.add(fixed);

            // 口令红包
            NavigationVo password = new NavigationVo(
                    "口令红包",
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/password.png",
                    "hongbao://FRCommandRedViewController"
            );
            list.add(password);

        }else { // andriod

            // 定时红包
            NavigationVo fixed = new NavigationVo(
                    "定时红包",
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/fixed.png",
                    "hongbao://FixRedActivity"
            );
            list.add(fixed);

            // 口令红包
            NavigationVo password = new NavigationVo(
                    "口令红包",
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/password.png",
                    "hongbao://CommandRedActivity"
            );
            list.add(password);

        }

        // 签到领奖
        NavigationVo sign = new NavigationVo(
                "签到领奖",
                "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/sign.png",
                Config.getRedBaseUrl()+"/c/p/a/sign/index"
        );
        list.add(sign);

        if(flag) {
            // 幸运转盘
            NavigationVo luck = new NavigationVo(
                    "幸运转盘",
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/luck.png",
                    Config.getRedBaseUrl() + "/c/p/rotate/index"
            );
            list.add(luck);
        }else {
            // 邀请好友
            NavigationVo luck = new NavigationVo(
                    "邀请好友",
                    "https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/luck.png",
                    Config.getRedBaseUrl() + "/c/p/invitation/invitationCommissionMenu"
            );
            list.add(luck);
        }

        return list;

    }


    /**
     * 首页任务导航
     *
     * @param flag
     * @param platform
     * @return
     */
    public MissionNavigationVo indexMissionNavigationList(boolean flag, int platform) {

        MissionNavigationVo missionNavigationVo = new MissionNavigationVo();

        if(flag) { // 正常状态

            List<NavigationVo> list = new ArrayList<>();

            List<ReIndexMissionNavigation> navigationList = reIndexMissionNavigationDAO.selectByPlatform(platform);
            for (ReIndexMissionNavigation navigation : navigationList) {
                NavigationVo vo = new NavigationVo();
                vo.setNavigationImg(navigation.getNavigationImg());
                vo.setNavigationName(navigation.getNavigationName());
                vo.setNavigationUrl(navigation.getNavigationUrl());
                list.add(vo);
            }

            int num = list.size();
            if(num % 2 == 1) {
                NavigationVo vo = new NavigationVo();
                vo.setNavigationImg("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/wait.png");
                list.add(vo);
            }

            missionNavigationVo.setTitle("https://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/hongbao-index/mission.png");
            missionNavigationVo.setData(list);

        }

        return missionNavigationVo;
    }





}
