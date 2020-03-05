package com.mao.util;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author mao by 11:11 2020/3/5
 */
public class MailUtil {

    private static final String HOST = "smtp.qq.com";
    private static final int PORT = 25;
    private static final String USERNAME = "1607293758@qq.com";
    private static final String PASSWORD = "salhsyprytrnhdcb";
    private static final String DEFAULT_ENCODING = "UTF-8";

    private volatile static JavaMailSender javaMailSender;

    /**
     * 获取JavaMailSender实例
     * @return JavaMailSender
     */
    private static JavaMailSender getInstance(){
        if (null == javaMailSender){
            synchronized (JavaMailSender.class){
                if (null == javaMailSender){
                    javaMailSender = init();
                }
            }
        }
        return javaMailSender;
    }

    /**
     * JavaMailSender初始化
     * @return JavaMailSender
     */
    private static JavaMailSender init(){
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(HOST);
        sender.setPort(PORT);
        sender.setUsername(USERNAME);
        sender.setPassword(PASSWORD);
        sender.setDefaultEncoding(DEFAULT_ENCODING);
        return sender;
    }

    /**
     * 发送邮件
     * @param to 接收方
     * @param subject 标题
     * @param text 内容
     * @return 是否发送成功
     */
    public static boolean sendMail(String to, String subject, String text, boolean html){
        JavaMailSender javaMailSender = getInstance();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(USERNAME);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text,html);
            javaMailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {

    }

}