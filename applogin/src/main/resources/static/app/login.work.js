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
            var me = this ;
            var viewHeadLeft = new ViewHeadLeft() ;
            this.dv_head.append(new ViewHeadLogo().render().el)
                .append(viewHeadLeft.render().el)
                .append(new ViewHeadRight().render().el) ;
            this.showTime(viewHeadLeft) ;
            viewHeadLeft.addModel({text:me.getTime()}) ;
        },
        showTime:function(viewHeadLeft){
            var me = this ;
            setInterval(function(){
                viewHeadLeft.addModel({text:me.getTime()}) ;
            },1000) ;
        },

        addMenu:function(){
            this.dv_menu.append() ;
        },
        addBody:function(){
            this.dv_body.html("内容默认显示") ;
        },
        addfooter:function(){
            this.dv_footer.html("© luke-shop") ;
        },
        SystemTime:null,
        getTime:function() {
            var date ;
            if(this.SystemTime){
                this.SystemTime = new Date(this.SystemTime.getTime()+1000) ;
            }else{
                if($("#systime").length>0){
                    var systime = $("#systime").text() ;
                    this.SystemTime = new Date( parseInt(systime));
                }else{
                    this.SystemTime = new Date() ;
                }
            }
            date = ls.tm_getCurrentDate(3,this.SystemTime) ;
            return date;
        }
    }) ;

    var ViewHeadLeft = Backbone.View.extend({
        tagName:"ul",
        initialize:function(){
            this.$el.addClass("layui-nav layui-layout-left") ;
            var $li = new ViewLi().setElId("li_showTime").render().$el  ;
            this.$el.append($li) ;
        },
        render:function(){
            return this ;
        },
        addModel:function(model){
            var m = new ModelLi(model) ;
            this.model = m ;
            $("#li_showTime").remove() ;
            var $li = new ViewLi({model:m}).setElId("li_showTime").render().$el  ;
            this.$el.append($li) ;
        }

    }) ;
    var ViewHeadRight = Backbone.View.extend({
        tagName:"ul",
        initialize:function(){
            this.$el.addClass("layui-nav layui-layout-right") ;
            var vl_exit = new ViewLi({model:new ModelLi({text:'退出'})}).setElId("li_exit").render() ;
            var $li_exit = vl_exit.$el ;

            var vl_userInfo =  new ViewLi({model:new ModelLi({text:'用户信息'})}).setElId("li_userInfo").render() ;
            var $li_userInfo = vl_userInfo.$el  ;

            this.$el.append($li_userInfo) ;
            this.$el.append($li_exit) ;
        },
        events:{
            "click #li_exit":"li_exit_click_handler" //退出事件
        },
        render:function(){
            return this ;
        },
        li_exit_click_handler:function(){
            ls.ajax({
                url:""
            })
        }
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

    /**
     tip:'说明',
     text:'测试项',
     jsurl:[js-url]
     child:[{text:'测试子项1',tip:'测试子项1说明'},{text:'测试子项2',tip:'测试子项2说明'}]
     */
    var ModelLi =  Backbone.Model.extend({
        defaults:{
            tip:'说明',
            text:'测试项',
            src:''
        },
        initialize:function(){
        }
    }) ;


    var ViewLi = Backbone.View.extend({
        tagName:"li",
        events:{
            "click a":"li_click_handler"
        },
        initialize:function(){
            this.model = this.model?this.model:new ModelLi() ;
            this.listenTo(this.model,'change text',this.changeModelText) ;
            this.data_show1(this.model.attributes,this.$el) ;

        },
        li_click_handler:function(je){
            // console.dir($(je.currentTarget)) ;
            var $item = $(je.currentTarget) ;
            if($item.attr("jsurl")){
                require($item.attr("jsurl"));
            }
        },
        data_show1:function(obj,$el){
            obj = obj||{}
            var text = obj.text||'' ;
            $el.addClass("layui-nav-item").append(
                $("<a>").attr("href","javascript:;").attr("jsurl",obj.jsurl||"").text(text)
            ) ;
            if(obj.child){
                var $dl = $("<dl>").addClass("layui-nav-child") ;
                $el.append($dl) ;
                this.data_dg1(obj.child,$dl)
            }
        },
        data_dg1:function(child,$el){
            for(var obj in child){
                obj = obj||{} ;
                var m = new ModelLi(child[obj]) ;
                var $dd = $("<dd>").append($("<a>").attr("href","javascript:;").attr("jsurl",obj.jsurl||"").text(m.attributes.text)) ;
                $el.append($dd) ;
                if(m.child){
                    var $dl = $("<dl>").addClass("layui-nav-child") ;
                    $el.append($dl) ;
                    this.data_dg1(m.child,$dl)
                }
            }
        },
        render:function(){
            return this ;
        },
        changeModelText:function(m){
            $("a:eq(1)",this.$el).text(m.attributes.text) ;
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