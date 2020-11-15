package com.github.caryyu.excel2pdf.watermark;

import com.github.caryyu.excel2pdf.excel2pdf.FontUtils;
import com.github.caryyu.excel2pdf.watermark.font.WaterMakFont;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;

import java.awt.Color;
import java.io.InputStream;
import java.io.OutputStream;

import static com.lowagie.text.pdf.PdfStream.BEST_COMPRESSION;

/**
 * @author liaojinlong
 * @since 2020/10/6 18:49
 */
public class WaterMark {
    /**
     * 水印位置（上/下）
     */
    private Boolean onTop = Boolean.TRUE;
    /**
     * 字体
     */
    private BaseFont font = FontUtils.BASE_FONT_CHINESE;
    /**
     * 字体大小
     */
    private Float fontSize = 38F;
    /**
     * 透明度
     */
    private PdfGState pdfGState = new PdfGState();
    /**
     *
     */
    private Color color = Color.GRAY;
    private WaterMakFont waterMarkFont;

    {
        waterMarkFont = new WaterMakFont(Element.ALIGN_RIGHT, 100, 150, 45);
        // 设置填充字体不透明度为0.4f
        pdfGState.setFillOpacity(0.4f);
    }

    public Boolean getOnTop() {
        return onTop;
    }

    public BaseFont getFont() {
        return font;
    }

    public Float getFontSize() {
        return fontSize;
    }

    public PdfGState getPdfGState() {
        return pdfGState;
    }

    public Color getColor() {
        return color;
    }

    public WaterMakFont getWaterMarkFont() {
        return waterMarkFont;
    }

    public void addWaterMark(PdfWriter pdfWriter, String text) throws Exception {
        // 加入水印
        PdfContentByte waterMark = pdfWriter.getDirectContent();
        // 开始设置水印
        waterMark.beginText();
        // 设置水印字体参数及大小
        waterMark.setFontAndSize(font, fontSize);
        // 设置透明度
        waterMark.setGState(pdfGState);
        // 设置水印对齐方式 水印内容 X坐标 Y坐标 旋转角度
        waterMark.showTextAligned(waterMarkFont.getAlignment(),
                text,
                waterMarkFont.getX(),
                waterMarkFont.getY(),
                waterMarkFont.getRotation());

        // 设置水印颜色
        waterMark.setColorFill(color);
        //结束设置
        waterMark.endText();
        waterMark.stroke();
    }

    /**
     * 【功能描述：添加图片和文字水印】 【功能详细描述：功能详细描述】
     *
     * @param srcFile  待加水印文件
     * @param destFile 加水印后存放地址
     * @param text     加水印的文本内容
     * @throws Exception
     */
    public void addWaterMark(InputStream srcFile, OutputStream destFile, String text) throws Exception {
        this.addWaterMark(srcFile, null, destFile, text);
    }

    /**
     * 【功能描述：添加图片和文字水印】 【功能详细描述：功能详细描述】
     *
     * @param srcFile  待加水印文件
     * @param destFile 加水印后存放地址
     * @param text     加水印的文本内容
     * @throws Exception
     */
    public void addWaterMark(InputStream srcFile, byte[] ownerPassword, OutputStream destFile, String text) throws Exception {

        // 待加水印的文件
        PdfReader reader = new PdfReader(srcFile, ownerPassword);
        // 加完水印的文件
        PdfStamper stamper = new PdfStamper(reader, destFile);
        int total = reader.getNumberOfPages() + 1;
        PdfContentByte pdfContentByte;
        // 循环对每页插入水印
        for (int i = 1; i < total; i++) {
            // 水印的起始
            pdfContentByte = onTop ? stamper.getOverContent(i) : stamper.getUnderContent(i);
            // 开始
            pdfContentByte.beginText();
            // 设置颜色 默认为蓝色
            pdfContentByte.setColorFill(color);
            // 设置字体及字号
            pdfContentByte.setFontAndSize(font, fontSize);
            // 开始写入水印
            pdfContentByte.showTextAligned(waterMarkFont.getAlignment(),
                    text,
                    waterMarkFont.getX(),
                    waterMarkFont.getY(),
                    waterMarkFont.getRotation());
            pdfContentByte.endText();
        }
        stamper.close();
    }

    /**
     * 给pdf文件添加水印
     *
     * @param srcFile  要加水印的原pdf文件路径
     * @param destFile 加了水印后要输出的路径
     * @param image    水印图片
     * @throws Exception
     */
    public void addWaterMark(InputStream srcFile, OutputStream destFile, Image image) throws Exception {
        this.addWaterMark(srcFile, null, destFile, image);
    }

    /**
     * 给pdf文件添加水印
     *
     * @param InPdfFile     要加水印的原pdf文件路径
     * @param ownerPassword 密码
     * @param outPdfFile    加了水印后要输出的路径
     * @param image         水印图片
     * @throws Exception
     */
    public void addWaterMark(InputStream InPdfFile, byte[] ownerPassword, OutputStream outPdfFile, Image image) throws Exception {

        PdfReader reader = new PdfReader(InPdfFile, ownerPassword);

        PdfStamper stamper = new PdfStamper(reader, outPdfFile);
        image.setAbsolutePosition(waterMarkFont.getX(), waterMarkFont.getY());
        image.setCompressionLevel(BEST_COMPRESSION);
        image.setAlignment(waterMarkFont.getAlignment());
        image.setRotation(waterMarkFont.getRotation());
        PdfContentByte pdfContentByte;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            pdfContentByte = onTop ? stamper.getOverContent(i) : stamper.getUnderContent(i);
            pdfContentByte.setGState(pdfGState);
            pdfContentByte.addImage(image);

        }
        stamper.close();// 关闭
    }


}
