package com.hk.todolist
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

//AT the end of this tut, let's know what exactly adapter's role is and how I can use it
//this will have most of logic here, main activity will not have much business inside
class TodoAdapter (
    private val todos: MutableList<Todo>
        ):RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false,

            )
        )
    }

    //iit will bind the data from our data from todos list, to the view
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            tvTodoTitle.text = curTodo.title;
            cbDone.isChecked = curTodo.isChecked;
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}

