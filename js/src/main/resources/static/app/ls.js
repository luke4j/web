
/**
 * 此js文件依赖于jquery 与layui
 *
 * */

var ls = ls||{} ;

ls.layui_reader = function(){
    layui.use('element', function(){
        var element = layui.element;
        element.render() ;
    });
} ;

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

ls.confirm = function(msg,yesCallBack){
    layui.use("layer",function() {
        var layer = layui.layer;
        layer.confirm(msg, {icon: 3, title:'提示'}, function(index){
            //do something
            yesCallBack() ;
            layer.close(index);
        });
    }) ;
} ;

ls.empty_function = function(){}



/**
 * 程序统一使用的ajax方法
 */
ls.ajax = function(p){
    var _p = {
        url:'',//请求地址
        data:{},//请求发送的参数
        dataType:'json',
        type:'POST',
        async:true,
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
            ls.contextPath = reps.data._info ;
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


ls.moneyToChinese = function (Num) {    //阿拉伯数字转中文大写格式
    var isNegative = false;// 是否负数
    if (Num < 0) {
        Num = -Num;
        isNegative = true;
    }
    if (typeof Num == 'number') {
        Num = Num.toString();
    }
    for (var i = Num.length - 1; i >= 0; i--) {
        Num = Num.replace(",", "");// 替换money中的“,”
        Num = Num.replace(" ", "");// 替换money中的空格
    }
    Num = Num.replace("￥", "");// 替换掉可能出现的￥字符
    if (isNaN(Num)) { // 验证输入的字符是否为数字
        return;
    }
// ---字符处理完毕，开始转换，转换采用前后两部分分别转换---//
    part = String(Num).split(".");
    newchar = "";
// 小数点前进行转化
    for (var i = part[0].length - 1; i >= 0; i--) {
        if (part[0].length > 10) {
            alertWarning("位数过大，无法计算");
            return "";
        } // 若数量超过拾亿单位，提示
        tmpnewchar = "";
        perchar = part[0].charAt(i);
        switch (perchar) {
            case "0" :
                tmpnewchar = "零" + tmpnewchar;
                break;
            case "1" :
                tmpnewchar = "壹" + tmpnewchar;
                break;
            case "2" :
                tmpnewchar = "贰" + tmpnewchar;
                break;
            case "3" :
                tmpnewchar = "叁" + tmpnewchar;
                break;
            case "4" :
                tmpnewchar = "肆" + tmpnewchar;
                break;
            case "5" :
                tmpnewchar = "伍" + tmpnewchar;
                break;
            case "6" :
                tmpnewchar = "陆" + tmpnewchar;
                break;
            case "7" :
                tmpnewchar = "柒" + tmpnewchar;
                break;
            case "8" :
                tmpnewchar = "捌" + tmpnewchar;
                break;
            case "9" :
                tmpnewchar = "玖" + tmpnewchar;
                break;
        }
        switch (part[0].length - i - 1) {
            case 0 :
                tmpnewchar = tmpnewchar + "元";
                break;
            case 1 :
                if (perchar != 0)
                    tmpnewchar = tmpnewchar + "拾";
                break;
            case 2 :
                if (perchar != 0)
                    tmpnewchar = tmpnewchar + "佰";
                break;
            case 3 :
                if (perchar != 0)
                    tmpnewchar = tmpnewchar + "仟";
                break;
            case 4 :
                tmpnewchar = tmpnewchar + "万";
                break;
            case 5 :
                if (perchar != 0)
                    tmpnewchar = tmpnewchar + "拾";
                break;
            case 6 :
                if (perchar != 0)
                    tmpnewchar = tmpnewchar + "佰";
                break;
            case 7 :
                if (perchar != 0)
                    tmpnewchar = tmpnewchar + "仟";
                break;
            case 8 :
                tmpnewchar = tmpnewchar + "亿";
                break;
            case 9 :
                tmpnewchar = tmpnewchar + "拾";
                break;
        }
        newchar = tmpnewchar + newchar;
    }
// 小数点之后进行转化
    if (Num.indexOf(".") != -1) {
        if (part[1].length > 2) {
            alertWarning("小数点之后只能保留两位,系统将自动截段");
            part[1] = part[1].substr(0, 2);
        }
        for (var i = 0; i < part[1].length; i++) {
            tmpnewchar = "";
            perchar = part[1].charAt(i);
            switch (perchar) {
                case "0" :
                    tmpnewchar = "零" + tmpnewchar;
                    break;
                case "1" :
                    tmpnewchar = "壹" + tmpnewchar;
                    break;
                case "2" :
                    tmpnewchar = "贰" + tmpnewchar;
                    break;
                case "3" :
                    tmpnewchar = "叁" + tmpnewchar;
                    break;
                case "4" :
                    tmpnewchar = "肆" + tmpnewchar;
                    break;
                case "5" :
                    tmpnewchar = "伍" + tmpnewchar;
                    break;
                case "6" :
                    tmpnewchar = "陆" + tmpnewchar;
                    break;
                case "7" :
                    tmpnewchar = "柒" + tmpnewchar;
                    break;
                case "8" :
                    tmpnewchar = "捌" + tmpnewchar;
                    break;
                case "9" :
                    tmpnewchar = "玖" + tmpnewchar;
                    break;
            }
            if (i == 0)
                tmpnewchar = tmpnewchar + "角";
            if (i == 1)
                tmpnewchar = tmpnewchar + "分";
            newchar = newchar + tmpnewchar;
        }
    }
// 替换所有无用汉字
    while (newchar.search("零零") != -1)
        newchar = newchar.replace("零零", "零");
    newchar = newchar.replace("零亿", "亿");
    newchar = newchar.replace("亿万", "亿");
    newchar = newchar.replace("零万", "万");
    newchar = newchar.replace("零元", "元");
    newchar = newchar.replace("零角", "");
    newchar = newchar.replace("零分", "");

    if (newchar.charAt(newchar.length - 1) == "元" || newchar.charAt(newchar.length - 1) == "角") {
        newchar = newchar + "整";
    }

    if (isNegative) {
        newchar = '负' + newchar;
    }

    return "人民币"+newchar;
} ;

