package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map; /**
 * @Description:
 * @Author: yp
 */
public interface SetMealDao {

    /**
     * 新增套餐t_setmeal插入一条记录
     * @param setmeal
     */
    void add(Setmeal setmeal);

    /**
     * 套餐管理检查组t_setmeal_checkgroup插入多条记录
     * @param map
     */
    void setSetMealAndCheckgroup(Map map);

    /**
     * 查询所有的套餐
     * @return
     */
    List<Setmeal> getSetmeal();

    /**
     * 根据id查询套餐
     * @param id
     * @return
     */
    Setmeal findById(Integer id);

    /** 
    * @Description: 创建套餐管理分页查询
    * @Param: [queryString] 
    * @return: com.github.pagehelper.Page<com.itheima.pojo.Setmeal> 
    * @Author: JinPeng
    * @Date: 2019/8/29 
    */
    Page<Setmeal> findByConditions(String queryString);


}
