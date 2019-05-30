
/**
 * 此js文件依赖于jquery 与layui
 *
 * */

var ls = ls||{} ;

ls.error = function(error){
    layui.use("layer",function(){
        var layer = layui.layer ;
        layer.open({
            title: '异常',
            content: "ls.alert参数不是字符，而是"+typeof(msg)
        });
    }) ;
} ;
ls.alert = function(msg){
    layui.use("layer",function(){
        var layer = layui.layer ;
        if(typeof(msg)!="string"){
            layer.open({
                title: '异常',
                content: "ls.alert参数不是字符，而是"+typeof(msg)
            });
            return false ;
        }
        layer.open({
            title: '提示',
            content: msg
        });
    }) ;
};



/**
 * 程序统一使用的ajax方法
 */
ls.ajax = function(p){
    var _p = {
        url:'',//请求地址
        data:{},//请求发送的参数
        dataType:'json',
        type:'POST',
        statusCode:{
            404:function(){
                layui.use("layer",function(){
                    var layer = layui.layer ;
                    layer.open({
                        title: '异常',
                        content: 'ajax.url请求地址未找到'
                    });
                }) ;
            },
            405:function(){
                layui.use("layer",function(){
                    var layer = layui.layer ;
                    layer.open({
                        title: '异常',
                        content: 'ajax.url字符串异常'
                    });
                }) ;
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){  //请求异常

        }
    } ;
    if(!p){
        alert("ls.ajax 至少要有字符串类型的URL做为参数！！！") ;
        return false ;
    }else if(typeof(p)=="string"){
        _p.url = p ;
    }else if(typeof(p)=="object"){
        _p = $.extend(_p,p) ;
    }
    var succ ;
    if(_p.success&&  typeof(_p.success)=="function" )
        succ = _p.success ;

    _p.success = function(resp, textStatus, jqXHR){ //请求成功
        if(resp.success=="true"&&succ){
            succ(resp, textStatus, jqXHR) ;
        }else{
            layui.use("layer",function(){
                var layer = layui.layer ;
                layer.open({
                    title: '异常',
                    content: resp.errMsg
                });
            }) ;
        }
    }

    $.ajax(_p) ;
} ;
ls.contextPath ;
ls.getContextPath = function(){
    if(ls.contextPath){
        return ls.contextPath ;
    }
    $.ajax("getAppRootUrl.act",{
        async:false,
        type:'POST',
        success:function(reps){
            ls.contextPath = reps.data.info ;
        }
    }) ;
    return ls.contextPath ;
} ;
ls.cookieGetToken = function(){
    return $.cookie("luke-shop") ;
} ;
ls.cookieSetToken = function(token){
    $.cookie("luke-shop",token,{expires:7}) ;
};
ls.cookieDelToken = function(){
    $.removeCookie("luke-shop")
} ;

/**
 * 返回时间字符串
 * @param format  1:yyyy-MM-dd ;2:yyyy-MM-dd HH:mm:ss ;
 * @param time
 * @returns {string}
 */
ls.tm_getCurrentDate = function(format,time) {
    var now = time ? time : new Date();
    var year = now.getFullYear(); //得到年份
    var month = now.getMonth();//得到月份
    var date = now.getDate();//得到日期
    var day = now.getDay();//得到周几
    var hour = now.getHours();//得到小时
    var minu = now.getMinutes();//得到分钟
    var sec = now.getSeconds();//得到秒
    month = month + 1;
    if (month < 10) month = "0" + month;
    if (date < 10) date = "0" + date;
    if (hour < 10) hour = "0" + hour;
    if (minu < 10) minu = "0" + minu;
    if (sec < 10) sec = "0" + sec;
    var time = "";
    switch (day) {
        case 1:
            day = "一" ;break ;
        case 2:
            day = "二" ;break ;
        case 3:
            day = "三" ;break ;
        case 4:
            day = "四" ; break ;
        case 5:
            day = "五" ;break ;
        case 6:
            day = "六" ;break ;
        case 0:
            day = "日" ;break ;
    }
    //精确到天
    if (format == 1) {
        time = year + "-" + month + "-" + date;
    }
    //精确到分
    else if (format == 2) {
        time = year + "-" + month + "-" + date + " " + hour + ":" + minu + ":" + sec;
    }
    //精确到分
    else if (format == 3) {
        time = year + "-" + month + "-" + date + " " + hour + ":" + minu + ":" + sec+"  周"+day;
    }
    return time;
} ;