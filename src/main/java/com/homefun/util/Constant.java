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




}
