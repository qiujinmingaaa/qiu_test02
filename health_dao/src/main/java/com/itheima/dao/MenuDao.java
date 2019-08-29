package com.itheima.dao;

import com.itheima.pojo.Menu;

import java.util.List;

/**
 * @ProjectName: git_demo
 * @Package: com.itheima.dao
 * @ClassName: MenuDao
 * @Description: 菜单管理
 * @Author: liuXiGua
 * @Date: 2019/8/29 11:38
 */
public interface MenuDao {

    /**
     * @description: 查询全部菜单信息
     * @author: liuXiGua
     * @time: 2019/8/29 11:39
     */
    List<Menu> findAllMenu();
}
