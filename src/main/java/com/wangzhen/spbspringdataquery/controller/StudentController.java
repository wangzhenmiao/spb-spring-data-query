package com.wangzhen.spbspringdataquery.controller;

import com.wangzhen.spbspringdataquery.bean.Clazz;
import com.wangzhen.spbspringdataquery.bean.Student;
import com.wangzhen.spbspringdataquery.service.SchoolService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StudentController
 * @Description
 * @Author momo
 * @Date 2019/2/4 下午4:54
 **/
@RestController
@RequestMapping("/student")
public class StudentController {

    // 注入ShcoolService
    @Resource
    private SchoolService schoolService;
    @RequestMapping("/save")
    public String save() {

        Clazz clazz1 = new Clazz("疯狂java开发1班");
        Clazz clazz2 = new Clazz("疯狂java开发2班");
        // 保存班级对象数据
        List<Clazz> clazzs = new ArrayList<>();
        clazzs.add(clazz1);
        clazzs.add(clazz2);
        schoolService.saveClazzAll(clazzs);

        Student swk = new Student("孙悟空","广州",700,'男',clazz1);
        Student zzj = new Student("蜘蛛精","广州",700,'女',clazz1);
        Student nmw = new Student("牛魔王","广州",500,'男',clazz2);

        List<Student> students = new ArrayList<>();
        students.add(swk);
        students.add(zzj);
        students.add(nmw);
        schoolService.saveStudentAll(students);
        return "保存学生对象成功";
    }
    /**
     * 查询某个班级下所有的学生姓名，年龄，性别
     * @param clazzName
     * @return
     */
    @RequestMapping("/getClazzStus")
    public List<Map<String, Object>> getClazzStus(String clazzName){
        return schoolService.getStusByClazzName(clazzName);
    }
    /**
     * 查询某个班级下所有的学生姓名，性别
     * @param clazzName
     * @return
     */
    @RequestMapping("/findNameAndSexByClazzName")
    public List<Map<String, Object>> findNameAndSexByClazzName(String clazzName){
        return schoolService.findNameAndSexByClazzName(clazzName);
    }
    /**
     * ，查询某个班级下某种性别的所有学生的姓名
     * @param clazzName
     * @return
     */
    @RequestMapping("/findNameByClazzNameAndSex")
    public List<String> findNameByClazzNameAndSex(String clazzName ,Character sex){
        return schoolService.findNameByClazzNameAndSex(clazzName ,sex);
    }



    @RequestMapping("/findClazzNameByStuName")
    public String findClazzNameByStuName(String stuName){
        return schoolService.findClazzNameByStuName(stuName);
    }



    @RequestMapping("/deleteStuByStuName")
    public String deleteStuByStuName(String stuName){
        return "删除数据："+schoolService.deleteStuByStuName(stuName);
    }

}
