package com.github.caryyu.excel2pdf.excel2pdf.excel;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author liaojinlong
 * @since 2020/10/5 20:03
 */
public class ExcelObject {
    /**
     * 锚名称
     */
    private String anchorName;
    /**
     * Excel Stream
     */
    private InputStream inputStream;
    /**
     * POI Excel
     */
    private Excel excel;

    public ExcelObject(InputStream inputStream) throws IOException {
        this.inputStream = inputStream;
        this.excel = new Excel(this.inputStream);
    }

    public ExcelObject(String anchorName, InputStream inputStream) throws IOException {
        this.anchorName = anchorName;
        this.inputStream = inputStream;
        this.excel = new Excel(this.inputStream);
    }

    public String getAnchorName() {
        return anchorName;
    }

    public void setAnchorName(String anchorName) {
        this.anchorName = anchorName;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Excel getExcel() {
        return excel;
    }
}