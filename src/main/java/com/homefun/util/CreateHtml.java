/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.PrintWriter;
import java.util.Properties;

/**
 * 调用
 String realpath = request.getRealPath("/");
 //模板路径
 String urlVm = "/WEB-INF/vm/test.vm";
 //生成的html的路径，在webroot的html文件夹下面
 String urlHtml = realpath+"/html/test.htm";
 //设置模板中的元素
 VelocityContext  context = new VelocityContext();
 context.put("title", "hello word ");
 CreateHtml.createHtml(realpath,urlVm,urlHtml,context);
 */
public class CreateHtml {

    public static void createHtml(String realpath, String urlVm,
            String urlHtml, VelocityContext context) {
        
        // 加载路径
        Properties prop = new Properties();
        prop.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, realpath);
        try {
            Velocity.init(prop);
            // 初始化vm模板
            Template template = new Template();
            template = Velocity.getTemplate(urlVm, "utf-8");
            
            // 生成html页面
            PrintWriter writer = new PrintWriter(urlHtml);
            template.merge(context, writer);
            
            //切记关闭流
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
}