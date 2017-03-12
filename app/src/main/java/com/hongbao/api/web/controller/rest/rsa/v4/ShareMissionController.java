package com.hongbao.api.web.controller.rest.rsa.v4;

import com.hongbao.api.consts.ParamConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.model.vo.ArticleVo;
import com.hongbao.api.service.AppClickShareService;
import com.hongbao.api.util.JsonUtil;
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
 * Created by Summer on 2016/12/7.
 */
@RequestMapping(value = "/rest/r/share", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class ShareMissionController {

    @Autowired
    private AppClickShareService appClickShareService;

    /**
     * 分享任务列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.articleId, required = true) long articleId) {

        Long id = null;

        if(articleId != 0) {
            id = articleId;
        }

        List<ArticleVo> articleList = appClickShareService.selectByArticleId(id);
        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("articleList", articleList);
        return JsonUtil.buildSuccessDataJson(dataResult);
    }


}
