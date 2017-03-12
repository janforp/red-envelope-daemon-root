package com.hongbao.api.web.controller.page;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.service.wall.DianCaiService;
import com.hongbao.api.util.MD5Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Summer on 2016/9/27.
 */
@RequestMapping(value = "/page/diancai", produces = RequestConsts.CONTENT_TYPE_JSON, method = {RequestMethod.POST, RequestMethod.GET})
@Controller
public class DianCaiNotifyController {

    @Autowired
    private DianCaiService dianCaiService;


    /**
     * 点财积分墙 回调
     *
     *
     * timestamp    时间戳（是服务器当前时间戳，毫秒）
     *
     * orderId      订单号，每个订单号都具有唯一性；用于排重
     *
     * deviceId     设备号，手机唯一，即手机的IMEI或 MAC地址
     *
     * appId        在点财的应用所对应的APPID值
     *
     * score        虚拟货币数量
     *
     * rate         汇率：1元钱=多少积分(>=1)   注:默认值100 (1元=100积分)
     *
     * tradeType    表示广告任务的类型  0安装激活任务； 1(或大于1)-签到任务
     *
     * adName       广告的名称
     *
     * adPackageName   广告的包名
     *
     * userId       开发者给用户分配的userId
     *
     * token        验证密文，其计算方法为：上述参数（adName除外）连接成字符串，然后MD5加密，即：
                    token=MD5(timestamp+deviceId+appId+score+rate+tradeType+adPackageName+userId+callbackKey);
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/notify.php")
    public void notify(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("utf-8");

        String callbackKey = "iJu8CLDBD3";

        String timestamp = request.getParameter("timestamp");
        String orderId = request.getParameter("orderId");
        String deviceId = request.getParameter("deviceId");
        String appId = request.getParameter("appId");
        String score = request.getParameter("score");
        String rate = request.getParameter("rate");
        String tradeType = request.getParameter("tradeType");
        String adName = request.getParameter("adName");
        String adPackageName = request.getParameter("adPackageName");
        String userId = request.getParameter("userId");
        String token = request.getParameter("token");


        // 生成签名
        String sig = MD5Encryption.encodeMD5(timestamp + deviceId + appId + score + rate + tradeType + adPackageName +
                userId + callbackKey);

        if(sig.equals(token)) { // 验证合法
            System.out.println("sign--success");
            dianCaiService.callback(orderId, deviceId, appId, Double.valueOf(score), Double.valueOf(rate),
                    Integer.valueOf(tradeType), adName, adPackageName, Integer.valueOf(userId));
            response.getWriter().write("200");
        }else { // 验证参数不匹配
            System.out.println("sign--error");
            response.getWriter().write("403");
        }

    }


}
