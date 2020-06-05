package com.xxkj.resume.resume.controller;

import com.xxkj.resume.resume.domain.entity.CommonResult;
import com.xxkj.resume.resume.util.WordUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zjx
 * @create 2020/3/9 0:40
 */
@RestController
public class ExportController {

    @GetMapping(value = "/exportResumeForm")
    public CommonResult exportRecomApprovalForm() throws Exception {
        WordUtil.exportResume();

        return new CommonResult() {{
            setData(null);
            setStatus(true);
            setMessage("下载成功");
            setCode(60000);
        }};
    }
}
