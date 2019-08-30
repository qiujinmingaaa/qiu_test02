package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constants.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.User;
import com.itheima.service.User_ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * @program: project_01
 * @description:
 * @author:
 * @create: 2019-08-26 20:30
 **/

@RestController
@RequestMapping("/usermanagement")
//user_management
public class user_managementController {

    @Reference
    private User_ManagementService user_management;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /** 
    * @Description: 分页查询 
    * @Param: [queryPageBean] 
    * @return: com.itheima.entity.PageResult 
    * @Author: JinPeng
    * @Date: 2019/8/29 
    */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = user_management.findPage(queryPageBean);
        List rows = pageResult.getRows();
        for (Object row : rows) {
            System.out.println("row = " + row.toString());
        }
        return pageResult;

    }
    
    /** 
    * @Description: 增加用户
    * @Param: [user, roleIds] 
    * @return: com.itheima.entity.Result 
    * @Author: JinPeng
    * @Date: 2019/8/29 
    */
    @RequestMapping("/add")
    public Result add(@RequestBody User user, Integer[] roleIds) {
        try {

            String password = user.getPassword();
            String encode = bCryptPasswordEncoder.encode(password);
            user.setPassword(encode);
            user_management.add(user, roleIds);
            return new Result(true, MessageConstant.ADD_USER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_USER_FAIL);
        }
    }

    /** 
    * @Description: 编辑用户
    * @Param: [user, roleIds] 
    * @return: com.itheima.entity.Result 
    * @Author: JinPeng
    * @Date: 2019/8/29 
    */
    @RequestMapping("/edit")
    public Result edit(@RequestBody User user, Integer[] roleIds) {
        try {
            String password = user.getPassword();
            String encode = bCryptPasswordEncoder.encode(password);
            user.setPassword(encode);
            user_management.edit(user,roleIds);
            return new Result(true, MessageConstant.EDIT_USER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_USER_FAIL);
        }
    }


    /** 
    * @Description: 根据id查询用户
    * @Param: [id] 
    * @return: com.itheima.entity.Result 
    * @Author: JinPeng
    * @Date: 2019/8/29 
    */
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            User user = user_management.findById(id);
            user.setPassword(null);
            return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS,user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_SUCCESS);
        }
    }
    /** 
    * @Description: 查询所有角色
    * @Param: [] 
    * @return: com.itheima.entity.Result 
    * @Author: JinPeng
    * @Date: 2019/8/29 
    */
    @RequestMapping("/findAll")
    public Result findAll() {
        List<Map> role = user_management.findAll();
        if (role != null && role.size() > 0) {
            return new Result(true, MessageConstant.QUERY_CHECKUSER_SUCCESS, role);
        }
        return new Result(false, MessageConstant.QUERY_CHECKUSER_FAIL);
    }

    /** 
    * @Description: 查询用户已有角色
    * @Param: [] 
    * @return: com.itheima.entity.Result 
    * @Author: JinPeng
    * @Date: 2019/8/29 
    */
    @RequestMapping("/roleId")
    public Result roleIds(Integer id) {
        try {
            List<Integer> list = user_management.roleId(id);
            return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ROLE_FAIL);
        }
    }
    /** 
    * @Description: 根据id删除用户
    * @Param:  
    * @return:  
    * @Author: JinPeng
    * @Date: 2019/8/29 
    */
        @RequestMapping("/delete")
            public Result delete(Integer id){
                try {
                    user_management.delete(id);
                    return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new Result(false, MessageConstant.DELETE_CHECKITEM_SUCCESS);
                }
            }
}
