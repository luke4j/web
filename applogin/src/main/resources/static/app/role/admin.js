define(function(require) {

    require("jquery");
    require("backbone");
    require("layui");
    require('ls');


    var View = Backbone.View.extend({
        initialize:function(){
            this.render() ;
        },
        render:function(){
        // <div id="test9" class="demo-tree demo-tree-box" style="width: 200px; height: 300px; overflow: scroll;"></div>
            var $tree = $("<div id='itemTree'>").addClass("demo-tree-box") ;
            $("#dv_main_body").append($tree) ;
            var me = this ;
            layui.use('tree', function(){
                var tree = layui.tree;

                //渲染
                var inst1 = tree.render({
                    elem: '#itemTree'  //绑定元素
                    ,data: [{
                        title: '江西' //一级菜单
                        ,children: [{
                            title: '南昌' //二级菜单
                            ,children: [{
                                title: '高新区' //三级菜单
                                //…… //以此类推，可无限层级
                            }]
                        }]
                    },{
                        title: '陕西' //一级菜单
                        ,children: [{
                            title: '西安' //二级菜单
                        }]
                    }]
                });
            });
            return this ;
        },

        data:function(){
            return data1 = [{
                title: '江西'
                ,id: 1
                ,children: [{
                    title: '南昌'
                    ,id: 1000
                    ,children: [{
                        title: '青山湖区'
                        ,id: 10001
                    },{
                        title: '高新区'
                        ,id: 10002
                    }]
                },{
                    title: '九江'
                    ,id: 1001
                },{
                    title: '赣州'
                    ,id: 1002
                }]
            },{
                title: '广西'
                ,id: 2
                ,children: [{
                    title: '南宁'
                    ,id: 2000
                },{
                    title: '桂林'
                    ,id: 2001
                }]
            },{
                title: '陕西'
                ,id: 3
                ,children: [{
                    title: '西安'
                    ,id: 3000
                },{
                    title: '延安'
                    ,id: 3001
                }]
            }] ;
        }


    }) ;
    return View ;

}) ;