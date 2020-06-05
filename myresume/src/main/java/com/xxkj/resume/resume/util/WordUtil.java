package com.xxkj.resume.resume.util;

import com.xxkj.resume.resume.domain.entity.WordResumeDto;
import freemarker.log.Logger;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @Description 利用FreeMarker导出Word
 */
public class WordUtil {
    private Logger log = Logger.getLogger(WordUtil.class.toString());

    public String createDoc(Map dataMap, String path, String srcFileName) {
        String fileName = UUID.randomUUID().toString() + ".doc";
        HttpServletResponse httpServletResponse = ServletCommonUtil.getHttpServletResponse();
        try {
            httpServletResponse.setHeader("Content-Disposition",
                    "attachment; filename=\"" + new String(fileName.getBytes("gbk"), "iso-8859-1") + "\"");
            httpServletResponse.setContentType("application/octet-stream;charset=UTF-8");

            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
            /*Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("utf-8");
            configuration.setDirectoryForTemplateLoading(new File(filePath));
            Template t = configuration.getTemplate("证明.ftl");
            Writer writer = httpServletResponse.getWriter();
            t.process(dataMap, new BufferedWriter(writer));*/

            FreemarkerUtils.build(this.getClass(), path).setTemplate(srcFileName).generate(dataMap,
                    httpServletResponse.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileName;
    }

    public static String getRootClassPath() {
        String rootClassPath = "";
        try {
            String path = WordUtil.class.getClassLoader().getResource("").toURI().getPath();
            rootClassPath = new File(path).getAbsolutePath();
        } catch (Exception e) {
            String path = WordUtil.class.getClassLoader().getResource("").getPath();
            rootClassPath = new File(path).getAbsolutePath();
        }
        return rootClassPath;
    }

    /**
     * 获得图片的Base64编码
     *
     * @param imgFile
     * @return
     */
    public String getImageStr(String imgFile) {
        //imgFile = getRootClassPath() + imgFile;
        imgFile = "D:/code/freemarkerWord/src/main/resources/1.jpeg";
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
        } catch (FileNotFoundException e) {
            log.error("加载图片未找到", e);
            e.printStackTrace();
        }
        try {
            data = new byte[in.available()];
            //注：FileInputStream.available()方法可以从输入流中阻断由下一个方法调用这个输入流中读取的剩余字节数
            in.read(data);
            in.close();
        } catch (IOException e) {
            log.error("IO操作图片错误", e);
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);

    }

    public static void main(String[] args) {

    }

    public static String exportResume() {
        WordUtil emw = new WordUtil();
        Map dataMap = new HashMap(40);
        // 头像
        dataMap.put("image", new WordUtil().getImageStr("/1.jpg"));
        // 头 自定义信息
        dataMap.put("userName", "test");
        dataMap.put("headDf1", "1版");
        dataMap.put("headVal1", "123456");
        dataMap.put("headDf2", "1版");
        dataMap.put("headVal2", "123456");
        dataMap.put("headDf3", "1版");
        dataMap.put("headVal3", "123456");

        // 头 基本信息
        dataMap.put("basicSchool", "黄xx");
        dataMap.put("basicPhone", 26);
        dataMap.put("basicMail", "1999/12");
        dataMap.put("basicPosition", "群众");
        dataMap.put("basicAge", "汉");
        dataMap.put("basicHonor", "1997/09/01");

        dataMap.put("tecDirection", "111");
        dataMap.put("tecDirectionVal", "222");
        dataMap.put("workHis", "333");
        dataMap.put("time", "444");
        dataMap.put("companyName", "555");

        dataMap.put("post", "111");
        dataMap.put("itemIntroduction", "222");

        // 项目或实习经验
        dataMap.put("itemExp", "项目或实习经验");
        dataMap.put("itemName", "444");
        dataMap.put("itemPeriod", "555");
        dataMap.put("itemPersons", "555");
        dataMap.put("itemContent", "555");

        // 个人荣誉
        dataMap.put("success", "个人荣誉");
        dataMap.put("successContent", "444");
        // 兴趣爱好
        dataMap.put("hobby", "兴趣爱好");
        dataMap.put("hobbyContent", "555");

        List list = new ArrayList();
        list.add(new WordResumeDto() {{
            setTime("2020-12-09 12:33:23");
            setCompanyName("ssssss");
            setPost("ddd");
            setItemIntroduction("xxxxxx");
        }});
        list.add(new WordResumeDto() {{
            setTime("2020-12-09 14:33:23");
            setCompanyName("22222");
            setPost("222");
            setItemIntroduction("222");
        }});
        dataMap.put("list", list);

        return emw.createDoc(dataMap, "/templates/", "resumeTemplate.ftl");
    }
}