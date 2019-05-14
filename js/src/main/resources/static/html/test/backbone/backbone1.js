$(function(){

    var Cls_User = Backbone.Model.extend({
        defaults:function(){
            return {
                id:"",
                name:"",
                age:"",
                addr:""
            } ;
        },
        initialize:function(){
            this.on("change",function(m,v){
                console.dir(arguments) ;
            }) ;
        },
        validate:function(as,opt){

            console.dir(arguments) ;
            var msg = "" ;
            return msg ;
        },
        insert:function(){
            if(this.isValid()){
                console.dir(this.attributes) ;
            }

        }
    }) ;

    var user =  new Cls_User () ;
    // tst_btn_1 测试按钮ID
    $("#tst_btn_1").on("click",function($e){
        user.set({name:"luke"}) ;
        user.insert() ;
    }) ;
}) ;