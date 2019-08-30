package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Member;

import java.util.List;
/**
 * @Description:
 * @Author: qiu
 */

public interface MenberManageSrevice {
    void add(Member member);

    PageResult page(QueryPageBean pageBean);

    void delete01(int id);

    Member seefrom(int id);

    void edit(Member checkItem);

    List<Member> getAllMember();
}
