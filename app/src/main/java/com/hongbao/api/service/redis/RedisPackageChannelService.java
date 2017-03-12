package com.hongbao.api.service.redis;

import com.hongbao.api.consts.CacheConsts;
import com.hongbao.api.dao.RePackageChannelDAO;
import com.hongbao.api.dao.RePackageChannelUpdateDAO;
import com.hongbao.api.model.RePackageChannel;
import com.hongbao.api.model.RePackageChannelUpdate;
import com.hongbao.api.model.dto.PackageChannelInfo;
import com.wujie.common.redis.StringKeyRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Summer on 2017/1/3.
 */
@Service
public class RedisPackageChannelService {

    @Autowired
    private StringKeyRedisTemplate cacheRedisTemplate_version;
    @Autowired
    private RePackageChannelDAO rePackageChannelDAO;
    @Autowired
    private RePackageChannelUpdateDAO rePackageChannelUpdateDAO;


    /**
     * 获取最近版本状态
     *
     * @param channelName
     * @param packageName
     * @return
     */
    public PackageChannelInfo getPackageChannel(String channelName, String packageName) {
        String key = channelName + "-" + packageName;
        PackageChannelInfo info = cacheRedisTemplate_version.getObj(key);
        if(info == null) {
            // 从数据库查询
            RePackageChannel rePackageChannel = rePackageChannelDAO.selectByPrimaryKey(channelName, packageName);
            if(rePackageChannel != null) {
                info = new PackageChannelInfo();
                info.setVersionCode(rePackageChannel.getVersionCode());
                info.setStatus(rePackageChannel.getStatus());
                // 设置缓存
                updatePackageChannel(key, info);
            }
        }
        return info;
    }


    /**
     * 获取该设备的状态
     *
     * @param deviceId
     * @param channelName
     * @param packageName
     * @return
     */
    public String deviceStatus(String deviceId, String channelName, String packageName, int appVersion) {

        String key = channelName + "-" + deviceId;

        String status = cacheRedisTemplate_version.get(key);
        if(status == null) {

            // 判断app来源，是否是强制更新的
            RePackageChannelUpdate rePackageChannelUpdate = rePackageChannelUpdateDAO.selectByPrimaryKey(deviceId, channelName, packageName);
            if(rePackageChannelUpdate == null) { // 应用市场
                status = "0";
            }else {
                int version = rePackageChannelUpdate.getVersionCode();
                if(version != appVersion) { // 非app内部推送版本
                    status = "0";
                }else {
                    status = "1";
                }
            }

        }

        return status;

    }

    /**
     * 设置设备状态缓存一天
     */
    public void updateDeviceStatus(String key, String status) {
        cacheRedisTemplate_version.setex(key, status, CacheConsts.SECONDS_OF_ONE_DAY);
    }

    /**
     * 设置PackageChannel缓存一个星期
     */
    public void updatePackageChannel(String key, PackageChannelInfo info) {
        cacheRedisTemplate_version.setex(key, info, CacheConsts.SECONDS_OF_ONE_WEEK);
    }

    /**
     * 删除PackageChannel缓存
     */
    public void deletePackageChannel(String key) {
        cacheRedisTemplate_version.delete(key);
    }

}
