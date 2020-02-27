//javascript for system log
//2020-02-27

$(function () {
    //加载日志列表
    loadLogData();
    //加载参数
    loadLogParamData();
});

/**
 * 加载日志参数
 */
function loadLogParamData(){
    let userId = logParam.user_id;
    if (null != userId) {
        $("#userId").attr("data-id",userId).val(logParam.user_name);
    }
    let dataType = logParam.data_type;
    if (null == dataType)
        dataType = "";
    $("#dataType").find("option[value="+dataType+"]").attr("selected",true);
    let processType = logParam.process_type;
    if (null == processType)
        processType = "";
    $("#processType").find("option[value="+processType+"]").attr("selected",true);
    let startTime = logParam.start_time;
    if (startTime > 0) {
        let startTimeDate = formatDate(startTime);
        $("#startTime").val(startTimeDate);
    }
    let endTime = logParam.end_time;
    if (endTime > 0) {
        let endTimeDate = formatDate(endTime);
        $("#endTime").val(endTimeDate);
    }
    let processAccess = $("#processAccess");
    switch (logParam.process_access) {
        case true:
            $(processAccess).find("option[value='true']").attr("selected",true);
            break;
        case false:
            $(processAccess).find("option[value='false']").attr("selected",true);
            break;
        default:
            $(processAccess).find("option[value='']").attr("selected",true);
            break;
    }
}

/**
 * 查询按钮点击方法
 */
$("#sysLogSearch").on("click",function () {
    let _logParam = {
        user_id: 1,
        user_name: "",
        data_type: "",
        process_type: "",
        start_time: 0,
        end_time: 0,
        process_access: null,
        page: 1,
        limit: 10
    };
    _logParam = getLogParam(_logParam);
    //发送请求
    ky_post_submit("/log/sys",_logParam,false);
});

/**
 * 获取日志查询参数
 */
function getLogParam(param) {
    //数据类型
    param.data_type = $("#dataType").val();
    //操作类型
    param.process_type = $("#processType").val();
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
    //操作类型
    let accessString = $("#processAccess").val();
    switch (accessString) {
        case "true":
            param.process_access = true;
            break;
        case "false":
            param.process_access = false;
            break;
        default:
            param.process_access = null;
            break;
    }
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
function loadLogDataPre(table) {
    //清除原有数据
    $(table).find(".ky-data-tr-one,.ky-data-tr-two").remove();
    //等待
    loading();
}

/**
 * 数据加载前置
 * @param data
 */
function loadLogData(data) {
    let table = $(".ky-data-table");
    loadLogDataPre(table);
    //最少等待1秒钟
    setTimeout(function () {
        //加载数据
        loadLogDataDo(data,table);
        //去除loading
        pop_off();
    },1000);
}

/**
 * 数据加载
 * @param data
 * @param table
 */
function loadLogDataDo(data,table) {
    let html = '';
    if (null == logs || logs.length <= 0) {
        html = '<tr><td colspan="8" style="text-align: center;height: 50px;color: #888888">无数据，或数据加载失败</td></tr>';
    } else {
        let one = true;
        for (let i = 0,len = logs.length; i < len; i++) {
            let pre;
            if (one) {
                pre = '<tr class="ky-data-tr-one">';
            } else {
                pre = '<tr class="ky-data-tr-two">';
            }
            one = !one;
            let c = logs[i].process_access ? '<span class="ky-icon-green">成功</span>' : '<span class="ky-icon-red">失败</span>';
            let fix = '<td>'+logs[i].id+'</td>' +
                '                <td>'+logs[i].user_login+'</td>' +
                '                <td>'+logs[i].request_url+'</td>' +
                '                <td>'+logs[i].process_name+'</td>' +
                '                <td>'+logs[i].data_type+'</td>' +
                '                <td>'+logs[i].process_type+'</td>' +
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