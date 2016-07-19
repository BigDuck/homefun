/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.*;
import java.util.Iterator;

/**
 * 图片工具类， 图片水印，文字水印，缩放，补白等
 * @author Carl He
 */
public final class ImagesUtils {
    /**图片格式：JPG*/
    private static final String PICTRUE_FORMATE_JPG = "jpg";
    private static Logger logger=LoggerFactory.getLogger(ImagesUtils.class);
    private ImagesUtils(){}
    /**
     * 添加图片水印
     * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
     * @param waterImg  水印图片路径，如：C://myPictrue//logo.png
     * @param x 水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y 水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     */
    public final static void pressImage(String targetImg, String waterImg, int x, int y, float alpha) {
        try {
            File file = new File(targetImg);
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);

            Image waterImage = ImageIO.read(new File(waterImg));    // 水印文件
            int width_1 = waterImage.getWidth(null);
            int height_1 = waterImage.getHeight(null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if(x < 0){
                x = widthDiff / 2;
            }else if(x > widthDiff){
                x = widthDiff;
            }
            if(y < 0){
                y = heightDiff / 2;
            }else if(y > heightDiff){
                y = heightDiff;
            }
            g.drawImage(waterImage, x, y, width_1, height_1, null); // 水印文件结束
            g.dispose();
            ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
                logger.info("水印结束");
        } catch (IOException e) {
            logger.error("添加水印失败{}",e.getCause());
        }
    }

    /**
     * 添加文字水印
     * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
     * @param pressText 水印文字， 如：中国证券网
     * @param fontName 字体名称，    如：宋体
     * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
     * @param fontSize 字体大小，单位为像素
     * @param color 字体颜色
     * @param x 水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     */
    public static void pressText(String targetImg, String pressText, String fontName, int fontStyle, int fontSize, Color color, int x, int y, float alpha) {
        try {
            File file = new File(targetImg);

            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setColor(color);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

            int width_1 = fontSize * getLength(pressText);
            int height_1 = fontSize;
            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if(x < 0){
                x = widthDiff / 2;
            }else if(x > widthDiff){
                x = widthDiff;
            }
            if(y < 0){
                y = heightDiff / 2;
            }else if(y > heightDiff){
                y = heightDiff;
            }

            g.drawString(pressText, x, y + height_1);
            g.dispose();
            ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
            System.out.println("水印结束");
        } catch (Exception e) {
            logger.error("文字水印失败{}",e.getCause());
        }
    }

