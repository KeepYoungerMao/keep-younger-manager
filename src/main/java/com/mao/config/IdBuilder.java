package com.mao.config;

import com.mao.util.SnowFlake;
import org.springframework.stereotype.Component;

/**
 * id创建器
 * @author zongx at 2020/1/11 19:46
 */
@Component
public class IdBuilder {

    private static final long dataCenterId = 12;
    private static final long machineId = 12;

    private SnowFlake snowFlake;

    public SnowFlake getInstance(){
        if (null == snowFlake){
            synchronized (SnowFlake.class){
                if (null == snowFlake){
                    snowFlake = new SnowFlake(dataCenterId,machineId);
                }
            }
        }
        return snowFlake;
    }

}
