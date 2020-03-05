package com.mao.service.mail;

import com.mao.config.IdBuilder;
import com.mao.entity.sys.log.EmailLog;
import com.mao.service.log.LogService;
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

    /**
     * thymeleaf模板引擎
     */
    private TemplateEngine templateEngine;
    private LogService logService;
    private IdBuilder idBuilder;

    @Autowired
    public void setTemplateEngine(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }
    @Autowired
    public void setLogService(LogService logService){
        this.logService = logService;
    }
    @Autowired
    public void setIdBuilder(IdBuilder idBuilder){
        this.idBuilder = idBuilder;
    }

    /**
     * 发送普通邮件（异步执行）
     * @param to 接收方
     * @param subject 标题
     * @param text 内容
     */
    @Async
    @Override
    public void sendMail(String to, String subject, String text) {
        boolean success = MailUtil.sendMail(to,subject,text,false);
        System.out.println(success);
    }

    /**
     * 发送模板邮件（异步执行）
     * html格式邮件
     * properties为参数列表。参数列表需遵循选择模板中的参数列表。
     * 并记录邮件日志
     * @param to 接收方
     * @param template 模板
     * @param properties 参数
     */
    @Async
    @Override
    public void sendMail(String to, MailTemplate template, Map<String, Object> properties) {
        //整合参数，使用thymeleaf模板生成内容
        Context context = new Context();
        String[] params = template.getParams();
        for (String param : params)
            if (properties.containsKey(param))
                context.setVariable(param,properties.get(param));
        String process = templateEngine.process(template.getTemplate(),context);
        //记录邮件刚发送的时间
        Long date = System.currentTimeMillis();
        //邮件发送
        boolean success = MailUtil.sendMail(to,template.getSubject(),process,true);
        //收集日志数据
        long id = idBuilder.getInstance().nextId();
        EmailLog emailLog = new EmailLog(id,to,template.name(),success,date);
        //日志的保存
        logService.saveEmailLog(emailLog);
    }

}