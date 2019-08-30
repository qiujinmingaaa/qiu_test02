package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Menu;

import java.util.List;

public interface MenuService {

    void add(Menu menu);

    PageResult findPage(QueryPageBean queryPageBean);

    void delete(Integer id);

    Menu findById(Integer id);

    void edit(Menu menu);

    List<Menu> findAllMenu();

    List<Menu> menuList(String username);
}
