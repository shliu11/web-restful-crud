package com.example.webrestfulcrud.component;


import com.sun.corba.se.spi.resolver.LocalResolver;
import org.omg.CORBA.Object;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * 可以在连接上携带国际化的区域信息
 */
public class MyLocaleResolver implements LocaleResolver {


    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String l=httpServletRequest.getParameter("language");
        Locale locale=Locale.getDefault();//初始locale获取操作系统默认的Locale值,如果访问请求连接上有携带区域信息则会被重新赋值，没有携带就是默认值
//        Locale locale=null;//如果是这样写成null,启动后访问报500
        if(!StringUtils.isEmpty(l)){
            //l="en_US",en表示语言，US表示国家
            String[] arr=l.split("_");
            locale=new Locale(arr[0],arr[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
