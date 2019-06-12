package studyserverone.easyPOI.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import studyserverone.easyPOI.dao.ScmYearEndMapper;
import studyserverone.easyPOI.entity.ScmYearEnd;
import studyserverone.easyPOI.entity.ScmYearEndExample;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author zhutong
 * @date 2019/6/10 21:49
 */
@RestController
@RequestMapping("easy/poi")
public class easyDemoController {
    
    @Resource
    private ScmYearEndMapper scmYearEndMapper;
    
    @RequestMapping("demo")
    @ResponseBody
    public String demo() throws Exception{
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("年结信息表","年结"),
                ScmYearEnd.class, scmYearEndMapper.selectByExample(new ScmYearEndExample()));
        //1:使用File类创建一个要操作的文件路径
        File file = new File("D:" + File.separator + "demo" + File.separator + "demo.xls");
        //如果文件的目录不存在
        if(!file.getParentFile().exists()){ 
            file.getParentFile().mkdirs(); //创建目录
        }
        //2: 实例化OutputString 对象
        OutputStream output = new FileOutputStream(file);
        workbook.write(output);
        output.close();
        return "success";
    }
}
