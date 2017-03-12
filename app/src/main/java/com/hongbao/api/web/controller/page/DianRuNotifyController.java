package com.hongbao.api.web.controller.page;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.service.wall.DianRuService;
import com.hongbao.api.util.MD5Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * Created by Summer on 2016/9/27.
 */
@RequestMapping(value = "/page/dianru", produces = RequestConsts.CONTENT_TYPE_JSON, method = {RequestMethod.POST, RequestMethod.GET})
@Controller
public class DianRuNotifyController {

    @Autowired
    private DianRuService dianRuService;

    /**
     * 点入 andriod 积分墙 回调
     *
     *
     * hashid       唯一标识 ID
     *
     * appid        开发者应用 ID
     *
     * adid         广告 ID
     *
     * adname       广告名称(urlencode)
     *
     * userid       开发者设置的用户 ID
     *
     * mac          mac 地址
     *
     * deviceid     设备唯一标识(IMEI)
     *
     * source       渠道来源
     *
     * point        积分
     *
     * time         时间戳
     *
     * appsecret    开发者设置的密钥
     *
     * checksum     签名结果
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/andriod.php")
    @ResponseBody
    public String andriod(HttpServletRequest request) throws Exception {

        request.setCharacterEncoding("utf-8");

        String appsecret = "u4UrTf8gyT";

        String hashid = request.getParameter("hashid");
        String appid = request.getParameter("appid");
        String adid = request.getParameter("adid");
        String adname = request.getParameter("adname");
        String userid = request.getParameter("userid");
        String mac = request.getParameter("mac");
        String deviceid = request.getParameter("deviceid");
        String source = request.getParameter("source");
        String point = request.getParameter("point");
        String time = request.getParameter("time");


        String checksum = request.getParameter("checksum").toUpperCase();

        adname = URLDecoder.decode(adname);

        String parameter = "?hashid="+hashid+"&appid="+appid+"&adid="+adid+"&adname="+adname+"&userid="+userid+"&mac="+
                mac+"&deviceid="+deviceid+"&source="+source+"&point="+point+"&time="+time+"&appsecret="+appsecret;


        // 生成签名
        String sign = MD5Encryption.encodeMD5(parameter).toUpperCase();

        Integer userId = Integer.valueOf(userid);
        Integer score = Integer.valueOf(point);

        String result = "";

        if(checksum.equals(sign)) { // 验证合法
            System.out.println("sign--success");
            dianRuService.andriod_callback(hashid, appid, adid, adname, userId, mac, deviceid, source, score);
            result = "{\"message\":\"成功\", \"success\":\"true\" }";
        }else { // 验证参数不匹配
            System.out.println("sign--error");
            result = "{\"message\":\"失败\", \"success\":\"false\" }";
        }

        return result;

    }



    /**
     * 点入 ios 积分墙 回调
     *
     *
     * hashid       唯一标识 ID
     *
     * appid        开发者应用 ID
     *
     * adid         广告 ID
     *
     * adname       广告名称(urlencode)
     *
     * userid       开发者设置的用户 ID
     *
     * deviceid     设备唯一标识(IMEI)
     *
     * source       渠道来源
     *
     * point        积分
     *
     * time         时间戳
     *
     * appsecret    开发者设置的密钥
     *
     * checksum     签名结果
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/ios.php")
    @ResponseBody
    public String ios(HttpServletRequest request) throws Exception {

        request.setCharacterEncoding("utf-8");

        String appsecret = "u4UrTf8gyT";

        String hashid = request.getParameter("hashid");
        String appid = request.getParameter("appid");
        String adid = request.getParameter("adid");
        String adname = request.getParameter("adname");
        String userid = request.getParameter("userid");
        String deviceid = request.getParameter("deviceid");
        String source = request.getParameter("source");
        String point = request.getParameter("point");
        String time = request.getParameter("time");


        String checksum = request.getParameter("checksum").toUpperCase();

        adname = URLDecoder.decode(adname);

        String parameter = "?hashid="+hashid+"&appid="+appid+"&adid="+adid+"&adname="+adname+"&userid="+userid+
                "&deviceid="+deviceid+"&source="+source+"&point="+point+"&time="+time+"&appsecret="+appsecret;


        // 生成签名
        String sign = MD5Encryption.encodeMD5(parameter).toUpperCase();

        Integer userId = Integer.valueOf(userid);
        Integer score = Integer.valueOf(point);

        String result = "";

        if(checksum.equals(sign)) { // 验证合法
            System.out.println("sign--success");
            dianRuService.ios_callback(hashid, appid, adid, adname, userId, deviceid, source, score);
            result = "{\"message\":\"成功\", \"success\":\"true\" }";
        }else { // 验证参数不匹配
            System.out.println("sign--error");
            result = "{\"message\":\"失败\", \"success\":\"false\" }";
        }

        return result;

    }


}
