define(function(require) {

    require("jquery");
    require("backbone");
    require("layui");
    require('ls');

    var View = Backbone.View.extend({
        initialize:function(){
            var token= ls.cookieGetToken() ;
            ls.cookieDelToken() ;
            ls.ajax({
                url:'logout.act',
                data:{
                    loginTuken:token,
                },
                success:function(resp){
                    window.location.href = ls.getContextPath() ;
                }
            }) ;
        }
    }) ;
    return View ;


}) ;