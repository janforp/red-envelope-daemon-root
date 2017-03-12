package com.hongbao.api.web.controller.page;

import com.hongbao.api.config.Config;
import com.hongbao.api.consts.JsonConsts;
import com.hongbao.api.consts.MsgConsts;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.web.logger.ControllerLogger;
import org.craigq.common.logger.ILogger;
import org.craigq.common.logger.LogMgr;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by wuqiang on 15-8-4.
 *
 * @author wuqiang
 */
@RequestMapping(value = "/page/error", produces = "application/json;charset=utf-8",
        method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.TRACE})
@Controller
public class ErrorPageController {

    @RequestMapping(value = "/400.do")
    @ResponseBody
    public String e400(HttpServletRequest request) throws IOException {
        if (Config.isDebug()) {
            ILogger loggerObj = LogMgr.getLogger(this.getClass());
            if (loggerObj != null && loggerObj instanceof ControllerLogger) {
                ControllerLogger logger = (ControllerLogger) loggerObj;
                String businessParameter = logger.getDecryptBusinessParameter();
                if (businessParameter == null || businessParameter.length() == 0) {
                    businessParameter = logger.getBusinessParameter();
                }
                if (businessParameter != null) {
                    return JsonUtil.buildErrorJson(400, MsgConsts.PARAMETERS_ERROR + "; \n参数 : " + businessParameter);
                }
            }
        }
        return JsonConsts.ERROR_400;
    }
}
