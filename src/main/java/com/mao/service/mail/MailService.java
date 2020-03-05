package com.mao.service.mail;

import java.util.Map;

/**
 * 邮件服务
 * @author mao by 10:48 2020/3/5
 */
public interface MailService {

    //发送普通邮件
    void sendMail(String to, String subject, String text);

    //发送模板邮件
    void sendMail(String to, MailTemplate template, Map<String, Object> properties);

}