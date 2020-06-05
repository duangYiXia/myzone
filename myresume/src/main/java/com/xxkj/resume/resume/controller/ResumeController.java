package com.xxkj.resume.resume.controller;

import com.lowagie.text.DocumentException;
import com.xxkj.resume.resume.service.ConfigService;
import com.xxkj.resume.resume.util.PdfUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * @Author zjx
 * @create 2020/3/20 1:02
 */
@Controller
@RequestMapping
public class ResumeController {
    @Value("${DEST}")
    public String dest;

    @Autowired
    private ConfigService configService;

    @RequestMapping("/info")
    public String otherInfo(Model model) {
        model.addAttribute("name", configService.getLeaveInfo("leave"));
        return "index";
    }

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", configService.getLeaveInfo("leave"));
        return "index";
    }

    @RequestMapping("/lookPdf")
    public void indexHtml(Map<String, Object> model) throws FileNotFoundException, DocumentException {
        // 读取pdf并预览
        PdfUtils.readPdf(dest);
        //PDFGenerator pdfGenerator = new PDFGenerator("templates/",".html");
        //pdfGenerator.generate(new File("D:/code/myzone/target/classes/static/file/finishResume.pdf"),"card/voucher3", model);
    }

    @GetMapping(value = "/preview")
    public void pdfStreamHandler(HttpServletRequest request, HttpServletResponse response) {
        //PDF文件地址
        File file = new File(dest);
        if (file.exists()) {
            byte[] data = null;
            FileInputStream input=null;
            try {
                input= new FileInputStream(file);
                data = new byte[input.available()];
                input.read(data);
                response.getOutputStream().write(data);
            } catch (Exception e) {
                System.out.println("pdf文件处理异常：" + e);
            }finally{
                try {
                    if(input!=null){
                        input.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
