package io.github.jinlonghliao.commons.document.excel.parser;

import io.github.jinlonghliao.common.collection.CollectionUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Excel 解析
 *
 * @author liaojinlong
 * @since 2020/10/3 09:05
 */
public class ExcelParse {
    private final ExcelCellValueParse excelCellValueParse;

    public ExcelParse() {
        this(new ExcelCellValueParse.DefaultExcelCellValueParse(null));
    }

    public ExcelParse(ExcelCellValueParse excelCellValueParse) {
        this.excelCellValueParse = excelCellValueParse;
    }

    /**
     * Excel 解析
     *
     * @param inputStream
     * @param fileNames
     * @return List<List < Map < String, Object>>>
     * @throws IOException
     */
    public synchronized List<List<Map<String, Object>>> excelParse(InputStream inputStream, List<List<String>> fileNames) throws IOException {
        final Workbook workbook = WorkbookFactory.create(inputStream);
        this.excelCellValueParse.setWorkBook(workbook);
        Integer[] sheetIndex = new Integer[fileNames.size()];
        for (int i = 0; i < fileNames.size(); i++) {
            sheetIndex[i] = i;
        }
        return parseWorkbook(workbook, fileNames, sheetIndex);
    }

    /**
     * Excel 解析
     *
     * @param inputStream
     * @param fileNames
     * @param sheetIndex
     * @return List<List < Map < String, Object>>>
     * @throws IOException
     */
    public synchronized List<List<Map<String, Object>>> excelParse(InputStream inputStream, List<List<String>> fileNames, Integer... sheetIndex) throws IOException {
        final Workbook workbook = WorkbookFactory.create(inputStream);
        this.excelCellValueParse.setWorkBook(workbook);
        return parseWorkbook(workbook, fileNames, sheetIndex);
    }

    /**
     * Sheet 解析
     *
     * @param workbook
     * @param fileNames
     * @param sheetIndex
     * @return List<List < Map < String, Object>>>
     */
    public List<List<Map<String, Object>>> parseWorkbook(Workbook workbook, List<List<String>> fileNames, Integer[] sheetIndex) {
        List<List<Map<String, Object>>> data = CollectionUtils.newArrayList(fileNames.size());
        for (Integer index : sheetIndex) {
            final Sheet sheet = workbook.getSheetAt(index);
            final List<String> fieldNames = fileNames.get(index);
            data.add(parseSheet(sheet, fieldNames));
        }
        return data;
    }

    /**
     * ROW 解析
     *
     * @param sheet
     * @param fieldNames
     * @return List<Map < String, Object>>
     */
    public List<Map<String, Object>> parseSheet(Sheet sheet, List<String> fieldNames) {
        List<Map<String, Object>> data = CollectionUtils.newArrayList(sheet.getLastRowNum());
        for (Row row : sheet) {
            data.add(parseRows(row, fieldNames));
        }
        return data;
    }

    /**
     * Row 解析
     *
     * @param row
     * @param fieldNames
     * @return Map<String, Object>
     */
    public Map<String, Object> parseRows(Row row, List<String> fieldNames) {
        Map<String, Object> data = CollectionUtils.newHashMap(row.getRowNum());
        int index = 0;
        for (Cell cell : row) {
            final String fieldName = fieldNames.get(index++);
            data.put(fieldName, parseCell(cell));
        }
        return data;
    }

    /**
     * 具体实现
     *
     * @param cell
     * @return Object
     */
    public Object parseCell(Cell cell) {
        if (cell == null) {
            return null;
        }
        Object data;
        final CellType cellType = cell.getCellType();
        switch (cellType) {
            case _NONE:
            case BLANK:
                data = "";
                break;
            case BOOLEAN:
                data = cell.getBooleanCellValue();
                break;
            case ERROR:
                data = cell.getErrorCellValue();
                break;
            case NUMERIC:
                final Double numericCellValue = cell.getNumericCellValue();
                if (DateUtil.isCellDateFormatted(cell)) {
                    data = cell.getDateCellValue();
                } else {
                    final int intValue = numericCellValue.intValue();
                    final boolean b = intValue <= numericCellValue;
                    data = b ? numericCellValue : intValue;
                }
                break;
            case FORMULA:
                if (Objects.nonNull(excelCellValueParse.getFormulaEvaluator())) {
                    final CellValue cellValue = excelCellValueParse.getFormulaEvaluator().evaluate(cell);
                    final CellType type = cellValue.getCellType();
                    switch (type) {
                        case _NONE:
                        case BLANK:
                            data = "";
                            break;
                        case BOOLEAN:
                            data = cellValue.getBooleanValue();
                            break;
                        case ERROR:
                            data = cellValue.getErrorValue();
                            break;
                        case NUMERIC:
                            final double value = cellValue.getNumberValue();
                            if (DateUtil.isCellDateFormatted(cell)) {
                                return DateUtil.getJavaDate(value);
                            } else {
                                data = value;
                            }
                            break;
                        case STRING:
                        default:
                            data = cell.getStringCellValue();
                    }
                } else {
                    data = cell.getNumericCellValue();
                }
                break;
            case STRING:
            default:
                data = cell.getStringCellValue();
        }
        return excelCellValueParse.parse(cellType, cell, data);
    }
}
