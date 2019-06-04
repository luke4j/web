define(function(require) {

    require("jquery");
    require("backbone");
    require("layui");
    require('ls');
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
            this.dv_menu_scroll = $("<div>").addClass("layui-side-scroll") ;
            this.dv_menu = $("<div>").addClass("layui-side layui-bg-black").append(this.dv_menu_scroll) ;
            this.dv_body = $("<div>").addClass("layui-body").attr("id","dv_main_body") ;
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
            var $menu = new ViewMenu().render().$el ;
            this.dv_menu_scroll.append($menu) ;
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
    var ViewMenu = Backbone.View.extend({
        tagName:"ul",
        initialize:function(){
            this.$el.addClass("layui-nav layui-nav-tree").attr("id","ul_menu") ;
        },
        render:function(){
            this.getRole() ;
            return this ;
        },
        getRole:function(){
            var me = this ;
            ls.ajax({
                url:"getRole.act",
                data:{loginTuken:ls.cookieGetToken()},
                success:function(resp){
                    for(var i in resp.data.child){
                        var child = resp.data.child[i] ;
                        var menu = new ViewLi({model:new ModelLi(child)}) ;
                        me.$el.append(menu.$el) ;
                    }
                   ls.layui_reader() ;
                }
            })
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

            var dataExit = {text:'退出',jsurl:'app/login/logout'} ;
            var vl_exit = new ViewLi({model:new ModelLi(dataExit)}).setElId("li_exit").render() ;
            var $li_exit = vl_exit.$el ;

            var dataUpdatePassword = {text:'修改密码',jsurl:'app/login/updatePassword'} ;
            var dataShowStaffInfo = {text:'个人信息',jsurl:'app/login/showStaffInfo'} ;
            var changeStore = {text:'切换站点',jsurl:'app/login/changeStore'} ;
            var dataUser = {model:new ModelLi({text:'用户信息',child:[dataUpdatePassword,dataShowStaffInfo,changeStore]})} ;
            var vl_userInfo =  new ViewLi(dataUser).setElId("li_userInfo").render() ;
            var $li_userInfo = vl_userInfo.$el  ;

            this.$el.append($li_userInfo) ;
            this.$el.append($li_exit) ;
        },
        events:{
        },
        render:function(){
            return this ;
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
            jsurl:''
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
            var $item = $(je.currentTarget) ;
            if($item.attr("jsurl")){
                require([$item.attr("jsurl")],function(FunModel){
                    if(FunModel){
                        $("#dv_main_body").empty() ;
                        new FunModel({$el:$("#dv_main_body")});
                        ls.layui_reader() ;
                    }else{
                        layui.use("layer",function(){
                            var layer = layui.layer ;
                            layer.open({
                                title: 'js异常',
                                content: $item.attr("jsurl")+'.js文件===定义异常'
                            });
                        }) ;
                    }

                });
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
                var $dd = $("<dd>").append($("<a>").attr("href","javascript:;").attr("jsurl",m.attributes.jsurl||"").text(m.attributes.text)) ;
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
        ls.layui_reader() ;
        new ViewMainAdmin() ;
    }) ;

}) ;