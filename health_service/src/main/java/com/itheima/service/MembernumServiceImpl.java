package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MembernumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @program: health
 * @description:
 * @author:qiu
 * @create: 2019-08-29 11;30
 **/
@Service(interfaceClass = MembernumService.class)
@Transactional
public class MembernumServiceImpl implements MembernumService {
    @Autowired
    private MembernumDao memberDao;
    private String[] s={"0-18岁","18-30岁","30-60岁","60岁以上"};
    @Override
    public List<Map> getSex() {

        return memberDao.getSexMember();
    }

    @Override
    public List<Map> getAgeGroup() {
        List<Map> mapList=new ArrayList<>();
        int counts1=0;
        int counts2=0;
        int counts3=0;
        int counts4=0;
        List<String> idcards=memberDao.getAllIdCards();
        Calendar calendar=Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        for (String idcard : idcards) {
            String beginYear = idcard.substring(6, 10);
            String beginMonth = idcard.substring(11, 12);
            String beginDay = idcard.substring(13, 14);
            int currentAge=year-Integer.parseInt(beginYear);
            if (month>Integer.parseInt(beginMonth)){
                currentAge--;
            }else if (month==Integer.parseInt(beginMonth)){
                if (day>Integer.parseInt(beginDay)){
                    currentAge--;
                }
            }
            if (currentAge<=18){
               counts1++;
            }else if (currentAge<=30){
                counts2++;
            }else if (currentAge<=60){
                counts3++;
            }else {
                counts4++;
            }
        }
        int[] arrage={counts1,counts2,counts3,counts4};
        for (int i = 0; i < arrage.length; i++) {
            Map map=new HashMap();
            map.put("value",arrage[i]);
            map.put("name",s[i]);
            mapList.add(map);

        }
        return mapList;
    }
}
