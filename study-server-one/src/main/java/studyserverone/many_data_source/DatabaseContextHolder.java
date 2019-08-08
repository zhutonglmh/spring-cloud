package studyserverone.many_data_source;

/**
 * @author zhutong
 * @date 2019/8/6 10:57
 */
public class DatabaseContextHolder {
    
    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();
    
    public static void setDatabaseType(DataSourceType type){
        contextHolder.set(type);
    }
    
    public static DataSourceType getDatabaseType(){
        return contextHolder.get();
    }
}
