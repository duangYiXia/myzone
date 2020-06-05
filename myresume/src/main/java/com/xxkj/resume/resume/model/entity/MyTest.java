package com.xxkj.resume.resume.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangjingxin
 * @since 2020-05-26
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
