package com.hongbao.api.web.controller.rest.rsa.v5;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.dto.ReBannerInfo;
import com.hongbao.api.model.dto.RequestAttrDto;
import com.hongbao.api.model.vo.XuanshangDetailVo;
import com.hongbao.api.model.vo.XuanshangVo;
import com.hongbao.api.service.HbPackageChannelService;
import com.hongbao.api.service.redis.RedisXuanshangService;
import com.hongbao.api.service.user.XuanshangService;
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
 * Created by Janita on 2016/12/26.
 */
@Controller
@RequestMapping(value = "/rest/r/xuanshang",produces = RequestConsts.CONTENT_TYPE_JSON,method = RequestMethod.POST)
public class XuanshangController {

    @Autowired
    private HbPackageChannelService hbPackageChannelService;
    @Autowired
    private XuanshangService xuanshangService;
    @Autowired
    private RedisXuanshangService redisXuanshangService;

    /**
     * 悬赏任务首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/index")
    @ResponseBody
    public String index(HttpServletRequest request){

        RequestAttrDto dto = RequestAttrDto.getAppInfo(request);
        // 用户id
        String user_id = (String) request.getAttribute(RequestConsts.ATTR_USER_ID);
        Integer userId = null;
        if(!StringUtil.isEmpty(user_id)) {
            userId = Integer.valueOf(user_id);
        }

        boolean flag = hbPackageChannelService.selectAppVersionStatus(dto.getPackageName(),dto.getChannelName(),dto.getVersionCode(),dto.getDeviceId());
        List<XuanshangVo> vos = null;
        if (flag){//审核通过之后正常查询数据库
            vos = redisXuanshangService.getXuanshangList(userId,null);
        }else {//审核阶段展示指定的几条数据
            vos = xuanshangService.getXuanshangList(10,null);
        }
        List<ReBannerInfo> banners = redisXuanshangService.selectXuanshangBanner(dto.getPlatform(),dto.getVersionCode(),dto.getPackageName(),dto.getChannelName(),flag);
        int times = redisXuanshangService.missionTimes(dto.getUserId());
        Map<String, Object> dataResult = new HashMap<>(3);
        dataResult.put("bannerList", banners);
        dataResult.put("times", times);
        dataResult.put("xuanshangList", vos);

        return JsonUtil.buildSuccessDataJson(dataResult);
    }

    /**
     * 上拉加载悬赏任务列表
     * 悬赏任务首页
     * 悬赏任务列表
     * 悬赏任务列表不需要登录
     * 且发布人与做任务的人看见的都是一样的
     * 具体的区别在详情页下面的按钮区别
     * @param request
     * @param xuanshangId   当前页面的最后一个id
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.xuanshangId,required = true)Long xuanshangId){

        if (xuanshangId == 0){
            xuanshangId = null;
        }
        String user_id = (String) request.getAttribute(RequestConsts.ATTR_USER_ID);
        Integer userId = null;
        if(!StringUtil.isEmpty(user_id)) {
            userId = Integer.valueOf(user_id);
        }

        RequestAttrDto dto = RequestAttrDto.getAppInfo(request);
        boolean flag = hbPackageChannelService.selectAppVersionStatus(dto.getPackageName(),dto.getChannelName(),dto.getVersionCode(),dto.getDeviceId());
        List<XuanshangVo> xuanshangList = null;
        // 审核通过之后就可以正常加载，若还没通过审核，则显示首页的几条指定的数据即可
        if (flag){
            xuanshangList = redisXuanshangService.getXuanshangList(userId,xuanshangId);
        }else {
            xuanshangList = xuanshangService.getXuanshangList(10,xuanshangId);
        }
        Map<String ,Object> dataResult = new HashMap<>(1);
        dataResult.put("xuanshangList",xuanshangList);

        return JsonUtil.buildSuccessDataJson(dataResult);
    }

    /**
     * 悬赏任务详情
     * @param request
     * @param xuanshangId   详情的id
     * @return
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public String xuanshangDetail(HttpServletRequest request,
                                  @RequestParam(value = ParamConsts.xuanshangId,required = true)Long xuanshangId){

        // 用户id
        String user_id = (String) request.getAttribute(RequestConsts.ATTR_USER_ID);
        Integer userId = null;
        if(!StringUtil.isEmpty(user_id)) {
            userId = Integer.valueOf(user_id);
        }
        XuanshangDetailVo detailVo = redisXuanshangService.getXuanshangDetail(userId,xuanshangId);

        Map<String ,Object> dataResult = new HashMap<>(1);
        dataResult.put("detail",detailVo);

        return JsonUtil.buildSuccessDataJson(dataResult);
    }
}
