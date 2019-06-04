define(function(require) {

    require("jquery");
    require("backbone");
    require("layui");
    require('ls');

    var View = Backbone.View.extend({
        events:function(){
            console.dir("=========================") ;
            return {
                "click button[event-filter='btn_add_role]":"btn_add_role_click_handler"
            }
        },
        btn_add_role_click_handler:function(){
            ls.alert("123") ;
        },
        initialize:function(){
            this.render() ;
        },
        render:function(){
            this.addMenu() ;
            this.addTable() ;
            return this ;
        },
        addMenu:function(){
            var $dv_menu = $("<div>").addClass("layui-btn-group dv_role_table_tool").hide().attr("id","myRole").append(
                $("<button>").addClass("layui-btn ").text("添加角色").attr("id","btn_add_role").attr("data-type","btn_add_role")
            )  ;
            $("#dv_main_body").append($dv_menu) ;
        },
        addTable:function(){
            var $tbl = $("<table>").attr("id","tbl_role").attr("lay-filter","tbl_role").addClass("layui-table") ;
            $("#dv_main_body").append($tbl) ;
            var $toolbar = $("<div>")
                .append($("<a class=\"layui-btn layui-btn-primary layui-btn-xs\" lay-event=\"detail\">查看</a>"))
                .append($("<a class=\"layui-btn layui-btn-xs\" lay-event=\"edit\">编辑</a>"))
                .append($("<a class=\"layui-btn layui-btn-danger layui-btn-xs\" lay-event=\"del\">删除</a>")) ;
            layui.use('table', function() {
                var table = layui.table;
                table.render({
                    elem: '#tbl_role'
                    ,toolbar:'#myRole'
                    ,height: 312
                    // ,url: '//www.layui.com/demo/table/user/' //数据接口
                    ,page: true //开启分页
                    ,cols: [[ //表头
                        {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                        ,{field: 'username', title: '用户名', width:80}
                        ,{field: 'sex', title: '性别', width:80, sort: true}
                        ,{field: 'city', title: '城市', width:80}
                        ,{field: 'sign', title: '签名', width: 177}
                        ,{field: 'experience', title: '积分', width: 80, sort: true}
                        ,{field: 'score', title: '评分', width: 80, sort: true}
                        ,{field: 'classify', title: '职业', width: 80}
                        ,{field: 'wealth', title: '财富', width: 135, sort: true}
                        ,{field:'',title:'操作',toolbar:$toolbar}
                    ]]
                }) ;
                table.on("tool(tbl_role)",function(){
                    console.dir(arguments) ;
                }) ;
                $('.layui-table-tool .layui-btn').on('click', function(){
                    var type = $(this).data('type');
                    console.dir(type) ;
                    // active[type] ? active[type].call(this) : '';
                    ls.alert(type) ;
                });
            }) ;
        }
    }) ;
    return View ;


}) ;