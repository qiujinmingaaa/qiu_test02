package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.MemberManageDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: health
 * @description:
 * @author:
 * @create: 2019-08-29 17:54
 **/
@Service(interfaceClass = MenberManageSrevice.class)
@Transactional
public class MenberManageSreviceImpl implements MenberManageSrevice {
    @Autowired
    private MemberManageDao memberManageDao;
    @Override
    public void add(Member member) {
        memberManageDao.add(member);

    }

    @Override
    public PageResult page(QueryPageBean pageBean) {
        int currentPage=pageBean.getCurrentPage();
        int pageSize=pageBean.getPageSize();
        String queryPage=pageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<Member> checkItems=memberManageDao.findByCondition(queryPage);
        return new PageResult(checkItems.getTotal(),checkItems.getResult());
    }

    @Override
    public void delete01(int id) {
        memberManageDao.findIfUsed(id);

    }

    @Override
    public Member seefrom(int id) {
        return memberManageDao.seefrom(id);
    }

    @Override
    public void edit(Member checkItem) {
        memberManageDao.edit(checkItem);
    }

    @Override
    public List<Member> getAllMember() {
        return memberManageDao.getAllMember();
    }
}
