package com.example.webrestfulcrud.controller;

import com.example.webrestfulcrud.dao.DepartmentDao;
import com.example.webrestfulcrud.dao.EmployeeDao;
import com.example.webrestfulcrud.entities.Department;
import com.example.webrestfulcrud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * thymeleaf默认拼接返回view:classpath/templates/  +return + .html
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @GetMapping("/emps")
    //查询所有员工回显到列表页面上
    public String list(Model model){
        Collection<Employee> employees =  employeeDao.getAll();
        //放在请求域中进行共享
        model.addAttribute("emps",employees);
        return "emp/list";//返回列表
    }

    @GetMapping("/emp")
    //来到添加员工页面
    public String toAddPage(Model model){
        //查出所有部门返回页面形成下拉框选项
        Collection<Department> departments=departmentDao.getDepartments();
        model.addAttribute("depts",departments);
          return "emp/add";
    }

    @PostMapping("/emp")
    //添加员工
    //springmvc会自动将请求参数和方法的入参对象的属性进行一一绑定；要求是请求参数的名字和入参对象javabean的属性名相同
    public String addEmployee(Employee  employee){
        employeeDao.save(employee);

        //添加完成之后继续回到列表页面回显
        // /代表当前项目路径
        return "redirect:/emps";
    }
    @GetMapping("/emp/{id}")
    //来到修改员工信息页面,携带路径变量
    public String toEditPage(@PathVariable("id") Integer id, Model model){

        //查出员工信息
        Employee employee= employeeDao.get(id);
        model.addAttribute("emp",employee);
        //显示部门列表下拉框
        Collection<Department> departments=departmentDao.getDepartments();
        model.addAttribute("depts",departments);

        //add是一个添加修改二合一页面
        return "emp/add";
    }

    @PutMapping("/emp")
    //修改员工信息
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
