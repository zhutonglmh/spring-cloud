package studyserverone.many_data_source;

/**
 * 数据源枚举
 * @author zhutong
 * @date 2019/8/8 16:46
 */
public enum DataSourceTypeEnum {
    
    /***
     * 数据源1
     */
    DataSourceOne("DATASOURCE1","数据源-01(财务集成平台库)"),
    
    /***
     * 数据源2
     */
    DataSourceTwo("DATASOURCE2","数据源-02(财务集成平台库2)");
    
    /**
     * 数据源名称
     */
    private String dbName;
    
    /**
     * 备注/含义
     */
    private String remarks;
    
    DataSourceTypeEnum(String dbName, String remarks) {
        this.dbName = dbName;
        this.remarks = remarks;
    }
    
    public String getDbName() {
        return dbName;
    }
    
    public String getRemarks() {
        return remarks;
    }
}
