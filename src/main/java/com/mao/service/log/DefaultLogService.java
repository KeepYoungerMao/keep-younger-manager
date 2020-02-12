package com.mao.service.log;

import com.mao.config.IdBuilder;
import com.mao.entity.sys.Log;
import com.mao.entity.sys.LoginEnum;
import com.mao.entity.sys.LoginLog;
import com.mao.entity.sys.User;
import com.mao.mapper.sys.LogMapper;
import com.mao.service.sys.SystemService;
import com.mao.util.HttpUtil;
import com.mao.util.SU;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志管理
 * @author mao by 18:03 2020/1/8
 */
@Service
public class DefaultLogService implements LogService {

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
        log.setProcess_date(SU.getLongDate());
        return log;
    }

    /**
     * 用户登录登出日志的保存
     * @param request request
     * @param username 用户登陆名
     * @param type 登录登出类型
     * @param address 操作者登录时所在城市
     */
    @Override
    public void saveLoginLog(HttpServletRequest request, String username,
                             LoginEnum type, String address) {
        if (null != username){
            User user = systemService.getUserByUsername(username, false);
            LoginLog log = makeLoginLog(request,user,type,address);
            logMapper.saveLoginLog(log);
        }
    }

    /**
     * 组装login_log数据
     * @param request request
     * @param user 用户数据
     * @param type 登录登出类型
     * @param address 操作者所在城市
     * @return LoginLog
     */
    private LoginLog makeLoginLog(HttpServletRequest request, User user, LoginEnum type, String address){
        LoginLog log = new LoginLog();
        log.setId(idBuilder.getInstance().nextId());
        log.setUser_id(user.getId());
        log.setUser_login(user.getUsername());
        log.setAccount_ip(HttpUtil.getAccountIp(request));
        log.setAccount_address(address);
        log.setLogin_type(type);
        log.setLogin_date(SU.getLongDate());
        return log;
    }

}