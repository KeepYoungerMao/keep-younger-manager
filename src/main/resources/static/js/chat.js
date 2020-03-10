let ws = null;
const urlPrefix = "ws://"+window.location.host+"/chat/";

$(document).ready(function(){
    //登录名
    let username = sessionStorage.getItem("username");
    //console.log(username);
    //连接前缀
    let url = urlPrefix + username;
    //拼凑链接
    ws = new WebSocket(url);
    ws.onopen = function () {
        console.log("建立 web socket 连接...");
    };
    ws.onmessage = function(event){
        //服务端发送的消息
        makeData(event.data);
    };
    ws.onclose = function(){
        console.log("关闭 web socket 连接...");
    };
    //客户端发送消息到服务器
    $('#chat-send').click(function(){
        sendMessage();
    });

    //公共聊天室消息发送回车响应
    $("#chat-message-input").bind('keypress',function(event){
        if(event.keyCode == 13) {
            sendMessage();
        }
    });

    // 退出聊天室
    $('#btn_exit').click(function(){
        if(ws){
            ws.close();
        }
    });

    $("#btn_send_point").click(function() {
        let sender = $("#in_sender").val();
        let receive = $("#in_receive").val();
        let message = $("#in_point_message").val();
        $.get("/chat/"+sender+"/to/"+receive+"?message="+message,function() {
            $("#message_content").append('['+sender+']->['+receive+']：'+message+'\n');
        })
    });

});

//聊天窗口状态
let chatOpen = false;

/**
 * 聊天窗口的摊开与收合
 */
function chatClick() {
    if (chatOpen){
        $(".ky-chat-box").css("right","-360px");
        setTimeout(function () {
            $(".ky-chat-body").css("display","none");
        },300);
    } else {
        $(".ky-chat-body").css("display","block");
        $(".ky-chat-box").css("right","0");
    }
    chatOpen = !chatOpen;
}

/**
 * 服务端发送消息的处理
 * @param data
 */
function makeData(data) {
    //console.log(data);
    data = JSON.parse(data);
    switch (data.type){
        case "TEXT":
            appendTextMessage(data.user,data.message);
            break;
        default:
            break;
    }
}

/**
 * 添加文本消息类
 * @param user 用户名
 * @param message 消息
 */
function appendTextMessage(user, message) {
    let box = $(".ky-message-box");
    let html = '<div class="ky-message-left">'+
            '<div class="ky-message-user"></div>'+
                '<div class="ky-message-pop">'+
                    '<div class="ky-message-username">'+
                        '<span>'+user+'</span>'+
                    '</div>'+
                    '<div class="ky-message-pop-box">'+message+'</div>'+
                '</div>'+
        '</div>';
    $(box).append(html);
    $(box).scrollTop($(box).prop('scrollHeight'));
}

/**
 * 客户端向服务端发送消息
 */
function sendMessage() {
    let input = $('#chat-message-input');
    let msg = $(input).val();
    if(ws){
        let message = {
            type: "TEXT",
            message: msg
        };
        ws.send(JSON.stringify(message));
        $(input).val('');
    }
}