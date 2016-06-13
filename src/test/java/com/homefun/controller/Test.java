/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.controller;

import com.homefun.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Name：Test
 * Time：2016/5/28 15:11
 * author：WPJ587
 * description：
 **/

public class Test {
    public static void main(String[] args) {
       List<String> hello=new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            hello.add("agddfd");
        }
        System.out.println("结果："+hello);
        System.out.println(hello.toString().substring(1,hello.toString().length()-1).replace(",","+").trim());
    }
}
