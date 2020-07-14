package com.xxkj.swaggerdemo.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@ApiModel(value="MyTest对象", description="")
public class MyTest extends Model<MyTest> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String des;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
