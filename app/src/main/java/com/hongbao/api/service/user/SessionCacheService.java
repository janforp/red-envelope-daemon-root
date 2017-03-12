package com.hongbao.api.service.user;

import com.hongbao.api.consts.CacheConsts;
import com.wujie.common.redis.StringKeyRedisTemplate;
import org.craigq.common.logger.LogMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by wuqiang on 16-1-29.
 *
 * @author wuqiang
 */
@Service
public class SessionCacheService {

    private static final Random random = new Random();
    public static final String cookieName_cache_session_id = "SID";
    /**
     * 保存在request范围的cacheSessionId
     */
    public static final String CACHE_SESSION_ID = "CACHE_SESSION_ID";
    /**
     * 缓存到redis中的session的属性map在request范围的属性名
     */
    public static final String CACHE_SESSION = "CACHE_SESSION";

    @Autowired
    private StringKeyRedisTemplate cacheRedisTemplate_session;

    /**
     * 从request属性范围获取sessionMap
     *
     * @param request
     * @return
     */
    public Map<String, Serializable> getSession(HttpServletRequest request) {
        Object sessionMapObj = request.getAttribute(CACHE_SESSION);
        if (sessionMapObj != null) {
            return (Map<String, Serializable>) sessionMapObj;
        }
        return null;
    }

//    /**
//     * 注销一个session
//     *
//     * @param request
//     * @param response
//     */
//    public void invalidate(HttpServletRequest request, HttpServletResponse response) {
//        request.removeAttribute(CACHE_SESSION);
//        String cacheSessionId = (String) request.getAttribute(CACHE_SESSION_ID);
//        if (cacheSessionId != null) {
//            removeCachedSession(cacheSessionId);// 从缓存中删除旧的session
//        }
//        initialize(request, response);
//    }

    /**
     * 先从request属性中获取CACHE_SESSION_MAP，如果没有则从redis获取，如果还是没有就new HashMap()，并设置到属性范围
     *
     * @param sessionId
     * @return
     */
    public Map<String, Serializable> getCachedSession(String sessionId) {
        Map<String, Serializable> sessionMap = null;
        try {
            sessionMap = cacheRedisTemplate_session.getObj(sessionId);
        } catch (Exception e) {
            LogMgr.getLogger().error("CacheSessionMgr.getCachedSession", e);
        }
        return sessionMap;
    }

    /**
     * 直接删除redis中对应sessionId的缓存
     *
     * @param sessionId
     */
    public void removeCachedSession(String sessionId) {
        try {
            cacheRedisTemplate_session.delete(sessionId);
        } catch (Exception e) {
            LogMgr.getLogger().error("CacheSessionMgr.removeCachedSession", e);
        }
    }

    /**
     * 更新redis中对应sessionId的缓存
     *
     * @param sessionId
     * @param sessionMap
     */
    public void updateCachedSession(String sessionId, Map<String, Serializable> sessionMap) {
        try {
            cacheRedisTemplate_session.setex(sessionId, sessionMap, CacheConsts.SECONDS_OF_ONE_DAY); // session有效时间将通过配置文件配置
//            cacheRedisTemplate_session.set(sessionId, sessionMap);
        } catch (Exception e) {
            LogMgr.getLogger().error("CacheSessionMgr.updateCachedSession", e);
        }
    }

    /**
     * 初始化一个session：产生新的sessionId并设置到request属性范围、setCookie到response；产生新的会话Map并设置到属性范围
     *
     * @param request
     * @param response
     * @return 新的sessionId
     */
    public String initialize(HttpServletRequest request, HttpServletResponse response, String userKey) {
//        String newSessionId = newCacheSessionId(request);
        String newSessionId = userKey;
        request.setAttribute(CACHE_SESSION_ID, newSessionId);
        response.addCookie(getCacheSessionCookie(newSessionId, request.getContextPath(), request.isSecure()));

//        Map<String, Object> sessionMap = new HashMap<>();
//        request.setAttribute(CACHE_SESSION, sessionMap);
//        this.updateCachedSession(newSessionId, sessionMap);
        return newSessionId;
    }

    /**
     * 从cookie中获取客户端的sessionId，获取到了并把sessionId设置到request属性范围
     *
     * @param request
     * @return
     */
    public String getRequestedCacheSessionId(HttpServletRequest request) {
        String id = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookieName_cache_session_id.equals(cookie.getName())) {
                    id = cookie.getValue();
                    break;
                }
            }
        }
        if (id != null) {
            request.setAttribute(CACHE_SESSION_ID, id);
        }
        return id;
    }

    /**
     * 重新产生一个sessionId
     *
     * @param request
     * @return
     */
    public String newCacheSessionId(HttpServletRequest request) {
        String _workerName = null;
        long _reseed = 100000;
        String id = null;
        while (id == null || id.length() == 0) {
            long r0 = random.nextLong();
            if (r0 < 0)
                r0 = -r0;
            // random chance to reseed
            if (_reseed > 0 && (r0 % _reseed) == 1L) {
                random.setSeed(random.nextLong() ^ System.currentTimeMillis() ^ request.hashCode() ^ Runtime.getRuntime().freeMemory());
            }
            long r1 = random.nextLong();
            if (r1 < 0)
                r1 = -r1;
            id = Long.toString(r0, 36) + Long.toString(r1, 36);

            //add in the id of the node to ensure unique id across cluster
            //NOTE this is different to the node suffix which denotes which node the request was received on
            if (_workerName != null) {
                id = _workerName + id;
            }
        }
        return id;
    }

    /**
     * 构造sessionId的Cookie对象
     *
     * @param cacheSessionId
     * @param contextPath
     * @param requestIsSecure
     * @return
     */
    public Cookie getCacheSessionCookie(String cacheSessionId, String contextPath, boolean requestIsSecure) {
        String sessionPath = contextPath;
        sessionPath = (sessionPath == null || sessionPath.length() == 0) ? "/" : sessionPath;
        Cookie cookie = new Cookie(cookieName_cache_session_id, cacheSessionId);
//        cookie.setDomain(null);
        cookie.setPath(sessionPath);
        cookie.setMaxAge(-1);
        cookie.setHttpOnly(false);
        cookie.setSecure(requestIsSecure);
        return cookie;
    }
}
