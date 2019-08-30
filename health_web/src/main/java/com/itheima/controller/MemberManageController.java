package com.itheima.controller;
/**
 * @Description:
 * @Author: qiu
 */

import com.alibaba.dubbo.config.annotation.Reference;

import com.itheima.constants.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import com.itheima.service.MenberManageSrevice;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * @program: health
 * @description:
 * @author:
 * @create: 2019-08-29 17:45
 **/
@RestController
@RequestMapping("/manage")
public class MemberManageController {
    @Reference
    private MenberManageSrevice menberManageSrevice;
    @RequestMapping("/add")
    public Result add(@RequestBody Member member){
        try {
            menberManageSrevice.add(member);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"增加失败");
        }
    }

    @RequestMapping("/page")
    public PageResult page(@RequestBody QueryPageBean pageBean){
        return menberManageSrevice.page(pageBean);

    }

    @RequestMapping("/delete")
    public Result delete(int id){
        try {
            menberManageSrevice.delete01(id);
            return new Result(true,"成功");
            }catch (Exception e) {
            return new Result(false,"失败");
        }

    }

    @RequestMapping("/seefrom")
    public Result seefrom(int id){
        try {
            Member member = menberManageSrevice.seefrom(id);
            return new Result(true,"成功",member);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }

    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Member member){
        try {
            menberManageSrevice.edit(member);
            return new Result(true, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");

        }

    }

    @RequestMapping("/exportMemberReport")
    public Result findAllGroups(HttpServletRequest request, HttpServletResponse response) {
        try {
            //远程调用报表服务获取报表数据
            List<Member> members = menberManageSrevice.getAllMember();
            System.out.println(members);

           /* //取出返回结果数据，准备将报表数据写入到Excel文件中
            String reportDate = (String) result.get("reportDate");
            Integer todayNewMember = (Integer) result.get("todayNewMember");
            Integer totalMember = (Integer) result.get("totalMember");
            Integer thisWeekNewMember = (Integer) result.get("thisWeekNewMember");
            Integer thisMonthNewMember = (Integer) result.get("thisMonthNewMember");
            Integer todayOrderNumber = (Integer) result.get("todayOrderNumber");
            Integer thisWeekOrderNumber = (Integer) result.get("thisWeekOrderNumber");
            Integer thisMonthOrderNumber = (Integer) result.get("thisMonthOrderNumber");
            Integer todayVisitsNumber = (Integer) result.get("todayVisitsNumber");
            Integer thisWeekVisitsNumber = (Integer) result.get("thisWeekVisitsNumber");
            Integer thisMonthVisitsNumber = (Integer) result.get("thisMonthVisitsNumber");
            List<Map> hotSetmeal = (List<Map>) result.get("hotSetmeal");*/

            //获得Excel模板文件绝对路径
            String temlateRealPath = request.getSession().getServletContext().getRealPath("template") +
                    File.separator + "member.xlsx";

            //读取模板文件创建Excel表格对象
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(temlateRealPath)));
            XSSFSheet sheet = workbook.getSheetAt(0);
            System.out.println(000000);
            int i=0;
            int row=2;
            for (Member member : members) {
                i++;
                XSSFRow row1 = sheet.getRow(row);
                row1.getCell(0).setCellValue(i);
                row1.getCell(1).setCellValue(member.getName());
                row1.getCell(2).setCellValue(member.getSex());
                row1.getCell(3).setCellValue(member.getIdCard());
                row1.getCell(4).setCellValue(member.getPhoneNumber());
                row1.getCell(5).setCellValue(member.getRegTime());
                row++;

            }
            System.out.println(1111111);


            /*XSSFRow row = sheet.getRow(2);
            row.getCell(5).setCellValue(reportDate);//日期

            row = sheet.getRow(4);
            row.getCell(5).setCellValue(todayNewMember);//新增会员数（本日）
            row.getCell(7).setCellValue(totalMember);//总会员数

            row = sheet.getRow(5);
            row.getCell(5).setCellValue(thisWeekNewMember);//本周新增会员数
            row.getCell(7).setCellValue(thisMonthNewMember);//本月新增会员数

            row = sheet.getRow(7);
            row.getCell(5).setCellValue(todayOrderNumber);//今日预约数
            row.getCell(7).setCellValue(todayVisitsNumber);//今日到诊数

            row = sheet.getRow(8);
            row.getCell(5).setCellValue(thisWeekOrderNumber);//本周预约数
            row.getCell(7).setCellValue(thisWeekVisitsNumber);//本周到诊数

            row = sheet.getRow(9);
            row.getCell(5).setCellValue(thisMonthOrderNumber);//本月预约数
            row.getCell(7).setCellValue(thisMonthVisitsNumber);//本月到诊数

            int rowNum = 12;
            for (Map map : hotSetmeal) {//热门套餐
                String name = (String) map.get("name");
                Long setmeal_count = (Long) map.get("setmeal_count");
                BigDecimal proportion = (BigDecimal) map.get("proportion");
                row = sheet.getRow(rowNum++);
                row.getCell(4).setCellValue(name);//套餐名称
                row.getCell(5).setCellValue(setmeal_count);//预约数量
                row.getCell(6).setCellValue(proportion.doubleValue());//占比
            }*/


            //通过输出流进行文件下载
            System.out.println(22222);
            ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition", "attachment;filename=member.xlsx");
            workbook.write(out);

            out.flush();
            out.close();
            workbook.close();

            return null;
        } catch (Exception e) {
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL, null);
        }
    }

}
