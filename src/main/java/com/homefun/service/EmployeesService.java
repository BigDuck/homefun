/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.service;

import com.homefun.model.EmpListRequest;
import com.homefun.model.Employees;

import java.util.List;

/**
 * Created by WPJ587 on 2016/5/18.
 */
public interface EmployeesService extends IService<Employees> {
    /**
     * 根据empListRequest 获取员工
     * @param empListRequest
     * @return
     */
    List<Employees> selectEmpsByEmpRequest(EmpListRequest empListRequest);
    /**
     * 匹配前端信息json
     * @param empListRequest
     * @return
     */
     List<Employees> getSomeMsgebyEmpRequest(EmpListRequest empListRequest);
}
