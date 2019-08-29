package com.itheima.service;

import com.itheima.pojo.Menu;

import java.util.List;

/**
 * @description: 菜单管理Service接口
 * @author: liuXiGua
 * @time: 2019/8/29 11:32
 */
public interface MenuService {
    List<Menu> findAllMenu();
}
