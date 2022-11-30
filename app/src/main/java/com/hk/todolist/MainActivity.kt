package com.hk.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //this onCreate Function will be called when this activity is craeted
    private lateinit var todoAdapter: TodoAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //has recycled view and editText and buttons.

        //initiate toDoAdapter. what is toDoAdapter? is Recycled view adapter. so, i guess we gonna attach the adapter to the revyvle d view
        todoAdapter = TodoAdapter(mutableListOf())

        //Yes. rvTodoItems is the id of RecycledViewElement. a RecycledViewElement needs adapter.
        rvTodoItems.adapter = todoAdapter
        //this is just specifiing the layout of the recyclledView. what else other than Linear Layout?
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        //this is setting onclick, if the user click add button, call addTodo in todoAdapterMethod
        btnAddTodo.setOnClickListener {
            //get the text currently in the editText field.
            val todoTitle = etTodoTitle.text.toString()
            //if it s not empty,
            if(todoTitle.isNotEmpty()){
                //make a toDoObject, with the input text, and isChecked is false.
                val todoObject = Todo(etTodoTitle.text.toString(), false)
                //call adapter to add the to do object.j
                todoAdapter.addTodo(todoObject)
                //clear the editTextField
                etTodoTitle.text.clear()
            }
        }
        btnDeleteDone.setOnClickListener{
           //get all the items that is checked, and then send it to todoAdapter.delete
            //in fact the comment above is already done by the method in todoAdapter. so I can just call it.
            todoAdapter.deleteDoneTodos();
        }
    }
}