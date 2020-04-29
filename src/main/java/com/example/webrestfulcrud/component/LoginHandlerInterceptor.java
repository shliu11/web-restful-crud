package com.example.webrestfulcrud.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器----要能使用需要在MyMvcConfig里配置起来
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    //目标方法执行之前拦截检查方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       Object user= request.getSession().getAttribute("loginUser");
       if(!StringUtils.isEmpty(user)){//已登录，放行请求
           return true;
       }else{
           //未登录，返回登录页面
           request.setAttribute("msg","没有权限，请先登录");
           request.getRequestDispatcher("/index").forward(request,response);//spring基础,servlet只有过滤器，拦截器是基于spring的
           return false;
       }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
