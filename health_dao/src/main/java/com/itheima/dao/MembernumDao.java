package com.itheima.dao;
/**
 * @program: health
 * @description:
 * @author:qiu
 * @create: 2019-08-29 11;30
 **/
import java.util.List;
import java.util.Map;


public interface MembernumDao {
    List<Map> getSexMember();

    List<String> getAllIdCards();
}
