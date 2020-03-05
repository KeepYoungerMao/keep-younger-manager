package com.mao.entity.sys.log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 邮件日志
 * @author mao by 16:45 2020/3/5
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailLog {

    private Long id;            //主键
    private String email;       //接收方邮箱
    private String type;        //邮件类型
    private Boolean success;    //是否发送成功
    private Long date;          //发送时间

}