package com.hongbao.api.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.security.MessageDigest;
import java.security.GeneralSecurityException;

/**
 * Created by Summer on 2016/10/10.
 */
public class YoumiSign {
    /**
     * 签名生成算法
     *
     * @param HashMap<String,String> params 请求参数集，所有参数必须已转换为字符串类型
     * @param String                 dev_server_secret 开发者在有米后台设置的密钥
     * @return String
     * @throws IOException
     */
    public static String getSignature(HashMap<String, String> params, String dev_server_secret) throws IOException {
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, String> sortedParams = new TreeMap<String, String>(params);

        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();
        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (Map.Entry<String, String> param : entrys) {
            basestring.append(param.getKey()).append("=").append(param.getValue());
        }
        basestring.append(dev_server_secret);
        //System.out.println(basestring.toString());
        // 使用MD5对待签名串求签
        byte[] bytes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(basestring.toString().getBytes("UTF-8"));
        } catch (GeneralSecurityException ex) {
            throw new IOException(ex);
        }
        // 将MD5输出的二进制结果转换为小写的十六进制
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex);
        }
        return sign.toString();
    }

    /**
     * 对一条完整的未签名的URL做签名，并将签名结果添加到URL的末尾
     *
     * @param String url 未做签名的完整URL
     * @param String dev_server_secret 签名秘钥
     * @return String
     * @throws IOException, MalformedURLException
     */
    public static String getUrlSignature(String url, String dev_server_secret) throws IOException, MalformedURLException {
        try {
            URL urlObj = new URL(url);
            String query = urlObj.getQuery();
            String[] params = query.split("&");
            Map<String, String> map = new HashMap<String, String>();
            for (String each : params) {
                String name = each.split("=")[0];
                String value;
                try {
                    value = URLDecoder.decode(each.split("=")[1], "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    value = "";
                }
                map.put(name, value);
            }
            String signature = getSignature((HashMap<String, String>) map, dev_server_secret);
            return url + "&sign=" + signature;
        } catch (MalformedURLException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 检查一条完整的包含签名参数的URL，其签名是否正确
     *
     * @param String url 已经签名的完整URL
     * @param String dev_server_secret 签名秘钥
     * @return boolean
     */
    public static boolean checkUrlSignature(String signedUrl, String dev_server_secret) {
        String urlSign = "";
        try {
            URL urlObj = new URL(signedUrl);
            String query = urlObj.getQuery();
            String[] params = query.split("&");
            Map<String, String> map = new HashMap<String, String>();
            for (String each : params) {
                String name = each.split("=")[0];
                String value;
                try {
                    value = URLDecoder.decode(each.split("=")[1], "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    value = "";
                }
                if ("sign".equals(name)) {
                    urlSign = value;
                } else {
                    map.put(name, value);
                }
            }
            if ("".equals(urlSign)) {
                return false;
            } else {
                String signature = getSignature((HashMap<String, String>) map, dev_server_secret);
                return urlSign.equals(signature);
            }
        } catch (MalformedURLException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
