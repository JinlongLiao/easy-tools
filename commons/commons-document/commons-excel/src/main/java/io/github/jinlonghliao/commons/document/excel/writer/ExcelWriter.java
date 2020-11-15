package io.github.jinlonghliao.commons.document.excel.writer;

import io.github.jinlonghliao.common.collection.CollectionUtils;
import io.github.jinlonghliao.commons.document.excel.writer.callback.DefaultExcelWriterCallback;
import io.github.jinlonghliao.commons.document.excel.writer.callback.ExcelWriterCallback;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Excel 生成工具
 *
 * @author liaojinlong
 * @since 2020/10/3 18:31
 */
public class ExcelWriter {
    private static ExcelWriter instance = null;
    private static final ExcelWriterCallback DEFAULT_EXCEL_WRITER_CALLBACK;

    static {
        DEFAULT_EXCEL_WRITER_CALLBACK = new DefaultExcelWriterCallback();
    }

    public synchronized void writerExcel(File excel,
                                         List<String> sheetNames,
                                         List<List<Map<String, Object>>> dataList,
                                         ExcelWriterCallback callback) throws IOException {
        if (Objects.nonNull(callback)) {
            callback = DEFAULT_EXCEL_WRITER_CALLBACK;
        }
        final Workbook workbook = WorkbookFactory.create(excel);

        Sheet sheet;
        for (int i = 0; i < dataList.size(); i++) {
            if (!CollectionUtils.isEmpty(dataList)) {
                String sheetName = sheetNames.get(i);
                sheet = workbook.createSheet(sheetName);
            } else {
                sheet = workbook.createSheet();
            }
            callback.setSheet(sheet);
            List<Map<String, Object>> data = dataList.get(i);
            writeSheetData(sheet, data, callback);
        }
    }

    private void writeSheetData(Sheet sheet, List<Map<String, Object>> data, ExcelWriterCallback callback) {
        int index = 0;
        for (int i = 0; i < data.size(); i++) {
            Map<String, Object> item = data.get(i);
            if (index == 0) {
                final Row row = sheet.createRow(index);
                callback.setRow(index++, row);
                int cellIndex = 0;
                for (String key : item.keySet()) {
                    final Cell cell = row.createCell(cellIndex);
                    cell.setCellValue(key);
                    callback.setCell(index - 1, cellIndex++, cell);
                }
            }
            final Row row = sheet.createRow(index);
            callback.setRow(index++, row);
            int cellIndex = 0;
            for (Object value : item.values()) {
                final Cell cell = row.createCell(cellIndex);
                if (value instanceof Date) {
                    cell.setCellValue((Date) value);
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                } else if (value instanceof Boolean) {
                    cell.setCellValue((Boolean) value);
                } else if (value instanceof LocalDate) {
                    cell.setCellValue((LocalDate) value);
                } else if (value instanceof LocalDateTime) {
                    cell.setCellValue((LocalDateTime) value);
                } else if (value instanceof Calendar) {
                    cell.setCellValue((Calendar) value);
                } else {
                    cell.setCellValue(String.valueOf(value));
                }
                callback.setCell(index - 1, cellIndex++, cell);
            }
        }
    }
}
