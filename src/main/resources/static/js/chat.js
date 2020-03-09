$(document).ready(function(){
    let username = sessionStorage.getItem("username");
    console.log(username);
    let urlPrefix ='ws://localhost:8080/chat/';
    let ws = null;
    $('#btn_join').click(function(){
        let username = $('#in_user_name').val();
        let url = urlPrefix + username;
        ws = new WebSocket(url);
        ws.onopen = function () {
            console.log("建立 web socket 连接...");
        };
        ws.onmessage = function(event){
            //服务端发送的消息
            $('#message_content').append(event.data+'\n');
        };
        ws.onclose = function(){
            $('#message_content').append('用户['+username+'] 已经离开聊天室!');
            console.log("关闭 web socket 连接...");
        }
    });
    //客户端发送消息到服务器
    $('#btn_send_all').click(function(){
        let msg = $('#in_room_msg').val();
        if(ws){
            let message = {
                type: "TEXT",
                message: msg
            };
            ws.send(JSON.stringify(message));
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