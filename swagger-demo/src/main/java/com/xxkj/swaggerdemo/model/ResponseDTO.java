package com.xxkj.swaggerdemo.model;

import com.xxkj.swaggerdemo.constants.Message;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author zjx
 * @create 2020/3/20 10:30
 */
@Data
@ApiModel("返回类")
public class ResponseDTO<T> implements Serializable {

    @ApiModelProperty("线程号")
    private String txID;

    @ApiModelProperty("返回的状态")
    private boolean status;

    @ApiModelProperty("返回信息码")
    private String code;

    @ApiModelProperty("返回数据")
    private T data;

    @ApiModelProperty("错误返回的文字信息")
    private String message;

    private ResponseDTO() {

    }

    public static ResponseDTO success() {
        return new ResponseDTO() {{
            setCode("A00000");
            setMessage("成功");
            setStatus(true);
            setTxID(String.valueOf(Thread.currentThread().getId()));
        }};
    }

    public static <T> ResponseDTO<T> success(T data) {
        return new ResponseDTO<T>() {{
            setCode("A00000");
            setMessage("成功");
            setStatus(true);
            setData(data);
            setTxID(String.valueOf(Thread.currentThread().getId()));
        }};
    }

    public static <T> ResponseDTO<T> common(Message message, T data) {
        return new ResponseDTO<T>() {{
            setCode(message.getCode());
            setMessage(message.getDetail());
            setStatus(true);
            setData(data);
            setTxID(String.valueOf(Thread.currentThread().getId()));
        }};
    }

    public static ResponseDTO fail() {
        return new ResponseDTO() {{
            setCode("A00001");
            setMessage("失败");
            setStatus(true);
            setTxID(String.valueOf(Thread.currentThread().getId()));
        }};
    }
}

