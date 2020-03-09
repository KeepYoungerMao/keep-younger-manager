//javascript for global keep younger UI
//2020-01-10

//global-keep-younger.ui.js is a global javascript which all html will be used
//and chat.js too
document.write("<script type='text/javascript' src='/static/js/chat.js'></script>");

let WIDTH = window.innerWidth;
let HEIGHT = window.innerHeight;

/**
 * 监听窗口的变化
 */
window.onresize = function () {
    WIDTH = window.innerWidth;
    HEIGHT = window.innerHeight;
    //设置一些窗口的大小
    $(".ky-header-fun").css("height",(HEIGHT-50)+'px');
};

/*
* init function
*/
$(function () {
    //菜单加载
    loadMenu();
});

/**
 * 菜单加载
 * 从缓存中获取菜单数据
 * 获取当前url。根据当前url显示菜单列表
 */
function loadMenu() {
    let menuJson = sessionStorage.getItem("menu");
    if (null != menuJson) {
        let currentMenu = window.location.pathname;
        let menu = JSON.parse(menuJson);
        let id = 0,pid = 0,has = false;
        for (let i = 0; i < menu.length; i++) {
            if (menu[i].url === currentMenu) {
                id = menu[i].id;
                pid = menu[i].pid;
                has = true;
                break;
            }
        }
        if (has) {
            let title = '';
            let menuList = '';
            for (let j = 0; j < menu.length; j++) {
                if (menu[j].id === pid) {
                    title += '<span>'+menu[j].name+'</span><i class="fa fa-'+menu[j].icon+' ky-sidebar-menu-i-active"></i>';
                }
                if (menu[j].pid === pid) {
                    if (menu[j].id === id)
                        menuList += '<li class="ky-sidebar-menu-active"><a href="'+menu[j].url+'"><i class="fa fa-'+menu[j].icon+'"></i><span>'+menu[j].name+'</span></a></li>';
                    else
                        menuList += '<li><a href="'+menu[j].url+'"><i class="fa fa-'+menu[j].icon+'"></i><span>'+menu[j].name+'</span></a></li>';
                }
            }
            $(".ky-sidebar-headline").append(title);
            $(".ky-sidebar-menu").append(menuList);
        }
    }
    //执行菜单动画响应操作
    UILoad();
}

/**
 * 画面需要执行的一些操作
 */
function UILoad() {
    //设置功能块的窗口大小
    $(".ky-header-fun").css("height",(HEIGHT-50)+'px');
    //菜单的展开与关闭
    $(".ky-sidebar-hold i").on("click",function () {
        let type = $(this).attr("data-type");
        //console.log(type);
        if("on" === type){
            //设置缩小菜单栏
            $(this).parent().parent().css("width","64px");
            //设置菜单字隐藏
            $(this).parent().siblings("div").find("ul li span").addClass("ky-sidebar-menu-span-active");
            //设置状态
            $(this).attr("data-type","off");
            //设置主菜单显示图标
            $(this).parent().siblings(".ky-sidebar-top").find(".ky-sidebar-headline span").addClass("ky-sidebar-menu-span-active");
            $(this).parent().siblings(".ky-sidebar-top").find(".ky-sidebar-headline i").removeClass("ky-sidebar-menu-i-active");
            //设置主界面左移
            $(".ky-container").css("left","64px");
            //设置按钮图标改变
            $(this).removeClass("fa-outdent").addClass("fa-indent");
        }else{
            //设置展开菜单
            $(this).parent().parent().css("width","200px");
            //设置延时加载字
            setTimeout(function () {
                $(".ky-sidebar-hold i").parent().siblings("div")
                    .find("ul li span").removeClass("ky-sidebar-menu-span-active");
            },200);
            //设置状态
            $(this).attr("data-type","on");
            //设置主菜单显示字
            $(this).parent().siblings(".ky-sidebar-top").find(".ky-sidebar-headline span").removeClass("ky-sidebar-menu-span-active");
            $(this).parent().siblings(".ky-sidebar-top").find(".ky-sidebar-headline i").addClass("ky-sidebar-menu-i-active");
            //设置主界面右移
            $(".ky-container").css("left","200px");
            //设置按钮图标改变
            $(this).removeClass("fa-indent").addClass("fa-outdent");
        }
    });
    let menu_time_out;
    //鼠标放置菜单处动画
    $(".ky-sidebar-top").hover(function () {
        //鼠标移入
        menu_time_out = setTimeout(function () {
            let sidebar = $(".ky-sidebar");
            let type = $(sidebar).find(".ky-sidebar-hold i").attr("data-type");
            if("off" === type){
                //只有关闭状态下才操作
                //设置展开菜单
                $(sidebar).css("width","200px");
                //设置延时加载字
                $(".ky-sidebar .ky-sidebar-menu li span").removeClass("ky-sidebar-menu-span-active");
                //设置主菜单显示字
                $(sidebar).find(".ky-sidebar-headline span").removeClass("ky-sidebar-menu-span-active");
                $(sidebar).find(".ky-sidebar-headline i").addClass("ky-sidebar-menu-i-active");
            }
            console.log(menu_time_out);
        },300);
    },function () {
        clearTimeout(menu_time_out);
        //鼠标移除
        let type = $(this).parent().find(".ky-sidebar-hold i").attr("data-type");
        if("off" === type){
            //只有关闭状态下才操作
            //设置缩小菜单栏
            $(this).parent().css("width","64px");
            //设置菜单字隐藏
            $(".ky-sidebar .ky-sidebar-menu li span").addClass("ky-sidebar-menu-span-active");
            //设置主菜单显示图标
            $(this).parent().find(".ky-sidebar-headline span").addClass("ky-sidebar-menu-span-active");
            $(this).parent().find(".ky-sidebar-headline i").removeClass("ky-sidebar-menu-i-active");
        }
    });
}


