package com.lmandy.utils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lmandy on 2017/10/31.
 * 静态类
 */
public class Constants {
    /**
     * 存储登录session
     */
    public static final Map<String,HttpSession> sessionMap = new HashMap<>();

}
