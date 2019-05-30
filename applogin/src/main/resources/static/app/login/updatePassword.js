define(function(require) {

    require("jquery");
    require("backbone");
    require("layui");
    require('ls');

    var View = Backbone.View.extend({
        $el:$("#frm_updatePassword"),
        initialize:function(){
            this.render() ;
        },
        render:function(){
            $.get("app/login/updatePassword.html",function(data){
                layui.use(["layer","form"],function(){
                    var layer = layui.layer ;
                    var form = layui.form;
                    var openLayer = layer.open({
                        title: '修改密码',
                        btn: [],
                        area:['400px','350px'],
                        content: data
                    });
                    form.render() ;
                    form.on('submit(frm_updatePassword)',function(data){
                        console.dir(data);
                        ls.alert(data) ;
                        return false ;
                    }) ;
                }) ;
            }) ;
            return this ;
        }
    }) ;

    var Model = Backbone.Model.extend({
        defaults:{
            loginTuken:ls.cookieGetToken(),
            password :'',
            newPassword:''
        }
    }) ;

    return function(){
        new View() ;
    } ;
}) ;