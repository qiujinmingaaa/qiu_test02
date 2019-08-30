package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: yp
 */
public interface SetMealService {

    /**
     * 新增套餐
     * @param setmeal
     * @param checkgroupIds
     */
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 查询所有的套餐
     * @return
     */
    List<Setmeal> getSetmeal();

    /**
     * 根据套餐id查询套餐(包含检查组,检查项)
     * @param id
     * @return
     */
    Setmeal findById(Integer id);

    /** 
    * @Description: 创建套餐管理分页查询
    * @Param: [queryPageBean] 
    * @return: com.itheima.entity.PageResult 
    * @Author: JinPeng
    * @Date: 2019/8/29 
    */
    PageResult findPage(QueryPageBean queryPageBean);
}
