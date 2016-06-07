

/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.util;

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
