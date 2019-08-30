package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Permission;

public interface SetDao {
    void add(Permission permission);

    Page<Permission> page(String queryString);

    Permission findPerById(String id);

    void edit(Permission permission);

    void delete(String id);

    void addMenu(Menu menu);
}
