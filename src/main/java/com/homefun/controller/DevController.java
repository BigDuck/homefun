package com.homefun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Name：DevController
 * Time：2016/6/9 9:47
 * author：WPJ587
 * description：开发人员或者运营人员使用
 **/

@Controller
@RequestMapping("/dev")
public class DevController extends BaseController {
    @RequestMapping("/todruid")
    public Object druid() {
        return "dev/druid";
    }
}
