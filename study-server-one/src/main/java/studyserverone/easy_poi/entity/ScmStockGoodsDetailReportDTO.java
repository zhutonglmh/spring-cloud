package studyserverone.easy_poi.entity;

import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhutong
 * @date 2019/7/3 14:50
 */
@Data
@ExcelTarget(value = "scmStockGoodsDetailReportDTO")
public class ScmStockGoodsDetailReportDTO {
    
    /**
     * 商户id
     */
    private String tenantId;
    
    /**
     * 机构id
     */
    private String orgId;
    
    /**
     * 机构名称
     */
    private String orgName;
    
    /**
     * 仓库id
     */
    private String depotId;
    
    /**
     * 仓库名称
     */
    private String depotName;
    
    /**
     * 日期
     */
    private Date bussDate;
    
    /**
     * 物资id
     */
    private String goodsId;
    
    /**
     * 物资编码
     */
    private String goodsCode;
    
    /**
     * 物资名称
     */
    private String goodsName;
    
    /**
     * 物资规格
     */
    private String goodsSpec;
    
    /**
     * 类别id
     */
    private String cateId;
    
    /**
     * 类别名称
     */
    private String cateName;
    
    /**
     * 标准单位id
     */
    private String unitId;
    
    /**
     * 标准单位名称
     */
    private String unitName;
    
    /**
     * 期初数量
     */
    private BigDecimal begNum;
    
    /**
     * 期初金额
     */
    private BigDecimal begAmt;
    
    /**
     * 入库数量
     */
    private BigDecimal inNum;
    
    /**
     * 入库金额
     */
    private BigDecimal inAmt;
    
    /**
     * 出库数量
     */
    private BigDecimal outNum;
    
    /**
     * 出库金额
     */
    private BigDecimal outAmt;
    
    /**
     * 期末数量
     */
    private BigDecimal endNum;
    
    /**
     * 期末金额
     */
    private BigDecimal endAmt;
    
    /**
     * 物资集合
     */
    private List<String> goodsList;
    
    /**
     * 开始日期
     */
    private Date startDate;
    
    /**
     * 结束日期
     */
    private Date endDate;
    
    /**
     * 总入库数
     */
    private BigDecimal totalInNum;
    
    /**
     * 总入库金额
     */
    private BigDecimal totalInAmt;
    
    /**
     * 总出库数
     */
    private BigDecimal totalOutNum;
    
    /**
     * 总出库金额
     */
    private BigDecimal totalOutAmt;
    
    /**
     * 总期初数
     */
    private BigDecimal totalBegNum;
    
    /**
     * 总期初金额
     */
    private BigDecimal totalBegAmt;
    
    /**
     * 总期末数
     */
    private BigDecimal totalEndNum;
    
    /**
     * 总期末金额
     */
    private BigDecimal totalEndAmt;
    
    /**
     * 页码
     */
    private Integer page;
    
    /**
     * 行数
     */
    private Integer rows;
    
    /**
     * 集合
     */
    private List<ScmStockGoodsDetailReportDTO> list;
    
    private String date;
}
