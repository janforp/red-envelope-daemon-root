package com.hongbao.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 属性文件加载工具类
 *
 * @author wuqiang
 */
public class PropertiesLoader {

    private static Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

    public static Properties init(String propertyFilePathName) {
        Properties properties = null;
        InputStream in = null;
        try {
            in = PropertiesLoader.class.getClassLoader().getResourceAsStream(
                    propertyFilePathName);
            if (in != null) {
                properties = new Properties();
                properties.load(in);
            }
        } catch (IOException e) {
            logger.error("load " + propertyFilePathName
                    + " into PropertiesLoader error!", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("close " + propertyFilePathName + " error!");
                }
            }
        }
        return properties;
    }

    /**
     * 支持绝对路径的属性文件
     */
    public static Properties init(File propertiesFile) {
        if (propertiesFile == null || !propertiesFile.exists()) {
            return null;
        }
        Properties properties = null;
        InputStream in = null;
        try {
            in = new FileInputStream(propertiesFile);
            if (in != null) {
                properties = new Properties();
                properties.load(in);
            }
        } catch (IOException e) {
            logger.error("load " + propertiesFile
                    + " into PropertiesLoader error!", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("close " + propertiesFile + " error!");
                }
            }
        }
        return properties;
    }

}
