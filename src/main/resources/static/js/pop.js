/*
 * pop.js 弹窗
 * 包含主动消失的弹框，需要确认的弹框
 */

/**
 * 弹窗：确认窗
 * 用户需要点击按钮窗口才能关闭
 * mode：1：只有“确认按钮”，点击确认按钮后执行“yes”方法
 * mode：2：包含“确认”、“取消”按钮
 * @param msg 信息
 * @param mode 模式
 * @param yes
 * @param no
 * @param fn mode=2时启用，确认的功能
 */
function pop(msg,mode,yes,no,fn) {
    switch (mode){
        case 1:
            pop1(msg,yes);
            break;
        case 2:
            pop2(msg,yes,no,fn);
            break;
        default:
            pop1(msg,yes);
            break;
    }
}
function pop1(msg,yes) {
    if(null == yes || '' === yes){
        yes = "确定";
    }
    pop_off();
    var pop = '<div class="mao-pop-zzc">'+
                '<div class="mao-pop-point">'+
                    '<div class="mao-pop">'+
                        '<div class="mao-pop-title">'+
                            '<i class="fa fa-bell-o warn"></i>'+
                            '<span>提示</span>'+
                        '</div>'+
                        '<div class="mao-pop-content">'+
                            '<p>'+msg+'</p>'+
                        '</div>'+
                        '<div class="mao-pop-bottom">'+
                            '<button class="yes" onclick="pop_off()">'+yes+'</button>'+
                        '</div>'+
                    '</div>'+
                '</div>'+
            '</div>';
    $(".ky-container").append(pop);
}
function pop2(msg,yes,no,fn) {
    if(null == yes || '' === yes){
        yes = "确定";
    }
    if(null == no || '' === no){
        no = "取消";
    }
    pop_off();
    var pop = '<div class="mao-pop-zzc">'+
                '<div class="mao-pop-point">'+
                    '<div class="mao-pop">'+
                        '<div class="mao-pop-title">'+
                            '<i class="fa fa-bell-o warn"></i>'+
                            '<span>提示</span>'+
                        '</div>'+
                        '<div class="mao-pop-content">'+
                            '<p>'+msg+'</p>'+
                        '</div>'+
                        '<div class="mao-pop-bottom">'+
                            '<button class="yes" '+fn+'>'+yes+'</button>'+
                            '<button class="cancel" onclick="pop_off()">'+no+'</button>'+
                        '</div>'+
                    '</div>'+
                '</div>'+
            '</div>';
    $(".ky-container").append(pop);
}

/**
 * 清除弹框
 */
function pop_off() {
    $(".ky-container").find(".mao-pop-zzc").remove();
}

/**
 * 弹框：提示框
 * 提示少量文字。弹框过一定时间会主动消失
 * @param msg 提示信息
 * @param wait 等待时间
 */
function tips(msg,wait) {
    var tips = '<div class="mao-pop-zzc">'+
                '<div class="mao-pop-point">'+
                    '<div class="mao-tips">'+
                        '<span>'+msg+'</span>'+
                    '</div>'+
                '</div>'+
            '</div>';
    $(".ky-container").append(tips);
    if(wait == null || wait <= 0)
        wait = 1000;
    setTimeout(function () {
        pop_off();
    },wait);
}

function loading() {
    let tips = '<div class="mao-pop-zzc">'+
        '<div class="mao-pop-point">'+
        '<div class="mao-tips">'+
        '<i class="fa fa-spinner fa-spin" style="font-size: 40px;color: #ffffff"></i>'+
        '</div>'+
        '</div>'+
        '</div>';
    $(".ky-container").append(tips);
}