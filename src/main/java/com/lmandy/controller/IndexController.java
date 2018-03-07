package com.lmandy.controller;

import com.lmandy.bean.User;
import com.lmandy.service.IUserService;
import com.lmandy.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lmandy on 2017/10/9.
 */
@Controller
public class IndexController {

    @Autowired
    private IUserService userService;

    /**
     * 登录页
     * @return
     */
    @RequestMapping("loginPage")
    public String loginPage(HttpServletRequest request, Model model){
        if(request.getAttribute("msg") != null)
            model.addAttribute("msg",request.getAttribute("msg"));

        return "login";
    }

    /**
     * 登录
     * @param userName
     * @param passWord
     * @param remeberMe
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(String userName, String passWord, String remeberMe, HttpServletRequest request, HttpServletResponse response, Model model) {

        User user = userService.getUserByUserNameAndPassWord(userName, passWord);

        if(user != null){
            //登录成功将用户放到session中
            request.getSession().setAttribute("user",user);

            Constants.sessionMap.put(user.getUserName(),request.getSession());


            if(remeberMe !=null && remeberMe.equals("1")){
                String loginInfo = userName+"#"+passWord;
                Cookie cookie = new Cookie("loginInfo",loginInfo);
                cookie.setMaxAge(30*24*60*60);   //存活期为一个月 30*24*60*60
                cookie.setPath("/login");
                response.addCookie(cookie);
            }else {
                Cookie cookie = new Cookie("loginInfo",null);
                cookie.setPath("/login");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }else {
            model.addAttribute("msg","用户名或密码错误!");
            return "login";
        }
        return "redirect:/";

    }

    /**
     * 首页
     * @param request
     * @return
     */
    @RequestMapping("/")
    public String index(HttpServletRequest request){

        return "index";
    }

    /**
     * 销毁session
     * @param request
     * @return
     */
    @RequestMapping("delSession")
    public String delSession(HttpServletRequest request){
        Object user = request.getSession().getAttribute("user");
        if(user !=null)
            request.getSession().removeAttribute("user");

        return "redirect:/loginPage";
    }

    @RequestMapping("/{pathname}")
    public String pathname(@PathVariable("pathname") String pathname){

        return pathname;
    }

}
