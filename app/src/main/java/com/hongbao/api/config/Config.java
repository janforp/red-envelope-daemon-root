package com.hongbao.api.config;

import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.PropertiesLoader;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by wuqiang on 15-8-4.
 *
 * @author wuqiang
 */
public final class Config {
    private static Logger log = LoggerFactory.getLogger(Config.class);
    private static Properties properties;
    private static Set<String> tmpSubDirectoryNames = new HashSet<>();

    // 全局默认字符集编码
    public static final String CHARSET = "utf-8";
    // 开发模式，仅本地开发环境时，设置为true
    private static boolean devModel = false;
    // 调试模式，则生产环境也可以使用，有可能可以输出更多信息
    private static boolean debug = false;
    // 支持的版本
    private static String[] supportedVersion = new String[]{"1.0.0"};
    /**
     * #当前服务运行的服务器所在环境位置:如果是非阿里云中,就填空白
     * #否则如果当前运行在阿里云杭州的ECS中,就填写cn-hangzhou
     * #其他阿里云区域填写对应的区域region:cn-beijing / cn-hongkong / cn-shanghai / cn-shenzhen / cn-qingdao / us-west-1(美国硅谷) / ap-southeast-1(亚太(新加坡))
     */
    private static String serverRuntimeRegion = "";
    // 临时文件目录，一定以“分隔符”结尾
    private static String tmpDirectory = "/tmp/aigouapp/";
    // 安全配置
    private static SecurityConfig security;
    // 阿里云OSS配置
    private static AliyunOss aliyunOss;
    private static String logoutUri = "/c/rest/a/f/me/logout";
    // 红包域名地址
    private static String redBaseUrl;
    // 微信绑定说明页
    private static String weixinBindUrl;

    /**
     * 触发执行static{}
     */
    public static void init() {
        // 什么以不执行, 只是触发执行static{}
    }

