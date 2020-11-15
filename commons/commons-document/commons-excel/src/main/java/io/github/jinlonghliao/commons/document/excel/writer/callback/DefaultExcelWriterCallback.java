package io.github.jinlonghliao.commons.document.excel.writer.callback;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认实现
 *
 * @author liaojinlong
 * @since 2020/10/3 18:53
 */
public class DefaultExcelWriterCallback implements ExcelWriterCallback {
    private static final Logger log = LoggerFactory.getLogger(DefaultExcelWriterCallback.class);


    @Override
    public void setWorkBook(Workbook workbook) {
        log.trace("not implements");
    }

    @Override
    public void setSheet(Sheet sheet) {
        log.trace("not implements");
    }

    @Override
    public void setRow(int index, Row row) {
        log.trace("not implements");

    }

    @Override
    public void setCell(int rowIndex, int cellIndex, Cell cell) {
        log.trace("not implements");
    }


}
