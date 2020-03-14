package com.mao.service.data.bjx;

import com.mao.entity.data.bjx.Bjx;
import com.mao.entity.data.bjx.BjxParam;

import java.util.List;

/**
 * 百家姓数据处理
 * @author zongx at 2020/3/14 22:55
 */
public interface BjxService {

    /**
     * 查询百家姓列表
     * @param bjxParam 百家姓参数
     * @return 百家姓列表
     */
    List<Bjx> getBjx(BjxParam bjxParam);

}
