//javascript for system email log
//2020-02-27

$(function () {
    //加载日志列表
    loadEmailLogData();
    //加载参数
    loadEmailLogParamData();
    //加载页码
    loadEmailLogPage();
});

/**
 * 加载页码
 */
function loadEmailLogPage() {
    let currentPage = emailLogParam.page === 0 ? 1 : emailLogParam.page;
    let totalPage = emailLogParam.total === 0 ? 1 : emailLogParam.total;
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
 * 加载日志参数
 */
function loadEmailLogParamData(){
    //user_id
    $("#email").val(emailLogParam.email);
    //type
    let type = emailLogParam.type;
    if (null == type)
        type = "";
    $("#type").find("option[value="+type+"]").attr("selected",true);
    //start_time
    let startTime = emailLogParam.start_time;
    if (startTime > 0) {
        let startTimeDate = formatDate(startTime);
        $("#startTime").val(startTimeDate);
    }
    //end_time
    let endTime = emailLogParam.end_time;
    if (endTime > 0) {
        let endTimeDate = formatDate(endTime);
        $("#endTime").val(endTimeDate);
    }
    //success
    let success = $("#success");
    switch (emailLogParam.success) {
        case true:
            $(success).find("option[value='true']").attr("selected",true);
            break;
        case false:
            $(success).find("option[value='false']").attr("selected",true);
            break;
        default:
            $(success).find("option[value='']").attr("selected",true);
            break;
    }
    //limit
    let limit = emailLogParam.limit;
    $("#limitSelect").find("option[value="+limit+"]").attr("selected",true);
}

/**
 * 查询按钮点击方法
 */
$("#sysLogSearch").on("click",function () {
    searchClick();
});

/**
 * 查询方法
 */
function searchClick() {
    let _emailLogParam = {
        email: "",
        type: "",
        success: null,
        start_time: 0,
        end_time: 0,
        page: 2,
        limit: 10
    };
    _emailLogParam = getEmailLogParam(_emailLogParam);
    //console.log(_logParam);
    //发送请求
    ky_post_submit("/log/email",_emailLogParam,false);
}

/**
 * 获取邮件日志查询参数
 */
function getEmailLogParam(param) {
    //邮件名称
    param.email = $("#email").val();
    //操作类型
    param.type = $("#type").val();
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
    //操作是否成功
    let success = $("#success").val();
    switch (success) {
        case "true":
            param.success = true;
            break;
        case "false":
            param.success = false;
            break;
        default:
            param.success = null;
            break;
    }
    //当前页
    let currentPage = $("#currentPage").text();
    param.page = Number(currentPage);
    //条数限制
    let limit = $("#limitSelect").val();
    param.limit = Number(limit);
    return param;
}

/**
 * 数据加载前置
 * @param table table
 */
function loadEmailLogDataPre(table) {
    //清除原有数据
    $(table).find(".ky-data-tr-one,.ky-data-tr-two").remove();
    //等待
    loading();
}

/**
 * 数据加载前置
 * @param data
 */
function loadEmailLogData(data) {
    let table = $(".ky-data-table");
    loadEmailLogDataPre(table);
    //最少等待1秒钟
    setTimeout(function () {
        //加载数据
        loadEmailLogDataDo(data,table);
        //去除loading
        pop_off();
    },1000);
}

/**
 * 数据加载
 * @param data
 * @param table
 */
function loadEmailLogDataDo(data,table) {
    let html = '';
    if (null == emailLogs || emailLogs.length <= 0) {
        html = '<tr><td colspan="8" style="text-align: center;height: 50px;color: #888888">无数据，或数据加载失败</td></tr>';
    } else {
        let one = true;
        for (let i = 0,len = emailLogs.length; i < len; i++) {
            let pre;
            if (one) {
                pre = '<tr class="ky-data-tr-one">';
            } else {
                pre = '<tr class="ky-data-tr-two">';
            }
            one = !one;
            let c = emailLogs[i].success ? '<span class="ky-icon-green">成功</span>' : '<span class="ky-icon-red">失败</span>';
            let fix = '<td>'+emailLogs[i].id+'</td>' +
                '                <td>'+emailLogs[i].email+'</td>' +
                '                <td>'+emailLogs[i].type+'</td>' +
                '                <td>'+c+'</td>' +
                '                <td>'+formatDate(emailLogs[i].date)+'</td>' +
                '                <td>' +
                '                    <a class="ky-icon-red" href="#">删除</a>' +
                '                </td>' +
                '            </tr>';
            html += pre + fix;
        }
    }
    $(table).append(html);
}