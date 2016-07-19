package com.homefun.model;

/**
 * Name：EmpListRequest
 * Time：2016/7/15 10:53
 * author：WPJ587
 * description：员工请求实体类
 **/

public class EmpListRequest  extends BaseEntity{
    private static final long serialVersionUID = -5725183221486879791L;
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
     * 当前偏移量
     */
    private int offset=0;
    /**
     * 员工状态{@link com.homefun.util.Constant}
     * 0 在职
     */
    private int state=0;
    /**
     * 查找条件
     * 1 员工名字
     * 2 员工
     */
    private int searchType=0;
    /**
     * 查找参数
     */
    private String params;
    /**
     * 归属店铺
     */
    private String shop_id;

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public boolean isPaging() {
        return paging;
    }

    public void setPaging(boolean paging) {
        this.paging = paging;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
