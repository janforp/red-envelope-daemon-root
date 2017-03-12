package com.hongbao.api.service.redis;

import com.hongbao.api.consts.CacheConsts;
import com.hongbao.api.dao.ReAccountDetailDAO;
import com.hongbao.api.dao.ReBannerDAO;
import com.hongbao.api.dao.ReDiscoverBannerDAO;
import com.hongbao.api.dao.ReXuanshangBannerDAO;
import com.hongbao.api.enums.MissionSubtype;
import com.hongbao.api.enums.MissionType;
import com.hongbao.api.model.dto.BannerInfo;
import com.hongbao.api.model.dto.IndexRollInfo;
import com.hongbao.api.model.dto.ReBannerInfo;
import com.wujie.common.redis.StringKeyRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Summer on 2017/1/3.
 */
@Service
public class RedisBannerService {

    @Autowired
    private StringKeyRedisTemplate cacheRedisTemplate_banner;
    @Autowired
    private ReBannerDAO reBannerDAO;
    @Autowired
    private ReDiscoverBannerDAO reDiscoverBannerDAO;
    @Autowired
    private ReXuanshangBannerDAO reXuanshangBannerDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;


    /**
     * 缓存首页滚动列表
     *
     * @return
     */
    public List<String> indexRollList() {

        List<String> rollList = cacheRedisTemplate_banner.getObj(CacheConsts.INDEX_ROLL_LIST);

        if(rollList == null || rollList.size() == 0) {

            rollList = new ArrayList<>();

            List<IndexRollInfo> rollInfoList = reAccountDetailDAO.selectIndexRollList();

            for (IndexRollInfo info : rollInfoList) {

                String content = null;

                String nickName = info.getNickname();
                int missionType = info.getMissionType();
                int missionSubtype = info.getMissionSubtype();
                int detailType = info.getDetailType();
                BigDecimal money = info.getMoney();
                if(detailType == 0) { // 支出
                    if(missionType == MissionType.other_mission.val) { // 其他
                        if(missionSubtype == MissionSubtype.zhifubao.val) {
                            content = "恭喜" + nickName + "提现了" + money + "元到支付宝";
                        }else if(missionSubtype == MissionSubtype.huafei.val) {
                            content = "恭喜" + nickName + "充值了" + money + "元话费";
                        }else if(missionSubtype == MissionSubtype.weixin.val) {
                            content = "恭喜" + nickName + "提现了" + money + "元到微信";
                        }
                    }
                }else { // 收入
                    if(missionType == MissionType.other_mission.val) { // 其他
                        if(missionSubtype == MissionSubtype.fix_red.val) {
                            content = "恭喜" + nickName + "领取了定时红包获得" + money + "元现金";
                        }else if(missionSubtype == MissionSubtype.word_red.val) {
                            content = "恭喜" + nickName + "领取了口令红包获得" + money + "元现金";
                        }else if(missionSubtype == MissionSubtype.register.val) {
                            content = "恭喜" + nickName + "完成注册任务获得" + money + "元现金";
                        }else if(missionSubtype == MissionSubtype.newcomer.val) {
                            content = "恭喜" + nickName + "完成新手任务获得" + money + "元现金";
                        }else if(missionSubtype == MissionSubtype.sign.val) {
                            content = "恭喜" + nickName + "连续签到获得" + money + "元现金";
                        }else if(missionSubtype == MissionSubtype.rotate.val) {
                            content = "恭喜" + nickName + "大转盘抽中" + money + "元现金";
                        }
                    }else if (missionType == MissionType.demo_mission.val) { // 试玩任务
                        content = "恭喜" + nickName + "完成试玩任务获得" + money + "元现金";
                    }else if (missionType == MissionType.share_mission.val) { // 分享任务
                        content = "恭喜" + nickName + "完成分享任务获得" + money + "元现金";
                    }else if (missionType == MissionType.attention_mission.val) { // 关注任务
                        content = "恭喜" + nickName + "完成关注任务获得" + money + "元现金";
                    }else if (missionType == MissionType.alliance_mission.val) { // 联盟任务
                        content = "恭喜" + nickName + "完成联盟任务获得" + money + "元现金";
                    }else if (missionType == MissionType.deepness_mission.val) { // 深度任务
                        content = "恭喜" + nickName + "完成深度任务获得" + money + "元现金";
                    }else if (missionType == MissionType.great_mission.val) { // 高额任务
                        content = "恭喜" + nickName + "完成高额任务获得" + money + "元现金";
                    }else if (missionType == MissionType.red_pool.val){
                        content = "恭喜" + nickName + "抢红包获得" + money + "元现金";
                    }else if (missionType == MissionType.reward_mission.val){
                        content = "恭喜" + nickName + "完成悬赏任务获得" + money + "元现金";
                    }else if (missionType == MissionType.android_integral.val){
                        content = "恭喜" + nickName + "完成热门任务任务获得" + money + "元现金";
                    }else if (missionType == MissionType.ios_high_reward.val){
                        content = "恭喜" + nickName + "完成热门任务任务获得" + money + "元现金";
                    }
                }

                if(content != null) {
                    rollList.add(content);
                }

            }

            // 设置缓存 10分钟
            updateRoll(CacheConsts.INDEX_ROLL_LIST, rollList);

        }

        return rollList;
    }


