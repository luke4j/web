define(function(require){

    require("jquery") ;
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
            if(this.isValid()){
                ls.ajax({
                    url:'',
                    data:this.attributes,
                    success:function(reps){

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
        },
        /**页面事件*/
        events:function(){
            return{
                "click #btn_login":"btn_login_click_handler",//点击登录事件
                "click #dv_rememberMe":"dv_rememberMe_click_handler",//点击记住我事件
                "blur #ipt_password":"ipt_password_blur_handler",//输入密码后直接MD5加密
                "keypress #ipt_password":"ipt_password_keypress_handler",//密码窗回车事件
            } ;
        },
        ipt_password_keypress_handler:function(je){
            if(je.keyCode==13){
                $(je.currentTarget).blur() ;
                this.btn_login_click_handler() ;
            }
        },
        ipt_password_blur_handler:function(je){
            var ps = $(je.currentTarget).val() ;
            ps = hex_md5(ps) ;
            $(je.currentTarget).val(ps) ;
        },
        dv_rememberMe_click_handler:function(je){
            var pic = document.getElementById('picture');
            if(pic.getAttribute("src",2) =="img/check.png"){
                pic.src ="img/checked.png" ;
                this.model.set("rememberMe",true) ;
            }else{
                pic.src ="img/check.png" ;
                this.model.set("rememberMe",false) ;
            }

        },
        btn_login_click_handler:function(je){
            this.model.set("loginName",this.$loginName.val()) ;
            this.model.set("password",this.$password.val()) ;
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

