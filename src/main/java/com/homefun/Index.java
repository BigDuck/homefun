/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun;

import com.homefun.util.FileUploadConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Name：Index
 * Time：2016/5/18 15:03
 * author：WPJ587
 * description：程序主入口
 **/

@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration
//@EnableScheduling
public class Index  extends WebMvcConfigurerAdapter
{
    @Autowired
    FileUploadConfiguration fileUploaderConfiguration;

    public static void main( String[] args )
    {
        SpringApplication.run(Index.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String location = "file:" + fileUploaderConfiguration.getPathToUploadFolder();
        registry.addResourceHandler("/images/**").addResourceLocations(location);
    }

    @Bean
    @ConfigurationProperties("fileUpload")
    public FileUploadConfiguration uploaderConfiguration() {
        return new FileUploadConfiguration();
    }


}

