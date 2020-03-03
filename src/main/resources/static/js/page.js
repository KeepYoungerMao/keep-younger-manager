/**
 * 页码加载方法
 * 点击链接url采用执行方法，传入请求page参数
 * @param cur 当前page
 * @param total 总页数
 * @param urlPre 执行方法
 * @param e 放置页码的地方 $(this)
 */
function loadPage(cur,total,urlPre,e) {
    let max = 10;   //总加载
    let half = max/2;
    if (total <= max){
        let html = '';
        let next = '';
        for (let i = 1; i <= total; i++){
            if (i === cur){
                html += '<span>'+i+'</span>';
                next += '<a id="next-page" href="javascript:'+urlPre+'('+(cur === total ? total : cur+1)+')">下一页</a>';
            } else {
                html += '<a href="javascript:'+urlPre+'('+i+')">'+i+'</a>';
            }
        }
        html += next;
        $(e).append(html);
    } else {
        //如total = 20, cur = 2, : 12345678910
        if (cur > half){
            if ((total - cur) < half){
                let html2 = '';
                for (let j = (total-max); j <= total; j++){
                    if (j === cur){
                        html2 += '<span>'+j+'</span>';
                    }  else {
                        html2 += '<a href="javascript:'+urlPre+'('+j+')">'+j+'</a>';
                    }
                }
                html2 += '<a id="next-page" href="javascript:'+urlPre+'('+(cur === total ? total : cur+1)+')">下一页</a>';
                $(e).append(html2);
            } else {
                let html3 = '';
                for (let k = (cur - half); k <= (cur-1); k++){
                    html3 += '<a href="javascript:'+urlPre+'('+k+')">'+k+'</a>';
                }
                html3 += '<span>'+cur+'</span>';
                for (let l = cur+1; l <= (cur+half); l++){
                    html3 += '<a href="javascript:'+urlPre+'('+l+')">'+l+'</a>';
                }
                html3 += '<a id="next-page" href="javascript:'+urlPre+'('+(cur+1)+')">下一页</a>';
                $(e).append(html3);
            }
        } else {
            let html4 = '';
            for (let m = 1; m <= max; m++){
                if (m === cur){
                    html4 += '<span>'+m+'</span>';
                } else {
                    html4 += '<a href="javascript:'+urlPre+'('+m+')">'+m+'</a>';
                }
            }
            html4 += '<a id="next-page" href="javascript:'+urlPre+'('+(cur+1)+')">下一页</a>';
            $(e).append(html4);
        }
    }
}