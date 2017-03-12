package com.hongbao.api.web.controller.rest.aesfull.v4;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.service.AndriodAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Summer on 16/11/15.
 */
@RequestMapping(value = "/rest/a/f/andriod", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class AndriodAppController {

    @Autowired
    private AndriodAppService andriodAppService;

    /**
     * 保存用户本地app列表
     *
     * @param request
     * @param app
     */
    @RequestMapping(value = "/app")
    @ResponseBody
    public void app(HttpServletRequest request,
                    @RequestParam(value = ParamConsts.app, required = false) String app) {
        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);
        andriodAppService.saveAppData(userId, app);
    }

}
