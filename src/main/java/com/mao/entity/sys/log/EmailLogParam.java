package com.mao.entity.sys.log;

import com.mao.entity.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * 邮件发送日志参数
 * @author mao by 17:48 2020/3/5
 */
@Getter
@Setter
public class EmailLogParam extends Page {

    private String email;           //接收方
    private String type;            //邮件类型
    private Boolean success;        //是否发送成功
    private Long start_time;        //开始时间
    private Long end_time;          //结束时间

}