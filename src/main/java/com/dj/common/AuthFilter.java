package com.dj.common;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

public class AuthFilter implements Filter {

    //白名单 表示应该放行的请求
    private static final String[] WHITE_URLS = {"/login.html","/register.html","/user/login","/user/register"};
    //放行资源文件
    private static final String[] FILE_SUFFIX = {"jpeg","jpg","png","gif","bmp","webp","css","js","woff","woff2"};


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath = request.getServletPath();  //得到请求的地址
        //Arrays.stream(WHITE_URLS).anyMatch(url->url.equals(servletPath))和 Arrays.asList(WHITE_URLS).contains(servletPath)
        //java 8  stream API
        if (Arrays.stream(WHITE_URLS).anyMatch(url->url.equals(servletPath) || enWith(servletPath))){
            filterChain.doFilter(request,response);
        }else {
            Object user = request.getSession().getAttribute("user");
            if (user!=null){
                filterChain.doFilter(request,response);
            }else {
                response.sendRedirect("/login.html");
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean enWith(String path){
        for (String fileSuffix:FILE_SUFFIX) {
            if (path.endsWith(fileSuffix)){
                return true;
            }
        }
        return false;
    }

}
