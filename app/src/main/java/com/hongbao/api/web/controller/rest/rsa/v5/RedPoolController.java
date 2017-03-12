package com.hongbao.api.web.controller.rest.rsa.v5;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.dto.ReBannerInfo;
import com.hongbao.api.model.vo.RedVo;
import com.hongbao.api.service.HbPackageChannelService;
import com.hongbao.api.service.user.UserRedService;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Summer on 2016/12/23.
 */
@RequestMapping(value = "/rest/r/red", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class RedPoolController {

    @Autowired
    private HbPackageChannelService hbPackageChannelService;
    @Autowired
    private UserRedService userRedService;

    /**
     * 红包池
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/index")
    @ResponseBody
    public String index(HttpServletRequest request) {

        // 包名
        String packageName = (String) request.getAttribute(RequestConsts.HEADER_PACKAGE_NAME);
        // 渠道
        String channelName = (String) request.getAttribute(RequestConsts.HEADER_CHANNEL_NAME);
        // 版本号
        String versioncodeString = (String) request.getAttribute(RequestConsts.HEADER_VERSION_CODE);
        int versioncode = Integer.valueOf(versioncodeString);
        // 设备号
        String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);
        // 平台 0-ios, 1-android
        int platform = (Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM);
        // 用户id
        String user_id = (String) request.getAttribute(RequestConsts.ATTR_USER_ID);
        Integer userId = null;
        if(!StringUtil.isEmpty(user_id)) {
            userId = Integer.valueOf(user_id);
        }

        // true: 正常  false: 审核中
        boolean flag = hbPackageChannelService.selectAppVersionStatus(packageName, channelName, versioncode, deviceId);

        // banner列表
        List<ReBannerInfo> bannerList = userRedService.bannerList(platform, versioncode, packageName, channelName, flag);
        // 今日可抢红包次数
        int times = userRedService.redTimes(userId);
        // 红包列表
        List<RedVo> redList = userRedService.redVoList(userId, null);

        Map<String, Object> dataResult = new HashMap<>(3);
        dataResult.put("bannerList", bannerList);
        dataResult.put("times", times);
        dataResult.put("redList", redList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }

    /**
     * 红包池 (分页)
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.redId, required = true) long redId) {

        // 用户id
        String user_id = (String) request.getAttribute(RequestConsts.ATTR_USER_ID);
        Integer userId = null;
        if(!StringUtil.isEmpty(user_id)) {
            userId = Integer.valueOf(user_id);
        }

        Long id = null;
        if(redId != 0) {
            id = redId;
        }

        // 红包列表
        List<RedVo> redList = userRedService.redVoList(userId, id);

        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("redList", redList);

        return JsonUtil.buildSuccessDataJson(dataResult);

    }


}
