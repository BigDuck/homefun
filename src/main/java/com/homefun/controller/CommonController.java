/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Name：CommonIndexController
 * Time：2016/5/20 9:57
 * author：WPJ587
 * description：比较业务无关的控制器
 **/
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController{
    /**
     * 弹出百度地图，进行位置的选择
     * @return
     */
    @RequestMapping(value = "/map",method = RequestMethod.GET)
    public Object showBaiduMap(){
        return "common/baiduMap";
    }
}
