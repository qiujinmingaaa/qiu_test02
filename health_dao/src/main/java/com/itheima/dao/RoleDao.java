package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Role;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @Author: yp
 */
public interface RoleDao {

    /**
     * 根据userId查询角色(查询当前用户的角色)
     * @param userId
     * @return
     */
    Set<Role> findByUserId(Integer userId);

    /**
     * @description: 新增 角色
     * @author: liuXiGua
     * @time: 2019/8/29 11:44
     */
    void addRole(Role role);

    /**
     * @description: 新增 角色和权限关联
     * @author: liuXiGua
     * @time: 2019/8/29 11:44
     */
    void addrole_permission(Map map);

    /**
     * @description: 新增 角色和菜单关联
     * @author: liuXiGua
     * @time: 2019/8/29 11:44
     */
    void addrole_menuId(Map map);

    /**
     * @description: 多条件分页查询
     * @author: liuXiGua
     * @time: 2019/8/29 11:44
     */
    Page<Role> findPage(String queryString);

    /**
     * @description: 查询 角色通过角色ID
     * @author: liuXiGua
     * @time: 2019/8/29 11:44
     */
    Role findRoleById(Integer id);

    /**
     * @description: 查询 权限ID通过角色ID
     * @author: liuXiGua
     * @time: 2019/8/29 11:44
     */
    List<Integer> findPermissionIds(Integer id);

    /**
     * @description: 查询 菜单ID通过角色ID
     * @author: liuXiGua
     * @time: 2019/8/29 11:44
     */
    List<Integer> findMenuIds(Integer id);

    /**
     * @description: 修改 角色信息
     * @author: liuXiGua
     * @time: 2019/8/29 11:44
     */
    void editRole(Role role);

    /**
     * @description: 删除 角色和权限关联
     * @author: liuXiGua
     * @time: 2019/8/29 11:44
     */
    void deletePermissionIdsByroleId(Integer id);

    /**
     * @description: 删除 角色和菜单关联
     * @author: liuXiGua
     * @time: 2019/8/29 11:44
     */
    void deletemenuIdsByroleId(Integer id);

    /**
     * @description: 查询 用户ID通过角色ID
     * @author: liuXiGua
     * @time: 2019/8/29 11:44
     */
    List<Integer> findUserIdsByRoleId(Integer id);

    /**
     * @description: 删除 角色通过角色ID
     * @author: liuXiGua
     * @time: 2019/8/29 11:44
     */
    void deleteRoleById(Integer id);
}
