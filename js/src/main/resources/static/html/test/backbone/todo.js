$(function(){

 var TodoModel = Backbone.Model.extend({
    defaults:function(){
        return {
            name:'luke',
            age:'age',
            done: false,
            order: Todos.nextOrder()
        } ;
    },
    toggle: function() {
          this.save({done: !this.get("done")});
        }
 }) ;
console.dir(TodoModel) ;

}) ;