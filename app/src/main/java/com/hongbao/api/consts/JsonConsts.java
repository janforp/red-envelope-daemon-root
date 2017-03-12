package com.hongbao.api.consts;

import com.hongbao.api.util.JsonUtil;

/**
 * Created by wuqiang on 15-8-11.
 * <p/>
 * 一些固定的JSON结果
 *
 * @author wuqiang
 */
public class JsonConsts {

    // 只含有一个成功code的json
    public static final String SUCCESS_default = JsonUtil.buildSuccessMsgJson(null);

    public static final String ERROR_404 = JsonUtil.buildErrorJson(404, MsgConsts.ERROR_404);
    public static final String ERROR_400 = JsonUtil.buildErrorJson(400, MsgConsts.ERROR_400);
    public static final String ERROR_system_error = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_EXCEPTION, MsgConsts.SYSTEM_ERROR);
    public static final String ERROR_unable_encrypt_response = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_EXCEPTION, MsgConsts.UNABLE_ENCRYPT_RESPONSE_ERROR);

    public static final String ERROR_version_required = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_ILLEGAL_REQUEST, "version required.");
    public static final String ERROR_platform_required = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_ILLEGAL_REQUEST, "platform required.");
    public static final String ERROR_screen_info_required = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_ILLEGAL_REQUEST, "screen-width and screen-height required.");
    public static final String ERROR_version_illegal = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_ILLEGAL_REQUEST, "version illegal.");
    public static final String ERROR_systemParameter_required = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_ILLEGAL_REQUEST, "s required.");
    public static final String ERROR_failed_to_decrypt_systemParameter = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_ILLEGAL_REQUEST, "failed to decrypt s.");
    public static final String ERROR_decryptSystemParameter_required = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_ILLEGAL_REQUEST, "s required.");
    public static final String ERROR_parse_decryptSystemParameter_error = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_ILLEGAL_REQUEST, "parse parameter error.");
    public static final String ERROR_timestamp_deviceId_aesKey_reqId_required = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_ILLEGAL_REQUEST, "timestamp, deviceId, key, reqId required.");
    public static final String ERROR_timestamp = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_TIMESTAMP_ERROR, MsgConsts.TIMESTAMP_ERROR);
    public static final String ERROR_reqId = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_ILLEGAL_REQUEST, MsgConsts.REQID_ERROR);
    public static final String ERROR_failed_to_decrypt_requestBody = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_ILLEGAL_REQUEST, "failed to decrypt data.");
    public static final String ERROR_userKey_required = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_ILLEGAL_REQUEST, "uk required.");
    public static final String ERROR_timestamp_deviceId_reqId_required = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_ILLEGAL_REQUEST, "timestamp, deviceId, reqId required.");

    public static final String ERROR_401_current_user_not_exist = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_UNAUTHORIZED, MsgConsts.USER_NOT_EXIST);
    public static final String ERROR_401_user_permanent_blocked = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_UNAUTHORIZED, MsgConsts.USER_PERMANENT_BLOCKED);
    public static final String ERROR_401_user_password_change = JsonUtil.buildErrorJson(BaseConsts.JSON_INFO_ERROR_CODE_UNAUTHORIZED, MsgConsts.USER_PASSWORD_CHANGE);


    public static final String ERROR_user_not_exist = JsonUtil.buildErrorJson(MsgConsts.USER_NOT_EXIST);
    public static final String ERROR_login_error = JsonUtil.buildErrorJson(MsgConsts.LOGINNAME_PASSWORD_ERROR);
    public static final String ERROR_user_permanent_blocked = JsonUtil.buildErrorJson(MsgConsts.USER_PERMANENT_BLOCKED);
    public static final String ERROR_parameters = JsonUtil.buildErrorJson(MsgConsts.PARAMETERS_ERROR);
    public static final String ERROR_length = JsonUtil.buildErrorJson(MsgConsts.PARAMETERS_LENGTH_ERROR);
    public static final String ERROR_password_illegal = JsonUtil.buildErrorJson(MsgConsts.PASSWORD_ILLEGAL);
    public static final String ERROR_phone_number_illegal = JsonUtil.buildErrorJson(MsgConsts.PHONE_NUMBER_ILLEGAL);
    public static final String ERROR_phone_number_already_register = JsonUtil.buildErrorJson(MsgConsts.PHONE_NUMBER_ALREADY_REGISTER);
    public static final String ERROR_phone_number_not_register = JsonUtil.buildErrorJson(MsgConsts.PHONE_NUMBER_NOT_REGISTER);
    public static final String ERROR_verify_sms_send_fail = JsonUtil.buildErrorJson(MsgConsts.VERIFY_SMS_SEND_FAIL);
    public static final String ERROR_verify_code_expire = JsonUtil.buildErrorJson(MsgConsts.VERIFY_CODE_EXPIRE);
    public static final String ERROR_verify_code_wrong = JsonUtil.buildErrorJson(MsgConsts.VERIFY_CODE_WRONG);
    public static final String ERROR_already_band_phone = JsonUtil.buildErrorJson(MsgConsts.ALREADY_BAND_PHONE);

    public static final String SUCCESS_RESET_PWD = JsonUtil.buildSuccessMsgJson(MsgConsts.RESET_PWD_SUCCESS);
    public static final String ERROR_RESET_PWD = JsonUtil.buildErrorJson(MsgConsts.RESET_PWD_ERROR);
    public static final String ERROR_input_empty_phone = JsonUtil.buildErrorJson(MsgConsts.INPUT_EMPTY_PHONE);
    public static final String ERROR_phone_number_already_used = JsonUtil.buildErrorJson(MsgConsts.PHONE_NUMBER_ALREADY_USED);

    public static final String ERROR_captcha_code_expire = JsonUtil.buildErrorJson(MsgConsts.CAPTCHA_CODE_EXPIRE);
    public static final String ERROR_captcha_code_wrong = JsonUtil.buildErrorJson(MsgConsts.CAPTCHA_CODE_WRONG);

    public static final String SUCCESS_verify_sms_send_success = JsonUtil.buildSuccessMsgJson(MsgConsts.VERIFY_SMS_SEND_SUCCESS);

}
