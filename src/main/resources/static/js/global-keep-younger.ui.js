//javascript for global keep younger UI
//2020-01-10

var WIDTH = window.innerWidth;
var HEIGHT = window.innerHeight;

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
 */
function loadMenu() {
    var menuJson = sessionStorage.getItem("menu");
    if (null != menuJson) {
        var currentMenu = window.location.pathname;
        var menu = JSON.parse(menuJson);
        var id = 0,pid = 0,has = false;
        for (var i = 0; i < menu.length; i++) {
            if (menu[i].url === currentMenu) {
                id = menu[i].id;
                pid = menu[i].pid;
                has = true;
                break;
            }
        }
        if (has) {
            var title = '';
            var menuList = '';
            for (var j = 0; j < menu.length; j++) {
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
        var type = $(this).attr("data-type");
        console.log(type);
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
    var menu_time_out;
    //鼠标放置菜单处动画
    $(".ky-sidebar-top").hover(function () {
        //鼠标移入
        menu_time_out = setTimeout(function () {
            var sidebar = $(".ky-sidebar");
            var type = $(sidebar).find(".ky-sidebar-hold i").attr("data-type");
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
        var type = $(this).parent().find(".ky-sidebar-hold i").attr("data-type");
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
    var form = document.createElement("form");
    form.action = url;
    form.method = "post";
    form.style.display="none";
    if(target) {
        form.target = target;
    }
    for (var x in params) {
        var opt = document.createElement("input");
        opt.name = x;
        opt.value = params[x];
        form.appendChild(opt);
    }
    var btn = document.createElement("input");
    btn.type = "submit";
    form.appendChild(btn);
    document.body.appendChild(form);
    form.submit();
    document.body.removeChild(form);
}
