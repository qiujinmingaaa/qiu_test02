package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.itheima.entity.Result;
import com.itheima.service.MembernumService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: health
 * @description:
 * @author:qiu
 * @create: 2019-08-29 11;30
 **/
@RestController
@RequestMapping("/member")
public class MembernumController {
    //qiu 获得性别的统计数据
    @Reference
    private MembernumService memberService;
    @RequestMapping("/getSex")
    public Result getSex(){
        Map map=new HashMap<>();
        List<String> memberNames=new ArrayList<>();
        List<Map> memberCount=new ArrayList<>();

        try {
            memberCount=memberService.getSex();
            map.put("memberCount",memberCount);
            for (Map map1 : memberCount) {
                memberNames.add((String)map1.get("name"));
            }
            map.put("memberNames",memberNames);
            return new Result(true, "返回数据成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"返回数据失败");
        }
    }
    //qiu 获得年龄段的统计数据
    @RequestMapping("/getAgeGroup")
    public Result getAgeGroup(){
        Map map=new HashMap<>();
        List<String> memberNames=new ArrayList<>();
        List<Map> memberCount=new ArrayList<>();
        try {
            memberCount=memberService.getAgeGroup();
            map.put("memberCount",memberCount);
            for (Map map1 : memberCount) {
                memberNames.add((String)map1.get("name"));
            }
            map.put("memberNames",memberNames);
            return new Result(true, "返回数据成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"返回数据失败");
        }
    }

}
