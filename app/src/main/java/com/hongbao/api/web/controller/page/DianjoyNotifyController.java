package com.hongbao.api.web.controller.page;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.service.wall.DianjoyService;
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
@RequestMapping(value = "/page/dianjoy", produces = RequestConsts.CONTENT_TYPE_JSON, method = {RequestMethod.POST, RequestMethod.GET})
@Controller
public class DianjoyNotifyController {

    @Autowired
    private DianjoyService dianjoyService;

    /**
     * dianjoy 回调
     *
     *
     * snuid        开发者给用户分配的userid(仅限数字、英文，不超过100字符。*切勿使用中文*)
     *
     * device_id    设备号，手机唯一，一个手机对应一个账户
     *
     * app_id       在点乐的应用所对应的DIANLE_APP_ID值
     *
     * currency     积分，注意：不是钱
     *
     * app_ratio    汇率：1分钱=多少积分(>=1)
     *
     * time_stamp   时间戳
     *
     * ad_name      广告名
     *
     * pack_name    包名
     *
     * token        验证密文，其计算方法为：time_stamp（是服务器当前时间戳，毫秒）的值和前面提到的secret_key连接成字符串，
                    然后MD5加密，即：token = MD5(time_stamp + secret_key)。

     * task_id      深度任务的唯一标识符
                    32位的16进制码
                    当trade_type=1时，不出现此字段

     * trade_type   表示广告任务的类型 1-安装激活任务 4-次日打开深度任务
                    注意：同一广告可产生多次任务，并产生更多收入
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/notify.php")
    public void notify(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("utf-8");

        String secret_key = "xinrqErvFsa4beMnAy826NVgTfVNkU8K";

        String userId = request.getParameter("snuid");
        String device_id = request.getParameter("device_id");
        String app_id = request.getParameter("app_id");
        String currency = request.getParameter("currency");
        String app_ratio = request.getParameter("app_ratio");
        String time_stamp = request.getParameter("time_stamp");
        String ad_name = request.getParameter("ad_name");
        String pack_name = request.getParameter("pack_name");
        String task_id = request.getParameter("task_id");
        String trade_type = request.getParameter("trade_type");

        String token = request.getParameter("token");

        // 生成的token
        String trueToken = MD5Encryption.encodeMD5(time_stamp + secret_key);

        if(token.equals(trueToken)) { // 验证合法
            dianjoyService.callback(userId, device_id, app_id, currency, app_ratio,
                    time_stamp, ad_name, pack_name, task_id, trade_type);
            response.getWriter().write("200");
        }else { // 验证参数不匹配
            response.getWriter().write("403");
        }

    }


}
