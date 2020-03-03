package com.mao.mapper.sys;

import com.mao.entity.sys.Log;
import com.mao.entity.sys.LogParam;
import com.mao.entity.sys.LoginLog;
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
    Long getTotalPage(LogParam logParam);

}