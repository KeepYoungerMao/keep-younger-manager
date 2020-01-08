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
    UILoad();
});

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
