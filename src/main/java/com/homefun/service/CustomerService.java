/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.service;

import com.homefun.model.BTRequestParams;
import com.homefun.model.Customer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WPJ587 on 2016/5/18.
 */
public interface CustomerService extends IService<Customer>{
    /**
     * 根据
     * @param customer
     * @return
     */
    List<Customer> getByCustomers(Customer customer);

    /**
     * 根据时间范围获取相应订单
     * @param page 当前页码
     * @param pageSize 每页条数
     * @param startTime 订单下单开始时间
     * @param endTime 订单下单结束时间
     * @return 相应的订单集合
     */
    List<Customer> getCustomersByDate(int page,int pageSize,Date startTime,Date endTime);

    /**
     * 根据条件进行总数的查询，把查询条件放在实体类
     * @param customer
     * @return
     */
    int getTotalByCustomer(Customer customer);

    /**
     * 根据前端请求的实体类进行查询
     * @param btRequestParams
     * @return
     */
    List<Customer> getCustomersByBTRequest(BTRequestParams btRequestParams);

    /**
     * 根据时间和查询的
     * @param time_code
     * @param startTime
     * @param endTime
     * @return
     */
    List<HashMap<String, Object>> findBuyTimeAndCount(int time_code,Date startTime,Date endTime);

    /**
     *
     * @param params 结果集
     * @param chartTitle echart图主标题
     * @param subText echart图副标题
     * @return
     */
     Object hashMapData2Echarts(List<HashMap<String,Object>> params,String chartTitle,String subText);

    /**
     * 把Customer封装成pie的json
     * @param customers
     * @return
     */
    Object customers2EchartsPie(List<Customer> customers,String title,String subText);
}
