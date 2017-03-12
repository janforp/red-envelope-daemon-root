package com.hongbao.api.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.hongbao.api.config.Config;
import com.hongbao.api.model.vo.AliyunOSSFederationTokenVo;
import org.springframework.stereotype.Service;

/**
 * Created by wuqiang on 15-9-10.
 *
 * @author wuqiang
 */
@Service
public class AliyunStsService {

    // 当前 STS API 版本
    public static final String STS_API_VERSION = "2015-04-01";

    // 目前只有"cn-hangzhou"这个region可用, 不要使用填写其他region的值
    public static final String REGION_CN_HANGZHOU = "cn-hangzhou";

    private Config.AliyunOss aliyunOss = Config.getAliyunOss();

    // profile的region, 暂时只能填写这个REGION_CN_HANGZHOU, 与OSS所在区域无关
    private IClientProfile clientProfile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, aliyunOss.getAccessKeyId(), aliyunOss.getAccessKeySecret());

    private String appUserOssPolicy = "{\n" +
            "    \"Version\": \"1\",\n" +
            "    \"Statement\": [\n" +
            "        {\n" +
            "            \"Effect\": \"Allow\",\n" +
            "            \"Action\": [\n" +
            "                \"oss:PutObject\",\n" +
            "                \"oss:GetObject\"\n" +
            "            ],\n" +
            "            \"Resource\": [\n" +
            "                \"acs:oss:*:*:" + aliyunOss.getBucketName() + "/" + aliyunOss.getUserImgPath() + "*\"\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    public AliyunOSSFederationTokenVo getOssUploadToken(String userId) {

        try {
            String policy = String.format(appUserOssPolicy, userId);

            // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
            IAcsClient client = new DefaultAcsClient(clientProfile);

            // 创建一个 AssumeRoleRequest 并设置请求参数
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setVersion(STS_API_VERSION);
            request.setMethod(MethodType.POST);
            // 此处必须为 HTTPS
            request.setProtocol(ProtocolType.HTTPS);

            request.setRoleArn(aliyunOss.getStsRoleArn());
            request.setRoleSessionName(userId);
            request.setPolicy(policy);
            request.setDurationSeconds(aliyunOss.getStsTokenDurationSeconds());

            // 发起请求，并得到response
            final AssumeRoleResponse response = client.getAcsResponse(request);
            AssumeRoleResponse.Credentials credentials = response.getCredentials();

//            long startTimeInSecond = System.currentTimeMillis() / 1000;
//            long expiration = startTimeInSecond + aliyunOss.getStsTokenDurationSeconds();
//            expiration = expiration - (5 * 60); // 提前5分钟让这个token失效，避免出现极端情况

            AliyunOSSFederationTokenVo ossTokenVo = new AliyunOSSFederationTokenVo();

            // 访问密钥标识
            ossTokenVo.setTempAk(credentials.getAccessKeyId());
            // 访问密钥
            ossTokenVo.setTempSk(credentials.getAccessKeySecret());
            // 安全令牌
            ossTokenVo.setSecurityToken(credentials.getSecurityToken());
            // 失效时间
            ossTokenVo.setExpiration(credentials.getExpiration());

            return ossTokenVo;

        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        // 只有 RAM用户（子账号）才能调用 AssumeRole 接口
        // 阿里云主账号的AccessKeys不能用于发起AssumeRole请求
        // 请首先在RAM控制台创建一个RAM用户，并为这个用户创建AccessKeys
        String accessKeyId = "kpjpBeIsyFWSjSvw";
        String accessKeySecret = "QSO1a68D3GXcLz5jm7TYYRdt6JaabT";
        // AssumeRole API 请求参数: RoleArn, RoleSessionName, Policy, and DurationSeconds
        // RoleArn 需要在 RAM 控制台上获取
        String roleArn = "acs:ram::1399766284432007:role/sts-appuser-creater-role";
        // RoleSessionName 是临时Token的会话名称，自己指定用于标识你的用户，主要用于审计，或者用于区分Token颁发给谁
        // 但是注意RoleSessionName的长度和规则，不要有空格，只能有'-' '_' 字母和数字等字符
        // 具体规则请参考API文档中的格式要求
        String roleSessionName = "12345";
        // 如何定制你的policy?
        String policy = "{\n" +
                "    \"Version\": \"1\",\n" +
                "    \"Statement\": [\n" +
                "        {\n" +
                "            \"Effect\": \"Allow\",\n" +
                "            \"Action\": [\n" +
                "                \"oss:PutObject\",\n" +
                "                \"oss:GetObject\"\n" +
                "            ],\n" +
                "            \"Resource\": [\n" +
                "                \"acs:oss:*:*:ag-aw-test/i/12345/*\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        // 此处必须为 HTTPS
        ProtocolType protocolType = ProtocolType.HTTPS;
        try {
            final AssumeRoleResponse response = assumeRole(accessKeyId, accessKeySecret, roleArn, roleSessionName, policy, protocolType);
            System.out.println("Expiration: " + response.getCredentials().getExpiration());
            System.out.println("Access Key Id: " + response.getCredentials().getAccessKeyId());
            System.out.println("Access Key Secret: " + response.getCredentials().getAccessKeySecret());
            System.out.println("Security Token: " + response.getCredentials().getSecurityToken());
        } catch (ClientException e) {
            System.out.println("Failed to get a token.");
            System.out.println("Error code: " + e.getErrCode());
            System.out.println("Error message: " + e.getErrMsg());
        }
    }

    static AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret,
                                         String roleArn, String roleSessionName, String policy,
                                         ProtocolType protocolType) throws ClientException {
        try {
            // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
            IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            // 创建一个 AssumeRoleRequest 并设置请求参数
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setVersion(STS_API_VERSION);
            request.setMethod(MethodType.POST);
            request.setProtocol(protocolType);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy);
            // 发起请求，并得到response
            final AssumeRoleResponse response = client.getAcsResponse(request);
            return response;
        } catch (ClientException e) {
            throw e;
        }
    }

}
