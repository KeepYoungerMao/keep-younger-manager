package com.mao.service.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 邮件模板
 * @author mao by 14:27 2020/3/5
 */
@Getter
@AllArgsConstructor
public enum MailTemplate {

    /**
     * 登录验证模板
     * 参数：code
     */
    LOGIN_CHECK("model/mail/loginCheck","KEEP YOUNGER 注册验证",new String[]{"code"});

    private String template;
    private String subject;
    private String[] params;

}