package com.lmandy.filter;

import com.lmandy.bean.User;
import com.lmandy.utils.Constants;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lmandy on 2017/10/23.
 * 登录过滤器
 */
//@WebFilter(filterName = "logInFilter",urlPatterns = "/*")
public class LogInFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("执行过滤操作");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String contextPath = req.getContextPath();
        String uri = req.getRequestURI();

        String url = uri.substring(contextPath.length());

        if(url.equals("/login") || url.equals("/loginPage") || url.contains("static") || url.contains("druid")){

            filterChain.doFilter(request, response);

        }else {
            User systemUser = (User) req.getSession().getAttribute("user");
            if(systemUser == null){
                //不符合条件的，跳转到登录界面
                res.sendRedirect("/loginPage");
            } else{

                //本次登录被其挤掉
                if(!req.getSession().equals(Constants.sessionMap.get(systemUser.getUserName()))){

                    req.getSession().invalidate();

                   req.setAttribute("msg","你已被迫下线,你的账号在其他地方登录!");
                   req.getRequestDispatcher("/loginPage").forward(req,res);
                }

                filterChain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
