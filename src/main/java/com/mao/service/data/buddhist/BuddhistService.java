package com.mao.service.data.buddhist;

import com.mao.entity.data.buddhist.Buddhist;
import com.mao.entity.data.buddhist.BuddhistParam;

import java.util.List;

/**
 * 佛经数据处理
 * @author zongx at 2020/3/14 22:08
 */
public interface BuddhistService {

    /**
     * 查询佛经列表
     * @param buddhistParam 佛经参数
     * @return 佛经列表
     */
    List<Buddhist> getBuddhists(BuddhistParam buddhistParam);

}
