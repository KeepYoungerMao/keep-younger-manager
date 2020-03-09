package com.mao.web;

import com.mao.service.chat.ChatEnum;
import com.mao.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * 会话功能
 * @author mao by 10:39 2020/3/9
 */
@RestController
@ServerEndpoint("/chat/{user}")
public class ChatController {

    /**
     * 采用静态注入
     * websocket为多例，spring管理的对象是单例，当spring成功注入后
     * 便不会再次注入。如果是非静态，chatService会null
     */
    private static ChatService chatService;

    @Autowired
    public void setChatService(ChatService chatService){
        ChatController.chatService = chatService;
    }

    /**
     * 会话开启
     * @param user 用户名
     * @param session 用户socket Session
     */
    @OnOpen
    public void onOpen(@PathParam("user") String user, Session session){
        chatService.publicChat(ChatEnum.OPEN,user,session,null);
    }

    /**
     * 用户发送公共信息
     * @param user 用户名
     * @param message 发送信息
     */
    @OnMessage
    public void onMessage(@PathParam("user") String user, Session session, String message){
        chatService.publicChat(ChatEnum.MESSAGE,user,session,message);
    }

    /**
     * 会话关闭 用户退出会话
     * @param user 用户名
     * @param session 用户socket Session
     */
    @OnClose
    public void onClose(@PathParam("user") String user, Session session){
        chatService.publicChat(ChatEnum.CLOSE,user,session,null);
    }

    /**
     * 会话异常
     * @param session 用户socket Session
     * @param throwable 异常信息
     */
    @OnError
    public void onError(Session session, Throwable throwable){
        try{
            session.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }

}