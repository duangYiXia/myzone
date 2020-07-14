package com.xxkj.swaggerdemo.controller;


import com.xxkj.swaggerdemo.constants.Message;
import com.xxkj.swaggerdemo.constants.UriConstant;
import com.xxkj.swaggerdemo.model.ResponseDTO;
import com.xxkj.swaggerdemo.model.dto.MyTestDto;
import com.xxkj.swaggerdemo.model.entity.MyTest;
import com.xxkj.swaggerdemo.service.MyTestService;
import com.xxkj.swaggerdemo.uuid.PUID;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangjingxin
 * @since 2020-07-14
 */
@RestController
@RequestMapping(UriConstant.ROOT)
public class MyTestController {
    @Resource
    private MyTestService myTestService;

    @PostMapping(value = UriConstant.ADD)
    public ResponseDTO add(@RequestBody MyTestDto myTestDto) {
        try {
            return ResponseDTO.common(Message.SUCCESS, myTestService.save(new MyTest() {{
                setId(PUID.generate());
                setName(myTestDto.getName());
                setDes(myTestDto.getDes());
            }}));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.common(Message.FAIL, null);
        }
    }

    @GetMapping(value = UriConstant.LIST)
    public ResponseDTO list() {
        try {
            return ResponseDTO.common(Message.SUCCESS, myTestService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.common(Message.FAIL, null);
        }
    }
}
