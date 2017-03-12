package com.hongbao.api.web.controller.rest.rsa.v4;

import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.RePackageChannel;
import com.hongbao.api.service.AppUpdateService;
import com.hongbao.api.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Summer on 2016/11/11.
 */
@RequestMapping(value = "/rest/r/update", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class AppUpdateController {

    @Autowired
    private AppUpdateService appUpdateService;

    /**
     * 检查是否有新版本
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/check")
    @ResponseBody
    public String check(HttpServletRequest request) {

        RePackageChannel rePackageChannel = appUpdateService.checkUpdate(request);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("pushModel", rePackageChannel);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }

}
