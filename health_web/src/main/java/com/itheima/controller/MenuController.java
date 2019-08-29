package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
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

}
