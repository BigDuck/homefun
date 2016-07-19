

/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.util;

import java.awt.*;

/**
 * 用来读取存放上（application）传文件的路径
 */
public class FileUploadConfiguration {
    /**
     *上传图片存放的路径
     */
    private String pathToUploadFolder;
    /**
     * 文字水印名字
     */
    private String waterName;

    /***
     * slider 的图片路径
     * @return
     */
    private String sliderPath;
    /**
     * 字体
     */
    private String fontFamile="字体";

    public String getFontFamile() {
        return fontFamile;
    }
    private int fontStyle= Font.BOLD;
    /**
     * 字体大小
     */
    private int fontSize=20;
    public void setFontFamile(String fontFamile) {
        this.fontFamile = fontFamile;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getSliderPath() {
        return sliderPath;
    }

    public void setSliderPath(String sliderPath) {
        this.sliderPath = sliderPath;
    }

    public String getWaterName() {
        return waterName;
    }

    public void setWaterName(String waterName) {
        this.waterName = waterName;
    }

    public String getPathToUploadFolder() {
        return pathToUploadFolder;
    }

    public void setPathToUploadFolder(String pathToUploadFolder) {
        this.pathToUploadFolder = pathToUploadFolder;
    }
}
