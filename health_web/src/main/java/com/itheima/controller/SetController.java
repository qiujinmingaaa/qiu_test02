package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Permission;
import com.itheima.service.SetService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: health
 * @description:
 * @author:
 * @create: 2019-08-27 19:04
 **/
@RestController
@RequestMapping("/set")
public class SetController {
    @Reference
    private SetService setService;
    @RequestMapping("/add")
    public Result add(@RequestBody Permission permission) {

        try {
            setService.add(permission);
            return new Result(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败");
        }
    }

     @RequestMapping("/page")
     public PageResult page(@RequestBody QueryPageBean queryPageBean){

        return setService.page(queryPageBean);

    }

      @RequestMapping("/seefrom")
      public Result seefrom(String id){
         try {
             Permission permission=setService.findPerById(id);
             return new Result(true, "查询权限成功",permission);
         } catch (Exception e) {
             e.printStackTrace();
             return new Result(false,"查询成功");
         }
      }

  @RequestMapping("/edit")
  public Result edit(@RequestBody Permission permission){
     try {
         setService.edit(permission);
         return new Result(true, "更新成功");
     } catch (Exception e) {
         e.printStackTrace();
         return new Result(false,"更新失败");
     }
 }
    @RequestMapping("/delete")
    public Result delete(String id){
        try {
            setService.delete(id);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }
    @RequestMapping("/addMenu")
    public Result addMenu(@RequestBody Menu menu){
        try {
            setService.addMenu(menu);
            return new Result(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }








}
