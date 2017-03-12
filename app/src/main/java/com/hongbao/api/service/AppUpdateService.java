package com.hongbao.api.service;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.dao.RePackageChannelDAO;
import com.hongbao.api.dao.RePackageChannelUpdateDAO;
import com.hongbao.api.model.RePackageChannel;
import com.hongbao.api.model.RePackageChannelUpdate;
import com.hongbao.api.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Summer on 2016/11/11.
 */
@Service
public class AppUpdateService {

    @Autowired
    private RePackageChannelDAO rePackageChannelDAO;
    @Autowired
    private RePackageChannelUpdateDAO rePackageChannelUpdateDAO;

    /**
     * 检查是否有新版本
     *
     * @param request
     * @return
     */
    public RePackageChannel checkUpdate(HttpServletRequest request) {

        // 渠道
        String channelName = (String) request.getAttribute(RequestConsts.HEADER_CHANNEL_NAME);
        // 包名
        String packageName = (String) request.getAttribute(RequestConsts.HEADER_PACKAGE_NAME);
        // 版本号
        String versioncodeString = (String) request.getAttribute(RequestConsts.HEADER_VERSION_CODE);
        int versioncode = Integer.valueOf(versioncodeString);

        // 查询最新版本
        RePackageChannel rePackageChannel = rePackageChannelDAO.selectByPrimaryKey(channelName, packageName);
        int version = rePackageChannel.getVersionCode();

        if(version > versioncode) { // 有新版本

            String time = CommonMethod.fmtTime(System.currentTimeMillis());

            // 设备号
            String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);

            RePackageChannelUpdate update = rePackageChannelUpdateDAO.selectByPrimaryKey(deviceId, channelName, packageName);
            if(update == null) {
                update = new RePackageChannelUpdate();
                update.setDeviceId(deviceId);
                update.setChannelName(channelName);
                update.setPackageName(packageName);
                update.setVersionCode(version);
                update.setPushTime(time);
                rePackageChannelUpdateDAO.insert(update);
            }else {
                update.setVersionCode(version);
                update.setPushTime(time);
                rePackageChannelUpdateDAO.updateByPrimaryKey(update);
            }

        }else {
            rePackageChannel = null;
        }

        return rePackageChannel;

    }


}
