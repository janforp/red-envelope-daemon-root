package com.hongbao.api.model.vo;

import java.io.Serializable;

public class AliyunOSSVo implements Serializable {

    // 阿里云bucketName
    private String bucketName;
    // CDN加速域名,支持所有类型的文件的CDN加速访问
    private String cdnName;
    // 用户上传图片文件到OSS的路径
    private String imgPath;
    // 图片host
    private String imgHost;
    // CDN加速域名
    private String newCdnName;

    public String getImgHost() {
        return imgHost;
    }

    public void setImgHost(String imgHost) {
        this.imgHost = imgHost;
    }

    public String getNewCdnName() {
        return newCdnName;
    }

    public void setNewCdnName(String newCdnName) {
        this.newCdnName = newCdnName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getCdnName() {
        return cdnName;
    }

    public void setCdnName(String cdnName) {
        this.cdnName = cdnName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

}
