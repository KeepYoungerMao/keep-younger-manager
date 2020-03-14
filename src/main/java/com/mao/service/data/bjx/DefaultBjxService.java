package com.mao.service.data.bjx;

import com.mao.entity.data.bjx.Bjx;
import com.mao.entity.data.bjx.BjxParam;
import com.mao.mapper.data.BjxMapper;
import com.mao.service.BaseService;
import com.mao.util.SU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 百家姓数据处理
 * @author zongx at 2020/3/14 22:56
 */
@Service
public class DefaultBjxService extends BaseService implements BjxService {

    private BjxMapper bjxMapper;

    @Autowired
    public void setBjxMapper(BjxMapper bjxMapper){
        this.bjxMapper = bjxMapper;
    }

    /**
     * 查询百家姓列表
     * @param bjxParam 百家姓参数
     * @return 百家姓列表
     */
    @Override
    public List<Bjx> getBjx(BjxParam bjxParam) {
        Integer page = bjxParam.getPage();
        transPageParam(bjxParam);
        Long total = bjxMapper.getBjxTotalPage(bjxParam);
        bjxParam.setTotal(total > 0 ? SU.ceil(total,bjxParam.getLimit()) : 1);
        List<Bjx> bjx = bjxMapper.getBjx(bjxParam);
        bjxParam.setPage(page);
        return bjx;
    }

}
