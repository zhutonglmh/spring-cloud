package studyserverone.many_data_source;

/**
 * @author zhutong
 * @date 2019/8/6 10:46
 */
public enum DataSourceType {
    
    /**
     * 第一数据源
     */
    DATA_SOURCE_ONE("one","datasource-one","第一数据源"),
    /**
     * 第二数据源
     */
    DATA_SOURCE_TWO("two","datasource-two","第二数据源");
    
    DataSourceType(String name,String value,String remarks){
        this.name = name;
        this.value = value;
        this.remarks = remarks;
    };
    
    private String name;
    
    private String value;
    
    private String remarks;
    
    public String getName() {
        return name;
    }
    
    public String getValue() {
        return value;
    }
    
    public String getRemarks() {
        return remarks;
    }
}
