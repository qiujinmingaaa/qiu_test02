package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;

import com.itheima.dao.PermissionDao;
import com.itheima.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ProjectName: health
 * @Package: com.lh.service
 * @ClassName: PermissionServiceImpl
 * @Description: 权限管理
 * @Author: liuXiGua
 * @Date: 2019/8/28 17:57
 */
@Service(interfaceClass = PermissionService.class)
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionDao permissionDao;
    @Override

    /**
     * @description: 查询全部权限信息
     * @author: liuXiGua
     * @time: 2019/8/29 11:42
     */
    public List<Permission> findAllPermission() {
        List<Permission> permissions = permissionDao.findAllPermission();
        return permissions;
    }
}
