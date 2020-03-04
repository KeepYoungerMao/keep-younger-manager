package com.mao.mapper.sys;

import com.mao.entity.sys.log.Log;
import com.mao.entity.sys.log.LogParam;
import com.mao.entity.sys.log.LoginLog;
import com.mao.entity.sys.log.LoginLogParam;
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

}