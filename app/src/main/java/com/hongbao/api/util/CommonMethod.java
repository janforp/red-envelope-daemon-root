package com.hongbao.api.util;

import com.hongbao.api.consts.BaseConsts;
import com.hongbao.api.consts.RequestConsts;
import com.hongbao.api.enums.HbUserDevicePlatform;
import com.hongbao.api.enums.NetworkType;
import com.hongbao.api.task.dto.RequestExceptionReport;
import com.hongbao.api.task.sys.SystemReporterTask;
import com.hongbao.api.web.logger.ControllerLogger;
import org.craigq.common.logger.LogMgr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by wuqiang on 15-8-6.
 * <p/>
 * 公共方法，由此类维护
 *
 * @author wuqiang
 */
public class CommonMethod {

    public static String FMT_yyyy_MM_dd = "yyyy-MM-dd";
    public static String FMT_MM_dd = "MM-dd";
    public static String FMT_yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static String FMT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static String FMT_yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static String FMT_HH_mm_ss_SSS = "HH:mm:ss.SSS";
    public static String FMT_HH_mm_ss = "HH:mm:ss";

    private CommonMethod() {
    }

    /**
     * 获取当前登录用户的userId。在com.lswuyou.api.web.controller.auth以下的Controller中调用，一定不会返回null，无需验证
     */
    public static Long getRequestUserId(HttpServletRequest request) {
        return (Long) request.getAttribute(RequestConsts.ATTR_USER_ID);
    }

    /**
     * 获取当前登录用户的userType。在com.lswuyou.api.web.controller.auth以下的Controller中调用，一定不会返回null，无需验证
     */
    public static Integer getRequestUserType(HttpServletRequest request) {
        return (Integer) request.getAttribute(RequestConsts.ATTR_USER_TYPE);
    }

    /**
     * 获取当前登录用户的userKey。在com.lswuyou.api.web.controller.auth以下的Controller中调用，一定不会返回null，无需验证
     */
    public static String getRequestUserKey(HttpServletRequest request) {
        return (String) request.getAttribute(RequestConsts.ATTR_USER_KEY);
    }

    /**
     * 获取当前请求的平台。在Controller中调用，一定不会返回null，无需验证
     */
    public static Integer getRequestPlatform(HttpServletRequest request) {
        return (Integer) request.getAttribute(RequestConsts.ATTR_PLATFORM);
    }

    /**
     * 获取当前请求的客户端平台是否是IOS
     */
    public static boolean isRequestPlatformIos(HttpServletRequest request) {
        return HbUserDevicePlatform.iOS.val.equals(getRequestPlatform(request).intValue());
    }

    /**
     * 获取当前请求的客户端平台是否是Android
     */
    public static boolean isRequestPlatformAndroid(HttpServletRequest request) {
        return HbUserDevicePlatform.android.val.equals(getRequestPlatform(request).intValue());
    }

    /**
     * 获取当前请求所使用的客户端版本。在Controller中调用，一定不会返回null，无需验证
     */
    public static String getRequestVersion(HttpServletRequest request) {
        return (String) request.getAttribute(RequestConsts.ATTR_VERSION);
    }

    /**
     * 获取当前请求客户端所处的网络环境；一定不会为null
     */
    public static NetworkType getRequestNetworkType(HttpServletRequest request) {
        return (NetworkType) request.getAttribute(RequestConsts.ATTR_NETWORK_TYPE);
    }

    /**
     * 获取当前请求客户端的设备名称；可能为null
     */
    public static String getRequestDeviceName(HttpServletRequest request) {
        return (String) request.getAttribute(RequestConsts.ATTR_DEVICE_NAME);
    }

    /**
     * 在pub包下的Controller中无法获取到deviceId 获取当前请求客户端的设备ID； 在auth/rsa包下，一定不会为null
     */
    public static String getRequestDeviceId(HttpServletRequest request) {
        return (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);
    }

    /**
     * 获取当前请求客户端的屏幕宽度 。在Controller中调用，一定不会返回null，无需验证
     */
    public static Integer getRequestScreenWidth(HttpServletRequest request) {
        return (Integer) request.getAttribute(RequestConsts.ATTR_SCREEN_WIDTH);
    }

