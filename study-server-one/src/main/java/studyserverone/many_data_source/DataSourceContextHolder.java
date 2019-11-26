package studyserverone.many_data_source;

/**
 * 保证数据源切换的线程隔离
 * @author 朱同
 */
public class DataSourceContextHolder {
    
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();
    
    /**
     * 设置数据源名称
     * @param dbName 数据源名称
     */
    public static void setDataSourceKey(String dbName) {
        CONTEXT_HOLDER.set(dbName);
    }
    
    /**
     * 获取数据源名
     * @return 当前数据源名称
     */
    public static String getDataSourceKey() {
        return CONTEXT_HOLDER.get();
    }
    
    /**
     * 清除数据源名
     */
    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }
}
