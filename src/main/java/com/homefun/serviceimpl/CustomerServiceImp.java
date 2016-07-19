/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.serviceimpl;

import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.*;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.data.PointData;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonUtil;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.series.Series;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.pagehelper.PageHelper;
import com.homefun.dao.CustomerMapper;
import com.homefun.model.BTRequestParams;
import com.homefun.model.Customer;
import com.homefun.model.UserType;
import com.homefun.service.BaseService;
import com.homefun.service.CustomerService;
import com.homefun.service.UserTypeService;
import com.homefun.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Name：CustomerServiceImp
 * Time：2016/5/18 15:44
 * author：WPJ587
 * description：客户业务
 **/
@Service
public class CustomerServiceImp extends BaseService<Customer> implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private UserTypeService userTypeService;

    /**
     * 分页获取的订单（默认获取未删除）
     *
     * @param customer 条件实体类
     * @return
     */

    @Override
    public List<Customer> getByCustomers(Customer customer) {
        Example example = new Example(Customer.class);
        example.createCriteria().andEqualTo("customerIsdel", false);// 过滤失效不存在的订单
        if (customer.getPage() != null && customer.getRows() != null) {

            PageHelper.startPage(customer.getPage(), customer.getRows(), "id");
        }
        return customerTypeToName(customerMapper.selectByExample(example));
    }

    /**
     * 根据时间范围获取相应订单
     *
     * @param page      当前页码 当不需要分页的时候设置为0
     * @param pageSize  每页条数 当不需要分页的时候设置为0
     * @param startTime 订单下单开始时间
     * @param endTime   订单下单结束时间
     * @return
     */
    @Override
    public List<Customer> getCustomersByDate(int page, int pageSize, Date startTime, Date endTime) {
        Example example = new Example(Customer.class);
        if (page != 0 && pageSize != 0) {
            PageHelper.startPage(page, pageSize, true);
        }
        example.createCriteria().andBetween("customerBuytime", startTime, endTime);
        return customerTypeToName(customerMapper.selectByExample(example));
    }

    /**
     * 根据条件进行总数的查询，把查询条件放在实体类
     *
     * @param customer
     * @return
     */
    @Override
    public int getTotalByCustomer(Customer customer) {

        return customerMapper.selectCount(customer);
    }

    /**
     * 根据前端请求的实体类进行查询
     *
     * @param btRequestParams
     * @return
     */
    @Override
    public List<Customer> getCustomersByBTRequest(BTRequestParams btRequestParams) {
        if (btRequestParams == null) {
            return null;
        }
        Example example = new Example(Customer.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("customerIsdel", false);
        if (btRequestParams.getSearchCondition() != 0) {
            switch (btRequestParams.getSearchCondition()) {
                case 1:
                    criteria.andLike("contractNum", btRequestParams.getParams() + "%");
                    break;
                case 2:
                    criteria.andLike("customerName", "%" + btRequestParams.getParams() + "%");
                    break;
                case 3:
                    criteria.andLike("customerAddress", "%" + btRequestParams.getParams() + "%");
                    break;
            }
        }

        if (btRequestParams.isPaging()) {
            int page = btRequestParams.getOffset() / btRequestParams.getLimit() + 1;
            PageHelper.startPage(page, btRequestParams.getLimit(), true);
        }
        if (btRequestParams.getStartTime() != null && btRequestParams.getEndTime() != null) {
            criteria.andBetween("customerBuytime", btRequestParams.getStartTime(), btRequestParams.getEndTime());
        }
        if (btRequestParams.getUserTypeId() != 0) {
            criteria.andEqualTo("customerType", btRequestParams.getUserTypeId());
        }
        return customerTypeToName(customerMapper.selectByExample(example));
    }

    /**
     * 根据时间和查询的
     *
     * @param time_code
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<HashMap<String, Object>> findBuyTimeAndCount(int time_code, Date startTime, Date endTime) {
        if (startTime == null) { // 如果开始时间等于空那么默认往前推31
            startTime = DateUtil.getYesterday(new Date(), true, 31);
        }
        if (endTime == null) {
            endTime = new Date();
        }
        if (time_code > 3 || time_code < 0) {
            time_code = 0;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("time_code", time_code);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        List<HashMap<String, Object>> result = customerMapper.selectBuyTimeAndCount(params);

        return result;
    }

    /**
     * 将数值的userType转换可理解的套餐类型
     *
     * @param customers
     * @return
     */
    private List<Customer> customerTypeToName(List<Customer> customers) {
        if (customers == null) {
            return null;
        }

        List<UserType> userTypes = userTypeService.getUserType(new UserType());
        for (int i = 0; i < customers.size(); i++) {
            for (int j = 0; j < userTypes.size(); j++) {
                if (customers.get(i).getCustomerType() == userTypes.get(j).getId()) {
                    customers.get(i).setUserTypeName(userTypes.get(j).getUserType());
                }
            }
        }
        return customers;
    }

    /**
     * 把用hashmap封住的数据封装成echarts需要的数据格式
     *
     * @param params
     * @return
     */
    public Object hashMapData2Echarts(List<HashMap<String, Object>> params, String chartTitle, String subText) {
        List<String> dateStr = new ArrayList<>();
        List<Integer> orderCount = new ArrayList<>();


        // 先把hashmap的值全部取出来放到list里面方便使用
        for (int i = 0; i < params.size(); i++) {
            for (Map.Entry<String, Object> entry : params.get(i).entrySet()) {
                if ("bTime".equals(entry.getKey())) {
                    if(entry.getValue().toString().length()==8){
                        String date = entry.getValue().toString();
                        String year = date.substring(0, 4);
                        String month = date.substring(4, 6);
                        if (month.startsWith("0")) {
                            month = month.substring(1, 2);
                        }
                        String day = date.substring(6, 8);
                        if (day.startsWith("0")) {
                            day = day.substring(1, 2);
                        }
                        dateStr.add(year.substring(2, 4) + "年" + month + "月" + day + "日");
                    }
                    if(entry.getValue().toString().length()==6){
                        String date = entry.getValue().toString();
                        String year = date.substring(0, 4);
                        String month = date.substring(4, 6);
                        if (month.startsWith("0")) {
                            month = month.substring(1, 2);
                        }
                        dateStr.add(year.substring(2, 4) + "年" + month + "月");
                    }

                    if(entry.getValue().toString().length()==4){
                        String date = entry.getValue().toString();

                        dateStr.add(date.substring(2, 4) + "年");
                    }

                }
                if ("orderCount".equals(entry.getKey())) {
                    orderCount.add(Integer.valueOf(entry.getValue().toString()));
                }
            }
        }
        Option myOption = new Option();
        myOption.title().text(chartTitle).subtext(subText);
        myOption.tooltip().trigger(Trigger.axis);
        myOption.legend("订单数");
        myOption.toolbox().show(true).feature(Tool.mark, Tool.dataView,
                new MagicType(Magic.line, Magic.bar).show(true),
                Tool.restore, Tool.saveAsImage);
        myOption.calculable(true);
        myOption.xAxis(new CategoryAxis().data(dateStr.toArray()));
        myOption.yAxis(new ValueAxis());
        Bar bar = new Bar("订单量:");
        bar.data(orderCount.toArray());
        bar.markPoint().data(new PointData().type(MarkType.max), new PointData().type(MarkType.min));
        bar.markLine().data(new PointData().type(MarkType.average).name("平均订单量"));
        myOption.series(bar);
        return GsonUtil.prettyFormat(myOption).replace("\n","");
    }

    /**
     * 把Customer封装成pie的json
     *
     * @param customers
     * @return
     */
    @Override
    public Object customers2EchartsPie(List<Customer> customers,String chartTitle,String subText) {

        Set<String> pieShowSet=new HashSet<>();
        List<Integer> orderCountList=new LinkedList<>();
        int totalCount=customers.size();
        for (int i = 0; i <totalCount; i++) {
            pieShowSet.add(customers.get(i).getUserTypeName()); // 获取到用户类型
        }
        int orderCounts[]=new int[pieShowSet.size()];

        int indexCount=0;

        for (String str:pieShowSet){
            int count=0;

            for (int j = 0; j<customers.size() ; j++) {
                if(customers.get(j).getUserTypeName().equals(str)){
                 orderCounts[indexCount]=++count;
                }

            }

            indexCount++;
        }
        for (int i = 0; i <orderCounts.length ; i++) {
            orderCountList.add(orderCounts[i]);
        }
        Option myOption = new Option();
        myOption.title().text(chartTitle).subtext(subText);
        myOption.tooltip().trigger(Trigger.axis);
        myOption.legend("订单数");
        myOption.toolbox().show(true).feature(Tool.mark, Tool.dataView,
                new MagicType(Magic.line, Magic.bar).show(true),
                Tool.restore, Tool.saveAsImage).orient(Orient.vertical);

        myOption.calculable(true);
        myOption.xAxis(new CategoryAxis().data(pieShowSet.toArray()));
        myOption.yAxis(new ValueAxis());
        Bar bar = new Bar("订单量");
        bar.data(orderCountList.toArray());
        bar.markPoint().data(new PointData().type(MarkType.max), new PointData().type(MarkType.min));
        bar.markLine().data(new PointData().type(MarkType.average).name("平均订单量"));
        myOption.series(bar);

        return GsonUtil.prettyFormat(myOption);

    }


}
