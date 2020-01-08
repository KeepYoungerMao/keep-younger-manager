package com.mao.web;

import com.mao.service.aop.LogAop;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 基本web跳转
 * @author mao by 15:39 2020/1/6
 */
@Controller
public class CommonController implements ErrorController {

    private static final String ERROR = "error";

    @LogAop
    @GetMapping({"","index"})
    public String index(){
        return "index";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("login-error")
    public String loginError(Model model){
        model.addAttribute("error",true);
        return "login";
    }

    @GetMapping("auth")
    public String auth(){
        return "auth";
    }

    @Override
    public String getErrorPath() {
        return ERROR;
    }

    @GetMapping(ERROR)
    public String error(){
        return "error";
    }
}