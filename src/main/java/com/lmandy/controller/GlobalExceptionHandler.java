package com.lmandy.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lmandy on 2017/10/25.
 * 全局异常处理
 */
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    public static final String DEFAULT_ERROR_VIEW = "error";
//
//    //设置此handler处理所有异常
////    @ExceptionHandler(value=Exception.class)
//    public String defaultErrorHandler(HttpServletRequest req, Exception e, Model model) throws Exception {
//
//        model.addAttribute("exception",e);
//        model.addAttribute("url",req.getRequestURL());
//
//        e.printStackTrace();
//
//        return DEFAULT_ERROR_VIEW;
//    }
//
//}
