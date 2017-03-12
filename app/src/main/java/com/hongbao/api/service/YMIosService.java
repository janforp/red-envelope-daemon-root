package com.hongbao.api.service;

import com.hongbao.api.model.dto.YMInfo;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.util.YoumiAES;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Summer on 2016/10/10.
 */
@Service
public class YMIosService {

    /**
     * 有米积分墙链接
     * @param userId
     * @return
     */
    public String getYMUrl(Integer userId, String version) {

        YMInfo info = new YMInfo();

        int compare = CommonMethod.compareVersion(version, "10.0.0");

        if(compare < 0) { // 老版本

            // 对应网站用户标识 UserID
            String userid = userId + "";
            // 预留参数,如果不需要请留空
            String feedback = "";
            // 在有米官网获取的应用 ID
            String appid = "957d4780ba448cfe";
            // 在有米官网获取的应用密钥
            String app_secret = "49a43b7b0b0e69bb";

            // 加密结果
            String encryptResult = null;
            try {
                encryptResult = YoumiAES.encode(appid, app_secret, userid, feedback);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("encryptText: " + encryptResult);

            String url = "http://w.ymapp.com/wx/ios/lists.html?mt=1&r="+encryptResult;

            info.setStatus(1);
            info.setContent(url);

        }else {

            info.setStatus(0);
            info.setContent("此功能暂时只支持ios10以下设备");

        }

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("info", info);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


}
