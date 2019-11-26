package studyserverone.many_data_source;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 动态方式去切换数据源
 * @author 朱同
 */
@Aspect
@Component
@Slf4j
public class DynamicDataSourceAspect {
    
    /**
     * 数据源2 切入点
     * 
     * */
    @Pointcut("execution(* studyserverone.easy_poi.dao..*(..))")
    public void dataSourceTwoPointCut() {
    }
    
    /**
     * 设置数据源
     * @param joinPoint 连接点
     */
    @Before(value = "dataSourceTwoPointCut()")
    public void beforeSwitchDBBefore(JoinPoint joinPoint) {
       
        log.info("切换数据源为：" + DataSourceTypeEnum.DataSourceTwo.getDbName());
        DataSourceContextHolder.setDataSourceKey(DataSourceTypeEnum.DataSourceTwo.getDbName());
    }
    
    /**
     * 清除数据源设置
     * @param joinPoint 连接点
     */
    @After(value = "dataSourceTwoPointCut()")
    public void beforeSwitchDBAfter(JoinPoint joinPoint) {
    
        log.info("清除数据源!");
        DataSourceContextHolder.clearDataSourceKey();
    }
    
    
    
}
