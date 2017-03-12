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
 * mapping : /c/rest/p/*；请求和响应均为明文；(p代表public)
 *
 * @author wuqiang
 */
@Component
public class RestPubFilter implements Filter {
    private static Logger selfLogger = LoggerFactory.getLogger(RestPubFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 指明请求采用明文方式
        servletRequest.setAttribute(RequestConsts.ATTR_REQUEST_TYPE, RequestConsts.VALUE_REQUEST_TYPE_PUB);
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
