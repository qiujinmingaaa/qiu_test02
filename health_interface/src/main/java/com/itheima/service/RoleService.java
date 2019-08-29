package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Role;

import java.util.Map;
/**
 * @description: 角色管理Service接口
 * @author: liuXiGua
 * @time: 2019/8/29 11:32
 */
public interface RoleService {
    void addRole(Integer[] permissionIds, Integer[] menuIds, Role role);

    PageResult findPage(QueryPageBean queryPageBean);

    Map findRoleById(Integer id);

    void editRole(Integer[] permissionIds, Integer[] menuIds, Role role);

    Result delete(Integer id);
}
