package io.github.jinlonghliao.commons.document.excel.parser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author liaojinlong
 * @since 2020/10/5 21:43
 */

public interface ExcelCellValueParse {
    /**
     * 初始化
     *
     * @param workbook
     */
    void setWorkBook(Workbook workbook);

    /**
     * 转换为对应的 类型
     *
     * @param cellType
     * @param value
     * @param cell
     * @return Object
     */
    Object parse(CellType cellType, Cell cell, Object value);

    /**
     * 函数解析
     *
     * @return FormulaEvaluator
     */
    FormulaEvaluator getFormulaEvaluator();

    /**
     * 默认实现，未做任何处理
     *
     * @author liaojinlong
     * @since 2020/10/5 21:47
     */
    class DefaultExcelCellValueParse implements ExcelCellValueParse {
        protected Workbook workbook;

        public DefaultExcelCellValueParse(Workbook workbook) {
            this.workbook = workbook;
        }

        @Override
        public void setWorkBook(Workbook workbook) {
            this.workbook = workbook;
        }

        @Override
        public Object parse(CellType cellType, Cell cell, Object value) {
            return value;
        }

        @Override
        public FormulaEvaluator getFormulaEvaluator() {
            return this.workbook.getCreationHelper().createFormulaEvaluator();
        }

    }
}