    /**
     * rollList 设置缓存 10分钟
     */
    public void updateRoll(String key, List<String> rollList) {
        cacheRedisTemplate_banner.setex(key, rollList, CacheConsts.SECONDS_OF_TEN_MINUTE);
    }


    /**
     * 查询首页banner
     *
     * @param platform
     * @param flag
     * @return
     */
    public List<ReBannerInfo> selectIndexBanner(int platform, boolean flag, int versioncode, String packageName, String channelName) {

        List<BannerInfo> bannerList = cacheRedisTemplate_banner.getObj(CacheConsts.INDEX_BANNER);

        if (bannerList == null) {
            // 从数据库查询
            bannerList = reBannerDAO.selectAllOpen();
            if (bannerList != null) {
                this.updateBanner(CacheConsts.INDEX_BANNER, bannerList);
            }
        }

        List<ReBannerInfo> list = new ArrayList<>();

        for (BannerInfo index : bannerList) {

            ReBannerInfo info = new ReBannerInfo();

            if(flag){ // 正常状态

                // Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
                int bannerStatus = index.getBannerStatus();
                if(bannerStatus == 2 || bannerStatus == platform) {

                    if(versioncode >= index.getMinVersionCode().intValue() && versioncode <= index.getMaxVersionCode().intValue()) {

                        String limitChannelName = index.getLimitChannelName();
                        String limitPackageName =  index.getLimitPackageName();

                        if("0".equals(limitChannelName) && "0".equals(limitPackageName)) {
                            info.setBannerUrl(index.getBannerUrl());
                            info.setBannerImg(index.getBannerImg());
                            list.add(info);
                        }else if("0".equals(limitChannelName)) {

                            String[] packages = limitPackageName.split(",");
                            List<String> packageList = new ArrayList<>();
                            Collections.addAll(packageList, packages);

                            if(packageList.contains(packageName)) {
                                info.setBannerImg(index.getBannerImg());
                                info.setBannerUrl(index.getBannerUrl());
                                list.add(info);
                            }

                        }else if("0".equals(limitPackageName)) {

                            String[] channels = limitChannelName.split(",");
                            List<String> channelList = new ArrayList<>();
                            Collections.addAll(channelList, channels);

                            if(channelList.contains(channelName)) {
                                info.setBannerImg(index.getBannerImg());
                                info.setBannerUrl(index.getBannerUrl());
                                list.add(info);
                            }


                        }else {

                            String[] packages = limitPackageName.split(",");
                            List<String> packageList = new ArrayList<>();
                            Collections.addAll(packageList, packages);

                            String[] channels = limitChannelName.split(",");
                            List<String> channelList = new ArrayList<>();
                            Collections.addAll(channelList, channels);

                            if(packageList.contains(packageName) && channelList.contains(channelName)) {
                                info.setBannerImg(index.getBannerImg());
                                info.setBannerUrl(index.getBannerUrl());
                                list.add(info);
                            }

                        }

                    }

                }


            }else { // 审核状态

                // 审核状态是否显示（默认为0） 0-不显示；1-显示
                int isShow = index.getIsShow();
                // Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
                int bannerStatus = index.getBannerStatus();
                if(isShow == 1 && (bannerStatus == 2 || bannerStatus == platform)) {
                    info.setBannerImg(index.getBannerImg());
                    info.setBannerUrl(index.getBannerUrl());
                    list.add(info);
                }

            }

        }

        return list;

    }


