package com.hongbao.api.service;

import com.hongbao.api.dao.RePackageChannelDAO;
import com.hongbao.api.dao.RePackageChannelUpdateDAO;
import com.hongbao.api.model.RePackageChannel;
import com.hongbao.api.model.RePackageChannelUpdate;
import com.hongbao.api.model.dto.PackageChannelInfo;
import com.hongbao.api.service.redis.RedisPackageChannelService;
import com.hongbao.api.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Summer on 16/10/25.
 */
@Service
public class HbPackageChannelService {

    @Autowired
    private RePackageChannelDAO rePackageChannelDAO;
    @Autowired
    private RePackageChannelUpdateDAO rePackageChannelUpdateDAO;
    @Autowired
    private RedisPackageChannelService redisPackageChannelService;


    /**
     * 获取当前请求app的状态 true: 正常  false: 审核中
     *
     * @param packageName
     * @param channelName
     * @param versionCode
     * @param deviceId
     * @return
     */
    public boolean selectAppVersionStatus(String packageName, String channelName, int versionCode, String deviceId) {

        boolean flag = true;

        // 缓存中获取信息
        PackageChannelInfo info = redisPackageChannelService.getPackageChannel(channelName, packageName);

        if(info != null) {
            // 当前app版本号，最新的版本
            int appVersion = info.getVersionCode();
            if(appVersion == versionCode) { // 当前是最新版
                // 判断是否审核通过 0-审核中, 1-已通过, 2-未通过
                int status = info.getStatus();
                // 之前是没有强制更新的，现在有了，所以在市场上未通过，也是有可能被使用的
                if(status != 1) {
                    // 从缓存取这个设备状态
                    String deviceStatus = redisPackageChannelService.deviceStatus(deviceId, channelName, packageName, appVersion);
                    if("0".equals(deviceStatus)) { // 0表示显示审核中
                        flag = false;
                    }
                }
            }
        }

        return flag;

    }


    /**
     * 当前APP请求状态  true: 正常  false: 审核中
     * 3.0版本之前的
     *
     * @param version       该channelName, packageName下的用户的版本
     * @param packageName
     * @param channelName
     * @return
     */
    public boolean getFlag(String version, String packageName, String channelName) {

        boolean flag = true;

        // 查询当前APP状态
        RePackageChannel rePackageChannel = rePackageChannelDAO.selectByPrimaryKey(channelName, packageName);

        if(rePackageChannel != null) {
            // 该channelName, packageName下的最新的版本
            String appVersion = rePackageChannel.getAppVersion();

            // 版本比较
            int compareVersion = CommonMethod.compareVersion(appVersion, version);

            if(compareVersion == 0) { // 当前最新版

                // 判断是否审核通过 0-审核中, 1-已通过, 2-未通过
                int status = rePackageChannel.getStatus();

                if(status != 1) {
                    flag = false;
                }
            }
        }
        return flag;
    }


    /**
     * 当前APP请求状态  true: 正常  false: 审核中
     * 新版本的，加了强制更新之后的
     *
     * @param packageName
     * @param channelName
     * @param versionCode       用户使用的版本／发生请求的app版本，该数字是递增的
     * @param deviceId
     * @return
     */
    public boolean getAppVersionStatus(String packageName, String channelName, int versionCode, String deviceId) {

        boolean flag = true;

        // 查询当前APP状态
        RePackageChannel rePackageChannel = rePackageChannelDAO.selectByPrimaryKey(channelName, packageName);

//        if (rePackageChannel == null){
//            flag = false;
//        }

        if(rePackageChannel != null) {
            // 当前app版本号，最新的版本
            int appVersion = rePackageChannel.getVersionCode();

            if(appVersion == versionCode) { // 当前最新版

                // 判断是否审核通过 0-审核中, 1-已通过, 2-未通过
                int status = rePackageChannel.getStatus();
                // 之前是没有强制更新的，现在有了，所以在市场上未通过，也是有可能被使用的
                if(status != 1) {
                    // 判断app来源，是否是强制更新的
                    RePackageChannelUpdate rePackageChannelUpdate = rePackageChannelUpdateDAO.selectByPrimaryKey(deviceId, channelName, packageName);
                    if(rePackageChannelUpdate == null) { // 应用市场
                        flag = false;
                    }else {
                        int version = rePackageChannelUpdate.getVersionCode();
                        if(version != appVersion) { // 非app内部推送版本
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

}
