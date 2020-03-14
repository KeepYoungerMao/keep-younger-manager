package com.mao.mapper.data;

import com.mao.entity.data.bjx.Bjx;
import com.mao.entity.data.bjx.BjxParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 百家姓数据请求
 * @author zongx at 2020/3/14 22:56
 */
@Repository
@Mapper
public interface BjxMapper {

    /**
     * search bjx data list
     * @param bjxParam bjx param
     * @return bjx data list
     */
    List<Bjx> getBjx(BjxParam bjxParam);

    /**
     * search bjx total page
     * @param bjxParam bjx param
     * @return bjx total page
     */
    Long getBjxTotalPage(BjxParam bjxParam);

}
