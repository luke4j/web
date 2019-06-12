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
                    ,data: me.getTreeNodesData()
                    ,id:'itemTree'
                    ,operate:function(obj){
                        // console.dir(obj) ;
                        var type = obj.type; //得到操作类型：add、edit、del
                        var data = obj.data; //得到当前节点的数据
                        var elem = obj.elem; //得到当前节点元素
                        if(type=='add'){
                            // me.save(data) ;
                        }else if(type=='update'){
                            me.treeNodeEdit(obj) ;
                        }else if(type='del'){
                            me.treeNodeDel(obj) ;
                        }
                    }
                    ,edit: ['add', 'update', 'del'] //操作节点的图标
                    ,click: function(obj){
                        layer.msg(JSON.stringify(obj.data));
                    }
                });
            }) ;
            return this ;
        },
        /**编辑节点*/
        treeNodeEdit:function(obj){
            var me = this ;
            var fid = $(obj.elem).parent().parent().attr('data-id') ;
            $.get("app/admin/admin_item.html",function(html){
                /*加载所需要的layui元素*/
                layui.use(["layer","form"],function(){
                    var layer = layui.layer ;
                    var form = layui.form;
                    /*打开弹出窗*/
                    function openLayerIndex(ui_layer,html){
                        return ui_layer.open({
                            title: '修改功能',
                            btn: [],
                            area:['400px','400px'],
                            content: html
                        });
                    }
                    var index = openLayerIndex(layer,html) ;
                    /*渲染form，并给form添加事件*/
                    function renderForm(ui_form,index){
                        ui_form.render() ;
                        ui_form.val("item_form",{
                            fid:fid
                            ,id:obj.data.id
                            ,src:obj.data.src
                            ,title:obj.data.title
                            ,p_bm:obj.data.p_bm
                            ,tip:obj.data.tip
                        }) ;
                        ui_form.on('submit(saveItems)',function(data){
                            me.submit_treeNodeEdit_handler(data.field,openLayerIndex) ;
                            return false ;
                        }) ;
                    }
                    renderForm(form,index) ;
                }) ;
            }) ;
        },
        /**编码提交后台*/
        submit_treeNodeEdit_handler:function(data,index){
            var me = this ;
            ls.ajax({
                url:'admin/editTreeNode.act',
                data:data,
                success:function(res){
                    var tree = layui.tree ;
                    tree.reload('itemTree',{
                        data: me.getTreeNodesData()
                        ,id:'itemTree'
                    }) ;
                    layui.layer.closeAll() ;
                }
            }) ;
        },
        treeNodeDel:function(obj){
            ls.confirm("是否删除",function(){
                ls.ajax({
                    url:'admin/delTreeNode.act',
                    data:{
                        id:obj.data.id
                    },
                    success:ls.empty_function
                }) ;
            }) ;

        },
        getTreeNodesData:function(){
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