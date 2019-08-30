package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.User_ManagementDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: project_01
 * @description:
 * @author:
 * @create: 2019-08-17 15:04
 **/
@Service(interfaceClass = User_ManagementService.class)
@Transactional
public class User_Managementimpl implements User_ManagementService {

    @Autowired
    private User_ManagementDao user_managementDao;


    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<User> page = user_managementDao.findByConditions(queryPageBean.getQueryString());
        System.out.println("page = " + page);
        PageResult pageResult = new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    @Override
    public List<Map> findAll() {
        List<Map> role = user_managementDao.findAll();
        return role;
    }

    @Override
    public User findById(Integer id) {
       User user = user_managementDao.findById(id);
        return user;
    }

    @Override
    public void add(User user, Integer[] roleIds) {
        user_managementDao.add(user);
        Integer userId = user.getId();
        setCheckGroupAndCheckItem(userId,roleIds);
    }


    @Override
    public List<Integer> roleId(Integer id) {
        return user_managementDao.roleId(id);
    }

    @Override
    public void edit(User user, Integer[] roleIds) {
        user_managementDao.edit(user);
        user_managementDao.deleteCheckItemsById(user.getId());
        setCheckGroupAndCheckItem(user.getId(),roleIds);
    }

    @Override
    public void delete(Integer id) {
       user_managementDao.deleterole(id);
        user_managementDao.deleteuser(id);
    }

    private void setCheckGroupAndCheckItem(Integer userId, Integer[] roleIds) {
        for (Integer roleId : roleIds) {
            Map map = new HashMap();
            map.put("userId",userId);
            map.put("roleId",roleId);
            user_managementDao.setCheckGroupAndCheckItem(map);
        }
    }

}
