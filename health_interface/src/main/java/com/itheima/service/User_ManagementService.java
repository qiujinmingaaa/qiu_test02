package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.User;

import java.util.List;
import java.util.Map;

public interface User_ManagementService {
    PageResult findPage(QueryPageBean queryPageBean);

    List<Map> findAll();

    User findById(Integer id);

    void add(User user, Integer[] roleIds);

    List<Integer> roleId(Integer id);

    void edit(User user, Integer[] roleIds);

    void delete(Integer id);
}
