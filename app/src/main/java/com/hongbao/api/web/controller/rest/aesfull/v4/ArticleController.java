package com.hongbao.api.web.controller.rest.aesfull.v4;

import com.hongbao.api.config.Config;
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
@RequestMapping(value = "/rest/a/f/share", produces = RequestConsts.CONTENT_TYPE_JSON, method = RequestMethod.POST)
@Controller
public class ArticleController {

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
        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);
        List<ArticleVo> articleList = appClickShareService.selectByArticleIdAndUserId(id, userId);
        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("articleList", articleList);
        return JsonUtil.buildSuccessDataJson(dataResult);
    }

    /**
     * 分享链接
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/url")
    @ResponseBody
    public String url(HttpServletRequest request,
                      @RequestParam(value = ParamConsts.articleId, required = true) long articleId) {
        String userKey = request.getHeader(RequestConsts.HEADER_USER_KEY);
        String url = Config.getRedBaseUrl() + "/c/p/article/detail/" + articleId + "/" + userKey;
        Map<String, Object> dataResult = new HashMap<>(1);
        dataResult.put("url", url);
        return JsonUtil.buildSuccessDataJson(dataResult);
    }


}
