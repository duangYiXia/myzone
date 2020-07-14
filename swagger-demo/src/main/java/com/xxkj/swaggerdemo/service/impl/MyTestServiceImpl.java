package com.xxkj.swaggerdemo.service.impl;

import com.xxkj.swaggerdemo.model.entity.MyTest;
import com.xxkj.swaggerdemo.mapper.MyTestMapper;
import com.xxkj.swaggerdemo.service.MyTestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangjingxin
 * @since 2020-07-14
 */
@Service
public class MyTestServiceImpl extends ServiceImpl<MyTestMapper, MyTest> implements MyTestService {

}
