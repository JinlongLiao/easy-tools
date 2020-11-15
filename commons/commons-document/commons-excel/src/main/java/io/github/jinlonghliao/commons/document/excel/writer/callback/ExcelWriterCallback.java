package io.github.jinlonghliao.commons.document.excel.writer.callback;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Excel 生成回调方法
 *
 * @author liaojinlong
 * @since 2020/10/3 18:43
 */
public interface ExcelWriterCallback {
    /**
     * Excel 初始化 Workbook
     *
     * @param workbook
     */
    void setWorkBook(Workbook workbook);

    /**
     * Excel 初始化 Sheet
     *
     * @param sheet
     */
    void setSheet(Sheet sheet);

    /**
     * 写入row Data
     *
     * @param index
     * @param row
     */

    void setRow(int index, Row row);

    /**
     * 写入 Cell Data
     *
     * @param rowIndex
     * @param cellIndex
     * @param cell
     */
    void setCell(int rowIndex, int cellIndex, Cell cell);
}
