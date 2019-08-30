package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.MenuDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.itheima.dao.MenuDao;
import com.itheima.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = MenuService.class)
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;


    @Override
    public void add(Menu menu) {
        menuDao.add(menu);

    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {

        //1.调用分页插件的方法(参数1:查询页码; 参数2:一页查询的数量)
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //2.调用Dao Page对象是分页插件封装的对象,这里面包含了分页查询的数据(total,list...)
        Page<Menu> page =  menuDao.findByConditions(queryPageBean.getQueryString());
        PageResult pageResult =new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    @Override
    public void delete(Integer menuId) {
        long count=menuDao.findByMenuId(menuId);
        if (count>0){
            throw new RuntimeException("该菜单被引用了不能删除");

        }
        menuDao.delete(menuId);



    }

    @Override
    public Menu findById(Integer id) {
        return menuDao.findById(id);
    }

    @Override
    public void edit(Menu menu) {
        menuDao.edit(menu);
    }



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
