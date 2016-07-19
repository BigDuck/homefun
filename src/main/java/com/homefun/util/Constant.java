/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Name：Constant
 * Time：2016/5/28 14:50
 * author：WPJ587
 * description：全局常量
 **/

public class Constant {
    /**
     * 天
     */
    public static final int DAY = 0;
    /**
     * 年
     */
    public static final int YEAR = 1;
    /**
     * 月
     */
    public static final int MONTH = 2;
    /**
     * 周
     */
    public static final int WEEK = 3;
    /**
     * 存放搜索条件的常量
     */
    public static Map<Integer, String> SEARCH_CONDITION = new HashMap<Integer, String>() {
        private static final long serialVersionUID = 4666172108332101225L;

        {
            put(0, "选择查询条件");
            put(1, "按订单号查询");
            put(2, "按用户名查询");
            put(3, "按地址查询");
        }
    };
    public static Map<Integer, String> EMP_STATE = new HashMap<Integer, String>() {
        private static final long serialVersionUID = 4896121447465087526L;

        {
            put(0, "在职");
            put(1, "离职");
            put(2, "待离职");
        }
    };
    public static Map<String,String> SHOP_NAME=new HashMap<String, String>(){
        private static final long serialVersionUID = -5071833054046376165L;

        {
            // 以后在加载的时候从缓存里面进行加载
            put("HOME0001", "会展店");
            put("HOME0002", "总部");
        }
    };
    /**
     * common包
     */
    public static String COMMON = "common";
    public static String CUSTOMER = "customer";
    public static String EMP = "emp";

    public static void main(String[] args) {
        System.out.println(Constant.SEARCH_CONDITION.get(0));

    }
}
