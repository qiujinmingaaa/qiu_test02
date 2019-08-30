package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constants.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Menu;
import com.itheima.service.MenuService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @ProjectName: health
 * @Package: com.itheima.controller
 * @ClassName: MenuController
 * @Description: 菜单管理
 * @Author: liuXiGua
 * @Date: 2019/8/29 11:19
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Reference
    private MenuService menuService;


    @RequestMapping("/add")
    public Result add(@RequestBody Menu menu){
        try {
            menuService.add(menu);

            return new Result(true, MessageConstant.ADD_MENU_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_MENU_FAIL);
        }
    }


    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
       // PageResult pageResult =menuService.findPage(queryPageBean);
        PageResult pageResult = menuService.findPage(queryPageBean);
        return pageResult;
    }

    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            menuService.delete(id);
            return new Result(true, MessageConstant.DELETE_MENU_SUCCESS);

        } catch (RuntimeException e){
            e.printStackTrace();
            return new Result(false,e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_MENU_FAIL);
        }
    }



    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Menu menu=menuService.findById(id);
            return new Result(true, MessageConstant.QUERY_MENU_SUCCESS,menu);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_MENU_FAIL);
        }
    }


    @RequestMapping("/edit")
    public Result edit(@RequestBody Menu menu) {
        try {
            System.out.println("menu = " + menu);
            menuService.edit(menu);
            return new Result(true, MessageConstant.EDIT_MENU_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_MENU_FAIL);
        }
    }
    /**
     * @description: 查询全部菜单信息
     * @author: liuXiGua
     * @time: 2019/8/28 17:48
     */


    @RequestMapping("/findAllMenu")
    public Result findAllMenu(){
        Result result = new Result(true);
        try {
            List<Menu> menus = menuService.findAllMenu();
            result.setData(menus);
        } catch (Exception e) {
            result.setFlag(false);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/findMenuList")
    public Result menuList(String username){
        try {
            List<Menu> list = menuService.menuList(username);
            return new Result(true, MessageConstant.GET_MENU_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_MENU_FAIL);
        }
    }

}
