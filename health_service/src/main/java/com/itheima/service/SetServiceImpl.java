package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.SetDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: health
 * @description:
 * @author:
 * @create: 2019-08-27 19:10
 **/
@Service(interfaceClass = SetService.class)
@Transactional
public class SetServiceImpl implements SetService {
    @Autowired
    private SetDao setDao;
    @Override
    public void add(Permission permission) {
        setDao.add(permission);

    }

    @Override
    public PageResult page(QueryPageBean queryPageBean) {
        String queryString = queryPageBean.getQueryString();
        Integer pageSize = queryPageBean.getPageSize();
        Integer currentPage = queryPageBean.getCurrentPage();
        PageHelper.startPage(currentPage,pageSize);
        Page<Permission> pages=setDao.page(queryString);
        return new PageResult(pages.getTotal(),pages.getResult());
    }

    @Override
    public Permission findPerById(String id) {
        return setDao.findPerById(id);
    }

    @Override
    public void edit(Permission permission) {
        setDao.edit(permission);
    }

    @Override
    public void delete(String id) {
        setDao.delete(id);
    }

    @Override
    public void addMenu(Menu menu) {
        setDao.addMenu(menu);
    }
}
