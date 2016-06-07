package com.homefun.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.HashBiMap;
import com.homefun.dao.CustomerMapper;
import com.homefun.model.BTRequestParams;
import com.homefun.model.Customer;
import com.homefun.model.UserType;
import com.homefun.service.CustomerService;
import com.homefun.service.UserTypeService;
import com.homefun.util.Constant;
import com.homefun.util.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Name：Index
 * Time：2016/5/18 15:32
 * author：WPJ587
 * description：测试
 **/

public class Index extends BaseTest {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    UserTypeService userTypeService;

    @Test
    public void CustomerServiceTest() {
        List<Customer> res = customerService.selectAll();
        Customer customer = new Customer();
        customer.setCustomerIsdel(false);
        List<Customer> ressss = customerService.getByCustomers(customer);
        System.out.println(ressss.size());
        for (int i = 0; i < ressss.size(); i++) {
            System.out.println(ressss.get(i).toString());
            System.out.println(ressss.get(i).getCustomerIsdel());

        }
        System.out.println(">>>>>>>>>>>>" + ressss);
//        System.out.println(customerMapper.selectCount(new Customer()));
    }

    @Test
    public void userType() {
        List<UserType> res = userTypeService.getUserType(new UserType());
        System.out.println(res);
    }

    @Test
    public void example() {
        Example example = new Example(Customer.class);
        List<Example> exampleList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setPage(1);
        customer.setRows(10);
        PageHelper.startPage(customer.getPage(), customer.getRows(), "id");
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("customerAddress", "%思明区%");
        criteria.andEqualTo("customerType", 1);

//        exampleCriteria.andLike("customerAddress","%思明区%");
//        exampleCriteria.andEqualTo("customerName","吴培基");.andLike("customerAddress","%思明区%");
        //  example.or(example.createCriteria().andEqualTo("customerName","吴培基"));
        List<Customer> res = customerMapper.selectByExample(example);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i).toString());
        }
    }

    @Test
    public void page() {
        System.out.println(DateUtil.str2Date("2016-01-01", DateUtil.YMD));
        List<Customer> res = customerService.getCustomersByDate(1, 2, DateUtil.str2Date("2015-09-18", DateUtil.YMD), DateUtil.str2Date("2016-05-01", DateUtil.YMD));
        PageInfo pageInfo = new PageInfo<>(res);
        System.out.println(pageInfo.getTotal());
        System.out.println(res.toString());
    }

    @Test
    public void testCount() {
        Map<String, Object> pa = new HashMap<>();
        pa.put("time_code", 0);
        pa.put("startTime", DateUtil.str2Date("2016-01-18", DateUtil.YMD));
        pa.put("endTime", DateUtil.str2Date("2016-05-21", DateUtil.YMD));
       List<HashMap<String, Object>> res = customerMapper.selectBuyTimeAndCount(pa);
        String resultStr=customerService.hashMapData2Echarts(res,"订单统计","2016-01-18 至 2016-05-21").toString();
        System.out.println(resultStr);

    }
    @Test
    public void testPie(){
        BTRequestParams btRequestParams=new BTRequestParams();
        btRequestParams.setStartTime(DateUtil.str2Date("2016-05-08",DateUtil.YMD));
        List<Customer> customers=customerService.getCustomersByBTRequest(btRequestParams);
        String temp=customerService.customers2EchartsPie(customers,"pie表","2016-05-08").toString();
        System.out.println(temp);
    }
}
