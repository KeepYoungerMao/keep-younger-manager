package com.mao.service.log;

import com.mao.config.IdBuilder;
import com.mao.entity.sys.User;
import com.mao.entity.sys.log.*;
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
     * 邮件日志的保存
     * @param emailLog 邮件日志数据
     */
    @Override
    public void saveEmailLog(EmailLog emailLog) {
        logMapper.saveEmailLog(emailLog);
    }

    /**
     * 查询系统操作日志
     * @param logParam 日志请求参数
     * @return 系统操作日志列表
     */
    @Override
    public List<Log> getLogs(LogParam logParam) {
        Integer page = logParam.getPage();
        transPageParam(logParam);
        Long total = logMapper.getLogTotalPage(logParam);
        logParam.setTotal(total > 0 ? SU.ceil(total,logParam.getLimit()) : 1);
        List<Log> logs = logMapper.getLogs(logParam);
        logParam.setPage(page);
        return logs;
    }

    /**
     * 查询系统登录日志列表
     * @param loginLogParam 登录日志参数
     */
    @Override
    public List<LoginLog> getLoginLogs(LoginLogParam loginLogParam) {
        Integer page = loginLogParam.getPage();
        transPageParam(loginLogParam);
        Long total = logMapper.getLoginLogTotalPage(loginLogParam);
        loginLogParam.setTotal(total > 0 ? SU.ceil(total,loginLogParam.getLimit()) : 1);
        List<LoginLog> loginLogs = logMapper.getLoginLogs(loginLogParam);
        loginLogParam.setPage(page);
        return loginLogs;
    }

    /**
     * 查询系统邮件日志列表
     * @param emailLogParam 邮件日志参数
     * @return 邮件日志列表
     */
    @Override
    public List<EmailLog> getEmailLogs(EmailLogParam emailLogParam) {
        Integer page = emailLogParam.getPage();
        transPageParam(emailLogParam);
        Long total = logMapper.getEmailLogTotalPage(emailLogParam);
        emailLogParam.setTotal(total > 0 ? SU.ceil(total,emailLogParam.getLimit()) : 1);
        List<EmailLog> emailLogs = logMapper.getEmailLogs(emailLogParam);
        emailLogParam.setPage(page);
        return emailLogs;
    }

}