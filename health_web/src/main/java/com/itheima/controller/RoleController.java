package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.itheima.constants.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Role;
import com.itheima.service.RoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @ProjectName: health
 * @Package: com.itheima.controller
 * @ClassName: RoleController
 * @Description: 角色管理
 * @Author: liuXiGua
 * @Date: 2019/8/29 11:19
 */

@RestController
@RequestMapping("/role")
public class RoleController {

    @Reference
    private RoleService roleService;

    /**
     * @description: 新增角色以及相关权限
     * @author: liuXiGua
     * @time: 2019/8/29 11:22
     */
    @RequestMapping("/addRole")
    public Result addRole(Integer[] permissionIds, Integer[] menuIds,@RequestBody Role role){
        Result result = new Result(true);
        try {
            roleService.addRole(permissionIds,menuIds,role);
            result.setMessage(MessageConstant.ADD_ROLE_SUCCESS);
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.ADD_ROLE_FAIL);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @description: 多条件分页查询
     * @author: liuXiGua
     * @time: 2019/8/29 11:22
     */
    @RequestMapping("/findPage")
    public PageResult findPage(QueryPageBean queryPageBean){
        System.out.println(queryPageBean);
        PageResult pageResult = null;
        try {
            if (queryPageBean.getQueryString() != null || "".equals(queryPageBean.getQueryString())){
                queryPageBean.setCurrentPage(1);
            }
            pageResult = roleService.findPage(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageResult;
    }

    /**
     * @description: 通过角色ID查找角色信息
     * @author: liuXiGua
     * @time: 2019/8/29 11:23
     */
    @RequestMapping("/findRoleById")
    public Result findRoleById(Integer id){
        Result result = new Result(true);
        try {
            Map map = roleService.findRoleById(id);
            result.setData(map);
        } catch (Exception e) {
            result.setFlag(false);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @description: 修改角色以及对应权限相关信息
     * @author: liuXiGua
     * @time: 2019/8/29 11:24
     */
    @RequestMapping("/editRole")
    public Result editRole(Integer[] permissionIds, Integer[] menuIds,@RequestBody Role role){
        Result result = new Result(true);
        try {
            roleService.editRole(permissionIds,menuIds,role);
            result.setMessage(MessageConstant.EDIT_ROLE_SUCCESS);
        } catch (Exception e) {
            result.setMessage(MessageConstant.EDIT_ROLE_FAIL);
            result.setFlag(false);
            e.printStackTrace();
        }
        return result;
    }


    /**
     * @description: 删除角色以及相关权限
     * @author: liuXiGua
     * @time: 2019/8/29 11:25
     */
    @RequestMapping("/delete")
    public Result delete(Integer id){
        Result result = null;
        try {
            result = roleService.delete(id);
        } catch (Exception e) {
            result.setMessage(MessageConstant.DELETE_ROLE_FAIL);
            result.setFlag(false);
            e.printStackTrace();
        }
        return result;
    }
}
