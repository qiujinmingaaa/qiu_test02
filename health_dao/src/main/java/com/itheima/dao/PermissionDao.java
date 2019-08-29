package com.itheima.dao;

import com.itheima.pojo.Permission;

import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @Author: yp
 */
public interface PermissionDao {

    /**
     * 根据角色id查询权限(查询当前角色拥有的权限)
     * @param roleId
     * @return
     */
    Set<Permission> findByRoleId(Integer roleId);

    /**
     * @description: 查询全部权限信息
     * @author: liuXiGua
     * @time: 2019/8/29 11:41
     */
    List<Permission> findAllPermission();
}
