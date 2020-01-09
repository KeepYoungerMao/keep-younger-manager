package com.mao.config;

import com.mao.mapper.sys.MenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 项目启动数据初始化类
 * 用于固定的使用频繁的数据加载到内存
 * @author zongx at 2020/1/9 21:12
 */
@Component
public class ApplicationInit implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ApplicationInit.class);

    private MenuMapper menuMapper;

    @Autowired
    public void setMenuMapper(MenuMapper menuMapper){
        this.menuMapper = menuMapper;
    }

    @Override
    public void run(String... args) {
        Cache.MENU = menuMapper.getAllMenu();
        log.info("[ cache ] [ menu ] has cached");
    }

}
