package com.hongbao.api.dao;

import com.hongbao.api.model.ReVerifyCode;

import java.util.List;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-08-11
 */
public interface ReVerifyCodeDAO {
    int deleteByPrimaryKey(Long id);

    void insert(ReVerifyCode record);

    void insertSelective(ReVerifyCode record);

    void insertBatch(List<ReVerifyCode> records);

    ReVerifyCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReVerifyCode record);

    int updateByPrimaryKey(ReVerifyCode record);

    String selectLatestCodeByCellphoneAndNotVerifyWithinTime(String cellphone, long startTime);

    /**
     * 查询：where code_verify_status=#VerifyCodeVerifyStatus.not_verify.val# and generate_time >
     * #startTime# and telephone = #telephone#
     *
     * @param cellphone 验证码接收手机
     * @param startTime 在这个时间点之后
     */
    List<ReVerifyCode> selectByCellphoneAndCodeNotVerifyWithinTime(String cellphone, long startTime);

    /**
     * 把ids对应的 update code_verify_status = #verifyStatus# where id in(ids)
     */
    void updateCodeVerifyStatusByIds(List<Long> ids, int verifyStatus);

    /**
     * 把ids对应的 update code_verify_status = #verifyStatus# where id = #id#
     */
    void updateCodeVerifyStatusById(Long id, int verifyStatus);

}