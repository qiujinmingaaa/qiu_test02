package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Permission;

public interface SetService {
    void add(Permission permission);

    PageResult page(QueryPageBean queryPageBean);

    Permission findPerById(String id);

    void edit(Permission permission);

    void delete(String id);

    void addMenu(Menu menu);
}
