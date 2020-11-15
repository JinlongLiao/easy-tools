package com.github.caryyu.excel2pdf.excel2pdf.excel;

import io.github.jinlonghliao.common.collection.CollectionUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
/**
 *
 * @author liaojinlong
 * @since 2020/10/5 20:03
*/
public class Excel {

    protected Workbook workbook;
    protected Sheet activeSheet;
    protected List<Sheet> sheets;

    public Excel(InputStream is) throws IOException {
        this.workbook = WorkbookFactory.create(is);
        this.activeSheet = workbook.getSheetAt(workbook.getActiveSheetIndex());
        this.sheets = CollectionUtils.newArrayList(workbook);
    }

    public Sheet getActiveSheet() {
        return activeSheet;
    }

    public List<Sheet> getSheets() {
        return sheets;
    }

    public Workbook getWorkbook() {
        return workbook;
    }
}
