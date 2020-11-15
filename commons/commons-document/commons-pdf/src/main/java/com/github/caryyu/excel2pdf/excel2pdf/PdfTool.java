package com.github.caryyu.excel2pdf.excel2pdf;

import com.lowagie.text.Document;

import java.io.OutputStream;

/**
 * @author liaojinlong
 * @since 2020/10/5 20:05
 */
public class PdfTool {
    protected Document document;

    protected OutputStream os;

    public Document getDocument() {
        if (document == null) {
            document = new Document();
        }
        return document;
    }
}