/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Name：PeopleManagerConstroller
 * Time：2016/6/6 17:55
 * author：WPJ587
 * description：人员管理
 **/
@Controller
@RequestMapping("/admin")
public class PeopleManagerConstroller extends BaseController{
    @RequestMapping("/toemp")
    public Object empPage(){
        return "emp/empManage";
    }

}
