package com.hongbao.api.web.controller.page;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.service.wall.YMService;
import com.hongbao.api.util.MD5Encryption;
import com.hongbao.api.util.YoumiSign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.HashMap;

/**
 * Created by Summer on 2016/9/27.
 */
@RequestMapping(value = "/page/ym", produces = RequestConsts.CONTENT_TYPE_JSON, method = {RequestMethod.POST, RequestMethod.GET})
@Controller
public class YMNotifyController {

    @Autowired
    private YMService ymService;

    /**
     *  有米 andriod回调
     *
     *  order       string  订单ID：该值是唯一的，如果开发者接收到相同的订单号，那说明该订单已经存在。
     *  app         string  开发者应用ID
     *  ad          string  广告名，如果是应用类型的广告则是应用名 注:参数经过urlencode，请使用urldecode获取原始参数
     *  user        string  用户ID
     *  chn         int     渠道号
     *  points      int     用户可以赚取的积分
                            1. 如果该值为0，则表示可能用户因为某些情况拿不到积分，例如用户当天激活的次数超过最大的限制值（会和开发者进行结算，但是不会给用户积分）
                            2. 若 trade_type 为3或4的时候，points 积分字段将为 float 浮点型
     *  sig         string  参数签名
     *  adid        int     广告id
     *  pkg         String  应用包名
     *  device      string  设备ID：android是imei
     *  time        int     产生效果的时间
     *  price       float   应用可以获得的收入
     *  trade_type  int     回调的任务类型。1=>主任务；2=>附加任务(附加任务可能会有多个)；3=>分享主任务；4=>深度分享任务
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/andriod.php")
    public void andriodNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("utf-8");

        String dev_server_secret = "3c4a3682680e1a02";

        String order = request.getParameter("order");
        String app = request.getParameter("app");
        String ad = request.getParameter("ad");
        String user = request.getParameter("user");
        String chn = request.getParameter("chn");
        String points = request.getParameter("points");
        String sig = request.getParameter("sig");
        String adid = request.getParameter("adid");
        String pkg = request.getParameter("pkg");
        String device = request.getParameter("device");
        String time = request.getParameter("time");
        String price = request.getParameter("price");
        String trade_type = request.getParameter("trade_type");

        ad = URLDecoder.decode(ad);
        user = URLDecoder.decode(user);
        device = URLDecoder.decode(device);

        // 生成签名
        String sign = MD5Encryption.encodeMD5(dev_server_secret + "||" + order + "||" + app + "||" + user + "||" + chn + "||" + ad + "||" + points).substring(12, 20);

        if(sig.equals(sign)) { // 验证合法
            System.out.println("sign--success");
            ymService.androidCallback(order, app, ad, user, chn, points, adid, pkg, device, price, trade_type);
            response.getWriter().write("200");
        }else { // 验证参数不匹配
            System.out.println("sign--error");
            response.getWriter().write("403");
        }

    }

    /**
     * 有米 ios回调
     *
     *  order       string  订单ID：该值是唯一的，如果开发者接收到相同的订单号，那说明该订单已经存在。
     *  app         string  开发者应用ID
     *  ad          string  广告名，如果是应用类型的广告则是应用名 注:参数经过urlencode，请使用urldecode获取原始参数
     *  adid        int     广告的编号ID
     *  user        string  用户ID
     *  device      string  设备ID：iOS是MAC地址
     *  chn         int     渠道号
     *  price       float   开发者获得的收入
     *  points      int     用户可以赚取的积分
     *  time        int     有米创建订单的时间
     *  storeid     int     应用商店的 Id，该值在某些任务类型可能为空
     *  sign        string  签名
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/ios.php")
    public void iosNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("utf-8");

        String dev_server_secret = "69a3eb2676317a76";

        String order = request.getParameter("order");
        String app = request.getParameter("app");
        String ad = request.getParameter("ad");
        String adid = request.getParameter("adid");
        String user = request.getParameter("user");
        String device = request.getParameter("device");
        String chn = request.getParameter("chn");
        String price = request.getParameter("price");
        String points = request.getParameter("points");
        String time = request.getParameter("time");
        String storeid = request.getParameter("storeid");
        String sig = request.getParameter("sig");
        String sign = request.getParameter("sign");

//        String order = "YM140927--uPMAL-c7";
//        String app = "9076333dcfc7f490";
//        String ad = "去哪儿攻略";
//        String adid = "4188";
//        String user = "1067748";
//        String device = "0AD80C3C-D320-AC2B-5FD3-994E2FA7A153";
//        String chn = "0";
//        String price = "1.96";
//        String points = "979";
//        String time = "1411751092";
//        String storeid = "555610791";
//        String sig = "8ef41e70";
//        String sign = "";


        HashMap<String, String> map = new HashMap<>();
        map.put("order", order);
        map.put("app", app);
        map.put("ad", ad);
        map.put("adid", adid);
        map.put("user", user);
        map.put("device", device);
        map.put("chn", chn);
        map.put("price", price);
        map.put("points", points);
        map.put("time", time);
        map.put("storeid", storeid);
        map.put("sig", sig);

        // 生成签名
        String token = YoumiSign.getSignature(map, dev_server_secret);

        if(token.equals(sign)) { // 验证合法
            System.out.println("sign--success");
            ymService.iosCallback(order, app, ad, adid, user, device, chn, price, points, storeid);
            response.getWriter().write("200");
        }else { // 验证参数不匹配
            System.out.println("sign--error");
            response.getWriter().write("403");
        }

    }


}