    /**
     * initialization
     */
    static {
        try {

            Properties profileProperties = PropertiesLoader.init("application-profile.properties");
            String profileName = profileProperties.getProperty("profile_name");
            String profileNameProperties = profileName + ".properties";

            properties = PropertiesLoader.init(profileNameProperties);
            Field[] fields = Config.class.getDeclaredFields();
            if (fields != null) {
                for (Field fd : fields) {
                    String fieldName = fd.getName();
                    if (Modifier.isFinal(fd.getModifiers()) || "log".equals(fieldName)
                            || "properties".equals(fieldName)) {
                        continue;
                    }
                    Class<?> fieldType = fd.getType();
                    if (isSimpleDataTypeOrSimpleArray(fieldType)) {
                        Object realValue = null;
                        // 是简单数据类型
                        String value = null;
                        try {
                            value = properties.getProperty(fieldName);
                        } catch (Exception e) {
                        }
                        if (value != null) {
                            Method strSetter = null;
                            try {
                                strSetter = Config.class.getDeclaredMethod("set" + CommonMethod.initCap(fieldName), String.class);
                                strSetter.setAccessible(true);
                            } catch (Exception e) {
                            }
                            if (strSetter != null) {
                                // 有字符串类型的setter方法
                                try {
                                    strSetter.invoke(Config.class, value);
                                } catch (Exception e) {
                                    throw new RuntimeException(
                                            "failed to parse " + fieldName + " for " + Config.class
                                                    .getName() + " from " + profileNameProperties, e);
                                }
                            } else {
                                // 没有setter
                                try {
                                    if (String.class.equals(fieldType)) {
                                        realValue = value;
                                    } else if (Boolean.class.equals(fieldType)
                                            || boolean.class.equals(fieldType)) {
                                        realValue = Boolean.parseBoolean(value);
                                    } else if (Byte.class.equals(fieldType)
                                            || byte.class.equals(fieldType)) {
                                        realValue = Byte.parseByte(value);
                                    } else if (Short.class.equals(fieldType)
                                            || short.class.equals(fieldType)) {
                                        realValue = Short.parseShort(value);
                                    } else if (Integer.class.equals(fieldType)
                                            || int.class.equals(fieldType)) {
                                        realValue = Integer.parseInt(value);
                                    } else if (Long.class.equals(fieldType)
                                            || long.class.equals(fieldType)) {
                                        realValue = Long.parseLong(value);
                                    } else if (Float.class.equals(fieldType)
                                            || float.class.equals(fieldType)) {
                                        realValue = Float.parseFloat(value);
                                    } else if (Double.class.equals(fieldType)
                                            || double.class.equals(fieldType)) {
                                        realValue = Double.parseDouble(value);
                                    }
                                } catch (Exception e) {
                                    throw new RuntimeException(
                                            "failed to parse " + fieldName + " for " + Config.class
                                                    .getName() + " from " + profileNameProperties, e);
                                }
                                if (realValue != null) {
                                    fd.setAccessible(true);
                                    try {
                                        fd.set(Config.class, realValue);
                                    } catch (IllegalAccessException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }

                        }
                    } else {
                        // Bean对象
                        fd.setAccessible(true);
                        Object subBean = fd.get(Config.class);
                        if (subBean == null) {
                            subBean = fieldType.newInstance();
                            fd.set(Config.class, subBean);
                        }
                        Field[] subFields = fieldType.getDeclaredFields();
                        if (subFields != null) {
                            for (Field sfd : subFields) {
                                String subFieldName = sfd.getName();
                                Class<?> subFieldType = sfd.getType();
                                Object realValue = null;
                                String key = fieldName + "." + subFieldName;
                                String value = null;
                                try {
                                    value = properties.getProperty(key);
                                } catch (Exception e) {
                                }
                                if (value != null) {
                                    Method strSetter = null;
                                    try {
                                        strSetter = fieldType.getDeclaredMethod("set" + CommonMethod.initCap(subFieldName), String.class);
                                        strSetter.setAccessible(true);
                                    } catch (Exception e) {
                                    }
                                    if (strSetter != null) {
                                        // 有字符串类型的setter方法
                                        try {
                                            strSetter.invoke(subBean, value);
                                        } catch (Exception e) {
                                            throw new RuntimeException(
                                                    "failed to parse " + key + " for Config." + fieldName + " from " + profileNameProperties, e);
                                        }
                                    } else {
                                        // 没有setter
                                        try {
                                            if (String.class.equals(subFieldType)) {
                                                realValue = value;
                                            } else if (Boolean.class.equals(subFieldType)
                                                    || boolean.class.equals(subFieldType)) {
                                                realValue = Boolean.parseBoolean(value);
                                            } else if (Byte.class.equals(subFieldType)
                                                    || byte.class.equals(subFieldType)) {
                                                realValue = Byte.parseByte(value);
                                            } else if (Short.class.equals(subFieldType)
                                                    || short.class.equals(subFieldType)) {
                                                realValue = Short.parseShort(value);
                                            } else if (Integer.class.equals(subFieldType)
                                                    || int.class.equals(subFieldType)) {
                                                realValue = Integer.parseInt(value);
                                            } else if (Long.class.equals(subFieldType)
                                                    || long.class.equals(subFieldType)) {
                                                realValue = Long.parseLong(value);
                                            } else if (Float.class.equals(subFieldType)
                                                    || float.class.equals(subFieldType)) {
                                                realValue = Float.parseFloat(value);
                                            } else if (Double.class.equals(subFieldType)
                                                    || double.class.equals(subFieldType)) {
                                                realValue = Double.parseDouble(value);
                                            }
                                        } catch (Exception e) {
                                            throw new RuntimeException(
                                                    "failed to parse " + key + " for Config." + fieldName + " from " + profileNameProperties, e);
                                        }
                                        if (realValue != null) {
                                            sfd.setAccessible(true);
                                            try {
                                                sfd.set(subBean, realValue);
                                            } catch (IllegalAccessException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception mre) {
            throw new RuntimeException(mre);
        }
//        {
//            String tmp = "box";
//            tmpSubDirectoryNames.add(tmp);
//            TesseractUtil.init(tmpDirectory + tmp + File.separator);
//            try {
//                // tesseract test
//                TesseractUtil.testEnv();
//            } catch (Exception e) {
//                if (isDevModel()) {
//                    // 开发环境, 输出错误, 但不终止执行
//                    log.error("TesseractUtil.testEnv() - " + e.toString());
//                } else {
//                    throw e;
//                }
//            }
//        }

//        try {
//            if (!isDevModel()) {
//                // 开发环境不验证，因为如果环境不通过会抛出Error，无法捕获
//                ImageUtil.testEnv();
//            }
//        } catch (Exception e) {
//            if (isDevModel()) {
//                // 开发环境, 输出错误, 但不终止执行
//                log.error("ImageUtil.testEnv() - " + e.toString());
//            } else {
//                throw e;
//            }
//        }
        initTmpDirectory();
    }

    /**
     * 初始化临时目录
     */
    private static void initTmpDirectory() {
        if (!File.separator.equals(tmpDirectory)) {
            // 删除临时目录(如果是根目录,则不删除)
            deletFiles(tmpDirectory);
        }
        File tmpDirectoryFile = new File(tmpDirectory);
        if (!tmpDirectoryFile.exists()) {
            // 临时目录不存在, 则创建
            tmpDirectoryFile.mkdirs();
        }
        for (String subDir : tmpSubDirectoryNames) {
            File subDirFile = new File(tmpDirectory + subDir);
            if (subDirFile.exists()) {
                // 这个子目录还存在, 则单独删除这个子目录
                deletFiles(subDirFile.getAbsolutePath());
            }
            subDirFile.mkdirs();
        }
    }

    /**
     * 是否是简单数据类型(不能是数组)（Number/Boolean/String/byte/short/int/long/float/double/boolean）
     *
     * @return
     */
    public static boolean isSimpleDataTypeOrSimpleArray(Class fieldType) {
        boolean flag = false;
        if (fieldType == null) {
            return flag;
        }
        if (String.class.equals(fieldType)
                || String[].class.equals(fieldType)
                || Boolean.class.equals(fieldType)
                || Boolean[].class.equals(fieldType)
                || boolean.class.equals(fieldType)
                || boolean[].class.equals(fieldType)
                || Byte.class.equals(fieldType)
                || Byte[].class.equals(fieldType)
                || byte.class.equals(fieldType)
                || byte[].class.equals(fieldType)
                || Short.class.equals(fieldType)
                || Short[].class.equals(fieldType)
                || short.class.equals(fieldType)
                || short[].class.equals(fieldType)
                || Integer.class.equals(fieldType)
                || Integer[].class.equals(fieldType)
                || int.class.equals(fieldType)
                || int[].class.equals(fieldType)
                || Long.class.equals(fieldType)
                || Long[].class.equals(fieldType)
                || long.class.equals(fieldType)
                || long[].class.equals(fieldType)
                || Float.class.equals(fieldType)
                || Float[].class.equals(fieldType)
                || float.class.equals(fieldType)
                || float[].class.equals(fieldType)
                || Double.class.equals(fieldType)
                || Double[].class.equals(fieldType)
                || double.class.equals(fieldType)
                || double[].class.equals(fieldType)) {
            // 是基本数据类型
            flag = true;
        }
        return flag;
    }

    /**
     * 支持删除文件、文件夹
     *
     * @param filePath
     * @return
     */
    private static boolean deletFiles(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        if (file.exists()) {
            if (file.isDirectory()) {
                // 文件夹
                File[] subFiles = file.listFiles();
                for (File f : subFiles) {
                    deletFiles(f.getAbsolutePath());
                }
                flag = file.delete();
            } else {
                flag = file.delete();
            }
        }
        return flag;
    }

    /******************
     * properties -start
     ******************/
    public static final class SecurityConfig {
        private boolean realSms = true;
        private long timeDeviation = 300000L;
        private int verifySmsMaxCountOneHour = 10;
        private int verifySmsMaxCountOneDay = 20;
        private int verifySmsMaxCountForIpOneHour = 20;
        private int verifySmsMaxCountForIpOneDay = 50;
        // 短信验证码的有效时长的毫秒数，默认5分钟
        private long smsVerifyCodeExpiredTime = 300000L;
        // 短信验证码的Token有效时长的毫秒数，默认1小时
        private long smsVerifyTokenExpiredTime = 1 * 60 * 60 * 1000L;

        SecurityConfig() {
        }

        public boolean isRealSms() {
            return realSms;
        }

        public long getTimeDeviation() {
            return timeDeviation;
        }

        public int getVerifySmsMaxCountOneHour() {
            return verifySmsMaxCountOneHour;
        }

        public int getVerifySmsMaxCountOneDay() {
            return verifySmsMaxCountOneDay;
        }

        public int getVerifySmsMaxCountForIpOneHour() {
            return verifySmsMaxCountForIpOneHour;
        }

        public int getVerifySmsMaxCountForIpOneDay() {
            return verifySmsMaxCountForIpOneDay;
        }

        public long getSmsVerifyCodeExpiredTime() {
            return smsVerifyCodeExpiredTime;
        }

        public long getSmsVerifyTokenExpiredTime() {
            return smsVerifyTokenExpiredTime;
        }
    }

    public static final class AliyunOss {
        private String accessKeyId;
        private String accessKeySecret;
        private String region;
        private String bucketName;

        private String stsRoleArn;
        private Long stsTokenDurationSeconds = 3600L;
        // (一定不以"/"开头,且一定以"/"结尾)
        private String userImgPath;
        // (一定不以"/"开头,且一定以"/"结尾)
        private String userVideoPath;

        // CDN加速域名,支持所有类型的文件的CDN加速访问
        private String cdnName;
        // 图片专有CDN域名(支持图片动态压缩;支持CDN加速),只支持图片文件的CDN加速访问
        private String imgCdnName;
        // #阿里云外网域名后面部分, endpoint则就直接把region拼接在前面
        private String domain = ".aliyuncs.com";
        // #阿里云内网域名后面部分, endpoint则就直接把region拼接在前面
        private String internalDomain = "-internal.aliyuncs.com";

        private String imgHost;
        private String newCdnName;


        /**
         * OSS服务接入点
         * #外网如: http://oss-cn-hangzhou.aliyuncs.com
         * #内网如: http://oss-cn-hangzhou-internal.aliyuncs.com
         */
        private String _endpoint;
        // 外部endpoint的host部分(无论当前服务器所处的环境, 都是返回外部接入点(提供给客户端使用))
        private String _outter_endpointHost;
        // 非配置项,通过(bucketName和region和domain产生);获取我们的OSS Bucket Url(一定是“/”结尾);
        // 如:http://lswuyou-test.oss-cn-hangzhou.aliyuncs.com/
        private String _bucketUrl;

        AliyunOss() {
        }


        void set_endpoint(String _endpoint) {
            throw new UnsupportedOperationException("Config.AliyunOss.set_endpoint()");
        }

        void set_outter_endpointHost(String _outter_endpointHost) {
            throw new UnsupportedOperationException("Config.AliyunOss.set_outter_endpointHost()");
        }

        void set_bucketUrl(String _bucketUrl) {
            throw new UnsupportedOperationException("Config.AliyunOss.set_bucketUrl()");
        }

        void setNewCdnName(String newCdnName) {
            // 必须以“/”结尾
            if (newCdnName != null && (!newCdnName.endsWith("/"))) {
                newCdnName = newCdnName + "/";
            }
            this.newCdnName = newCdnName;
        }

        void setImgHost(String imgHost) {
            // 必须以“/”结尾
            if (imgHost != null && (!imgHost.endsWith("/"))) {
                imgHost = imgHost + "/";
            }
            this.imgHost = imgHost;
        }


        void setCdnName(String cdnName) {
            // 必须以“/”结尾
            if (cdnName != null && (!cdnName.endsWith("/"))) {
                cdnName = cdnName + "/";
            }
            this.cdnName = cdnName;
        }

        void setImgCdnName(String imgCdnName) {
            // 必须以“/”结尾
            if (imgCdnName != null && (!imgCdnName.endsWith("/"))) {
                imgCdnName = imgCdnName + "/";
            }
            this.imgCdnName = imgCdnName;
        }

        void setUserImgPath(String userImgPath) {
            // 不要以“/”开头
            if (userImgPath != null) {
                while (userImgPath.startsWith("/")) {
                    userImgPath = userImgPath.substring(1);
                }
            }
            // 必须以“/”结尾
            if (userImgPath != null && (!userImgPath.endsWith("/"))) {
                userImgPath = userImgPath + "/";
            }
            this.userImgPath = userImgPath;
        }

        void setUserVideoPath(String userVideoPath) {
            // 不要以“/”开头
            if (userVideoPath != null) {
                while (userVideoPath.startsWith("/")) {
                    userVideoPath = userVideoPath.substring(1);
                }
            }
            // 必须以“/”结尾
            if (userVideoPath != null && (!userVideoPath.endsWith("/"))) {
                userVideoPath = userVideoPath + "/";
            }
            this.userVideoPath = userVideoPath;
        }

        /**********************************
         * getter
         **********************************/
        public String getAccessKeyId() {
            return accessKeyId;
        }

        public String getAccessKeySecret() {
            return accessKeySecret;
        }

        public String getRegion() {
            return region;
        }

        public String getBucketName() {
            return bucketName;
        }


        public String getStsRoleArn() {
            return stsRoleArn;
        }

        public Long getStsTokenDurationSeconds() {
            return stsTokenDurationSeconds;
        }

        /**
         * (一定不以"/"开头,且一定以"/"结尾)
         *
         * @return
         */
        public String getUserImgPath() {
            return userImgPath;
        }

        /**
         * (一定不以"/"开头,且一定以"/"结尾)
         *
         * @return
         */
        public String getUserVideoPath() {
            return userVideoPath;
        }

        /**
         * CDN加速域名,支持所有类型的文件的CDN加速访问
         * <p/>
         * 一定是"/"结尾
         *
         * @return
         */
        public String getCdnName() {
            return cdnName;
        }


        public String getImgHost() {
            return imgHost;
        }

        public String getNewCdnName() {
            return newCdnName;
        }

        /**
         * 图片专有CDN域名(支持图片动态压缩;支持CDN加速),只支持图片文件的CDN加速访问
         * <p/>
         * 一定是"/"结尾
         *
         * @return
         */
        public String getImgCdnName() {
            return imgCdnName;
        }

        /**
         * OSS服务接入点(根据当前服务环境, 自动判断应该采用内网还是外网环境)
         * #外网如: http://oss-cn-hangzhou.aliyuncs.com/
         * #内网如: http://oss-cn-hangzhou-internal.aliyuncs.com/
         *
         * @return
         */
        public String getEndpoint() {
            if (_endpoint != null) {
                return _endpoint;
            }
            String serverRuntimeRegion = Config.getServerRuntimeRegion();
            if (StringUtils.isNotBlank(serverRuntimeRegion) && this.region.contains(serverRuntimeRegion)) {
                // OSS所在阿里云的区域和服务运行环境相同, 则使用内网接入点
                // 内网地址
                _endpoint = "http://" + this.region + this.internalDomain;
            } else {
                // 外网地址
                _endpoint = "http://" + this.region + this.domain;
            }
            return _endpoint;
        }

        /**
         * 外网endpoint的host部分
         * <p/>
         * this.region + this.domain
         *
         * @return 如:http://lswuyou-test.oss-cn-hangzhou.aliyuncs.com/
         */
        public String getOutterEndpointHost() {
            if (_outter_endpointHost != null) {
                return _outter_endpointHost;
            }
            _outter_endpointHost = this.region + this.domain;
            return _outter_endpointHost;
        }

        /**
         * 获取我们的OSS Bucket Url(外网地址)一定是“/”结尾
         * <p/>
         * "http://" + bucketName + "." + region + domain + "/"
         *
         * @return 如:http://lswuyou-test.oss-cn-hangzhou.aliyuncs.com/
         */
        public String getBucketUrl() {
            if (_bucketUrl != null) {
                return _bucketUrl;
            }
            _bucketUrl = "http://" + bucketName + "." + region + domain + "/";
            return _bucketUrl;
        }
    }

    public static boolean isDevModel() {
        return devModel;
    }

    public static boolean isDebug() {
        return debug;
    }

    static void setSupportedVersion(String supportedVersionStr) {
        if (StringUtils.isNotBlank(supportedVersionStr)) {
            supportedVersionStr = supportedVersionStr.trim();
            String[] array = supportedVersionStr.split(",");
            List<String> supportedVersionList = new ArrayList<>(array.length);
            for (String tmp : array) {
                tmp = tmp.trim();
                if (tmp.length() != 0) {
                    supportedVersionList.add(tmp);
                }
            }
            Config.supportedVersion = supportedVersionList.toArray(new String[supportedVersionList.size()]);
        }
    }

    public static String[] getSupportedVersion() {
        return supportedVersion;
    }

    /**
     * #当前服务运行的服务器所在环境位置:如果是非阿里云中,就填空白
     * #杭州区域:cn-hangzhou
     *
     * @return
     */
    public static String getServerRuntimeRegion() {
        return serverRuntimeRegion;
    }

    /**
     * 临时文件目录，一定以“分隔符”结尾
     *
     * @return
     */
    public static String getTmpDirectory() {
        return tmpDirectory;
    }

    static void setTmpDirectory(String tmpDirectory) {
        if (!tmpDirectory.endsWith(File.separator)) {
            // 一定以分隔符结尾
            tmpDirectory = tmpDirectory + File.separator;
        }
        if (File.separator.equals("/")) {
            // 是*nix系统, 而不是windows
            String lianxuSeparator = File.separator + File.separator;
            while (tmpDirectory.contains(lianxuSeparator)) {
                // 把连续的分隔符替换成一个
                tmpDirectory = tmpDirectory.replace(lianxuSeparator, File.separator);
            }
        }
        if (!tmpDirectory.endsWith(File.separator)) {
            // 一定要以分隔符结尾
            tmpDirectory = tmpDirectory + File.separator;
        }
        Config.tmpDirectory = tmpDirectory;
    }

    public static SecurityConfig getSecurity() {
        return security;
    }

    public static AliyunOss getAliyunOss() {
        return aliyunOss;
    }

    public static String getLogoutUri() {
        return logoutUri;
    }

    public static String getRedBaseUrl() {
        return redBaseUrl;
    }

    public static String getWeixinBindUrl() {
        return weixinBindUrl;
    }

}
