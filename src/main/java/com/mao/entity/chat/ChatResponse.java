package com.mao.entity.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 会话返回数据
 * @author mao by 14:44 2020/3/9
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {

    private ChatDataEnum type;  //消息类型
    private String user;        //发消息者
    private String message;     //消息内容

}