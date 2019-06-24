define(function(require) {

    require("jquery");
    require("backbone");
    require("layui");
    require('ls');


    var View = Backbone.View.extend({
        initialize:function(){
            this.render() ;
        }
      ,render:function(){
            ls.getMainBody().append($("<table >").width(350).attr("id","lgn_items_tree")) ;
            jQuery('#lgn_items_tree').jqGrid({
                "url":"data.json",
                "colModel":[
                    {
                        "name":"category_id",
                        "index":"accounts.account_id",
                        "sorttype":"int",
                        "key":true,
                        "hidden":true,
                        "width":50
                    },{
                        "name":"name",
                        "index":"name",
                        "sorttype":"string",
                        "label":"Name",
                        "width":170
                    },{
                        "name":"lft",
                        "hidden":true
                    },{
                        "name":"rgt",
                        "hidden":true
                    },{
                        "name":"level",
                        "hidden":true
                    },{
                        "name":"uiicon",
                        "hidden":true
                    }
                ],
                "width":"780",
                "hoverrows":false,
                "viewrecords":false,
                "gridview":true,
                "height":"auto",
                "sortname":"lft",
                "loadonce":true,
                "rowNum":100,
                "scrollrows":true,
                // enable tree grid
                "treeGrid":true,
                // which column is expandable
                "ExpandColumn":"name",
                // datatype
                "treedatatype":"json",
                // the model used
                "treeGridModel":"nested",
                // configuration of the data comming from server
                "treeReader":{
                    "left_field":"lft",
                    "right_field":"rgt",
                    "level_field":"level",
                    "leaf_field":"isLeaf",
                    "expanded_field":"expanded",
                    "loaded":"loaded",
                    "icon_field":"icon"
                },
                "sortorder":"asc",
                "datatype":"json",
            });
            // bind keyss
            $("#tree").jqGrid('bindKeys');
            // hide header
            // $('.ui-jqgrid-htable','.ui-jqgrid-hbox').hide();
            return this ;
        }
    }) ;
    return View ;

}) ;