package com.mao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 单元测试类使用
 * 用于数据离线处理
 * @author zongx at 2020/1/11 20:14
 */
@Repository
@Mapper
public interface TestMapper {

    List<Integer> getIds();

    void updateId(@Param("id") Integer id, @Param("id2") Long id2);

}
