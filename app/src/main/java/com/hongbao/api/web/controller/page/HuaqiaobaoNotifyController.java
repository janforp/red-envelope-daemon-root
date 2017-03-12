package com.hongbao.api.web.controller.page;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.service.business.HuaqiaobaoNotifyService;
import com.hongbao.api.util.MD5Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Summer on 2016/10/31.
 */
@RequestMapping(value = "/hqb/notify", produces = RequestConsts.CONTENT_TYPE_JSON, method = {RequestMethod.POST, RequestMethod.GET})
@Controller
public class HuaqiaobaoNotifyController {

    @Autowired
    private HuaqiaobaoNotifyService huaqiaobaoNotifyService;

    @RequestMapping(value = "/register")
    @ResponseBody
    public int register(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String secretKey = "3YqAEGhQc8";

        String type = request.getParameter("type");
        String uid = request.getParameter("uid");
        String phone = request.getParameter("phone");
        String sign = request.getParameter("sign");

        // 生成签名
        String realSign = MD5Encryption.encodeMD5(type+uid+phone+secretKey);

        if(sign.equals(realSign)) { // 验证合法
            return huaqiaobaoNotifyService.callback(type, uid, phone);
        }else { // 验证参数不匹配
            return 1;
        }

    }

}
