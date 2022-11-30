package com.hk.todolist

//data class is just like specifing the interface of an object.
//it is just like object.
data class Todo(
   val title:String,
   var isChecked: Boolean = false,
   )
