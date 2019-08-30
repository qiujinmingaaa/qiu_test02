package com.itheima.dao;
/**
 * @Description:
 * @Author: qiu
 */

import com.github.pagehelper.Page;
import com.itheima.pojo.Member;

import java.util.List;

public interface MemberManageDao {
    void add(Member member);

    Page<Member> findByCondition(String queryPage);

    void findIfUsed(int id);

    Member seefrom(int id);

    void edit(Member checkItem);

    List<Member> getAllMember();

}
