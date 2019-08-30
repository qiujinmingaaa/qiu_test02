package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Menu;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuDao {


    void add(Menu menu);

    Page<Menu> findByConditions(String queryString);

    void delete(Integer menuId);

    long findByMenuId(Integer menuId);

    Menu findById(Integer id);

    void edit(Menu menu);


/**
 * @ProjectName: git_demo
 * @Package: com.itheima.dao
 * @ClassName: MenuDao
 * @Description: 菜单管理
 * @Author: liuXiGua
 * @Date: 2019/8/29 11:38
 */


    /**
     * @description: 查询全部菜单信息
     * @author: liuXiGua
     * @time: 2019/8/29 11:39
     */
    List<Menu> findAllMenu();

    @Select("SELECT * FROM t_menu WHERE level = 1 AND id in (SELECT menu_id FROM t_user_role ur,t_role_menu rm,t_user u WHERE ur.role_id = rm.role_id AND ur.user_id = u.id AND u.username =#{username})")
    @Results(
            @Result(property = "children",column = "id",
                    many = @Many(select = "com.itheima.dao.MenuDao.findChildrenList")
            )
    )
    List<Menu> findMenuListByUsername(String username);
    @Select("SELECT * FROM t_menu WHERE parentMenuId = #{id}")
    List<Menu> findChildrenList(Integer id);
}
