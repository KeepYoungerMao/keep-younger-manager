package com.mao.web;

import com.mao.mapper.response.ResponseData;
import com.mao.service.sys.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于json数据返回的请求
 * @author zongx at 2020/2/27 16:47
 */
@RestController
@RequestMapping("v/search")
public class CommonDataController {

    private SystemService systemService;

    @Autowired
    public void setSystemService(SystemService systemService){
        this.systemService = systemService;
    }

    /**
     * 查询用户列表
     * 用于以用户id为查询条件的列表提供
     * @param kw 关键词
     * @return 用户列表
     */
    @GetMapping("user")
    public ResponseData getUser(String kw){
        return systemService.getUsers(kw);
    }

}
