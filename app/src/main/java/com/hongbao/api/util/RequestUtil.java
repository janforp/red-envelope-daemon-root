package com.hongbao.api.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuqiang on 15-8-7.
 *
 * @author wuqiang
 */
public class RequestUtil {
    /**
     * 取得客户端ip地址（可能有多个，如：192.168.10.2,192.168.10.1）<br>
     *
     * @param request HttpServletRequest
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
