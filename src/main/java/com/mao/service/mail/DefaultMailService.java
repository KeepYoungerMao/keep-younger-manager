package com.mao.service.mail;

import com.mao.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

/**
 * 邮件服务
 * @author mao by 10:49 2020/3/5
 */
@Service
public class DefaultMailService implements MailService {

    private TemplateEngine templateEngine;

    @Autowired
    public void setTemplateEngine(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }

    /**
     * 发送邮件（异步执行）
     * @param to 接收方
     * @param subject 标题
     * @param text 内容
     */
    @Async
    @Override
    public void sendMail(String to, String subject, String text) {
        System.out.println("当前执行线程："+Thread.currentThread().getName());
        boolean success = MailUtil.sendMail(to,subject,text,false);
        System.out.println(success);
    }

    /**
     * 发送模板邮件（异步）
     * html格式右键
     * @param to 接收方
     * @param template 模板
     * @param properties 参数
     */
    @Async
    @Override
    public void sendMail(String to, MailTemplate template, Map<String, Object> properties) {
        System.out.println("当前执行线程："+Thread.currentThread().getName());
        Context context = new Context();
        String[] params = template.getParams();
        for (String param : params)
            if (properties.containsKey(param))
                context.setVariable(param,properties.get(param));
        String process = templateEngine.process(template.getTemplate(),context);
        boolean success = MailUtil.sendMail(to,template.getSubject(),process,true);
        System.out.println(success);
    }

}