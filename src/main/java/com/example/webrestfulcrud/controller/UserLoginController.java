package com.example.webrestfulcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserLoginController {

//    @RequestMapping(value="/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
//    @GetMapping
//    @PutMapping
//    @DeleteMapping
/*    语法：@RequestParam(value=”参数名”,required=”true/false”,defaultValue=””)
    value：参数名
    required：是否包含该参数，默认为true，表示该请求路径中必须包含该参数，如果不包含就报错。
    defaultValue：默认参数值，如果设置了该值，required=true将失效，自动为false,如果没有传该参数，就使用默认值*/

    public String login(@RequestParam("username") String  username ,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
                session.setAttribute("loginUser",username);
            //防止表单重复提交，可以重定向到dashboard页面
            return "redirect:/main";//MyMVcConfig.java里配置
//            return "dashboard";
        }else{
            map.put("msg","用户名密码错误");
            return "index";
        }
    }




}
