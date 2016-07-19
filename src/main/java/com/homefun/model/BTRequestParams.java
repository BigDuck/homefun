/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.model;

import java.util.Date;

/**
 * Name：BTRequestParams
 * Time：2016/5/27 21:15
 * author：WPJ587
 * description：bootstrap-table前端请求实体类
 **/

public class BTRequestParams  extends BaseEntity{
    private static final long serialVersionUID = 6768995636213449878L;
    /**
     * 是否分页
     * 默认分页true
     */
    private boolean paging=true;
    /**
     * 排序asc desc
     */
    private String order;
    /**
     * 每页条数
     */
    private int limit=10;
    /**
     * 当前便宜量
     */
    private int offset=0;
    /**
     * 搜索条件
     * 0 代表没条件
     * 1 按照订单号
     * 2 用户名
     * 3 地址
     * 4 套餐类型
     */
    private int searchCondition;
    /**
     * 订单开始时间
     */
    private Date startTime;
    /**
     * 订单结束时间
     */
    private Date endTime;
    /**
     * 查询的参数内容
     */
    private String params;

    /**
     * 用户类型
     *
     */
    private int userTypeId=0;
    /**
     * 套餐类型
     */


    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(int searchCondition) {
        this.searchCondition = searchCondition;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {

        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {

        this.endTime = endTime;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public boolean isPaging() {
        return paging;
    }

    public void setPaging(boolean paging) {
        this.paging = paging;
    }

    @Override
    public String toString() {
        return "BTRequestParams{" +
                "order='" + order + '\'' +
                ", limit=" + limit +
                ", offset=" + offset +
                ", searchCondition=" + searchCondition +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", params='" + params + '\'' +
                '}';
    }
}