    /**
     * 获取当前请求客户端的屏幕高度 。在Controller中调用，一定不会返回null，无需验证
     */
    public static Integer getRequestScreenHeight(HttpServletRequest request) {
        return (Integer) request.getAttribute(RequestConsts.ATTR_SCREEN_HEIGHT);
    }

    /**
     * 获取客户端的IP，并裁切至数据库保存所支持的最大长度(64)
     *
     * @param clientIp
     * @return
     */
    public static String fixClientIpToMaxLength(String clientIp) {
        if (clientIp != null) {
            if (clientIp.length() > BaseConsts.SUPPORT_MAX_CLIENT_IP_LENGTH) {
                clientIp = clientIp.substring(0, BaseConsts.SUPPORT_MAX_CLIENT_IP_LENGTH);
            }
            return clientIp;
        } else {
            return null;
        }
    }

    /**
     * 获取客户端的IP，并裁切至数据库保存所支持的最大长度(64)
     *
     * @param request
     * @return
     */
    public static String getAndFixClientIpToMaxLength(HttpServletRequest request) {
        String clientIp = RequestUtil.getClientIp(request);
        return fixClientIpToMaxLength(clientIp);
    }

    /**
     * @param request
     * @param response
     * @param httpStatusCode HTTP状态码；如200, 404, 500
     * @param errorJson
     */
    public static void sendErrorJsonResponse(HttpServletRequest request, HttpServletResponse response,
                                             int httpStatusCode, String errorJson) {
        String charSet = request.getCharacterEncoding();
//        String contentType = "application/json;charset=" + charSet;
        String contentType = RequestConsts.CONTENT_TYPE_JSON;
        // 在响应头中声明，响应内容采用明文方式
        response.setHeader(RequestConsts.RESPONSE_HEADER_RESPONSE_DATA_TYPE, RequestConsts.VALUE_DATA_TYPE_PUB);
        response.setCharacterEncoding(charSet);
        response.setContentType(contentType);
        response.setStatus(httpStatusCode);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(errorJson);
        } catch (Exception e) {
            LogMgr.getLogger().error(e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static NetworkType parseNetworkType(String netType) {
        if (netType == null) {
            return NetworkType.unkown;
        }
        netType = netType.toLowerCase();
        if ("wifi".equalsIgnoreCase(netType)) {
            return NetworkType.net_wifi;
        } else if ("2g".equalsIgnoreCase(netType)) {
            return NetworkType.net_2g;
        } else if ("3g".equalsIgnoreCase(netType)) {
            return NetworkType.net_3g;
        } else if ("4g".equalsIgnoreCase(netType)) {
            return NetworkType.net_4g;
        }
        return NetworkType.unkown;
    }

    /**
     * 把str中的控制字符替换掉，不允许出现换行（\n和\r：会被替换成空白字符）
     */
    public static String replaceControlCharsAndTrimForOneRow(String str) {
        if (str == null || (str = str.trim()).length() == 0) {
            return str;
        }
        char chars[] = str.toCharArray();
        char finalChars[] = new char[chars.length];
        int pos = 0;
        for (char s : chars) {
            if (s > 31 && s != 127) {
                finalChars[pos] = s;
                pos++;
            }
        }
        if (pos == 0) {
            return "";
        }
        // ！！！！注意：因为在for循环中，没有类似于replaceControlCharsAndTrimForMultipleRow的替换操作，一下逻辑才成立
        if (pos == chars.length) {
            return str.trim();
        }
        return new String(finalChars, 0, pos).trim();
    }

    /**
     * 为用户产生randomKey（用于md5+salt加密密码）,长度8位
     */
    public static String generateUserRandomKey() {
        return RandomUtil.getRandomString(8);
    }

    /**
     * 为用户密码加密(md5+salt)
     *
     * @param password     密码md5
     * @param passwordSalt 用户的passwordSalt（salt）
     */
    public static String encyptUserPassword(String password, String passwordSalt) {
        byte[] encry = encyptUserPasswordToBytes(password, passwordSalt);
        return MD5Encryption.byte2String(encry);
    }

    /**
     * 为用户密码加密(md5+salt)
     *
     * @param password     密码md5
     * @param passwordSalt 用户的passwordSalt（salt）
     */
    public static byte[] encyptUserPasswordToBytes(String password, String passwordSalt) {
        return MD5Encryption.encryptMsg(password + passwordSalt);
    }

    /**
     * 校验密码
     *
     * @param encryptedPassword 数据库中存储的加密后的原密码
     * @param password          密码md5
     * @param passwordSalt      用户的passwordSalt（salt）
     * @return true 密码正确
     */
    public static boolean isUserPasswordCorrect(String encryptedPassword, String password,
                                                String passwordSalt) {
        byte[] encry = encyptUserPasswordToBytes(password, passwordSalt);
        return MD5Encryption.isEqual(MD5Encryption.string2Byte(encryptedPassword), encry);
    }

    /**
     * 由于要求客户端提供的密码必须时md5加密处理
     *
     * @param password
     * @return 如果验证通过，则返回trim(password).toLowerCase()；否则返回null，表明密码有误
     */
    public static String validatePasswordLegal(String password) {
        if (password == null) {
            return null;
        }
        password = password.trim();
        if (password.length() == 0) {
            return null;
        }
        if (password.length() != 32) {
            return null;
        }
        return password.toLowerCase();
    }

    /**
     * 判断location是否是空白的，主要用于修复location=="null"（"null"字符串的bug）
     */
    public static boolean isLocationBlank(String location) {
        if (location != null) {
            location = location.trim();
        }
        return location == null || location.length() == 0 || "未知".equals(location) || "unknown".equalsIgnoreCase(location) || "null".equals(location) || "(null)".equals(location);
    }

    /**
     * 判断str.trim()后是否是，null/""/"null"/"(null)"
     *
     * @param str
     * @return
     */
    public static boolean isClientBlankNullString(String str) {
        if (str != null) {
            str = str.trim();
        }
        return str == null || str.length() == 0 || "null".equals(str) || "(null)".equals(str);
    }

    /**
     * 验证手机号（isTelephoneValid这个方法完成）以及，修正手机号数据，用户有可能输入 8613812345678/+8613812345678/(86)13812345678，需要剔除掉“+”，“(”，“)”，“86”
     */
    public static String validateAndFixedTelephone(String telephoneString) {
        if (telephoneString == null) {
            return null;
        }
        telephoneString = telephoneString.replace("+", "").replace("(", "").replace(")", "");
        if (telephoneString.startsWith("86")) {
            telephoneString = telephoneString.substring(2).trim();
        }
        telephoneString = telephoneString.trim();
        if (!ValidateUtil.isChinaTelephone(telephoneString)) {
            // 手机号验证未通过
            return null;
        }
        return telephoneString;
    }

    /**
     * 把字符串中的字符串进行格式化 <br> 比如：<br> src={0}格式错误; <br> formatArg(src,"密码"));<br> 返回：密码错误
     */
    public static String formatArg(String src, Object arg) {
        if (arg == null || src == null || src.length() == 0) {
            return src;
        }
        return src.replace("{0}", String.valueOf(arg));
    }

    /**
     * 把字符串中的字符串进行格式化 <br> 比如：<br> src={0}格式错误; <br> formatArg(src,new String[]{"密码"}));<br>
     * 返回：密码错误
     */
    public static <T> String formatArg(String src, T... args) {
        if (args == null || args.length == 0 || src == null || src.length() == 0) {
            return src;
        }
        int len = args.length;
        for (int i = 0; i < len; i++) {
            src = src.replace("{" + i + "}", String.valueOf(args[i]));
        }
        return src;
    }

    private static final Pattern wy_className_pattern = Pattern.compile("^[^`~!@#$%^&*+=|':;',./?~！@#￥%……&*——+|‘；：”“’。，、？]{1,20}$");
    private static final Pattern not_symbol_pattern = Pattern.compile("^[^`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]{1,20}$");
    private static final Pattern password_pattern = Pattern.compile("^[0-9a-zA-Z]{6,18}$");

    /**
     * 为用户产生passwordSalt（用于md5+salt加密密码）,长度8位
     */
    public static String generateUserPasswordSalt() {
        return RandomUtil.getRandomString(8);
    }

    /**
     * 为用户产生userSecret,长度32位
     */
    public static String generateUserSecret() {
        return RandomUtil.getRandomString(32);
    }




    /**
     * 把第一个字母转换为大写
     *
     * @param src
     * @return
     */
    public static String initCap(String src) {
        if (src == null) {
            return null;
        }
        if (src.length() > 1) {
            return src.substring(0, 1).toUpperCase() + src.substring(1);
        } else {
            return src.toUpperCase();
        }
    }

    /**
     * 把第一个字母转换为小写
     *
     * @param src
     * @return
     */
    public static String initLow(String src) {
        if (src == null) {
            return null;
        }
        if (src.length() > 1) {
            return src.substring(0, 1).toLowerCase() + src.substring(1);
        } else {
            return src.toLowerCase();
        }
    }

    /**
     * 获取文件夹名的后缀，如果没有后缀则返回空字符串
     *
     * @param fileName
     * @return 例如：.jsp/.jpg
     */
    public static String getFileExtendName(String fileName) {
        String ext = "";
        if (fileName != null && fileName.length() > 0) {
            try {
                ext = fileName.substring(fileName.lastIndexOf("."),
                        fileName.length());
            } catch (StringIndexOutOfBoundsException e) {
                ext = "";
            }
        }
        return ext;
    }


    /**
     * 拼接消息内容
     *
     * @param replaces
     * @param body
     * @return
     */
    public static String formattingMsgBody(Map<String, String> replaces, String body) {
        for (String key : replaces.keySet()) {
            body = body.replace(key, replaces.get(key));
        }
        return body;
    }

    public static HbUserDevicePlatform parseDevicePlatform(int platform) {
        if (HbUserDevicePlatform.iOS.val.equals(platform)) {
            return HbUserDevicePlatform.iOS;
        } else if (HbUserDevicePlatform.android.val.equals(platform)) {
            return HbUserDevicePlatform.android;
        }
        return null;
    }

    private static final String[] weeks = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};


    /**
     * 验证字符字符串中是否存在符号
     *
     * @param source
     * @return
     */
    public static boolean validateSchoolName(String source) {
        if (source == null) {
            return false;
        }
        if (source.length() > 20) {
            return false;
        }
        return not_symbol_pattern.matcher(source).matches();
    }

    public static boolean validateRealName(String source) {
        if (source == null) {
            return false;
        }
        if (source.length() > 15) {
            return false;
        }
        return not_symbol_pattern.matcher(source).matches();
    }

    public static boolean validateClassName(String source) {
        if (source == null) {
            return false;
        }
        return wy_className_pattern.matcher(source).matches();
    }

    /**
     * 校验密码是否合法 (6到18位数字加字母)
     *
     * @param password
     * @return
     */
    public static boolean validatePassword(String password) {
        if (StringUtil.isEmpty(password)) {
            return false;
        }
        return password_pattern.matcher(password).matches();
    }

    /**
     * 验证字符是否在指定范围
     *
     * @param source
     * @return
     */
    public static boolean validateStringLengthMax_40(String source) {
        if (source == null) {
            return false;
        }
        return source.length() < 41;
    }

    /**
     * 返回最小值
     * @param a
     * @param b
     * @return
     */
    public static int getMinValue(int a, int b) {
        if(a <= b){
            return a;
        }
        return b;
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    /**
     * 异步发送Controller异常报告
     *
     * @param systemReporterTask
     * @param request
     * @param e
     * @param logger
     */
    public static void sendControllerExceptionReport(SystemReporterTask systemReporterTask,
                                                     HttpServletRequest request, Throwable e, ControllerLogger logger) {
        RequestExceptionReport report = new RequestExceptionReport(logger.getRequestId(), logger.getRequestUri(), e);
        report.setClientIp(logger.getClientIp());
        report.setRequestHeader(logger.getRequestHeader());
        report.setSystemParameter(logger.getSystemParameter());
        report.setDecryptSystemParameter(logger.getDecryptSystemParameter());
        report.setBusinessParameter(logger.getBusinessParameter());
        report.setDecryptBusinessParameter(logger.getDecryptBusinessParameter());
        report.setOccurTime(new Date());
        if (logger.getRequestHeader() == null) {
            Enumeration<String> headers = request.getHeaderNames();
            Map<String, String> _requestHeader = new HashMap<String, String>();
            if (headers != null) {
                while (headers.hasMoreElements()) {
                    String name = headers.nextElement();
                    String value = request.getHeader(name);
                    _requestHeader.put(name, value);
                }
            }
            report.setRequestHeader(_requestHeader);
        }
        systemReporterTask.addReport(report);
    }


    /**
     * 格式化毫秒时间戳，输出时分秒
     *
     * @param timestampInMs
     * @return
     */
    public static String fmtHMSS(Long timestampInMs) {
        if (timestampInMs == null) {
            return "";
        }
        return new SimpleDateFormat(CommonMethod.FMT_HH_mm_ss_SSS).format(new Date(timestampInMs));
    }

    /**
     * 格式化毫秒时间戳，输出到毫秒
     *
     * @param timestampInMs
     * @return
     */
    public static String fmtMillis(Long timestampInMs) {
        if (timestampInMs == null) {
            return "";
        }
        return new SimpleDateFormat(CommonMethod.FMT_yyyy_MM_dd_HH_mm_ss_SSS).format(new Date(timestampInMs));
    }

    /**
     * 格式化毫秒时间戳，输出到秒
     *
     * @param timestampInMs
     * @return
     */
    public static String fmtTime(Long timestampInMs) {
        if (timestampInMs == null) {
            return "";
        }
        return new SimpleDateFormat(CommonMethod.FMT_yyyy_MM_dd_HH_mm_ss).format(new Date(timestampInMs));
    }

    /**
     * 格式化毫秒时间戳，输出到分钟
     *
     * @param timestampInMs
     * @return
     */
    public static String fmtMinute(Long timestampInMs) {
        if (timestampInMs == null) {
            return "";
        }
        return new SimpleDateFormat(CommonMethod.FMT_yyyy_MM_dd_HH_mm).format(new Date(timestampInMs));
    }

    /**
     * 格式化毫秒时间戳，输出到日
     *
     * @param timestampInMs
     * @return
     */
    public static String fmtDay(Long timestampInMs) {
        if (timestampInMs == null) {
            return "";
        }
        return new SimpleDateFormat(CommonMethod.FMT_yyyy_MM_dd).format(new Date(timestampInMs));
    }

    /**
     * 格式化毫秒时间戳，输出yyyy-MM
     *
     * @param timestampInMs
     * @return
     */
    public static String formatYearMonth(Long timestampInMs) {
        if (timestampInMs == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM").format(new Date(timestampInMs));
    }

    /**
     * 格式化时间,只取: HH:mm
     *
     * @param targetDatetimeInMillis
     * @return
     */
    public static String fmtHMS(Long targetDatetimeInMillis) {
        if (targetDatetimeInMillis == null) {
            return null;
        }
        return new SimpleDateFormat(CommonMethod.FMT_HH_mm_ss).format(new Date(targetDatetimeInMillis));
    }

    /**
     * 格式化时间,只取: MM-dd
     *
     * @param targetDatetimeInMillis
     * @return
     */
    public static String fmtMd(Long targetDatetimeInMillis) {
        if (targetDatetimeInMillis == null) {
            return null;
        }
        return new SimpleDateFormat(CommonMethod.FMT_MM_dd).format(new Date(targetDatetimeInMillis));
    }

    /**
     * 获取13位时间戳
     *
     * @param time
     * @return
     */
    public static long get13Timestamp(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FMT_yyyy_MM_dd_HH_mm_ss);
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

}