    /**
     * 查询福利banner
     *
     * @param platform
     * @param flag
     * @return
     */
    public List<ReBannerInfo> selectWekfareBanner(int platform, boolean flag, int versioncode, String packageName, String channelName) {

        List<BannerInfo> bannerList = cacheRedisTemplate_banner.getObj(CacheConsts.WELFARE_BANNER);

        if (bannerList == null) {
            // 从数据库查询
            bannerList = reDiscoverBannerDAO.selectAllOpen();
            if (bannerList != null) {
                this.updateBanner(CacheConsts.WELFARE_BANNER, bannerList);
            }
        }

        List<ReBannerInfo> list = new ArrayList<>();

        for (BannerInfo index : bannerList) {

            ReBannerInfo info = new ReBannerInfo();

            if(flag){ // 正常状态

                // Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
                int bannerStatus = index.getBannerStatus();
                if(bannerStatus == 2 || bannerStatus == platform) {

                    if(versioncode >= index.getMinVersionCode().intValue() && versioncode <= index.getMaxVersionCode().intValue()) {

                        String limitChannelName = index.getLimitChannelName();
                        String limitPackageName =  index.getLimitPackageName();

                        if("0".equals(limitChannelName) && "0".equals(limitPackageName)) {
                            info.setBannerImg(index.getBannerImg());
                            info.setBannerUrl(index.getBannerUrl());
                            list.add(info);
                        }else if("0".equals(limitChannelName)) {

                            String[] packages = limitPackageName.split(",");
                            List<String> packageList = new ArrayList<>();
                            Collections.addAll(packageList, packages);

                            if(packageList.contains(packageName)) {
                                info.setBannerImg(index.getBannerImg());
                                info.setBannerUrl(index.getBannerUrl());
                                list.add(info);
                            }

                        }else if("0".equals(limitPackageName)) {

                            String[] channels = limitChannelName.split(",");
                            List<String> channelList = new ArrayList<>();
                            Collections.addAll(channelList, channels);

                            if(channelList.contains(channelName)) {
                                info.setBannerImg(index.getBannerImg());
                                info.setBannerUrl(index.getBannerUrl());
                                list.add(info);
                            }


                        }else {

                            String[] packages = limitPackageName.split(",");
                            List<String> packageList = new ArrayList<>();
                            Collections.addAll(packageList, packages);

                            String[] channels = limitChannelName.split(",");
                            List<String> channelList = new ArrayList<>();
                            Collections.addAll(channelList, channels);

                            if(packageList.contains(packageName) && channelList.contains(channelName)) {
                                info.setBannerUrl(index.getBannerUrl());
                                info.setBannerImg(index.getBannerImg());
                                list.add(info);
                            }

                        }

                    }

                }


            }else { // 审核状态

                // 审核状态是否显示（默认为0） 0-不显示；1-显示
                int isShow = index.getIsShow();
                // Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
                int bannerStatus = index.getBannerStatus();
                if(isShow == 1 && (bannerStatus == 2 || bannerStatus == platform)) {
                    info.setBannerUrl(index.getBannerUrl());
                    info.setBannerImg(index.getBannerImg());
                    list.add(info);
                }

            }

        }

        return list;

    }


    /**
     * 查询悬赏banner
     *
     * @param platform
     * @param flag
     * @return
     */
    public List<ReBannerInfo> selectXuanshangBanner(int platform, boolean flag, int versioncode, String packageName, String channelName) {

        List<BannerInfo> bannerList = cacheRedisTemplate_banner.getObj(CacheConsts.XS_BANNER);

        if (bannerList == null) {
            // 从数据库查询
            bannerList = reXuanshangBannerDAO.selectAllOpen();
            if (bannerList != null) {
                this.updateBanner(CacheConsts.XS_BANNER, bannerList);
            }
        }

        List<ReBannerInfo> list = new ArrayList<>();

        for (BannerInfo index : bannerList) {

            ReBannerInfo info = new ReBannerInfo();

            if(flag){ // 正常状态

                // Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
                int bannerStatus = index.getBannerStatus();
                if(bannerStatus == 2 || bannerStatus == platform) {

                    if(versioncode >= index.getMinVersionCode().intValue() && versioncode <= index.getMaxVersionCode().intValue()) {

                        String limitChannelName = index.getLimitChannelName();
                        String limitPackageName =  index.getLimitPackageName();

                        if("0".equals(limitChannelName) && "0".equals(limitPackageName)) {
                            info.setBannerUrl(index.getBannerUrl());
                            info.setBannerImg(index.getBannerImg());
                            list.add(info);
                        }else if("0".equals(limitChannelName)) {

                            String[] packages = limitPackageName.split(",");
                            List<String> packageList = new ArrayList<>();
                            Collections.addAll(packageList, packages);

                            if(packageList.contains(packageName)) {
                                info.setBannerImg(index.getBannerImg());
                                info.setBannerUrl(index.getBannerUrl());
                                list.add(info);
                            }

                        }else if("0".equals(limitPackageName)) {

                            String[] channels = limitChannelName.split(",");
                            List<String> channelList = new ArrayList<>();
                            Collections.addAll(channelList, channels);

                            if(channelList.contains(channelName)) {
                                info.setBannerImg(index.getBannerImg());
                                info.setBannerUrl(index.getBannerUrl());
                                list.add(info);
                            }


                        }else {

                            String[] packages = limitPackageName.split(",");
                            List<String> packageList = new ArrayList<>();
                            Collections.addAll(packageList, packages);

                            String[] channels = limitChannelName.split(",");
                            List<String> channelList = new ArrayList<>();
                            Collections.addAll(channelList, channels);

                            if(packageList.contains(packageName) && channelList.contains(channelName)) {
                                info.setBannerImg(index.getBannerImg());
                                info.setBannerUrl(index.getBannerUrl());
                                list.add(info);
                            }

                        }

                    }

                }


            }else { // 审核状态

                // 审核状态是否显示（默认为0） 0-不显示；1-显示
                int isShow = index.getIsShow();
                // Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
                int bannerStatus = index.getBannerStatus();
                if(isShow == 1 && (bannerStatus == 2 || bannerStatus == platform)) {
                    info.setBannerImg(index.getBannerImg());
                    info.setBannerUrl(index.getBannerUrl());
                    list.add(info);
                }

            }

        }

        return list;

    }


    /**
     * 设置banner缓存一个星期
     */
    public void updateBanner(String key, List<BannerInfo> bannerList) {
        cacheRedisTemplate_banner.setex(key, bannerList, CacheConsts.SECONDS_OF_ONE_WEEK);
    }

}
