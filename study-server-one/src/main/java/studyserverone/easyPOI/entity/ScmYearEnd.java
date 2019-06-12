package studyserverone.easyPOI.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 朱同
 */
@Data
@ExcelTarget(value = "scmYearEnd")
public class ScmYearEnd implements Serializable {
    
    @Excel(name = "主键id",needMerge = false,orderNum = "0",type = 1,width = 10,isStatistics = false,height = 10)
    private String id;
    
    @Excel(name = "商户",needMerge = false,orderNum = "2",type = 1,width = 10,isStatistics = false,height = 10)
    private String tenantId;
    
    @Excel(name = "年份",needMerge = false,orderNum = "3",type = 1,width = 10,isStatistics = true,height = 10)
    private String yearth;
    
    @Excel(name = "创建人id",needMerge = false,orderNum = "4",type = 1,width = 10,isStatistics = false,height = 10)
    private String createUser;
    
    @Excel(name = "创建人名称",needMerge = false,orderNum = "5",type = 1,width = 10,isStatistics = false,height = 10)
    private String createUserName;
    
    @Excel(name = "创建时间",needMerge = false,orderNum = "6",type = 1,width = 10,isStatistics = false,height = 10,databaseFormat = "yyyy-MM-dd  HH:mm:ss")
    private Date createTime;
    
    @Excel(name = "修改人id",needMerge = false,orderNum = "7",type = 1,width = 10,isStatistics = false,height = 10)
    private String updateUser;
    
    @Excel(name = "修改人",needMerge = false,orderNum = "8",type = 1,width = 10,isStatistics = false,height = 10)
    private String updateUserName;
    
    @Excel(name = "修改时间",needMerge = false,orderNum = "9",type = 1,width = 10,isStatistics = false,height = 10,databaseFormat = "yyyy-MM-dd  HH:mm:ss")
    private Date updateTime;
    
    @Excel(name = "删除标记",needMerge = false,orderNum = "10",type = 1,width = 10,isStatistics = false,height = 10)
    private Short deleteFlag;
}