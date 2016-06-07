/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.dao;

import com.homefun.common.MyMapper;
import com.homefun.model.Customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CustomerMapper extends MyMapper<Customer> {
    /**
     * 根据开始时间和结束时间，和分组条件进行查询
     *
     * @param params key time_code {@link com.homefun.util.Constant}
     *               key startTime
     *               key endTime
     * @return
     */
    List<HashMap<String, Object>> selectBuyTimeAndCount(Map<String, Object> params);
}