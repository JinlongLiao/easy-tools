/**
 * Copyright 2020-2021 JinlongLiao
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
