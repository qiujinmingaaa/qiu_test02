package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constants.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Menu;
import com.itheima.service.MenuService;
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
    /**
    * @Description: 获取当前角色的菜单集合
    * @Param: [username]
    * @return: com.itheima.entity.Result
    * @Author: Walvi
    * @Date: 2019/8/30
    */
    public Result menuList(String username) {
        try {
            List<Menu> list = menuService.menuList(username);
            return new Result(true, MessageConstant.GET_MENU_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_MENU_FAIL);
        }
    }

}
