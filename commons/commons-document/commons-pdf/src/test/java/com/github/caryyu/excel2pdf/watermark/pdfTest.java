package com.github.caryyu.excel2pdf.watermark;

import java.io.*;
import java.util.List;

import com.github.caryyu.excel2pdf.excel2pdf.PdfTableExcel;
import com.github.caryyu.excel2pdf.excel2pdf.excel.ExcelObject;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import io.github.jinlonghliao.common.core.util.ClassUtil;
import org.junit.Test;

public class pdfTest {
    @Test
    public void test1() throws Exception {
        /**
         * EXCEL name
         */
        final String format = "case1.xlsx";
        /**
         * PDF create
         */
        final String classPath = ClassUtil.getClassPath();
        final InputStream inputStream = ClassUtil.getClassLoader().getResourceAsStream(format);
        final ExcelObject excelObject = new ExcelObject(inputStream);
        final PdfTableExcel pdfTableExcel = new PdfTableExcel(excelObject);
        final List<PdfPTable> allTable = pdfTableExcel.getAllTable();
        Document document = new Document(new RectangleReadOnly(842, 595));
        // generate file
        document.setMargins(72, 72, 72, 72);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(classPath + "/demo1.pdf"));
        document.open();
        allTable.forEach(table -> document.add(table));
        new WaterMark().addWaterMark(pdfWriter,
                "万类霜天竞自由");
        // 关闭文档
        document.close();
        pdfWriter.close();

    }

    @Test
    public void test2() throws Exception {
        /**
         * EXCEL name
         */
        final String format = "case1.xlsx";
        /**
         * PDF create
         */
        final String classPath = ClassUtil.getClassPath();
        final InputStream inputStream = ClassUtil.getClassLoader().getResourceAsStream(format);
        final ExcelObject excelObject = new ExcelObject(inputStream);
        final PdfTableExcel pdfTableExcel = new PdfTableExcel(excelObject);
        final List<PdfPTable> allTable = pdfTableExcel.getAllTable();
        Document document = new Document(new RectangleReadOnly(842, 595));
        // generate file
        document.setMargins(72, 72, 72, 72);
        final FileOutputStream os = new FileOutputStream(classPath + "/demo2.pdf");
        PdfWriter pdfWriter = PdfWriter.getInstance(document, os);

        document.open();
        allTable.forEach(table -> document.add(table));
        document.close();
        pdfWriter.close();
        new WaterMark().addWaterMark(new FileInputStream(classPath + "/demo2.pdf"), new FileOutputStream(classPath + "/demo3.pdf"), "万类霜天竞自由");
    }

    @Test
    public void test3() throws Exception {
        /**
         * EXCEL name
         */
        final String format = "case1.xlsx";
        /**
         * PDF create
         */
        final String classPath = ClassUtil.getClassPath();
        final InputStream inputStream = ClassUtil.getClassLoader().getResourceAsStream(format);
        final ExcelObject excelObject = new ExcelObject(inputStream);
        final PdfTableExcel pdfTableExcel = new PdfTableExcel(excelObject);
        final List<PdfPTable> allTable = pdfTableExcel.getAllTable();
        Document document = new Document(new RectangleReadOnly(842, 595));
        // generate file
        document.setMargins(72, 72, 72, 72);
        final FileOutputStream os = new FileOutputStream(classPath + "/demo2.pdf");
        PdfWriter pdfWriter = PdfWriter.getInstance(document, os);

        document.open();
        allTable.forEach(table -> document.add(table));
        document.close();
        pdfWriter.close();
        new WaterMark().addWaterMark(new FileInputStream(classPath + "/demo2.pdf"),
                new FileOutputStream(classPath + "/demo4.pdf"),
                Image.getInstance(classPath + "/1.bmp"));
    }
}

