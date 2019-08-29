package com.itheima.jobs;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.service.OrderSettingService;
import com.itheima.util.DateUtils;

import java.util.Date;

/**
 * name : ly
 * @program: ClearOrderSettingJob
 * @description:新增ClearOrderSettingJob类
 * @author:
 * @create: 2019-08-29 11:39
 **/
public class ClearOrderSettingJob {
    @Reference
    private OrderSettingService orderSettingService;

    public void clearOrderSetting() throws Exception {
        Date date = new Date();
        String orderDate = DateUtils.parseDate2String(date, "yyyy-MM-dd");
        orderSettingService.delete(orderDate);
    }
}
