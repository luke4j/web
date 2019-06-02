define(function(require){

    require("jquery") ;
    require("jcookie") ;
    require("backbone") ;
    require("layui") ;
    require('app/ls') ;
    require("json2") ;
    require("md5") ;
    require("js/login.particles") ;//直接加载
    require(['js/login.app']) ;     //延时加载



    /**登录数据模型与登录方法*/
    var VOLogin = Backbone.Model.extend({
        defaults:{
            loginName:'',
            password:'',
            rememberMe:true,
            cookie:''
        },
        initialize:function(){
            /**验证时触发的事件*/
            this.on("invalid",function(model, error){
                layui.use('layer', function(){
                    var layer = layui.layer;
                    layer.open({
                        title: '请认真填写！！！'
                        ,content: error
                    });
                });
            }) ;
        },
        /**验证方法*/
        validate: function(attrs, options) {
            if(_.isEmpty(attrs.loginName)){
                return "登录名不能为空" ;
            }
            if(attrs.loginName.length<4){
                return "登录名不合法" ;
            }
            if(_.isEmpty(attrs.password)){
                return "登录密码不能为空" ;
            }
            if(attrs.password.length!=32){
                return "登录密码不合法" ;
            }
        },
        /**登录访求*/
        login:function(){
            if(this.attributes.rememberMe){
               $.cookie("luke-loginName",this.attributes.loginName,{expires:7}) ;
               $.cookie("luke-password",this.attributes.password,{expires:7})
            }else{
                $.removeCookie("luke-loginName") ;
                $.removeCookie("luke-password") ;
            }

            this.attributes.password = hex_md5(this.attributes.password) ;
            if(this.isValid()){
                ls.ajax({
                    url:'login.act',
                    data:this.attributes,
                    success:function(resp){
                        var localhost = $("#localhost").text() ;
                        if(resp.extend==null||resp.extend.token==null){
                            window.location.href=localhost ;
                        }else{
                            ls.cookieSetToken(resp.extend.token) ;
                            var token = ls.cookieGetToken() ;
                            if(token){
                                window.location.href=localhost+"?loginTuken="+token ;
                            }
                        }
                    }
                }) ;
            }
        }
    }) ;

    /**登录视图*/
    var ViewLoginApp = Backbone.View.extend({
        el: $("#dv_login"),
        initialize:function(){
            this.$loginName = $("#ipt_loginName") ;
            this.$password = $("#ipt_password") ;
            this.$loginName.focus() ;
        },
        render:function(){
            this.setTime() ;
            setInterval(this.setTime,1000) ;
            var loginName = $.cookie("luke-loginName") ;
            var password = $.cookie("luke-password") ;
            if(loginName && password){
                this.$loginName.val(loginName) ;
                this.$password.val(password) ;
            }
        },
        /**页面事件*/
        events:function(){
            return{
                "click #btn_login":"btn_login_click_handler",//点击登录事件
                "click #dv_rememberMe":"dv_rememberMe_click_handler",//点击记住我事件
                "keypress #ipt_password":"ipt_password_keypress_handler",//密码窗回车事件
                "keypress #ipt_loginName":"ipt_password_keypress_handler"//密码窗回车事件
            } ;
        },
        ipt_password_keypress_handler:function(je){
            if(je.keyCode==13){
                $(je.currentTarget).blur() ;
                this.btn_login_click_handler() ;
            }
        },

        dv_rememberMe_click_handler:function(je){
            var $pic = $("#picture") ;
            if($pic.attr("src")=="img/check.png"){
                $pic.attr("src","img/checked.png") ;
                this.model.set("rememberMe",true) ;
            }else{
                $pic.attr("src","img/check.png") ;
                this.model.set("rememberMe",false) ;
            }
        },
        btn_login_click_handler:function(je){
            this.model.set("loginName",this.$loginName.val()) ;
            this.model.set("password",this.$password.val()) ;

            if(this.model.rememberMe){
                $.cookie("luke-loginName",this.$loginName.val(),{expires:1}) ;
                $.cookie("luke-password",this.$password.val(),{expires:1}) ;
            }

            this.model.login() ;
        },
        /**功能，设置登录边上的时间*/
        SystemTime:null,
        setTime:function() {
            var date ;
            if(this.SystemTime){
                this.SystemTime = new Date(this.SystemTime.getTime()+1000) ;
            }else{
                var systime = $("#systime").text() ;
                this.SystemTime = new Date( parseInt(systime));
            }
            date = ls.tm_getCurrentDate(3,this.SystemTime) ;
            $("#dv_show_time").text("登录  "+date) ;
        },


    }) ;
    $(function(){
        var voLogin = new VOLogin() ;
        var viewLoginApp = new ViewLoginApp({model:voLogin}) ;
        viewLoginApp.render() ;
    }) ;

}) ;

