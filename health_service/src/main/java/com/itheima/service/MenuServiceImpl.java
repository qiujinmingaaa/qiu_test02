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

    @Override
    /**
    * @Description: 根据当前登录用户名查询该用户能看到的菜单
    * @Param: [username]
    * @return: java.util.List<com.itheima.pojo.Menu>
    * @Author: Walvi
    * @Date: 2019/8/30
    */
    public List<Menu> menuList(String username) {
        List<Menu> menuList = menuDao.findMenuListByUsername(username);
        return menuList;
    }
}
