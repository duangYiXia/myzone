package com.xxkj.swaggerdemo.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangjingxin
 * @since 2020-07-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="MyTest对象", description="")
public class MyTestVo implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "名称", example = "test-name")
    private String name;

    @ApiModelProperty(value = "描述", example = "12345")
    private String des;
}
