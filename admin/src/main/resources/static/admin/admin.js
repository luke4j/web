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
            layui.use(['tree', 'util'], function() {
                var tree = layui.tree  , layer = layui.layer  , util = layui.util ;
                tree.render({
                    elem: '#itemTree'
                    ,data: me.data()
                    ,operate:function(obj){
                        // console.dir(obj) ;
                        var type = obj.type; //得到操作类型：add、edit、del
                        var data = obj.data; //得到当前节点的数据
                        var elem = obj.elem; //得到当前节点元素
                        if(type=='add'){
                            // me.save(data) ;
                        }else if(type=='edit'){
                            me.treeNodeEdit(data) ;
                        }else if(type='del'){
                            me.treeNodeDel(data) ;
                        }
                    }
                    ,edit: ['add', 'update', 'del'] //操作节点的图标
                    // ,click: function(obj){
                    //     layer.msg(JSON.stringify(obj.data));
                    // }
                });
            }) ;
            return this ;
        },
        treeNodeEdit:function(data){
            var fid = $(obj.elem).parent().parent().attr('data-id') ;
            $.get("app/login/updatePassword.html",function(html){
                /*加载所需要的layui元素*/
                layui.use(["layer","form"],function(){
                    var layer = layui.layer ;
                    var form = layui.form;
                    /*打开弹出窗*/
                    function openLayer(ui_layer,html){
                        ui_layer.open({
                            title: '修改功能',
                            btn: [],
                            area:['400px','350px'],
                            content: html
                        });
                    }
                    var openLayer = openLayer(layer,html) ;
                    /*渲染form，并给form添加事件*/
                    function renderForm(ui_form,openLayer){
                        ui_form.render() ;
                        ui_form.on('submit(saveItems)',function(data){
                            return false ;
                        }) ;
                    }
                    renderForm(form,openLayer) ;
                }) ;
            }) ;
        },
        treeNodeDel:function(){
            ls.ajax({
                url:'',
                data:{}
            }) ;
        },
        data:function(){
            var data;
            ls.ajax({
                url:'admin/findAllItemTreeNode.act',
                async:false,
                success:function(res){
                    data = res.data.children ;
                }
            }) ;
            return data ;
        }
    }) ;
    return View ;

}) ;