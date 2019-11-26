package studyserverone.many_data_source;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源配置
 * @author 朱同
 */
@Configuration
@MapperScan(basePackages = {"studyserverone.study01.shiro.server","studyserverone.easy_poi.dao","studyserverone.easy_poi.dao2"}, sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataSourceFactory {
    
    
   @Value("${mybatis.mapperLocations}")
   private String mapperLocations;
    
    /***
     * 创建 DruidXADataSource 1 用@ConfigurationProperties自动配置属性
     */
    @Bean(name = "druidDataSourceOne")
    @ConfigurationProperties("spring.datasource.druid.db1")
    public DataSource druidDataSourceOne() {
        return new DruidXADataSource();
    }
    
    /***
     * 创建 DruidXADataSource 2
     */
    @Bean(name = "druidDataSourceTwo")
    @ConfigurationProperties("spring.datasource.druid.db2")
    public DataSource druidDataSourceTwo() {
        return new DruidXADataSource();
    }
    
    /**
     * 创建支持XA事务的Atomikos数据源1
     */
    @Bean(name = "dataSourceOne")
    public DataSource dataSourceOne(@Qualifier("druidDataSourceOne") DataSource druidDataSourceOne) {
        AtomikosDataSourceBean sourceBean = new AtomikosDataSourceBean();
        sourceBean.setXaDataSource((DruidXADataSource) druidDataSourceOne);
        // 必须为数据源指定唯一标识
        sourceBean.setUniqueResourceName("druidDataSourceOne");
        return sourceBean;
    }
    
    /**
     * 创建支持XA事务的Atomikos数据源2
     */
    @Bean(name = "dataSourceTwo")
    public DataSource dataSourceTwo(@Qualifier("druidDataSourceTwo") DataSource druidDataSourceTwo) {
        AtomikosDataSourceBean sourceBean = new AtomikosDataSourceBean();
        sourceBean.setXaDataSource((DruidXADataSource) druidDataSourceTwo);
        sourceBean.setUniqueResourceName("druidDataSourceTwo");
        return sourceBean;
    }
    
    /**
     * @param dataSourceOne 数据源1
     * @return 数据源1的会话工厂
     */
    @Bean(name = "sqlSessionFactoryOne")
    public SqlSessionFactory sqlSessionFactoryOne(@Qualifier("dataSourceOne") DataSource dataSourceOne)
            throws Exception {
        return createSqlSessionFactory(dataSourceOne);
    }
    
    
    /**
     * @param dataSourceTwo 数据源2
     * @return 数据源2的会话工厂
     */
    @Bean(name = "sqlSessionFactoryTwo")
    public SqlSessionFactory sqlSessionFactoryTwo(@Qualifier("dataSourceTwo") DataSource dataSourceTwo)
            throws Exception {
        return createSqlSessionFactory(dataSourceTwo);
    }
    
    
    /***
     * sqlSessionTemplate与Spring事务管理一起使用，以确保使用的实际SqlSession是与当前Spring事务关联的,
     * 此外它还管理会话生命周期，包括根据Spring事务配置根据需要关闭，提交或回滚会话
     * @param sqlSessionFactoryOne 数据源1
     * @param sqlSessionFactoryTwo 数据源2
     */
    @Bean
    public CustomSqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryOne") SqlSessionFactory sqlSessionFactoryOne,
                                                       @Qualifier("sqlSessionFactoryTwo")SqlSessionFactory sqlSessionFactoryTwo) {
        Map<Object, SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>(2);
        sqlSessionFactoryMap.put(DataSourceTypeEnum.DataSourceOne.getDbName(), sqlSessionFactoryOne);
        sqlSessionFactoryMap.put(DataSourceTypeEnum.DataSourceTwo.getDbName(), sqlSessionFactoryTwo);
        
        CustomSqlSessionTemplate customSqlSessionTemplate = new CustomSqlSessionTemplate(sqlSessionFactoryOne);
        customSqlSessionTemplate.setDefaultTargetSqlSessionFactory(sqlSessionFactoryOne);
        customSqlSessionTemplate.setTargetSqlSessionFactories(sqlSessionFactoryMap);
        return customSqlSessionTemplate;
    }
    
    /***
     * 自定义会话工厂
     * @param dataSource 数据源
     * @return :自定义的会话工厂
     */
    private SqlSessionFactory createSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        // 其他可配置项(包括是否打印sql,是否开启驼峰命名等)
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(StdOutImpl.class);
        factoryBean.setConfiguration(configuration);
        return factoryBean.getObject();
    }
}