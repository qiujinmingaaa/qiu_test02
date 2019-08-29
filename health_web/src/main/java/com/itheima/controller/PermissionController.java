package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.entity.Result;
import com.itheima.pojo.Permission;
import com.itheima.service.PermissionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: health
 * @Package: com.itheima.controller
 * @ClassName: PermissionController
 * @Description: 权限管理
 * @Author: liuXiGua
 * @Date: 2019/8/29 11:19
 */

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Reference
    private PermissionService permissionService;
    /**
     * @description: 查询全部权限信息
     * @author: liuXiGua
     * @time: 2019/8/29 11:19
     */
    @RequestMapping("/findAllPermission")
    public Result findAllPermission(){
        Result result = new Result(true);
        try {
            List<Permission> permissions = permissionService.findAllPermission();
            result.setData(permissions);
        } catch (Exception e) {
            result.setFlag(false);
            e.printStackTrace();
        }
        return result;
    }



}
