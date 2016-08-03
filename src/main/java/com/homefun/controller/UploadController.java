package com.homefun.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.homefun.common.FastJsonUtil;
import com.homefun.util.*;
import org.apache.commons.lang3.time.DateUtils;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Name：UploadController
 * Time：2016/6/14 17:22
 * author：WPJ587
 * description：上传的控制器
 **/
@Controller
@RequestMapping("/admin/upload")
public class UploadController extends BaseController {
    @Autowired
    private FileUploadConfiguration fileUploaderConfiguration;

    @RequestMapping("/header")
    public Object headerUpload() {
        return Constant.COMMON + "/headerUpload";
    }

    @RequestMapping(value = "/header/add", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file,
                         HttpServletRequest request, ModelMap model) {
        System.out.println(file.getSize());
        String fileName = FileUploadHelper.getUniqueName(file.getOriginalFilename());
        File targetFile = new File(fileUploaderConfiguration.getSliderPath(), fileName);
        File path = new File(fileUploaderConfiguration.getSliderPath());
        if (!path.exists()) {
            path.mkdirs();
        }
        try {
            file.transferTo(targetFile);
            String waterStr = new String(fileUploaderConfiguration.getWaterName().getBytes("ISO8859-1"), "UTF-8");
            ImagesUtils.pressText(fileUploaderConfiguration.getSliderPath() + fileName, waterStr, fileUploaderConfiguration.getFontFamile(), Font.BOLD, 20, Color.gray, 0, 0, 0.5f);
        } catch (UnsupportedEncodingException e) {
            return "-1";
            // e.printStackTrace();
        } catch (IOException e) {
            return "-1";
        }
        return "1";
    }

    @RequestMapping("/test")
    public Object test() {
        return Constant.COMMON + "/test";
    }

    @RequestMapping(value = "/croppy", method = RequestMethod.POST)
    @ResponseBody
    public Object updateUserLogo(String avatar_data,
                                 @RequestParam(value = "avatar_file") MultipartFile imageFile, HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map ;
        // 存储图片信息用于返回到前台，有没有用再说吧
        Map<String,Object> photoMsg=new HashMap<>();
        System.out.println("avatar_data=" + avatar_data);
         map= FastJsonUtil.json2Map(avatar_data);
//        Enumeration paramNames = request.getParameterNames();
//        while (paramNames.hasMoreElements()) {
//            String paramName = (String) paramNames.nextElement();
//
//            String[] paramValues = request.getParameterValues(paramName);
//            if (paramValues.length == 1) {
//                String paramValue = paramValues[0];
//                if (paramValue.length() != 0) {
//                    System.out.println("参数：" + paramName + "=" + paramValue);
//                    map.put(paramName, paramValue);
//                }
//            }
//        }

        int x = 0;
        if (StringUtils.isNoneEmtryAndNull(map.get("x"))) {
            System.out.println(Float.parseFloat(map.get("x").toString()));
            x = (int) Float.parseFloat(map.get("x").toString());
        }
        int y = 0;
        if (StringUtils.isNoneEmtryAndNull(map.get("y"))) {
            y = (int) Float.parseFloat(map.get("y").toString());
        }
        int width=0;
        if(StringUtils.isNoneEmtryAndNull(map.get("width"))){
            width= (int) Float.parseFloat(map.get("width").toString());
        }
        int height=0;

        if(StringUtils.isNoneEmtryAndNull(map.get("height"))){
            height= (int) Float.parseFloat(map.get("height").toString());
        }

        int rotate=0;
        if(StringUtils.isNoneEmtryAndNull(map.get("rotate"))){
            rotate= (int) Float.parseFloat(map.get("rotate").toString());
        }
        //获取服务器的实际路径
        // String realPath = request.getSession().getServletContext().getRealPath("/");
        //   System.out.println(realPath);
        //需要上传的路径，我的路径根据用户的和当前日期划分路径
        String resourcePath = "upload/image";
        Date date = new Date();

        String photoName=date.getTime()+"";
        if (imageFile != null) {
            try {
                //文件名
                photoMsg.put("name",photoName);
                //获取时间的路径
                //  String fileName = FileUploadHelper.getUniqueName(file.getOriginalFilename());
                //    File targetFile = new File(fileUploaderConfiguration.getSliderPath(), fileName);
                File path = new File(fileUploaderConfiguration.getSliderPath()+"/"+photoName);
                if (!path.exists()) {
                    path.mkdirs();
                }
                //先把用户上传到原图保存到服务器上
                File file = new File(path, photoName + ".jpg");
                imageFile.transferTo(file);
                if (file.exists()) {
                    String src = fileUploaderConfiguration.getSliderPath()+photoName+"/"+photoName;
                    boolean[] flag = new boolean[6];
                    //旋转后剪裁图片
                    flag[0] = ImagesUtils.cutAndRotateImage(src + ".jpg", src + "_s.jpg", x, y, width, height, rotate);
                    //缩放图片,生成不同大小的图片，应用于不同的大小的头像显示
                    flag[1] = ImagesUtils.scale2(src + "_s.jpg", src + "_s_200.jpg", 200, 200, true);
                    flag[2] = ImagesUtils.scale2(src + "_s.jpg", src + "_s_100.jpg", 100, 100, true);
                    flag[3] = ImagesUtils.scale2(src + "_s.jpg", src + "_s_50.jpg", 50, 50, true);
                    flag[4] = ImagesUtils.scale2(src + "_s.jpg", src + "_s_30.jpg", 30, 30, true);
                    flag[5] = ImagesUtils.scale2(src + "_s.jpg", src + "_200.jpg", 200, 200, true);
                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e.toString());
                return e.getCause();
            }

        }
        photoMsg.put("state",200);
        photoMsg.put("result",true);
        return photoMsg;
    }
}
