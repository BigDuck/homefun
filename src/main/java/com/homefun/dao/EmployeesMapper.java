/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.dao;

import com.homefun.common.MyMapper;
import com.homefun.model.Employees;

import java.util.List;
import java.util.Map;

public interface EmployeesMapper extends MyMapper<Employees> {
    /**
     * 获取员工的基本信息用于列表的显示（虽然参数用Map不太好）
     * @param params
     *  key empState 员工状态
     *      empName 员工名字
     *      empBelong 归属门店
     * @return
     */
    List<Employees> selectByRequest(Map<String, Object> params);
}