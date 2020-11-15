package com.github.caryyu.excel2pdf.excel2pdf.excel;

import io.github.jinlonghliao.commons.document.excel.parser.ExcelCellValueParse;
import org.apache.poi.ss.usermodel.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author liaojinlong
 * @since 2020/10/5 21:52
 */
public class PdfExcelCellValueParse extends ExcelCellValueParse.DefaultExcelCellValueParse {

    protected static ThreadLocal<SimpleDateFormat> SDF = ThreadLocal.withInitial(() ->
            new SimpleDateFormat("yyyy/MM/dd"));

    public PdfExcelCellValueParse(Workbook workbook) {
        super(workbook);
    }

    @Override
    public Object parse(CellType cellType, Cell cell, Object value) {
        Object data = null;
        switch (cellType) {
            case FORMULA:
                if (Objects.nonNull(getFormulaEvaluator())) {
                    final CellValue cellValue = getFormulaEvaluator().evaluate(cell);
                    final CellType type = cellValue.getCellType();
                    switch (type) {
                        case NUMERIC:
                            final Double num = cellValue.getNumberValue();
                            if (DateUtil.isCellDateFormatted(cell)) {
                                final Date javaDate = DateUtil.getJavaDate(num);
                                try {
                                    data = SDF.get().format(javaDate);
                                } catch (RuntimeException e) {
                                    throw e;
                                } finally {
                                    SDF.remove();
                                }
                                return data;
                            } else {
                                final Integer intValue = new Integer(num.intValue());
                                if (intValue <= num) {
                                    data = intValue.toString();
                                } else {
                                    data = num;
                                }
                            }
                            break;
                        default:
                            data = value;
                    }
                }
                break;
            case NUMERIC:
                final Double numericCellValue = cell.getNumericCellValue();
                if (DateUtil.isCellDateFormatted(cell)) {
                    final Date javaDate = DateUtil.getJavaDate(numericCellValue);
                    try {
                        data = SDF.get().format(javaDate);
                    } catch (RuntimeException e) {
                        throw e;
                    } finally {
                        SDF.remove();
                    }
                } else {
                    final Integer intValue = new Integer(numericCellValue.intValue());
                    if (intValue <= numericCellValue) {
                        data = intValue;
                    } else {
                        data = numericCellValue;
                    }

                }
                break;
            default:
                data = value;
        }
        return data.toString();
    }
}
