package com.xxkj.swaggerdemo.exception;

import com.xxkj.swaggerdemo.constants.Message;

/**
 * @Author zjx
 * @create 2020/7/14 17:16
 */
public class DemoException extends RuntimeException {

    private String code;
    private String colums;
    private StringBuffer log;

    public DemoException() {
        super();
    }

    public DemoException(Message message, Throwable throwable) {
        super(throwable);
        this.code = message.getCode();
    }
    public DemoException(String code, String colums, StringBuffer log, Throwable throwable) {
        super(throwable);
        this.code = code;
        this.colums = colums;
        this.log = log;
    }

}
