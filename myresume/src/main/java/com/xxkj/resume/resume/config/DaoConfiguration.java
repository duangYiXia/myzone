package com.xxkj.resume.resume.config;//package com.xxkj.myzone.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.activation.DataSource;
//import javax.naming.NamingException;
//import javax.persistence.EntityManagerFactory;
//import java.util.Properties;
//
//@Configuration
//@ComponentScan(basePackages = "com.xxkj.myzone")
//@EnableJpaRepositories(
//        basePackages = "com.xxkj.myzone",
//        entityManagerFactoryRef = "entityManagerFactory",
//        transactionManagerRef = "transactionManager")
//@EnableTransactionManagement
//class DaoConfiguration {
//
//    @Autowired private Environment environment;
//
//    @Value("${datasource.sampleapp.maxPoolSize:10}")
//    private int maxPoolSize;
//
//    /*
//     * Populate SpringBoot DataSourceProperties object directly from
//    application.yml
//     * based on prefix.Thanks to .yml, Hierachical data is mapped out of
//    the box with matching-name
//     * properties of DataSourceProperties object].
//     */
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSourceProperties dataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    /*
//     * Configure HikariCP pooled DataSource.
//     */
//    @Bean
//    public DataSource dataSource() {
//        DataSourceProperties dataSourceProperties = dataSourceProperties();
//        HikariDataSource dataSource =
//                (HikariDataSource)
//                        org.springframework.boot.jdbc.DataSourceBuilder.create(
//                                dataSourceProperties.getClassLoader())
//                                .driverClassName(dataSourceProperties.getDriverClassName())
//                                .url(dataSourceProperties.getUrl())
//                                .username(dataSourceProperties.getUsername())
//                                .password(dataSourceProperties.getPassword())
//                                .type(HikariDataSource.class)
//                                .build();
//        dataSource.setMaximumPoolSize(maxPoolSize);
//        return (DataSource) dataSource;
//    }
//
//    /*
//     * Entity Manager Factory setup.
//     */
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
//        LocalContainerEntityManagerFactoryBean factoryBean =
//                new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource((javax.sql.DataSource) dataSource());
//        factoryBean.setPackagesToScan(new String[] {"webroot.websrv"});
//        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
//        factoryBean.setJpaProperties(jpaProperties());
//        return factoryBean;
//    }
//
//    /*
//     * Provider specific adapter.
//     */
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
//        return hibernateJpaVendorAdapter;
//    }
//
//    /*
//     * Here you can specify any provider specific properties.
//     */
//    private Properties jpaProperties() {
//        Properties properties = new Properties();
//        properties.put(
//                "hibernate.dialect",
//                environment.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
//        return properties;
//    }
//
//    @Bean
//    @Autowired
//    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(emf);
//        return txManager;
//    }
//}