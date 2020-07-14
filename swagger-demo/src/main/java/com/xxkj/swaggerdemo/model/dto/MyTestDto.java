package com.xxkj.swaggerdemo.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
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
public class MyTestDto implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "名称", example = "test-name", required = true)
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "描述", example = "12345")
    private String des;
}
