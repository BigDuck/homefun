/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)//自动忽略空字段 之后做接口

public class BaseEntity implements Serializable{
    private static final long serialVersionUID = -2576295644443260169L;


    @Transient
    @JsonIgnore
    private Integer page = 1;
    @JsonIgnore
    @Transient
    private Integer rows = 10;



    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}