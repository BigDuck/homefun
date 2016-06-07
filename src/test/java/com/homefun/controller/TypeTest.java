/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.controller;

import com.homefun.dao.UserTypeMapper;
import com.homefun.model.BTRequestParams;
import com.homefun.model.Customer;
import com.homefun.model.UserType;
import com.homefun.service.CustomerService;
import com.homefun.service.UserTypeService;
import com.homefun.util.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;

/**
 * Name：TypeTest
 * Time：2016/5/22 14:09
 * author：WPJ587
 * description：
 **/
@Rollback(value = true)
public class TypeTest extends BaseTest {
    @Autowired
    UserTypeService userTypeService;
//    @Autowired
//    UserTypeMapper userTypeMapper;
    @Autowired
    CustomerService customerService;
    @Test
    public void add(){
        UserType userType=new UserType();
        userType.setUserType("520卡");
        userType.setUserDese("520活动");
        userType.setUserDate(new Date());
        int res = userTypeService.save(userType);
        System.out.println(userType.getId());
        List<UserType> re=userTypeService.getUserType(userType);
            if(re!=null&&re.size()>0){
              int id=  re.get(0).getId();
                System.out.println(id);
            }
        System.out.println("---over--------");
    }
    @Test
    public void page(){
        BTRequestParams btRequestParams=new BTRequestParams();
        btRequestParams.setSearchCondition(3);
        btRequestParams.setParams("思明区");
        btRequestParams.setStartTime(DateUtil.getYesterday(new Date(),true,180));
        btRequestParams.setEndTime(new Date());
        List<Customer> customers=customerService.getCustomersByBTRequest(btRequestParams);
        for (int i = 0; i <customers.size() ; i++) {
            System.out.println(customers.get(i).toString());
        }
//        System.out.println(userTypeMapper.selectAll()) ;
//
//        System.out.println(userTypeService.getUserType(new UserType()));
    }

}
