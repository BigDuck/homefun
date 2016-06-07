/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.db;

import com.homefun.common.MyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * Created by WPJ587 on 2015/10/4.
 */
@Configuration
public class OtherConfig {
    /**
     * 这个是为了使用通用mapper而独立配置的tk.mybatis.spring.mapper.MapperScannerConfigurer
     * 不能写在MybatisConfig否则胡出现druidDataSourceEntity为空的情况
     * 我也不知道为什么。
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.homefun.dao");
        mapperScannerConfigurer.setMarkerInterface(MyMapper.class);
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }
}
