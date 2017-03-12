package com.hongbao.api.web.filter;

import com.hongbao.api.consts.JsonConsts;
import com.hongbao.api.util.CommonMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wuqiang on 15-8-21.
 * <p/>
 * mapping : /*；
 *
 * @author wuqiang
 */
@Component
public class BaseFilter implements Filter {
    private static Logger selfLogger = LoggerFactory.getLogger(BaseFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 这样Jetty就不会在响应头中设置Server的信息
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setHeader("Server", "1");
        String requestUri = request.getRequestURI();
        if (requestUri.contains("//")) {
            // 当请求路径中含有连续两个“/”以上的请求，为非法请求；因为这样会导致他跳过部分filter
            CommonMethod.sendErrorJsonResponse(request, response, 404, JsonConsts.ERROR_404);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
