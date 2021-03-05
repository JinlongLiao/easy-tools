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
