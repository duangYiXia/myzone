package com.xxkj.resume.db.mycat;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Project code genertor
 *
 * @author lshaci
 * @since 0.0.3
 */
public class CodeGenerator {


    /**
     * Project package
     */
    private static String projectPackage;

    private static String outputFilePath;

    private static String outputMapXmlFilePath;

    private static String tableNames;

    /**
     * 包配置
     */
    private static String setMapper;
    private static String setEntity;
    private static String setService;
    private static String setController;

    /**
     * Database url
     */
    private static String url;
    /**
     * Database username
     */
    private static String username;
    /**
     * Database password
     */
    private static String password;
    /**
     * Database driver class
     */
    private static String driverClass;

    /**
     * 文件名后缀
     */
    private static String fileSuffix = ".java";

    /**
     * Init database information
     */
    static {
        Properties properties = new Properties();
        InputStream i = CodeGenerator.class.getResourceAsStream("/generatorConfig.properties");
        try {
            properties.load(i);
            url = properties.getProperty("generator.jdbc.url");
            username = properties.getProperty("generator.jdbc.username");
            password = properties.getProperty("generator.jdbc.password");
            driverClass = properties.getProperty("generator.jdbc.driverClass");
            projectPackage = properties.getProperty("projectPackage");
            outputFilePath = properties.getProperty("outputFilePath");
            outputMapXmlFilePath = properties.getProperty("outputMapXmlFilePath");
            tableNames = properties.getProperty("tableNames");
            setMapper = properties.getProperty("setMapper");
            setEntity = properties.getProperty("setEntity");
            setService = properties.getProperty("setService");
            setController = properties.getProperty("setController");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * main method, execute code generator
     */
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");

        String javaPath = projectPackage.replaceAll("\\.", "/");
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("zhangjingxin");
        gc.setOpen(false); // 是否打开输出目录
        //gc.setOutputDir(projectPath + "/src/main/java"); // 输出文件目录
        gc.setFileOverride(true); // 是否覆盖已有文件
        gc.setSwagger2(true);  // 实体属性 Swagger2 注解
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setBaseResultMap(true); // mapper.xml中生成BaseResultMap
        gc.setActiveRecord(true);
        gc.setBaseColumnList(true);

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        //dsc.setSchemaName("public");
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setDriverName(driverClass);

        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(projectPackage);
        //pc.setModuleName("model名"); 自定义包名
        pc.setMapper(setMapper);
        pc.setEntity(setEntity);
        pc.setService(setService);
        pc.setController(setController);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        /**mapper xml文件*/
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {

            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + outputMapXmlFilePath + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }

        });
        /**控制层*/
        templatePath = "/templates/controller.java.ftl";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 + pc.getModuleName()
                String expand = projectPath + outputFilePath + "controller";
                String entityFile = String.format(expand + File.separator + "%s" + fileSuffix, tableInfo.getControllerName());
                return entityFile;
            }
        });

        /**业务接口层*/
        templatePath = "/templates/service.java.ftl";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 + pc.getModuleName()
                String expand = projectPath + outputFilePath + "service";
                String entityFile = String.format(expand + File.separator + "%s" + fileSuffix, tableInfo.getServiceName());
                return entityFile;
            }
        });
        /**业务实现层*/
        templatePath = "/templates/serviceImpl.java.ftl";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 + pc.getModuleName()
                String expand = projectPath + outputFilePath + "service/impl";
                String entityFile = String.format(expand + File.separator + "%s" + fileSuffix, tableInfo.getServiceImplName());
                return entityFile;
            }
        });

        /**数据mapper层*/
        templatePath = "/templates/mapper.java.ftl";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 + pc.getModuleName()
                String expand = projectPath + outputFilePath + "mapper";
                String entityFile = String.format(expand + File.separator + "%s" + fileSuffix, tableInfo.getMapperName());
                return entityFile;
            }
        });

        /**数据entity层*/
        templatePath = "/templates/entity.java.ftl";
        // 自定义配置会优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 + pc.getModuleName()
                String expand = projectPath + outputFilePath + "model/entity";
                String entityFile = String.format(expand + File.separator + "%s" + fileSuffix, tableInfo.getEntityName());
                return entityFile;
            }
        });

		/*cfg.setFileOutConfigList(new IFileCreate() {
			@Override
			public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
				// 判断自定义文件夹是否需要创建
				checkDir("调用默认方法创建目录");
				return false;
			}
		});*/

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController(null);
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //strategy.setExclude("");
        // 表名
        strategy.setInclude(tableNames);
        strategy.setControllerMappingHyphenStyle(true);
        /**表前缀，要自动去除添加*/
        //strategy.setTablePrefix("t_");

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        mpg.execute();
    }
}
