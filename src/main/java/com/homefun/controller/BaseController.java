/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * Name：BaseController
 * Time：2016/5/20 9:57
 * author：WPJ587
 * description：所有控制器的父类
 **/
@Controller

public class BaseController {
    protected Logger myLogeer = LoggerFactory.getLogger(this.getClass().getName());

}
