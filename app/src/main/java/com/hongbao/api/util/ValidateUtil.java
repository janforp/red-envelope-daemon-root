package com.hongbao.api.util;

import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wuqiang on 15-8-6.
 *
 * @author wuqiang
 */
public class ValidateUtil {
    private static Logger logger = Logger.getLogger(ValidateUtil.class);

    /**
     * src为空则直接返回false
     */
    public static boolean match(String src, String regex) {
        if (src == null) {
            return false;
        }
        if (regex == null) {
            logger.error("调用ValidateUtil.match方法时，传入的regex（正则），为null！！，校验返回false\n");
            return false;
        }
        Matcher matcher = Pattern.compile(regex).matcher(src);
//        if (matcher.matches()) {
//            return true;
//        } else {
//            return false;
//        }
        return matcher.matches();
    }

    /**
     * 不匹配
     */
    public static boolean notMatch(String src, String regex) {
        return (!match(src, regex));
    }

    /**
     * Integer <br> 不匹配
     */
    public static boolean notMatch(Number num, String regex) {
        if (num == null) {
            return (!match(null, regex));
        }
        return (!match(num.toString(), regex));
    }

    // 验证国内手机号是否合法
    private static final Pattern pattern_china_telephone = Pattern.compile("^1[34578]\\d{9}$");

    /**
     * 验证国内手机号是否合法
     *
     * @param mobileNo +86/86/(86)开头的将会视为不合法，已支持170虚拟运营商号段，及其他176/177/178号段
     */
    public static boolean isChinaTelephone(String mobileNo) {
        if (mobileNo == null) {
            return false;
        }
        Matcher matcher = pattern_china_telephone.matcher(mobileNo);
//        if (matcher.matches()) {
//            return true;
//        } else {
//            return false;
//        }
        return matcher.matches();
    }

    public static boolean isEmail(String email) {
        String
                regex =
                "([a-zA-Z0-9\\.\\_\\-])+@([a-zA-Z0-9\\_\\-])+((\\.[a-zA-Z0-9\\_\\-]{1,6}){1,2})";
        return match(email, regex);
    }
}
