//javascript for system login log
//2020-03-04

$(function () {
    //加载登录日志列表
    loadLoginLogData();
    //加载参数
    loadLoginLogParamData();
    //加载页码
    loadLoginLogPage();
});

/**
 * 加载页码
 */
function loadLoginLogPage(){
    let currentPage = loginLogParam.page === 0 ? 1 : loginLogParam.page;
    let totalPage = loginLogParam.total === 0 ? 1 : loginLogParam.total;
    if (currentPage > totalPage) currentPage = totalPage;
    $("#currentPage").html(currentPage);
    $("#totalPage").html(totalPage);
    //加载页码
    loadPage(currentPage,totalPage,"searchByPage",$("#ky-page"));
}

/**
 * 页码点击方法
 * @param page 需要请求的方法
 */
function searchByPage(page) {
    console.log("点击页码："+page);
    $("#currentPage").html(page);
    searchClick();
}

/**
 * 加载参数
 */
function loadLoginLogParamData() {
    //user_id
    let userId = loginLogParam.user_id;
    if (null != userId) {
        $("#userId").attr("data-id",userId).val(loginLogParam.user_name);
    }
    //loginType
    let login_type = loginLogParam.login_type;
    if (null == login_type) login_type = "";
    $("#loginType").find("option[value="+login_type+"]").attr("selected",true);
    //start_time
    let startTime = loginLogParam.start_time;
    if (startTime > 0) {
        let startTimeDate = formatDate(startTime);
        $("#startTime").val(startTimeDate);
    }
    //end_time
    let endTime = loginLogParam.end_time;
    if (endTime > 0) {
        let endTimeDate = formatDate(endTime);
        $("#endTime").val(endTimeDate);
    }
    //limit
    let limit = loginLogParam.limit;
    $("#limitSelect").find("option[value="+limit+"]").attr("selected",true);
}

/**
 * 查询按钮点击方法
 */
$("#sysLoginLogSearch").on("click",function () {
    searchClick();
});

/**
 * 查询方法
 */
function searchClick() {
    let _loginLogParam = {
        user_id: 0,
        user_name: "",
        login_type: "",
        start_time: 0,
        end_time: 0,
        page: 1,
        limit: 10
    };
    _loginLogParam = getLogParam(_loginLogParam);
    //console.log(_logParam);
    //发送请求
    ky_post_submit("/log/login",_loginLogParam,false);
}

/**
 * 获取日志查询参数
 */
function getLogParam(param) {
    //登陆类型
    param.login_type = $("#loginType").val();
    //开始时间
    let startTime = new Date($("#startTime").val()).getTime();
    if (isNaN(startTime))
        startTime = 0;
    param.start_time = startTime;
    //结束时间
    let endTime = new Date($("#endTime").val()).getTime();
    if (isNaN(endTime))
        endTime = 0;
    param.end_time = endTime;
    //用户
    let userIdBox = $("#userId");
    let id = $(userIdBox).attr("data-id");
    param.user_name = $(userIdBox).val();
    if (id === undefined || id == null || '' === id || isNaN(id))
        id = null;
    param.user_id = id;
    //当前页
    let currentPage = $("#currentPage").text();
    param.page = Number(currentPage);
    //条数限制
    let limit = $("#limitSelect").val();
    param.limit = Number(limit);
    return param;
}

//延时查询值
let thisSearch = 0;
/**
 * 查询用户列表点击方法
 */
$("#userId").bind("input propertychange",function () {
    //清除id
    $("#userId").attr("data-id",null);
    //清除上一次查询
    if (null != thisSearch && thisSearch >= 0)
        clearTimeout(thisSearch);
    //设置.5秒后发送请求
    thisSearch = setTimeout(function () {
        searchUser();
    },500);
});

/**
 * 查询用户
 */
