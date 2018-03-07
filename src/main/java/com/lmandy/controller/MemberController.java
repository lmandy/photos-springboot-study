package com.lmandy.controller;

import com.lmandy.bean.User;
import com.lmandy.service.IUserService;
import com.lmandy.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by lmandy on 2017/10/17.
 * 会员列表
 */
@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    private IUserService iUserService;


    @RequestMapping("index")
    public String index(Integer pageNo, Integer pageSize, Model model){

        PageBean<User> pageBean = new PageBean(pageNo,pageSize);

        iUserService.indexPageInfo(pageBean);

        model.addAttribute("page",pageBean);

        return "member";
    }

    /**
     * 异步获取注册信息
     * @return
     */
    @RequestMapping("ajaxGetRegisterData")
    @ResponseBody
    public Map<String,Object> ajaxGetRegisterData(){
        return null;
    }
}
