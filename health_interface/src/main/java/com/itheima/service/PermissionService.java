package com.itheima.service;

import com.itheima.pojo.Permission;

import java.util.List;
/**
 * @description: 权限管理Service接口
 * @author: liuXiGua
 * @time: 2019/8/29 11:32
 */
public interface PermissionService {
    List<Permission> findAllPermission();
}
