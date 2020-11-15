package com.github.caryyu.excel2pdf;

import com.github.caryyu.excel2pdf.excel2pdf.PdfTableExcel;
import com.github.caryyu.excel2pdf.excel2pdf.excel.ExcelObject;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import io.github.jinlonghliao.common.core.util.ClassUtil;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class Simple1Tests {
    final String name = "case%s.xlsx";

    @Test
    public void testActiveTable() throws IOException, DocumentException {
        for (int i = 1; i < 6; i++) {
            /**
             * EXCEL name
             */
            final String format = String.format(name, i);
            /**
             * PDF create
             */
            final String classPath = ClassUtil.getClassPath();

            // generate file
            final OutputStream os = Files.newOutputStream(new File(classPath + File.separator + format + ".pdf").toPath());

            final InputStream inputStream = ClassUtil.getClassLoader().getResourceAsStream(format);
            final ExcelObject excelObject = new ExcelObject(inputStream);

            new Excel2Pdf(excelObject, os).convert();

        }
    }

    @Test
    public void testAlTable() throws IOException, DocumentException {
        for (int i = 1; i < 6; i++) {
            /**
             * EXCEL name
             */
            final String format = String.format(name, i);
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
            final OutputStream os = Files.newOutputStream(new File(classPath + File.separator + format + ".pdf").toPath());
            PdfWriter.getInstance(document, os);
            document.setMargins(72, 72, 72, 72);
            document.open();
            allTable.forEach(table -> document.add(table));
            document.close();
        }
    }
}