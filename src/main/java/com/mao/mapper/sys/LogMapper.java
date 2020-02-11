package com.mao.mapper.sys;

import com.mao.entity.sys.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 日志数据请求
 * @author mao by 17:48 2020/2/11
 */
@Repository
@Mapper
public interface LogMapper {

    //保存日志信息
    void saveLog(Log log);

}