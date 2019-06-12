define(function(require) {

    require("jquery");
    require("backbone");
    require("layui");
    require('md5');

    require('ls');


    var View = Backbone.View.extend({
        $el:$("#frm_updatePassword"),
        initialize:function(){
            this.render() ;
        },
        render:function(){
            var me = this ;
            /*加载修改密码html*/
            $.get("app/login/updatePassword.html",function(html){
                /*加载所需要的layui元素*/
                layui.use(["layer","form"],function(){
                    var layer = layui.layer ;
                    var form = layui.form;
                    /*打开弹出窗*/
                    var openLayer = me.openLayer(layer,html) ;
                    /*渲染form，并给form添加事件*/
                    me.renderForm(form,openLayer) ;
                }) ;
            }) ;
            return this ;
        },
        openLayer:function(ui_layer,html){
            ui_layer.open({
                title: '修改密码',
                btn: [],
                area:['400px','350px'],
                content: html
            });
        },
        renderForm:function(ui_form,openLayer){
            ui_form.render() ;
            ui_form.on('submit(updatePassword)',function(data){
                data.field.loginTuken = ls.cookieGetToken() ;
                data.field.password = hex_md5(data.field.password) ;
                data.field.newPassword = hex_md5(data.field.newPassword) ;
                data.field.newPassword2 = hex_md5(data.field.newPassword2) ;
                ls.ajax({
                    url:'updatePassword.act'
                    ,data:data.field
                    ,success:function(resp){
                        ls.confirm(resp.msg,function(){
                            window.location.href = ls.getContextPath() ;
                        }) ;
                    }
                }) ;
                return false ;
            }) ;
        }
    }) ;

    var Model = Backbone.Model.extend({
        defaults:{
            loginTuken:ls.cookieGetToken(),
            password :'',
            newPassword:''
        }
    }) ;

    return View ;
}) ;