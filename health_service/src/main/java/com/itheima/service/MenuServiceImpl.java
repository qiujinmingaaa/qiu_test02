package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;

import com.itheima.dao.MenuDao;
import com.itheima.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ProjectName: health
 * @Package: com.lh.service
 * @ClassName: MenuServiceImpl
 * @Description: 菜单管理
 * @Author: liuXiGua
 * @Date: 2019/8/28 17:49
 */
@Service(interfaceClass = MenuService.class)
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuDao menuDao;

    /**
     * @description: 查询全部菜单信息
     * @author: liuXiGua
     * @time: 2019/8/28 17:50
     */
    @Override
    public List<Menu> findAllMenu() {
        List<Menu> menus = menuDao.findAllMenu();
        return menus;
    }
}
