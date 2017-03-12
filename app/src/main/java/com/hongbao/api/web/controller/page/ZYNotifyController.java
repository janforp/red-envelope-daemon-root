package com.hongbao.api.web.controller.page;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.service.wall.ZYService;
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
@RequestMapping(value = "/page/zy", produces = RequestConsts.CONTENT_TYPE_JSON, method = {RequestMethod.POST, RequestMethod.GET})
@Controller
public class ZYNotifyController {

    @Autowired
    private ZYService zyService;

    /**
     * 中亿积分墙 回调
     *
     *
     * order        int     响应id
     *
     * app          int     应用id
     *
     * ad           String  广告标题
     *
     * integral     int     用户可以赚取的积分
     *
     * sign         int     标示
     *
     * adid         int     广告id
     *
     * pkg          String  应用包名
     *
     * device       String  设备ID：imei
     *
     * time         int     产生效果的时间
     *
     * price        float   应用可以获得的收入
     *
     * day          int     回调的任务为第几天任务
     *
     * other        String  该值是在请求过程中传入的预留参数，在此处回调。(一般用于开发者回调自己的用户ID)
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/notify.php")
    public void notify(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("utf-8");

        String dev_server_secret = "366d260a178f92be";

        String order = request.getParameter("order");
        String app = request.getParameter("app");
        String ad = request.getParameter("ad");
        String integral = request.getParameter("integral");
        String sign = request.getParameter("sign");
        String adid = request.getParameter("adid");
        String pkg = request.getParameter("pkg");
        String device = request.getParameter("device");
        String time = request.getParameter("time");
        String price = request.getParameter("price");
        String day = request.getParameter("day");
        String other = request.getParameter("other");


        System.out.println("order>>>>>>>>>>>>"+order);
        System.out.println("app>>>>>>>>>>>>"+app);
        System.out.println("ad>>>>>>>>>>>>"+ad);
        System.out.println("integral>>>>>>>>>>>>"+integral);
        System.out.println("adid>>>>>>>>>>>>"+adid);
        System.out.println("pkg>>>>>>>>>>>>"+pkg);
        System.out.println("device>>>>>>>>>>>>"+device);
        System.out.println("time>>>>>>>>>>>>"+time);
        System.out.println("price>>>>>>>>>>>>"+price);
        System.out.println("day>>>>>>>>>>>>"+day);
        System.out.println("other>>>>>>>>>>>>"+other);


        // 生成签名
        String sig = MD5Encryption.encodeMD5(dev_server_secret + "||" + order + "||" + app + "||" + ad + "||" +
                integral).substring(12, 20);

        if(sig.equals(sign)) { // 验证合法
            System.out.println("sign--success");
            zyService.callback(order, app, ad, integral, adid, pkg, device, price, day, other);
            response.getWriter().write("200");
        }else { // 验证参数不匹配
            System.out.println("sign--error");
            response.getWriter().write("403");
        }

    }


}
