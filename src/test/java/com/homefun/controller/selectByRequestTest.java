package com.homefun.controller;

import com.github.pagehelper.PageHelper;
import com.homefun.dao.EmployeesMapper;
import com.homefun.model.Employees;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Name：selectByRequestTest
 * Time：2016/7/15 17:24
 * author：WPJ587
 * description：
 **/

public class selectByRequestTest extends  BaseTest{
    @Autowired
    EmployeesMapper employeesMapper;
    @Test
    public void test (){
        PageHelper.startPage(1, 3, true);
        Map<String,Object> param=new HashMap<>();
        param.put("empName","吴培基");
        param.put("empBelong","af252c84a3314cd6b0568088584fb424");
      List<Employees> re=  employeesMapper.selectByRequest(param);
        for (int i = 0; i <re.size() ; i++) {
            System.out.println(re.get(i).toString());
        }

    }
}
