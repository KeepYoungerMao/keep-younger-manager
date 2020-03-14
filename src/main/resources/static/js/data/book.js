//javascript for data book
//2020-03-14

$(function () {
    //加载日志列表
    loadBookData();
    //加载参数
    loadBookParamData();
    //加载页码
    loadBookPage();
});

/**
 * 加载页码
 */
function loadBookPage() {
    let currentPage = bookParam.page === 0 ? 1 : bookParam.page;
    let totalPage = bookParam.total === 0 ? 1 : bookParam.total;
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
 * 加载古籍参数
 */
function loadBookParamData() {
    //bookName
    $("#bookName").val(bookParam.name);
    //bookAuth
    $("#bookAuth").val(bookParam.auth);
    //bookType
    let bookType = bookParam.type;
    $("#bookType").find("option[value="+bookType+"]").attr("selected",true);
    //bookDynasty
    let bookDynasty = bookParam.dynasty;
    $("#bookDynasty").find("option[value="+bookDynasty+"]").attr("selected",true);
    //bookFree
    let bookFree = $("#bookFree");
    switch (bookParam.free) {
        case true:
            $(bookFree).find("option[value='true']").attr("selected",true);
            break;
        case false:
            $(bookFree).find("option[value='false']").attr("selected",true);
            break;
        default:
            $(bookFree).find("option[value='']").attr("selected",true);
            break;
    }
    //bookOffSale
    let bookOffSale = $("#bookOffSale");
    switch (bookParam.off_sale) {
        case true:
            $(bookOffSale).find("option[value='true']").attr("selected",true);
            break;
        case false:
            $(bookOffSale).find("option[value='false']").attr("selected",true);
            break;
        default:
            $(bookOffSale).find("option[value='']").attr("selected",true);
            break;
    }
    //limit
    let limit = bookParam.limit;
    $("#limitSelect").find("option[value="+limit+"]").attr("selected",true);
}

/**
 * 查询按钮点击方法
 */
$("#bookSearch").on("click",function () {
    searchClick();
});

/**
 * 查询方法
 */
function searchClick() {
    let _bookParam = {
        name: "",
        auth: "",
        type: 0,
        dynasty: 0,
        free: null,
        off_sale: null,
        page: 1,
        limit: 10
    };
    _bookParam = getBookParam(_bookParam);
    //console.log(_bookParam);
    //发送请求
    ky_post_submit("/data/book",_bookParam,false);
}

/**
 * 获取日志查询参数
 */
function getBookParam(param) {
    //name
    param.name = $("#bookName").val();
    //auth
    param.auth = $("#bookAuth").val();
    //type
    param.type = $("#bookType").val();
    //dynasty
    param.dynasty = $("#bookDynasty").val();
    //free
    let free = $("#bookFree").val();
    switch (free) {
        case "true":
            param.free = true;
            break;
        case "false":
            param.free = false;
            break;
        default:
            param.free = null;
            break;
    }
    //off_sale
    let off_sale = $("#bookOffSale").val();
    switch (off_sale) {
        case "true":
            param.off_sale = true;
            break;
        case "false":
            param.off_sale = false;
            break;
        default:
            param.off_sale = null;
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
function loadBookDataPre(table) {
    //清除原有数据
    $(table).find(".ky-data-tr-one,.ky-data-tr-two").remove();
    //等待
    loading();
}

/**
 * 数据加载前置
 * @param data
 */
function loadBookData(data) {
    let table = $(".ky-data-table");
    loadBookDataPre(table);
    //最少等待1秒钟
    setTimeout(function () {
        //加载数据
        loadBookDataDo(data,table);
        //去除loading
        pop_off();
    },1000);
}

/**
 * 数据加载
 * @param data
 * @param table
 */
function loadBookDataDo(data,table) {
    let html = '';
    if (null == books || books.length <= 0) {
        html = '<tr><td colspan="8" style="text-align: center;height: 50px;color: #888888">无数据，或数据加载失败</td></tr>';
    } else {
        let one = true;
        for (let i = 0,len = books.length; i < len; i++) {
            let pre;
            if (one) {
                pre = '<tr class="ky-data-tr-one">';
            } else {
                pre = '<tr class="ky-data-tr-two">';
            }
            one = !one;
            let f = books[i].free ? '<span class="ky-icon-green">免费</span>' :
                '<span class="ky-icon-red">收费</span>';
            let o = books[i].off_sale ? '<span class="ky-icon-red">下架</span>' :
                '<span class="ky-icon-green">上架</span>';
            let fix = '<td>'+books[i].id+'</td>' +
                '                <td>'+books[i].name+'</td>' +
                '                <td>'+books[i].auth+'</td>' +
                '                <td>'+books[i].type+'</td>' +
                '                <td>'+books[i].dynasty+'</td>' +
                '                <td>'+f+o+'</td>' +
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