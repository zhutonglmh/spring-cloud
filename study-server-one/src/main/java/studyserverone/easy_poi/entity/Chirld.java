package studyserverone.easy_poi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

/**
 * @author zhutong
 * @date 2019/6/28 14:13
 */
@Data
@ExcelTarget(value = "child")
public class Chirld {
    
    @Excel(name = "商户",needMerge = false,orderNum = "2",type = 1,width = 10,isStatistics = false,height = 10)
    private String tenantId;
    
    @Excel(name = "年份",needMerge = false,orderNum = "3",type = 10,width = 10,isStatistics = true,height = 10)
    private String yearth;
}
