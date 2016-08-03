package com.homefun.controller;

import com.homefun.dao.EmployeesMapper;
import com.homefun.model.EmpListRequest;
import com.homefun.model.Employees;
import com.homefun.service.EmployeesService;
import org.junit.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

/**
 * Name：EmpTest
 * Time：2016/7/15 14:51
 * author：WPJ587
 * description：
 **/
@Rollback(value = false)
public class EmpTest extends BaseTest {

    @Autowired
    EmployeesService employeesService;

    @org.junit.Test
    public void empAdd() {
        EmpListRequest empListRequest = new EmpListRequest();
        empListRequest.setOffset(10);
        System.out.println(employeesService.getSomeMsgebyEmpRequest(empListRequest));
        empListRequest.setOffset(20);
        System.out.println(employeesService.getSomeMsgebyEmpRequest(empListRequest));
        empListRequest.setOffset(30);
        System.out.println(employeesService.getSomeMsgebyEmpRequest(empListRequest));
        empListRequest.setOffset(40);
        System.out.println(employeesService.getSomeMsgebyEmpRequest(empListRequest));

    }

    @org.junit.Test
    public void select() {
        EmpListRequest empListRequest = new EmpListRequest();
        empListRequest.setSearchType(1);
        empListRequest.setParams("吴培基");
        List<Employees> re = employeesService.getSomeMsgebyEmpRequest(empListRequest);
        for (int i = 0; i < re.size(); i++) {
            System.out.println(re.get(i).toString());
        }
    }

    @Test
    public void selectBykey() {
        Employees employees = employeesService.selectByKey(1);
        System.out.println(employees);
    }
}
