package studyserverone.many_data_source;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author zhutong
 * @date 2019/8/6 10:29
 */
@Configuration
@MapperScan(basePackages = {"studyserverone.easy_poi.dao"},sqlSessionTemplateRef = "sqlSessionTemplate")
public class MybatisConfig {
    
    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;
    @Value("${mybatis.typeAliasesPackage}")
    private String typeAliasesPackage;
  
    
    @Bean(name = "dataSourceOneProperties")
    @ConfigurationProperties(prefix = "spring.datasource-one")
    public Properties propertiesOne(){
        Properties properties= new Properties();
        convertProperties(properties);
        return  properties;
    }
    
    @Bean(name = "dataSourceTwoProperties")
    @ConfigurationProperties(prefix = "spring.datasource-two")
    public Properties propertiesTwo(){
        Properties properties= new Properties();
        convertProperties(properties);
        return  properties;
    }
    
    
    @Bean(name = "dataSourceOne")
    public DataSource druidDataSourceOne(@Qualifier("dataSourceOneProperties") Properties dataSourceOne) throws Exception {
    
        AtomikosDataSourceBean druidDataSource = new AtomikosDataSourceBean();
        druidDataSource.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        druidDataSource.setUniqueResourceName("dataSourceOne");
        druidDataSource.setXaProperties(dataSourceOne);
        return druidDataSource;
    }
    
    @Bean(name = "dataSourceTwo")
    public DataSource druidDataSourceTwo(@Qualifier("dataSourceTwoProperties") Properties dataSourceTwo) throws Exception {
        AtomikosDataSourceBean druidDataSource = new AtomikosDataSourceBean();
        druidDataSource.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        druidDataSource.setUniqueResourceName("dataSourceTwo");
        druidDataSource.setXaProperties(dataSourceTwo);
        return druidDataSource;
    }
    
    /**
     * @param dataSourceOne 数据源1
     * @return 数据源1的会话工厂
     */
    @Bean
    public SqlSessionFactory sqlSessionFactoryOne(DataSource dataSourceOne)
            throws Exception {
        return createSqlSessionFactory(dataSourceOne);
    }
    

    
    /***
     * sqlSessionTemplate与Spring事务管理一起使用，以确保使用的实际SqlSession是与当前Spring事务关联的,
     * 此外它还管理会话生命周期，包括根据Spring事务配置根据需要关闭，提交或回滚会话
     * @param sqlSessionFactoryOne 数据源1
     * @param sqlSessionFactoryTwo 数据源2
     */
    @Bean
    public CustomSqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactoryOne,
                                                       SqlSessionFactory sqlSessionFactoryTwo) {
        Map<Object, SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>();
        sqlSessionFactoryMap.put(DataSourceType.DATA_SOURCE_ONE.getName(), sqlSessionFactoryOne);
        sqlSessionFactoryMap.put(DataSourceType.DATA_SOURCE_TWO.getName(), sqlSessionFactoryTwo);
        
        CustomSqlSessionTemplate customSqlSessionTemplate = new CustomSqlSessionTemplate(sqlSessionFactoryTwo);
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
        /* *
         * 采用个如下方式配置属性的时候一定要保证已经进行数据源的配置(setDataSource)和数据源和MapperLocation配置(setMapperLocations)
         * factoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
         * factoryBean.getObject().getConfiguration().setLogImpl(StdOutImpl.class);
         **/
        return factoryBean.getObject();
    }
    
   
    
    
//    @Bean(name="dynamicDataSource")
//    @Primary
//    public DynamicDataSource DataSource(@Qualifier("dataSourceOne") DataSource dataSourceOne,
//                                        @Qualifier("dataSourceTwo") DataSource dataSourceTwo){
//        Map<Object, Object> targetDataSource = new HashMap<>(5);
//        targetDataSource.put(DataSourceType.DATA_SOURCE_ONE, dataSourceOne);
//        targetDataSource.put(DataSourceType.DATA_SOURCE_TWO, dataSourceTwo);
//        DynamicDataSource dataSource = new DynamicDataSource();
//        dataSource.setTargetDataSources(targetDataSource);
//        dataSource.setDefaultTargetDataSource(dataSourceOne);
//        return dataSource;
//    }
//    
//    @Bean(name="sessionFactory")
//    @Primary
//    public SqlSessionFactory sessionFactory(@Qualifier("dynamicDataSource")DynamicDataSource dataSource) throws Exception{
//        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//        sessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
//        return sessionFactoryBean.getObject();
//    }
    
//    /**
//     * 注入事物管理器
//     * @return
//     */
//    @Bean(name = "xatx")
//    public JtaTransactionManager regTransactionManager () {
//        UserTransactionManager userTransactionManager = new UserTransactionManager();
//        UserTransaction userTransaction = new UserTransactionImp();
//        return new JtaTransactionManager(userTransaction, userTransactionManager);
//    }
//    
//    @Bean
//    public WallFilter wallFilter(){
//        WallFilter wallFilter = new WallFilter();
//        //允许执行多条SQL
//        WallConfig config = new WallConfig();
//        config.setMultiStatementAllow(true);
//        wallFilter.setConfig(config);
//        return wallFilter;
//    }
    
    private void convertProperties(Properties properties){
        properties.forEach((k,v)-> properties.setProperty(k.toString(),String.valueOf(v)));
    }
    

}
