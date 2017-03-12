package com.hongbao.api.web.filter;

import com.hongbao.api.consts.RequestConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wuqiang on 15-8-6.
 * <p/>
 * mapping : /c/rest/a/s/*：请求加密，响应明文；(s代表semi)
 *
 * @author wuqiang
 */
@Component
public class RestAesSemiFilter implements Filter {
    private static Logger selfLogger = LoggerFactory.getLogger(RestAesSemiFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 指明请求类型是：使用userSecret作为密钥AES单向加密请求（请求加密，响应不加密）
        servletRequest.setAttribute(RequestConsts.ATTR_REQUEST_TYPE, RequestConsts.VALUE_REQUEST_TYPE_AES_USER_SECRET_SEMI);
        // 指明响应应当采用明文方式
        servletRequest.setAttribute(RequestConsts.ATTR_RESPONSE_DATA_TYPE, RequestConsts.VALUE_DATA_TYPE_PUB);
        // 在响应头中声明，响应内容采用明文方式
        response.setHeader(RequestConsts.RESPONSE_HEADER_RESPONSE_DATA_TYPE, RequestConsts.VALUE_DATA_TYPE_PUB);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
