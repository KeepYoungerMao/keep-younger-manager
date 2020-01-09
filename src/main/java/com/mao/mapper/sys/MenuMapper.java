package com.mao.mapper.sys;

import com.mao.entity.sys.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单数据请求
 * @author zongx at 2020/1/9 20:55
 */
@Repository
@Mapper
public interface MenuMapper {

    List<Menu> getAllMenu();

}