function searchUser() {
    //清除查询列表
    clearUserSearchBox();
    //请求
    let kw = $("#userId").val();
    if (null != kw && '' !== kw) {
        $.ajax({
            url: '/v/search/user',
            type: 'GET',
            data: {"kw":kw},
            success: function (data) {
                if (null == data) {
                    pop("用户列表查询失败");
                } else {
                    if (data.code === 200) {
                        loadSearchUser(data.data);
                    } else {
                        pop(data.msg);
                    }
                }
            }
        });
    }
}

/**
 * 查询到一般不查询的时候或查询错误的时候
 * 清除查询框内容
 */
function clearUserSearchBox() {
    let box = $("#userSearch");
    if (box !== undefined && null != box){
        $(box).remove();
    }
}

/**
 * 加载用户列表
 * @param users
 */
function loadSearchUser(users) {
    if (null != users && users.length > 0) {
        let html = '<ul id="userSearch">';
        for (let i = 0; i < users.length; i++) {
            html += '<a href="javascript:void(0)" onclick="userLiClick('+users[i].id+',\''+users[i].username+'\')">' +
                '<li>'+users[i].username+'</li></a>';
        }
        html += '</ul>';
        $("#userSearchBox").append(html);
    }
}

/**
 * 查询列表点击
 * @param id 用户id
 * @param username 用户登录名
 */
function userLiClick(id,username) {
    clearUserSearchBox();
    let userInput = $("#userId");
    $(userInput).val(username);
    $(userInput).attr("data-id",id);
}

/**
 * 查询用户列表失去焦点之后的操作
 * 检查是否获取到了用户
 */
function blurUserSearch() {
    setTimeout(function () {
        let userInput = $("#userId");
        let userId = $(userInput).attr("data-id");
        if (userId === undefined || '' === userId || null == userId || isNaN(userId)) {
            //没有获取到用户，清除input内容
            $(userInput).val('');
            $(userInput).attr("data-id","");
            clearUserSearchBox();
        }
    },100);
}

/**
 * 数据加载前置
 * @param table table
 */
function loadLoginLogDataPre(table) {
    //清除原有数据
    $(table).find(".ky-data-tr-one,.ky-data-tr-two").remove();
    //等待
    loading();
}

/**
 * 数据加载前置
 * @param data
 */
function loadLoginLogData(data) {
    let table = $(".ky-data-table");
    loadLoginLogDataPre(table);
    //最少等待1秒钟
    setTimeout(function () {
        //加载数据
        loadLoginLogDataDo(data,table);
        //去除loading
        pop_off();
    },1000);
}

/**
 * 数据加载
 * @param data
 * @param table
 */
function loadLoginLogDataDo(data,table) {
    let html = '';
    if (null == loginLogs || loginLogs.length <= 0) {
        html = '<tr><td colspan="8" style="text-align: center;height: 50px;color: #888888">无数据，或数据加载失败</td></tr>';
    } else {
        let one = true;
        for (let i = 0,len = loginLogs.length; i < len; i++) {
            let pre;
            if (one) {
                pre = '<tr class="ky-data-tr-one">';
            } else {
                pre = '<tr class="ky-data-tr-two">';
            }
            one = !one;
            let c = '<span class="ky-icon-green">'+getLoginType(loginLogs[i].login_type)+'</span>';
            let fix = '<td>'+loginLogs[i].id+'</td>' +
                '                <td>'+loginLogs[i].user_login+'</td>' +
                '                <td>'+loginLogs[i].account_ip+'</td>' +
                '                <td>'+loginLogs[i].account_address+'</td>' +
                '                <td>'+c+'</td>' +
                '                <td>' +
                '                    <a class="ky-icon-green" href="#">详情</a>' +
                '                    <a class="ky-icon-red" href="#">删除</a>' +
                '                </td>' +
                '            </tr>';
            html += pre + fix;
        }
    }
    $(table).append(html);
}

/**
 * 展示登录类型
 * @param type
 * @returns {string}
 */
function getLoginType(type) {
    switch (type) {
        case "PASSWORD_LOGIN":
            return "密码登录";
        case "PHONE_LOGIN":
            return "手机登录";
        case "THIRD_LOGIN":
            return "第三方登录";
        case "LOGOUT":
            return "登出";
        default:
            return "";
    }
}