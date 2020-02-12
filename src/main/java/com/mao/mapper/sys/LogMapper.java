package com.mao.mapper.sys;

import com.mao.entity.sys.Log;
import com.mao.entity.sys.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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

}