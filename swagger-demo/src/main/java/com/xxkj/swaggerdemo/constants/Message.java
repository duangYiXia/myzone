package com.xxkj.swaggerdemo.constants;

/**
 * @Author zjx
 * @create 2020/7/14 17:04
 */
public enum  Message {
    SUCCESS("A00000", "成功"),
    FAIL("A00001", "失敗");

    private String code;
    private String detail;

    public String getCode() {
        return code;
    }

    public String getDetail() {
        return detail;
    }

    Message(String code, String detail) {
        this.code = code;
        this.detail = detail;
    }
}
