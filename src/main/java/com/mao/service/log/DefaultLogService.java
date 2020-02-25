package com.mao.service.log;

import com.mao.config.IdBuilder;
import com.mao.entity.sys.*;
import com.mao.mapper.response.ResponseData;
import com.mao.mapper.sys.LogMapper;
import com.mao.service.BaseService;
import com.mao.service.sys.SystemService;
import com.mao.util.DateUtil;
import com.mao.util.HttpUtil;
import com.mao.util.SU;
import com.mao.util.baidu.BaiDuMapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 日志管理
 * @author mao by 18:03 2020/1/8
 */
@Service
public class DefaultLogService extends BaseService implements LogService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultLogService.class);

    private SystemService systemService;
    private IdBuilder idBuilder;
    private LogMapper logMapper;

    @Autowired
    public void setSystemService(SystemService systemService){
        this.systemService = systemService;
    }
    @Autowired
    public void setIdBuilder(IdBuilder idBuilder){
        this.idBuilder = idBuilder;
    }
    @Autowired
    public void setLogMapper(LogMapper logMapper){
        this.logMapper = logMapper;
    }

    /**
     * 系统操作日志的保存
     * 根据用户名获取用户信息
     * 根据request获取请求信息
     * 后续：根据url获取事件名称和事件类型
     *
     * 注：在进行日志保存的时候，又可能用户没有登录就请求了需要授权的请求
     * 因此对于username为null时，不做处理
     * 
     * @param request request
     * @param username 用户登陆名
     * @param access 是否通过
     */
    @Transactional
    @Override
    public void saveLog(HttpServletRequest request, String username, boolean access) {
        if (SU.isNotEmpty(username)){
            User user = systemService.getUserByUsername(username, false);
            Log log = makeLog(request, user, access);
            logger.info("[ {} ] request [ {} ] status : [ {} ]",username,request.getRequestURI(),access);
            logMapper.saveLog(log);
        }
    }

    /**
     * 组装log数据
     * @param request request
     * @param user 用户信息
     * @param access 操作是否通过
     * @return Log
     */
    private Log makeLog(HttpServletRequest request, User user, boolean access){
        Log log = new Log();
        log.setId(idBuilder.getInstance().nextId());
        log.setUser_id(user.getId());
        log.setUser_login(user.getUsername());
        log.setUser_role(user.getRole_id());
        log.setAccount_ip(HttpUtil.getAccountIp(request));
        log.setRequest_url(request.getRequestURI());
        log.setProcess_access(access);
        log.setProcess_date(DateUtil.getLongDate());
        return log;
    }

    /**
     * 用户登录登出日志的保存
     * @param request request
     * @param username 用户登陆名
     * @param type 登录登出类型
     */
    @Override
    public void saveLoginLog(HttpServletRequest request, String username, LoginEnum type) {
        if (null != username){
            User user = systemService.getUserByUsername(username,false);
            LoginLog log = makeLoginLog(request,user,type);
            logMapper.saveLoginLog(log);
        }
    }

    /**
     * 组装login_log数据
     * @param request request
     * @param user 用户数据
     * @param type 登录登出类型
     * @return LoginLog
     */
    private LoginLog makeLoginLog(HttpServletRequest request, User user, LoginEnum type){
        LoginLog log = new LoginLog();
        log.setId(idBuilder.getInstance().nextId());
        log.setUser_id(user.getId());
        log.setUser_login(user.getUsername());
        String ip = HttpUtil.getAccountIp(request);
        log.setAccount_ip(ip);
        log.setAccount_address(BaiDuMapUtil.getOnlyAddressByIP(ip));
        log.setLogin_type(type);
        log.setLogin_date(DateUtil.getLongDate());
        return log;
    }

    /**
     * 查询系统操作日志
     * @param logParam 日志请求参数
     * @return 系统操作日志列表
     */
    @Override
    public ResponseData getLogs(LogParam logParam) {
        if (null == logParam)
            logParam = new LogParam();
        if (null == logParam.getLimit() || logParam.getLimit() <= 0)
            logParam.setLimit(20);
        Integer page = logParam.getPage();
        page = null == page || page <= 1 ? 0 : (page - 1)*logParam.getLimit();
        logParam.setPage(page);
        List<Log> logs = logMapper.getLogs(logParam);
        return ok(logs);
    }
}