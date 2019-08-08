package studyserverone.easy_poi.style;

import cn.afterturn.easypoi.excel.export.styler.ExcelExportStylerDefaultImpl;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

/**
 * @author zhutong
 * @date 2019/6/18 19:42
 */
public class MyExcelStyle extends ExcelExportStylerDefaultImpl {
    
    public MyExcelStyle(Workbook workbook) {
        super(workbook);
    }
    
    @Override
    public CellStyle getTitleStyle(short color) {
        CellStyle titleStyle = this.workbook.createCellStyle();
        titleStyle.setBorderRight(BorderStyle.THIN); // 设置边框线
        titleStyle.setRightBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
        titleStyle.setBorderLeft(BorderStyle.THIN);
        titleStyle.setLeftBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
        titleStyle.setBorderTop(BorderStyle.THIN);
        titleStyle.setTopBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
        titleStyle.setBorderBottom(BorderStyle.THIN);
        titleStyle.setBottomBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
        titleStyle.setAlignment(HorizontalAlignment.CENTER);// 设置居中
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置居中
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        titleStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        titleStyle.setWrapText(true);
        Font titleFont = this.workbook.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 10);
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);
        return titleStyle;
    }
}
