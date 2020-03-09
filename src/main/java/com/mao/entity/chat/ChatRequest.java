package com.mao.entity.chat;

import lombok.Getter;
import lombok.Setter;

/**
 * 会话请求数据
 * 如果type是图片、文件等类型的，那message为链接
 * @author mao by 14:36 2020/3/9
 */
@Getter
@Setter
public class ChatRequest {

    private ChatDataEnum type;  //消息类型
    private String message;     //消息内容

}