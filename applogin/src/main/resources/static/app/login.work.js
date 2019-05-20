define(function(require) {

    require("jquery");
    require("backbone");
    require("layui");
    require('app/ls');
    require("json2");
    require("md5");

    var ViewMainAdmin = Backbone.View.extend({
        tagName:'div',
        initialize:function(){
            this.$el.addClass("layui-layout layui-layout-admin") ;
            this.$el.appendTo($("body")) ;
            this.render() ;
        },
        render:function(){
            this.dv_head = $("<div class='layui-header'>") ;
            this.dv_menu = $("<div>").addClass("layui-side layui-bg-black") ;
            this.dv_body = $("<div>").addClass("layui-body") ;
            this.dv_footer = $("<div>").addClass("layui-footer") ;
            this.$el.append(this.dv_head).append(this.dv_menu).append(this.dv_body).append(this.dv_footer)  ;
            this.addHead() ;
            this.addMenu() ;
            this.addBody() ;
            this.addfooter() ;
        },
        addHead:function(){
            this.dv_head.append(new ViewHeadLogo().render().el)
                .append(new ViewHeadLeft().render().el)
                .append(new ViewHeadRight().render().el) ;
        },
        addMenu:function(){
            this.dv_menu.append() ;
        },
        addBody:function(){
            this.dv_body.html("内容默认显示") ;
        },
        addfooter:function(){
            this.dv_footer.html("© luke-shop") ;
        }
    }) ;


    var ViewHeadLeft = Backbone.View.extend({
        tagName:"ul",
        initialize:function(){
            this.$el.addClass("layui-nav layui-layout-left") ;
            this.render() ;
        },
        render:function(){
            var $li = new ViewLi().setElId("li_showTime").render().$el  ;
            this.$el.append($li) ;
            var time = new ModelTime({vId:"li_showTime"}) ;
            return this ;
        }

    }) ;
    var ViewHeadRight = Backbone.View.extend({

    }) ;

    var ViewHeadLogo = Backbone.View.extend({
        tagName:"div",
        initialize:function(){
            this.$el.addClass("layui-logo")
                .append( $("<h3>").text("luke-shop")) ;
        },
        render:function(){
            return this ;
        }
    }) ;

    var ModelLi =  Backbone.Model.extend({
        defaults:{
            text:'测试项'
        },
        initialize:function(){
            var me = this ;
            this.on("change text",function(m){
                if(me.liView){
                    me.liView.setText(m.attributes.text) ;
                }
            }) ;
        }
    }) ;
    var ViewLi = Backbone.View.extend({
        tagName:"li",
        initialize:function(){
            this.$el.addClass("layui-nav-item").append(
                $("<a>").attr("href","javascript:;")
            ) ;
        },
        render:function(){
            return this ;
        },
        setText:function(text){
          $("a",this.$el).text(text) ;
        },
        setElId:function(id){
            this.$el.attr("id",id) ;
            return this ;
        }
    }) ;

    $(function(){
        layui.use('element', function(){
            var element = layui.element;
        });
        new ViewMainAdmin() ;
    }) ;

}) ;