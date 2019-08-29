package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.itheima.constants.MessageConstant;
import com.itheima.dao.RoleDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: health
 * @Package: com.lh.service
 * @ClassName: RoleServiceImpl
 * @Description: 角色管理
 * @Author: liuXiGua
 * @Date: 2019/8/28 17:26
 */
@Transactional
@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;
    @Override
    public void addRole(Integer[] permissionIds, Integer[] menuIds, Role role) {
        roleDao.addRole(role);
        add(permissionIds, menuIds, role);
    }

    private void add(Integer[] permissionIds, Integer[] menuIds, Role role) {
        Map map = new HashMap();
        map.put("roleId", role.getId());
        for (Integer permissionId : permissionIds) {
            map.put("permissionId", permissionId);
            roleDao.addrole_permission(map);
        }
        System.out.println(4);
        for (Integer menuId : menuIds) {
            map.put("menuId", menuId);
            roleDao.addrole_menuId(map);
        }
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageResult pageResult = new PageResult();
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<Role> page = roleDao.findPage(queryPageBean.getQueryString());
        pageResult.setTotal(page.getTotal());
        pageResult.setRows(page.getResult());
        return pageResult;
    }

    @Override
    public Map findRoleById(Integer id) {
        Map map = new HashMap();
        Role role = roleDao.findRoleById(id);
        map.put("roleData", role);
        List<Integer> permissionIds = roleDao.findPermissionIds(id);
        map.put("permissionIds", permissionIds);
        List<Integer> menuIds = roleDao.findMenuIds(id);
        map.put("menuIds", menuIds);
        return map;
    }

    @Override
    public void editRole(Integer[] permissionIds, Integer[] menuIds, Role role) {
        System.out.println(2);
        roleDao.editRole(role);
        roleDao.deletePermissionIdsByroleId(role.getId());
        roleDao.deletemenuIdsByroleId(role.getId());
        System.out.println(3);
        add(permissionIds, menuIds, role);
    }

    @Override
    public Result delete(Integer id) {
        List<Integer> userIds = roleDao.findUserIdsByRoleId(id);
        if (userIds.size() != 0){
            return new Result(false, "该角色已被用户引用,拒绝删除");
        }
        roleDao.deletemenuIdsByroleId(id);
        roleDao.deletePermissionIdsByroleId(id);
        roleDao.deleteRoleById(id);
        return new Result(true, MessageConstant.DELETE_ROLE_SUCCESS);
    }
}
