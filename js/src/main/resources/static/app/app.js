require.config({
    urlArgs:'js'+Math.random(),
    baseUrl: "/",
    map: {
        '*': {
            'css': 'js/css.min'
        }
    },
    paths:{
        jquery:'js/jquery',
        underscore:'js/underscore',
        backbone:'js/backbone',
        layui:'js/ui/layui/layui',
        md5:'js/md5',
        json2:'js/json2',
        ls:"app/ls"
    },
    shim:{
        md5:{
            deps:['jquery'],
            exports:'md5'
        },
        backbone:{
            deps:['jquery','underscore'],
            exports:'backbone'
        },
        layui:{
            deps:['jquery','css!js/ui/layui/css/layui.css'],
            exports:'layui'
        },
        ls:{
            deps:['jquery','underscore','backbone','json2','md5','layui','css!js/ui/layui/css/layui.css'],
            exports:'ls'
        }
    }
});