    /**
     * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
     * @param text
     * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
     */
    public static int getLength(String text) {
        int textLength = text.length();
        int length = textLength;
        for (int i = 0; i < textLength; i++) {
            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
                length++;
            }
        }
        return (length % 2 == 0) ? length / 2 : length / 2 + 1;
    }

    /**
     * 图片缩放
     * @param filePath 图片路径
     * @param height 高度
     * @param width 宽度
     * @param bb 比例不对时是否需要补白
     */
    public static void resize(String filePath, int height, int width, boolean bb) {
        try {
            double ratio = 0; //缩放比例
            File f = new File(filePath);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
            //计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue() / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
                g.dispose();
                itemp = image;
            }
            ImageIO.write((BufferedImage) itemp, "jpg", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /***
     * 获取图片的类型
     * @param o
     * @return
     * @date   2016-3-30下午4:59:36
     */
    private static String getFormatName(Object o) {
        try {
            ImageInputStream iis = ImageIO.createImageInputStream(o);
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            if (!iter.hasNext()) {
                return null;
            }

            ImageReader reader = iter.next();
            iis.close();
            return reader.getFormatName();
        } catch (IOException e) {
        }
        return null;
    }
    /**
     * 彩色转为黑白
     * @param srcImageFile 源图像地址
     * @param destImageFile 目标图像地址
     */
    public final static void gray(String srcImageFile, String destImageFile) {
        try {
            BufferedImage src = ImageIO.read(new File(srcImageFile));
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);
            src = op.filter(src, null);
            ImageIO.write(src, "jpg", new File(destImageFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /***
     * 转换图像格式
     * @param srcImageFile 原图像地址
     * @param formatName   转换类型
     * @param destImageFile 转换后的地址
     * @return
     * @author roychenyi
     * @date   2016-3-30下午2:49:15
     */
    public  static boolean convert(String srcImageFile, String formatName, String destImageFile) {
        try {
            File f = new File(srcImageFile);
            f.canRead();
            f.canWrite();
            BufferedImage src = ImageIO.read(f);
            ImageIO.write(src, formatName, new File(destImageFile));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /***
     * 缩放图像，按照长宽缩放
     * @param srcImageFile
     * @param result
     * @param height  变换后的高度
     * @param width   变换后的长度
     * @param bb      比例不对称时，是否补白，true 补白;false 不补白
     * @return

     * @date   2016-3-30下午2:44:37
     */
    public  static boolean scale2(String srcImageFile, String result, int height, int width, boolean bb) {
        try {
            double ratio = 0.0; // 缩放比例
            System.out.println("源文件："+srcImageFile+"结果:"+result);
            File file = new File(srcImageFile);
            BufferedImage bi = ImageIO.read(file); // 读入文件
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = (ImageReader) readers.next();
            ImageInputStream iis = ImageIO.createImageInputStream(file);
            reader.setInput(iis, true);
            int width1 = reader.getWidth(0); //
            int height1 = reader.getHeight(0); // 得到源图长
            Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
            // 计算比例
            if ((height1 > height) || (width1 > width)) {
                if (height1 > width1) {
                    ratio = (new Integer(height)).doubleValue()
                            / height1;
                } else {
                    ratio = (new Integer(width)).doubleValue() / width1;
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform
                        .getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {//补白
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                g.dispose();
                itemp = image;
            }
            ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /***
     * 按照比例缩放
     * @param srcImageFile
     * @param result
     * @param scale 缩放比例
     * @param flag 缩放选择:true 放大; false 缩小;
     * @return
     * @date   2016-3-30下午2:39:44
     */
    public  static boolean scale(String srcImageFile, String result,
                                 int scale, boolean flag) {
        try {
            File file = new File(srcImageFile);
            BufferedImage src = ImageIO.read(file); // 读入文件
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = (ImageReader) readers.next();
            ImageInputStream iis = ImageIO.createImageInputStream(file);
            reader.setInput(iis, true);
            int width = reader.getWidth(0); // 得到源图宽
            int height = reader.getHeight(0); // 得到源图长
            if (flag) {// 放大
                width = width * scale;
                height = height * scale;
            } else {// 缩小
                width = width / scale;
                height = height / scale;
            }
            Image image = src.getScaledInstance(width, height,
                    Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            ImageIO.write(tag, "jpg", new File(result));// 输出到文件流
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /***
     * 剪裁图片
     * @param src
     * @param dest
     * @param x  起点横坐标
     * @param y  纵坐标
     * @param w  长
     * @param h  高
     * @throws IOException
     * @date   2016-3-30下午12:19:10
     */
    public static boolean cutImage(String src,String dest,int x,int y,int w,int h) {
        try{
            Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = (ImageReader)iterator.next();
            InputStream in=new FileInputStream(src);
            ImageInputStream iis = ImageIO.createImageInputStream(in);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            x=(x>0?x:0);
            y=(y>0?y:0);
            Rectangle rect = new Rectangle(x, y, w,h);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0,param);
            ImageIO.write(bi, "jpg", new File(dest));
            return true;
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }

    }
    /***
     * 图片旋转指定角度
     * @param bufferedimage 图像
     * @param degree      角度
     * @return
     * @date   2016-3-30下午3:10:27
     */
    public static BufferedImage rotateImage(final BufferedImage bufferedimage,
                                            final int degree) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        //     graphics2d.setBackground(Color.white);
        graphics2d.setPaint(Color.WHITE);
        graphics2d.fillRect(0, 0, w, h);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(bufferedimage, 0, 0,Color.WHITE, null);
        graphics2d.dispose();
        return img;
    }
    public static boolean rotateImage(String srcImageFile, String result,
                                      int degree){
        try{
            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
            BufferedImage tag= rotateImage(src, degree);

            ImageIO.write(tag, "JPEG", new File(result));

            return true;
        }catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }
    public static BufferedImage Rotate(Image src, int angel) {
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        //  angel=angel>0?angel:(360-angel);
        // calculate the new image size
        Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(
                src_width, src_height)), angel);

        BufferedImage res = null;
        res = new BufferedImage(rect_des.width, rect_des.height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = res.createGraphics();
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, rect_des.width,  rect_des.height);
        // transform
        g2.translate((rect_des.width - src_width) / 2,
                (rect_des.height - src_height) / 2);
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

        g2.drawImage(src, null, null);
        return res;
    }
    public static Rectangle CalcRotatedSize(Rectangle src, int angel) {
        // if angel is greater than 90 degree, we need to do some conversion
        if (angel >= 90) {
            if(angel / 90 % 2 == 1){
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }

        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);

        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new java.awt.Rectangle(new Dimension(des_width, des_height));
    }

    public static boolean Rotate(String srcImageFile, String result,
                                 int degree){
        try{
            degree=degree>0?degree:(360-degree);
            BufferedImage src = ImageIO.read(new File(srcImageFile));
            BufferedImage tag = Rotate(src, degree);
            ImageIO.write(tag, "JPEG", new File(result));
            return true;
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }
    public static boolean cutAndRotateImage(String srcImageFile,String dest,int x,int y,int w,int h,int degree){
        try{
            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
            BufferedImage tag= Rotate(src, degree);
            Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = (ImageReader)iterator.next();
            //     InputStream in=new FileInputStream(tag);
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ImageOutputStream ios = ImageIO.createImageOutputStream(bs);
            ImageIO.write(tag, "jpg", ios);
            InputStream in=new ByteArrayInputStream(bs.toByteArray());
            ImageInputStream iis=ImageIO.createImageInputStream(in);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            x=(x>0?x:0);
            y=(y>0?y:0);
            Rectangle rect = new Rectangle(x, y, w,h);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0,param);
            ImageIO.write(bi, "jpg", new File(dest));
            return true;
        }catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

}