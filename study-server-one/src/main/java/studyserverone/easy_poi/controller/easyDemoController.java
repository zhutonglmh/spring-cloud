package studyserverone.easy_poi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import studyserverone.easy_poi.dao.ScmYearEndMapper;
import studyserverone.easy_poi.entity.Chirld;
import studyserverone.easy_poi.entity.ScmYearEnd;
import studyserverone.easy_poi.entity.ScmYearEndExample;
import studyserverone.easy_poi.style.MyExcelStyle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhutong
 * @date 2019/6/10 21:49
 */
@RestController
@RequestMapping("easy/poi")
public class easyDemoController {
    
    
    
    @Resource
    private ScmYearEndMapper scmYearEndMapper;
    
    @RequestMapping(value = "demo",method = RequestMethod.GET)
    public String demo(HttpServletRequest request, HttpServletResponse response) throws Exception{

        //1、生成文件
        ExportParams params = new ExportParams("年结信息表","年结");
        params.setStyle(MyExcelStyle.class);
        List<ScmYearEnd> scmYearEnds = scmYearEndMapper.selectByExample(new ScmYearEndExample());
        BigDecimal ss = BigDecimal.ZERO;
        for (ScmYearEnd scmYearEnd : scmYearEnds) {
            Chirld chirld = new Chirld();
            chirld.setTenantId(scmYearEnd.getTenantId());
            chirld.setYearth(scmYearEnd.getYearth());
            List<Chirld> chirlds = new ArrayList<>();
            chirlds.add(chirld);
            scmYearEnd.setChirld(chirlds);
            ss = ss.add(BigDecimal.valueOf(Double.valueOf(scmYearEnd.getYearth())));
        }
        Workbook workbook = ExcelExportUtil.exportBigExcel(params,
                ScmYearEnd.class, scmYearEnds);
        CellStyle redCellStyle = workbook.createCellStyle();
        redCellStyle.setBorderBottom(BorderStyle.THIN);
        redCellStyle.setBorderLeft(BorderStyle.THIN);
        redCellStyle.setBorderTop(BorderStyle.THIN);
        redCellStyle.setBorderRight(BorderStyle.THIN);
        redCellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setColor(Font.COLOR_RED);
        redCellStyle.setFont(font);
        workbook.getSheet("年结").getRow( workbook.getSheet("年结").getLastRowNum()).getCell(2).setCellStyle(redCellStyle);
        workbook.getSheet("年结").getRow( workbook.getSheet("年结").getLastRowNum()).getCell(2).setCellValue(ss.toPlainString());
        //2: 实例化OutputString 对象
        String fileName = "年结信息表.xls";
        //解决 文件名称乱码问题  先把文件名转换为ASCII码
        fileName = URLEncoder.encode(fileName,"UTF-8");
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + "数据统计.xls");
       
        workbook.write(output);
        output.close();
        return "success";
    }
    
    @RequestMapping(value = "demo2",method = RequestMethod.POST)
    public String demo2(@RequestBody Map msg) throws Exception{
    
        ExportParams params = new ExportParams("数据统计","EXPORT_LIST_SHEET_NAME");
        //设置自定义样式
        //查询数据生成excel 文件
        Workbook workbook = ExcelExportUtil.exportExcel(new TemplateExportParams("templates/demo.xlsx", true), msg);
//        easyDemoController.workbook = workbook;
        return "success";
    }
    
    
    @RequestMapping(value = "demo3",method = RequestMethod.POST)
    public String demo3(@RequestBody Map msg) throws Exception{
    
        Map<String,Object> map = new HashMap<>();
        
        List<Map<String,Object>> maps = (List<Map<String,Object>> )msg.get("data");
        System.out.println(maps.size());
        System.out.println(JSONObject.toJSONString(maps));
        return "success";
    }
}
