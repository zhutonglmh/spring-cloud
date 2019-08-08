package studyserverone;

import cn.afterturn.easypoi.cache.manager.FileLoaderImpl;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import studyserverone.easy_poi.entity.ScmStockGoodsDetailReportDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudyServerOneApplication.class)
public class StudyServerOneApplicationTests {
    
    @Test
    public void contextLoads() {
    


        ScmStockGoodsDetailReportDTO scmStockGoodsDetailReportDTO = new ScmStockGoodsDetailReportDTO();
        scmStockGoodsDetailReportDTO.setOrgName("测试门店");
        scmStockGoodsDetailReportDTO.setDepotName("测试仓库");
        scmStockGoodsDetailReportDTO.setCateName("测试类别");
        scmStockGoodsDetailReportDTO.setDate("2019-01-03~2019-07-04");
        scmStockGoodsDetailReportDTO.setGoodsName("测试物品名称");
        scmStockGoodsDetailReportDTO.setTotalBegNum(BigDecimal.TEN);
        scmStockGoodsDetailReportDTO.setTotalBegAmt(BigDecimal.TEN);
        scmStockGoodsDetailReportDTO.setTotalInNum(BigDecimal.TEN);
        scmStockGoodsDetailReportDTO.setTotalInAmt(BigDecimal.TEN);
        scmStockGoodsDetailReportDTO.setTotalOutNum(BigDecimal.TEN);
        scmStockGoodsDetailReportDTO.setTotalOutAmt(BigDecimal.TEN);
        scmStockGoodsDetailReportDTO.setTotalEndNum(BigDecimal.TEN);
        scmStockGoodsDetailReportDTO.setTotalEndAmt(BigDecimal.TEN);
        List<ScmStockGoodsDetailReportDTO> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ScmStockGoodsDetailReportDTO stockGoodsDetailReportDTO = new ScmStockGoodsDetailReportDTO();
            stockGoodsDetailReportDTO.setGoodsCode("0012");
            stockGoodsDetailReportDTO.setGoodsName("橘子");
            stockGoodsDetailReportDTO.setGoodsSpec("规格");
            stockGoodsDetailReportDTO.setCateName("测试类别");
            stockGoodsDetailReportDTO.setOrgName("测试门店");
            stockGoodsDetailReportDTO.setDepotName("测试仓库");
            stockGoodsDetailReportDTO.setUnitName("测试单位");
            stockGoodsDetailReportDTO.setBegNum(BigDecimal.TEN);
            stockGoodsDetailReportDTO.setBegAmt(BigDecimal.TEN);
            stockGoodsDetailReportDTO.setInNum(BigDecimal.TEN);
            stockGoodsDetailReportDTO.setInAmt(BigDecimal.TEN);
            stockGoodsDetailReportDTO.setOutNum(BigDecimal.TEN);
            stockGoodsDetailReportDTO.setOutAmt(BigDecimal.TEN);
            stockGoodsDetailReportDTO.setEndNum(BigDecimal.TEN);
            stockGoodsDetailReportDTO.setEndAmt(BigDecimal.TEN);
            list.add(stockGoodsDetailReportDTO);
        }

        scmStockGoodsDetailReportDTO.setList(list);
        String s = JSONObject.toJSONString(scmStockGoodsDetailReportDTO);
        Map<String,Object> map = JSONObject.parseObject(s,Map.class);
        TemplateExportParams params = new TemplateExportParams("templates/ee.xlsx", true);
        Workbook book = ExcelExportUtil.exportExcel(params, map);
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = null;
      try{
          fos = new FileOutputStream("D:/excel/进销存汇总表.xlsx");
          book.write(fos);
      }catch (Exception e){
      }finally {
          try{
              fos.close();
          }catch (Exception e1){

          }
      }

    }


    
}
