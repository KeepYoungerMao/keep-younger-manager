package com.mao.service.data.buddhist;

import com.mao.entity.data.buddhist.Buddhist;
import com.mao.entity.data.buddhist.BuddhistParam;
import com.mao.mapper.data.BuddhistMapper;
import com.mao.service.BaseService;
import com.mao.util.SU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 佛经数据处理
 * @author zongx at 2020/3/14 22:08
 */
@Service
public class DefaultBuddhistService extends BaseService implements BuddhistService {

    private BuddhistMapper buddhistMapper;

    @Autowired
    public void setBuddhistMapper(BuddhistMapper buddhistMapper){
        this.buddhistMapper = buddhistMapper;
    }

    /**
     * 查询佛经列表
     * @param buddhistParam 佛经参数
     * @return 佛经列表
     */
    @Override
    public List<Buddhist> getBuddhists(BuddhistParam buddhistParam) {
        Integer page = buddhistParam.getPage();
        transPageParam(buddhistParam);
        Long total = buddhistMapper.getBuddhistTotalPage(buddhistParam);
        buddhistParam.setTotal(total > 0 ? SU.ceil(total,buddhistParam.getLimit()) : 1);
        List<Buddhist> buddhists = buddhistMapper.getBuddhists(buddhistParam);
        buddhistParam.setPage(page);
        return buddhists;
    }

}
