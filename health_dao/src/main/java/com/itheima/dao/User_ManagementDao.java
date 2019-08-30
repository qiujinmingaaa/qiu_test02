package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.User;

import java.util.List;
import java.util.Map;

public interface User_ManagementDao {
    Page<User> findByConditions(String queryString);

    List<Map> findAll();

    User findById(Integer id);

    void add(User user);

    void setCheckGroupAndCheckItem(Map map);

    List<Integer> roleId(Integer id);

    void edit(User user);

    void deleteCheckItemsById(Integer id);

    void deleterole(Integer id);

    void deleteuser(Integer id);
}
