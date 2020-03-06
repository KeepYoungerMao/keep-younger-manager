package com.mao.mapper.sys;

import com.mao.entity.sys.log.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 日志数据请求
 * @author mao by 17:48 2020/2/11
 */
@Repository
@Mapper
public interface LogMapper {

    /**
     * save log data
     * @param log log data
     */
    void saveLog(Log log);

    /**
     * save login log data
     * @param loginLog login log data
     */
    void saveLoginLog(LoginLog loginLog);

    /**
     * search logs data
     * @param logParam params data
     * @return logs data
     */
    List<Log> getLogs(LogParam logParam);

    /**
     * search logs total page
     * @param logParam params data
     * @return total page
     */
    Long getLogTotalPage(LogParam logParam);

    /**
     * search login logs data
     * @param loginLogParam params data
     * @return login logs data
     */
    List<LoginLog> getLoginLogs(LoginLogParam loginLogParam);

    /**
     * search login logs total page
     * @param loginLogParam params data
     * @return total page
     */
    Long getLoginLogTotalPage(LoginLogParam loginLogParam);

    /**
     * save email log data
     * @param emailLog email log data
     */
    void saveEmailLog(EmailLog emailLog);

    /**
     * search email logs data
     * @param emailLogParam param data
     * @return email logs data
     */
    List<EmailLog> getEmailLogs(EmailLogParam emailLogParam);

    /**
     * search email log total page
     * @param emailLogParam param data
     * @return email log total page
     */
    Long getEmailLogTotalPage(EmailLogParam emailLogParam);

}