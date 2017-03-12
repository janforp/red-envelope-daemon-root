package com.hongbao.api.web.controller.page;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.service.wall.DaTouNiaoService;
import com.hongbao.api.util.DaTouNiaoSign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Summer on 2016/9/27.
 */
@RequestMapping(value = "/page/datouniao", produces = RequestConsts.CONTENT_TYPE_JSON, method = {RequestMethod.POST, RequestMethod.GET})
@Controller
public class DaTouNiaoNotifyController {

    @Autowired
    private DaTouNiaoService daTouNiaoService;

    /**
     * 大头鸟积分墙 回调
     *
     *
     * app_id       应用 ID
     *
     * udid         设备识别码(IMEI)
     *
     * userID       用户 ID
     *
     * clientParams 客户端自定义参数，可用于回调服务器传输参数用
     *
     * currency     充值的虚拟币金额(游戏币)
     *
     * orderID      唯一订单号
     *
     * verifier     验证串，appid, currency, orderID, secretKey, timestamp, udid, userID 几个参数连接起来进行
                    SHA256 加密后的结果: 参 数连接顺序按照以下排序:
                    verifier=SHA256(appid+currency+orderID+clientParams+Secret Key+timestamp+udid+userID)
                    SecretKey:为开发者应用与广 告平台约定的通信密钥，不在通信的过程中传输，只在对比 verifier 时使用，
                    可在 www.datouniao.com 网站上“我是开 发者”页面中自定义具体的值。
     *
     * timestamp    时间戳
     *
     * adname       激活产品名称
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/notify.php")
    @ResponseBody
    public int notify(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("utf-8");

        String secretKey = "8JtCHzz8ui";

        String app_id = request.getParameter("app_id");
        String udid = request.getParameter("udid");
        String userID = request.getParameter("userID");
        String clientParams = request.getParameter("clientParams");
        String currency = request.getParameter("currency");
        String orderID = request.getParameter("orderID");
        String verifier = request.getParameter("verifier");
        String timestamp = request.getParameter("timestamp");
        String adname = request.getParameter("adname");

        // 生成签名
        String sign = DaTouNiaoSign.sign(app_id + currency + orderID + clientParams + secretKey +
                timestamp + udid + userID);

        if(verifier.equals(sign)) { // 验证合法
            System.out.println("sign--success");
            daTouNiaoService.callback(app_id, udid, Integer.valueOf(userID), Double.valueOf(currency), orderID, adname);
            return 1;
        }else { // 验证参数不匹配
            System.out.println("sign--error");
        }

        return 0;

    }

}
