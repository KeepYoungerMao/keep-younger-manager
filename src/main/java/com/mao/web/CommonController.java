package com.mao.web;

import com.mao.service.sys.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private SystemService systemService;

    @Autowired
    public void setSystemService(SystemService systemService){
        this.systemService = systemService;
    }

    /**
     * 首页
     */
    @GetMapping({"","index"})
    public String index(Model model){
        model.addAttribute("menu",systemService.getAllMenu());
        return "index";
    }

    /**
     * 登录页面
     */
    @GetMapping("login")
    public String login(){
        return "login";
    }

    /**
     * 登录失败的跳转
     */
    @GetMapping("login-error")
    public String loginError(Model model){
        model.addAttribute("error",true);
        return "login";
    }

    /**
     * 授权失败的页面
     */
    @GetMapping("auth")
    public String auth(){
        return "auth";
    }

    @Override
    public String getErrorPath() {
        return ERROR;
    }

    /**
     * 系统错误跳转
     */
    @GetMapping(ERROR)
    public String error(){
        return "error";
    }
}