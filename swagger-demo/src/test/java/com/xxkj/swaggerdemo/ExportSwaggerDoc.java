package com.xxkj.swaggerdemo;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.nio.file.Paths;

/**
 * @Author zjx
 * @create 2020/7/14 18:05
 * @Des 使用RunWith注解和SpringBootTest注解，启动应用服务容器。
 * SpringBootTest.WebEnvironment.DEFINED_PORT表示使用application.yml定义的端口，
 *  而不是随机使用一个端口进行测试，这很重要。
 * Swagger2MarkupConfig 是输出文件的配置，如文件的格式和文件中的自然语言等
 * Swagger2MarkupConverter的from表示哪一个HTTP服务作为资源导出的源头(JSON格式)，
 *  可以自己访问试一下这个链接。8888是我的服务端口，需要根据你自己的应用配置修改。
 * toFile表示将导出文件存放的位置，不用加后缀名。也可以使用toFolder表示文件导出存放的路径。
 *  二者区别在于使用toFolder导出为文件目录下按标签TAGS分类的多个文件，使用toFile是导出一个文件（toFolder多个文件的合集）。
 */
@RunWith(SpringRunner.class)
/*@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)*/
public class ExportSwaggerDoc {
    /**
     * 生成AsciiDocs格式文档
     *
     * @throws Exception
     */
    @Test
    public void generateAsciiDocs() throws Exception {
        //    输出Ascii格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)//设置生成格式
                .withOutputLanguage(Language.ZH)//设置语言中文还是其他语言
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();
        //设置swagger-api的json来源
        Swagger2MarkupConverter.from(new URL("http://localhost:8081/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("./docs/asciidoc/generated/all"));//设置生成文件的路径
    }

    /**
     * 生成Markdown格式文档
     *
     * @throws Exception
     */
    @Test
    public void generateMarkdownDocs() throws Exception {
        //    输出Markdown格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL("http://localhost:8081/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("./docs/markdown/generated/all"));
    }

    /**
     * 生成Confluence格式文档
     *
     * @throws Exception
     */
    @Test
    public void generateConfluenceDocs() throws Exception {
        //    输出Confluence使用的格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.CONFLUENCE_MARKUP)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL("http://localhost:8081/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("./docs/confluence/generated"));
    }
}
