package com.gavin.enums;

/**
 * Created by 17428 on 2023/8/13.
 */
public enum  ProcessCodeEnum {


    SUCCESS("0000","成功"),
    FAIL("9999","系统繁忙，请稍后重试"),
    UNKNOWN("1111", "未知异常，请联系管理员"),
    PROCESS_ERR("P999","业务处理异常"),
    PARAM_WARN("A999","参数异常"),
    TICKET_VAILD("1100","非法的登录态"),
    PRIVACY_VALID("1101","未同意隐私服务员协议"),
    CONFIRM("0001","确认弹出框"),
    LOGIN_FAIL("1102","用户名或密码错误"),
    REPEAT("0002","重复提交"),
    LOGIN_FAIl("L999","登录异常，联系管理员");

    private String code;
    private String message;


    ProcessCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
