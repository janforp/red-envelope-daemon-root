package com.hongbao.api.model.dto;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by Janita on 2016/12/26.
 * request中的参数
 */
public class RequestAttrDto implements Serializable {

    private String packageName;
    private String channelName;
    private String versionCodeString;
    private Integer versionCode;
    private String deviceId;
    private int platform;
    private Integer userId;

    public static RequestAttrDto getAppInfo(HttpServletRequest request){

        RequestAttrDto dto = null;

        if (request != null){

            String packageName = (String) request.getAttribute(RequestConsts.HEADER_PACKAGE_NAME);
            String channelName = (String) request.getAttribute(RequestConsts.HEADER_CHANNEL_NAME);
            String versionCodeString = (String) request.getAttribute(RequestConsts.HEADER_VERSION_CODE);
            Integer versionCode = null;
            if (versionCodeString != null){
                versionCode = Integer.valueOf(versionCodeString);
            }
            int platform = (int) request.getAttribute(RequestConsts.ATTR_PLATFORM);
            String user_id = (String) request.getAttribute(RequestConsts.ATTR_USER_ID);
            Integer userId = null;
            if (!StringUtil.isEmpty(user_id)){
                userId = Integer.valueOf(user_id);
            }
            dto = new RequestAttrDto();

            dto.setPackageName(packageName);
            dto.setChannelName(channelName);
            dto.setVersionCode(versionCode);
            dto.setVersionCodeString(versionCodeString);
            dto.setPlatform(platform);
            dto.setUserId(userId);
        }
        return dto;
    }


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getVersionCodeString() {
        return versionCodeString;
    }

    public void setVersionCodeString(String versionCodeString) {
        this.versionCodeString = versionCodeString;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
