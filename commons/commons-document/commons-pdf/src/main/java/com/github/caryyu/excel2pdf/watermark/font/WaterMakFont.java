package com.github.caryyu.excel2pdf.watermark.font;

/**
 * @author liaojinlong
 * @since 2020/10/6 19:05
 */
public class WaterMakFont {
    private int alignment;
    private float x;
    private float y;
    private float rotation;

    public WaterMakFont(int alignment, float x, float y, float rotation) {
        this.alignment = alignment;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }


    public int getAlignment() {
        return alignment;
    }

    public void setAlignment(int alignment) {
        this.alignment = alignment;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
