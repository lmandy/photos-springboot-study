package com.lmandy.controller;

import com.alibaba.fastjson.JSON;
import com.lmandy.service.IImageService;
import com.lmandy.service.IJokeService;
import com.lmandy.utils.PageBean;
import com.lmandy.bean.User;
import com.lmandy.service.IAppInterFaceService;
import com.lmandy.service.IUserService;
import com.sun.org.apache.regexp.internal.RE;
import groovyjarjarantlr.collections.impl.LList;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by lmandy on 2017/10/18.
 */
@RequestMapping("app")
@RestController
public class AppInterFaceController {

    @Autowired
    private IAppInterFaceService appService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IJokeService jokeService;
    @Autowired
    private IImageService iImageService;

    public String register(String userName,String passWord){
        Map<String,Object> resMap = new HashMap();

        User user = userService.getByName(userName);
        if(user !=null){
            resMap.put("code",100);
            resMap.put("msg","用户名已存在");
        }else {
            User newUser = new User();
            user.setUserName(userName);
            user.setPassWord(passWord);
            userService.insert(newUser);

            if(newUser.getId() !=null){
                resMap.put("code",100);
                resMap.put("msg","注册成功");
            }else {
                resMap.put("code",100);
                resMap.put("msg","注册失败");
            }
        }

        return JSON.toJSONString(resMap);
    }

    public String login(String userName,String passWord){

        Map<String,Object> resMap = new HashMap();

        User user = userService.getUserByUserNameAndPassWord(userName,passWord);
        if(user !=null){
            resMap.put("code",100);
            resMap.put("msg","登录成功");
            resMap.put("user",user);
        }else {
            resMap.put("code",-100);
            resMap.put("msg","用户名或密码错误");
        }

        return JSON.toJSONString(resMap);
    }

    /**
     * 获取图片信息
     * @param pageNo
     * @param pageSize
     * @param categoryId
     * @return
     */
    @RequestMapping("getImgs")
    public String getImgs(Integer pageNo,Integer pageSize,Integer categoryId){
        Map<String,Object> resMap = new HashMap();

        PageBean pageBean = new PageBean(pageNo,pageSize);

        jokeService.indexPageInfo(pageBean);

        resMap.put("code",100);
        resMap.put("msg","成功");
        resMap.put("pageBean",pageBean);

        return JSON.toJSONString(resMap);
    }
    /**
     * 获取笑话信息
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("getJokes")
    public String getJokes(Integer pageNo,Integer pageSize){
        Map<String,Object> resMap = new HashMap();
        PageBean pageBean = new PageBean(pageNo, pageSize);

        iImageService.indexPageInfo(pageBean);

        resMap.put("code",100);
        resMap.put("msg","成功");
        resMap.put("pageBean",pageBean);

        return JSON.toJSONString(resMap);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList(){{
            add(1);
            add(2);
            add(3);
            add(2);
            add(2);
        }};


        List<Integer> collect = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect.toString());

        long count = list.stream().filter(e -> e == 2).count();

        System.out.println(count);


        Map<String,Object> map_ = new HashedMap(){{
            put("name","zhangsan");
            put("hight",178);
            put("age",18);
        }};

    }
}
