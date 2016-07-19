/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.homefun.dao.EmployeesMapper;
import com.homefun.model.EmpListRequest;
import com.homefun.model.Employees;
import com.homefun.service.BaseService;
import com.homefun.service.EmployeesService;
import com.homefun.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Name：EmployeesServiceImp
 * Time：2016/5/18 15:46
 * author：WPJ587
 * description：员工业务
 **/
@Service
public class EmployeesServiceImp extends BaseService<Employees> implements EmployeesService {
    @Autowired
    private EmployeesMapper employeesMapper;
    /**
     * 根据empListRequest 获取员工所有信息
     *
     * @param empListRequest
     * @return
     */
    @Override
    public List<Employees> selectEmpsByEmpRequest(EmpListRequest empListRequest) {
        if (empListRequest == null) {
            return null;
        }
        Example example = new Example(Employees.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("empState", empListRequest.getState()); // 未离职
        if (empListRequest.getSearchType() != 0) {
            switch (empListRequest.getSearchType()) {
                case 1: // 员工名字
                    criteria.andLike("empName", "%" + empListRequest.getParams() + "%");
                    break;
            }
        }
        // 归属哪个门店
        if (StringUtils.isNoneEmtryAndNull(empListRequest.getShop_id())) {
            criteria.andEqualTo("empBelong", empListRequest.getShop_id());
        }
        if (empListRequest.isPaging()) {
            int page = empListRequest.getOffset() / empListRequest.getLimit() + 1;
            PageHelper.startPage(page, empListRequest.getLimit(), true);
        }

        return selectByExample(example);
    }

    /**
     * 匹配前端信息json
     * @param empListRequest
     * @return
     */
    public List<Employees> getSomeMsgebyEmpRequest(EmpListRequest empListRequest) {
        Map<String,Object> params=new HashMap<>();
        params.put("empState",empListRequest.getState());
        if(empListRequest.getSearchType()==1){

            params.put("empName",empListRequest.getParams());
        }
        if(empListRequest.getShop_id()!=null){
            params.put("empBelong",empListRequest.getShop_id());
        }
        if (empListRequest.isPaging()) {
            int page = empListRequest.getOffset() / empListRequest.getLimit() + 1;
            PageHelper.startPage(page, empListRequest.getLimit(), true);
        }
        List<Employees> temp =employeesMapper.selectByRequest(params);

        return temp;
    }
}
