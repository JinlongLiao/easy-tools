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
