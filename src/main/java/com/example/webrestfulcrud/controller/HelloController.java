package com.example.webrestfulcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller

public class HelloController {

    @ResponseBody//将return的内容直接写向浏览器
    @RequestMapping("/hello")
    public String  hello(){
        return "helloWord！";
    }

    @RequestMapping("/success")
    //注意请求映射和返回的view不能相同，否则会报错
    //有数据返回
    //类上写@RestController或方法上写@ResponseBody，则数据不会写向页面，thymeleaf不起作用
    //如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，或者html，会直接向浏览器写数据，配置的视图解析器 InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。
    public String success(Map<String,Object> map){
        map.put("hello","<h1>hhhhhhhh</h1>");
        map.put("did","d2");
        map.put("class","mdiv2");
        map.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
        //classpath:/template/success.html
        return "success";//跳转到对应的view视图
    }


    //建议将页面都放在templates文件夹下，可以被模板引擎所解析
//    @RequestMapping({"/login","/login.html"})
//    public  String index(){
//
//        return "index";//此时index.html有两个页面，写本方法可以进行view映射到templates文件夹下index.html
//    }
}