/**
 * js 动态创建表单进行表单提交
 * @param url 请求地址
 * @param params 参数
 * @param target 是否另起窗口
 */
function ky_post_submit(url, params, target) {
    let form = document.createElement("form");
    form.action = url;
    form.method = "post";
    form.style.display="none";
    if(target) {
        form.target = target;
    }
    for (let x in params) {
        let opt = document.createElement("input");
        opt.name = x;
        opt.value = params[x];
        form.appendChild(opt);
    }
    let btn = document.createElement("input");
    btn.type = "submit";
    form.appendChild(btn);
    document.body.appendChild(form);
    form.submit();
    document.body.removeChild(form);
}

//===================== header 参数信息 ================================
const CONTENT_TYPE = "Content-Type";
const CONTENT_TYPE_VALUE = "application/json;charset=utf-8";
const REQUEST_TYPE = "REQUEST-TYPE";
const REQUEST_TYPE_VALUE = "ajax";

/**
 * 发送ajax方法时运行此函数
 * 添加header头部信息：
 * 1. JSON形式发送
 * Content-Type: application/json;charset=utf-8
 * 2. 告诉后台为ajax请求
 * REQUEST-TYPE: ajax
 */
$(document).ajaxSend(function (e,xhr,opt){
    xhr.setRequestHeader(CONTENT_TYPE,CONTENT_TYPE_VALUE);
    xhr.setRequestHeader(REQUEST_TYPE,REQUEST_TYPE_VALUE);
});

$.ajaxSetup({
    error: function (xhr, status, e) {
        switch (xhr.status){
            case 400:
                pop("无此服务[400]");
                break;
            case 401:
                pop("服务被限制[401]");
                break;
            case 403:
                pop("请求被驳回[403]");
                break;
            case 405:
                pop("请求错误[405]");
                break;
            case 408:
                pop("请求超时[408]");
                break;
            case 500:
                pop("服务器错误！请联系管理员[500]");
                break;
            default:
                pop("未知错误["+xhr.status+"]");
                break;
        }
    }
});

/**
 * 时间格式化
 * 返回格式：yyyy-MM-dd HH:mm:ss
 * 注：月份+1，美式月份从0开始
 * @param timestamp 时间戳
 * @return {string}
 */
function dateFormat(timestamp){
    let time = new Date(timestamp);
    let y = time.getFullYear(),
        M = time.getMonth() + 1,
        d = time.getDate(),
        H = time.getHours(),
        m = time.getMinutes(),
        s = time.getSeconds();
    //add0()方法在后面定义
    return  y+'-'+add0(M)+'-'+add0(d)+' '+add0(H)+':'+add0(m)+':'+add0(s);
}

/**
 * 加0操作
 */
function add0(m) {
    return m < 10 ? '0' + m: m
}

/**
 * 时间戳转换
 * 转换为：yyyy-MM-dd
 * @param now
 * @returns {string}
 */
function formatDate(now) {
    now = new Date(now);
    let year=now.getFullYear();
    let month=now.getMonth()+1;
    let date=now.getDate();
    return year+"-"+add0(month)+"-"+add0(date);
}
