package com.dj;

import com.dj.common.AuthFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@MapperScan("com.dj.mapper")
@SpringBootApplication
public class BootVueAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootVueAdminApplication.class, args);
    }


    //注册过滤器
    @Bean
    public FilterRegistrationBean<AuthFilter> getFilterRegistrationBean(){
        FilterRegistrationBean<AuthFilter> bean = new FilterRegistrationBean<>(new AuthFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }

}
