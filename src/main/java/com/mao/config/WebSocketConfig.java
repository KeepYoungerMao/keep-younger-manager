package com.mao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * websocket 配置
 * 配置开启websocket 注入ServerEndpointExporter容器
 * 注：ServerEndpointExporter在使用spring boot内置容器时需要注入
 *     不使用内置容器时（如打包后在tomcat中运行）不注入，否则会冲突报错
 * @author mao by 14:00 2020/3/9
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

}