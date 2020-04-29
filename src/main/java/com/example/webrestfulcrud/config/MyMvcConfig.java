package com.example.webrestfulcrud.config;

import com.example.webrestfulcrud.component.LoginHandlerInterceptor;
import com.example.webrestfulcrud.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@EnableWebMvc//不要完全接管springmvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    //视图映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
        registry.addViewController("/mmm").setViewName("success");


        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/main").setViewName("dashboard");

    }

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //对于静态资源 *.css,*.js等，从前springmvc是需要排除掉拦截的，
        // 但是现在springboot已经做好静态资源映射了，所以不需要管静态资源了----springboot2.0+还是被拦截了
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")//需要拦截的请求
                .excludePathPatterns("/index","/","/user/login","/*.css","/*.js");//过滤掉的不被拦截的请求
    }

    /**
     * 将自定义的国际化类放到容器中，在不知道bean id的情况下，spingboot会取方法名为bean id,
     * 所以此处方法名一定要写成localeResolver，
     * 这样在springboot自动配置的国家化LocalResolver类加载时WebMvcAutoConfiguration才会判断出容器中已经含有localeResolver
     * @return
     */

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }


}
