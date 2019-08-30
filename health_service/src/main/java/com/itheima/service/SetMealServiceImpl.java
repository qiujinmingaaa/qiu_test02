package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.alibaba.fastjson.JSONArray;
import com.itheima.constants.RedisConstant;
import com.itheima.dao.SetMealDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: yp
 */
@Service(interfaceClass = SetMealService.class)
@Transactional
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    private SetMealDao setMealDao;


    @Autowired
    private JedisPool jedisPool;
    /**
     * 新增套餐
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        //向t_setmeal插入一条记录
        setMealDao.add(setmeal);
        // 向t_setmeal_checkgroup插入多条记录
        Integer setmealId = setmeal.getId();
        setSetMealAndCheckgroup(setmealId,checkgroupIds);

        //把图片存到Redis
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
        //当套餐发生添加操作时，删除Redis中当前存的套餐信息
        jedisPool.getResource().del("getSetMeal");

    }

    /**
     * 查询所有的套餐
     *
     * @return
     */
    @Override
    public List<Setmeal> getSetmeal() {
        //先从Redis中获取套餐信息
        List<Setmeal> list = null;
        list = (List<Setmeal>) JSONArray.parse(jedisPool.getResource().get("getSetMeal"));
        if (list==null){//如果Redis里面没有套餐信息，再去数据库查询，并存进Redis中
            list =  setMealDao.getSetmeal();
            jedisPool.getResource().set("getSetMeal", JSON.toJSONString(list));
        }
        return list;
    }

    /**
     * 根据套餐id查询套餐(包含检查组,检查项)
     *
     * @param id
     * @return
     */
    @Override
    public Setmeal findById(Integer id) {
        //方式一: 1.调用dao根据id查询setmeal基本信息  2.调用Dao取出套餐id查询出检查组集合  3.遍历检查组集合 ,调用Dao查询出每一个检查组的检查项集合
        //方式二: 直接调用一次Dao(在MyBatis映射文件里面 直接关联查询, 使用ResulMap)
        //先从Redis中获取套餐详情信息
        Setmeal setmeal = null;
        setmeal = JSON.parseObject(jedisPool.getResource().get("findById"+id),Setmeal.class);
        if (setmeal==null){//如果Redis里面没有套餐详情信息，再去数据库查询，并存进Redis中
            setmeal = setMealDao.findById(id);
            jedisPool.getResource().set("findById"+id,JSON.toJSONString(setmeal));//确保存进去的key不同，存时key中加上当前id
        }
        return setmeal;
    }


    /**
     * @Description: 创建套餐管理分页查询
     * @Param: [queryPageBean]
     * @return: com.itheima.entity.PageResult
     * @Author: JinPeng
     * @Date: 2019/8/29
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //1.调用分页插件的方法
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //2.调用Dao获得Page
        Page<Setmeal> page = setMealDao.findByConditions(queryPageBean.getQueryString());
        //3.封装成PageResult 返回
        PageResult pageResult = new PageResult(page.getTotal(), page.getResult());
        return pageResult;
    }

    private void setSetMealAndCheckgroup(Integer setmealId, Integer[] checkgroupIds) {
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            for (Integer checkgroupId : checkgroupIds) {
                Map map = new HashMap();
                map.put("setmealId", setmealId);
                map.put("checkgroupId", checkgroupId);
                setMealDao.setSetMealAndCheckgroup(map);
            }
        }
    }
}
