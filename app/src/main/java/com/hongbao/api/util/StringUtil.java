package com.hongbao.api.util;

import java.util.*;

public class StringUtil {
    /**
     * 判断一个对象是否为空
     *
     * @param o
     * @return
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }else if (o instanceof String) {
            String s = (String) o;
            if("".equalsIgnoreCase(s.trim()) || "null".equalsIgnoreCase(s))
                return true;
            else
                return false;
        }else {
            return false;
        }
    }

    /**
     * 判断一个对象是否为空
     *
     * @param o
     * @return
     */
    public static boolean parseBool(Object o) {
        if (o == null) return false;
        String s = (String) o;
        if ("TRUE".equals(s.toUpperCase())) return true;
        else if ("FALSE".equals(s.toUpperCase())) return false;
        else return false;
    }

    /**
     * 获取一个UUID值
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 将逗号分隔的字符串转换为一个Set
     *
     * @param str
     * @return
     */
    public static Set<String> makeString2Set(String str) {
        Set<String> set = new LinkedHashSet<String>();
        if (!isEmpty(str)) {
            String[] strs = str.split(",");
            set.addAll(Arrays.asList(strs));
        }
        return set;
    }

    public static String makeList2String(List<Integer> idList, String token) {
        String str = "";
        if (idList == null || idList.size() <= 0) {
            return str;
        }

        for (int i=0; i<idList.size(); i++) {
            str += String.valueOf(idList.get(i));
            if (i < idList.size()-1) {
                str += token;
            }
        }

        return str;
    }

    /**
     * 将数组字符串转换为一个逗号相隔的、带单引号的字符串
     *
     * @param ids
     * @return
     */
    public static String getStrByArr(String[] ids){
        String str=null;
        if(ids != null && ids.length > 0){
            for(String id:ids){
                if(str == null)
                    str = "'"+id+"'";
                else
                    str += ",'"+id+"'";
            }
        }
        return str;
    }

    /**
     * 判断字符是否为纯数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        if(str == null) {
            return false;
        }
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

}