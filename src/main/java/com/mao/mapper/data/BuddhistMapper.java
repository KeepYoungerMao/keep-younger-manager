package com.mao.mapper.data;

import com.mao.entity.data.buddhist.Buddhist;
import com.mao.entity.data.buddhist.BuddhistParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 佛经数据请求
 * @author zongx at 2020/3/14 22:08
 */
@Repository
@Mapper
public interface BuddhistMapper {

    /**
     * search buddhist list data
     * @param buddhistParam buddhist param
     * @return buddhist list data
     */
    List<Buddhist> getBuddhists(BuddhistParam buddhistParam);

    /**
     * search buddhist total page
     * @param buddhistParam buddhist param
     * @return total page
     */
    Long getBuddhistTotalPage(BuddhistParam buddhistParam);

}
