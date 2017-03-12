package com.hongbao.api.web.controller.page;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.service.wall.BDService;
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
@RequestMapping(value = "/page/bd", produces = RequestConsts.CONTENT_TYPE_JSON, method = {RequestMethod.POST, RequestMethod.GET})
@Controller
public class BDNotifyController {

    @Autowired
    private BDService bdService;

    /**
     * 贝多积分墙 回调
     *
     *
     * userid       String  开发者给用户分配的userid
     *
     * device_id    String  设备号，手机唯一，即手机的IMEI或 MAC地址
     *
     * app_id       int     在贝多的应用所对应的APP_ID值
     *
     * currency     double  虚拟货币数量，注意：不是钱
     *
     * ratio        double  汇率：1元钱=多少积分(>=1)   注:默认值100
     *
     * time_stamp   long    时间戳
     *
     * ad_name      String  广告的名称
     *
     * ad_packname  String  应用包名
     *
     * token        String  验证密文，其计算方法为：time_stamp（是服务器当前时间戳，毫秒）的值和前面提到的secret_key连接成字符串，
                            然后MD5加密，即：token = MD5(time_stamp + secret_key)。
     *
     * trade_type   int     表示广告任务的类型  0-安装激活任务； 1(或大于1)-签到任务
                            注意：同一广告由于签到奖励可产生多次任务，并产生更多收入
     *
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/notify.php")
    public void notify(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("utf-8");

        String secret_key = "KQkqri7xJf6y";

        String userid = request.getParameter("userid");
        String device_id = request.getParameter("device_id");
        String app_id = request.getParameter("app_id");
        String currency = request.getParameter("currency");
        String ratio = request.getParameter("ratio");
        String time_stamp = request.getParameter("time_stamp");
        String ad_name = request.getParameter("ad_name");
        String ad_packname = request.getParameter("ad_packname");
        String token = request.getParameter("token");
        String trade_type = request.getParameter("trade_type");


        System.out.println("userid>>>>>>>>>>>>"+userid);
        System.out.println("device_id>>>>>>>>>>>>"+device_id);
        System.out.println("app_id>>>>>>>>>>>>"+app_id);
        System.out.println("currency>>>>>>>>>>>>"+currency);
        System.out.println("ratio>>>>>>>>>>>>"+ratio);
        System.out.println("time_stamp>>>>>>>>>>>>"+time_stamp);
        System.out.println("ad_name>>>>>>>>>>>>"+ad_name);
        System.out.println("ad_packname>>>>>>>>>>>>"+ad_packname);
        System.out.println("token>>>>>>>>>>>>"+token);
        System.out.println("trade_type>>>>>>>>>>>>"+trade_type);


        // 生成签名
        String sign = MD5Encryption.encodeMD5(time_stamp + secret_key);

        if(token.equals(sign)) { // 验证合法
            System.out.println("sign--success");
            bdService.callback(userid, device_id, app_id, Double.valueOf(currency), Double.valueOf(ratio),
                    Long.valueOf(time_stamp), ad_name, ad_packname, Integer.valueOf(trade_type));
            response.getWriter().write("200");
        }else { // 验证参数不匹配
            System.out.println("sign--error");
            response.getWriter().write("403");
        }

    }


}
