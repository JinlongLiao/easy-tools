package com.github.caryyu.excel2pdf.excel2pdf;


import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;

/**
 * @author liaojinlong
 * @since 2020/10/5 20:10
 */
public class FontUtils {
    /**
     * 中文字体支持
     */
    public final static BaseFont BASE_FONT_CHINESE;

    static {
        try {
            BASE_FONT_CHINESE = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            FontFactory.registerDirectories();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * POI Font => iText Font
     *
     * @param font
     * @return om.lowagie.text.Font
     */
    public static com.lowagie.text.Font getFont(org.apache.poi.ss.usermodel.Font font) {
        return FontFactory.getFont(font.getFontName(),
                BaseFont.IDENTITY_H, BaseFont.EMBEDDED,
                font.getFontHeightInPoints());
    }
}