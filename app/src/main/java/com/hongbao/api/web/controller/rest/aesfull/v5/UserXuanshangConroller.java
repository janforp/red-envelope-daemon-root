package com.hongbao.api.web.controller.rest.aesfull.v5;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.vo.MyXuanshangListVo;
import com.hongbao.api.model.vo.UserToVerifyVo;
import com.hongbao.api.model.vo.XuanshangVo;
import com.hongbao.api.service.redis.RedisXuanshangService;
import com.hongbao.api.service.user.XuanshangService;
import com.hongbao.api.util.JsonUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Janita on 2016/12/27.
 */
@RequestMapping(value = "/rest/a/f/xuanshang", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class UserXuanshangConroller {

    @Autowired
    private XuanshangService xuanshangService;
    @Autowired
    private RedisXuanshangService redisXuanshangService;


    /**
     * 用户发布悬赏任务
     * @param request
     * @param totalNum      总个数
     * @param singleMoney   单个金额
     * @param missionDesc   描述／要求
     * @param xuanshangImgs 图片，多张用分号隔开
     * @return
     */
    @RequestMapping(value = "/send")
    @ResponseBody
    public String send(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.totalNum, required = true) Integer totalNum,
                       @RequestParam(value = ParamConsts.singleMoney, required = true) String singleMoney,
                       @RequestParam(value = ParamConsts.missionDesc, required = false) String missionDesc,
                       @RequestParam(value = ParamConsts.xuanshangImgs, required = false) String xuanshangImgs){

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
//        return xuanshangService.send(userId,totalNum,new BigDecimal(singleMoney),missionDesc,xuanshangImgs);
        return redisXuanshangService.send(userId,totalNum,new BigDecimal(singleMoney),missionDesc,xuanshangImgs);

    }

    /**
     * 我参与的任务
     * @param request
     * @param id        当前页面最后一个参与任务的id
     * @return
     */
    @RequestMapping(value = "/myPartInList")
    @ResponseBody
    public String myPartInMission(HttpServletRequest request,
                                  @RequestParam(value = ParamConsts.id,required = true)Long id){

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        if (id == 0){
            id = null;
        }
        List<XuanshangVo> vos = redisXuanshangService.getMyPartInMissionList(userId,id);

        Map<String ,Object> dataResult = new HashMap<>(1);
        dataResult.put("list",vos);

        return JsonUtil.buildSuccessDataJson(dataResult);
    }

    /**
     * 我发布的悬赏任务列表
     * @param request
     * @param xuanshangId   当前页面最后一个我发布的悬赏任务的id
     * @return
     */
    @RequestMapping(value = "/mySendList")
    @ResponseBody
    public String mySendMission(HttpServletRequest request,
                                @RequestParam(value = ParamConsts.xuanshangId,required = true)Long xuanshangId){

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        if (xuanshangId == 0){
            xuanshangId = null;
        }
        List<XuanshangVo> vos = redisXuanshangService.getMySendMissionList(userId,xuanshangId);

        Map<String ,Object> dataResult = new HashMap<>(1);
        dataResult.put("list",vos);

        return JsonUtil.buildSuccessDataJson(dataResult);
    }

    /**
     * 交作业
     * 提交悬赏任务给发布者审核
     * @param request
     * @param xuanshangId       所做的悬赏任务的id
     * @param missionText       用户输入的备注
     * @param xuanshangImgs     用户上传的图片,
     * 格式：["https://ag-aw-test.oss-cn-hangzhou.aliyuncs.com/i/10/1482893568000499.jpg","https://ag-aw-test.oss-cn-hangzhou.aliyuncs.com/i/10/1482893571000430.jpg"]
     * @return
     */
    @RequestMapping(value = "/submit")
    @ResponseBody
    public String submitXuanshang(HttpServletRequest request,
                                  @RequestParam(value = ParamConsts.xuanshangId,required = true)Long xuanshangId,
                                  @RequestParam(value = ParamConsts.missionText,required = false)String missionText,
                                  @RequestParam(value = ParamConsts.xuanshangImgs, required = false) String xuanshangImgs){

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
//        return xuanshangService.submitXuanshang(userId,xuanshangId,missionText,xuanshangImgs);
        return redisXuanshangService.submitXuanshang(userId,xuanshangId,missionText,xuanshangImgs);

    }

    /**
     * 审核通过（悬赏任务）
     * @param request
     * @param xuanshangId       悬赏任务id
     * @param id                用户做的详情id
     * @return
     */
    @RequestMapping(value = "/pass")
    @ResponseBody
    public String passXuanshang(HttpServletRequest request,
                                @RequestParam(value = ParamConsts.xuanshangId,required = true)Long xuanshangId,
                                @RequestParam(value = ParamConsts.id,required = true)Long id){

//        return xuanshangService.pass(xuanshangId,id);
        return redisXuanshangService.pass(xuanshangId,id);
    }

    /**
     * 悬赏任务不通过
     * @param request
     * @param id            用户所做的任务的id
     * @param reason        未通过原因
     * @return
     */
    @RequestMapping(value = "/notPass")
    @ResponseBody
    public String notPassXuanshang(HttpServletRequest request,
                                   @RequestParam(value = ParamConsts.id,required = true)Long id,
                                   @RequestParam(value = ParamConsts.reason,required = true)String reason){

//        return xuanshangService.notPass(id,reason);
        return redisXuanshangService.notPass(id,reason);
    }

    /**
     * 已审核列表
     * 发布任务者可以看到此列表
     * 该任务所有已经审核过的列表
     * @param request
     * @param xuanshangId   悬赏任务id
     * @param  id           当前页面的最后一个id
     * @return
     */
    @RequestMapping(value = "/myVerifiedList")
    @ResponseBody
    public String GetMyVerifiedList(HttpServletRequest request,
                                        @RequestParam(value = ParamConsts.id,required = true)Long id,
                                        @RequestParam(value = ParamConsts.xuanshangId,required = true)Long xuanshangId){
        if (id == 0){
            id = null;
        }
        List<MyXuanshangListVo> details = redisXuanshangService.getMyVerifiedList(xuanshangId,id);

        Map<String,Object> dataResult = new HashedMap(1);
        dataResult.put("list",details);

        return JsonUtil.buildSuccessDataJson(dataResult);
    }

    /**
     * 待审核用户列表（翻页）
     * @param xuanshangId   悬赏任务id
     * @param id            当前页面的最后一个待审核记录待id
     * @return
     */
    @RequestMapping(value = "/toVerifyList")
    @ResponseBody
    public String getToVerifyList(HttpServletRequest request,
                                  @RequestParam(value = ParamConsts.xuanshangId,required = true)Long xuanshangId,
                                  @RequestParam(value = ParamConsts.id,required = true)Long id){
        if (id == 0){
            id=null;
        }
        List<UserToVerifyVo> verifyVos = redisXuanshangService.getToVerifyList(xuanshangId,id);
        Map<String,Object> dataResult = new HashMap<>(1);
        dataResult.put("list",verifyVos);

        return JsonUtil.buildSuccessDataJson(dataResult);
    }


    /**
     * 删除涉及违规的
     * 管理员删除
     * @param request
     * @param xuanshangId
     * @return
     */
    @RequestMapping(value = "/deleteXuanshang")
    @ResponseBody
    public String deleteXuanshang(HttpServletRequest request,
                                  @RequestParam(value = ParamConsts.xuanshangId,required = true)Long xuanshangId){

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        return redisXuanshangService.deleteXuanshang(xuanshangId,userId);
    }

    /**
     * 把该用户拉入黑名单
     * @param request
     * @param xuanshangId
     * @return
     */
    @RequestMapping(value = "/pushToBlack")
    @ResponseBody
    public String pushToBlack(HttpServletRequest request,
                              @RequestParam(value = ParamConsts.xuanshangId,required = true)Long xuanshangId){

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        return redisXuanshangService.pushToBlack(xuanshangId,userId);
    }
}
