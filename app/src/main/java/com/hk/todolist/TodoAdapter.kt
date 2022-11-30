package com.hk.todolist
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

//AT the end of this tut, let's know what exactly adapter's role is and how I can use it
//this will have most of logic here, main activity will not have much business inside
//Yes, thi si sjust how Recyclled view works.
class TodoAdapter (
    private val todos: MutableList<Todo>
        ):RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    //this holds the view => have access to the view.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false,


            )
        )
    }

    /**
     * @param todo object
     */
    fun addTodo(todo:Todo){
        //takes todoObject, and then just add to the todos.
        todos.add(todo);
        notifyItemInserted(todos.size-1)
    }

    fun deleteDoneTodos(){
        todos.removeAll {todo->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    //Don't worry about this function. very advanced
    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        //if isChecked, the title will be strikeThroughed.
        if(isChecked){
            //What does this or do?Performs a logical or operation between this Boolean and the other one
           tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG;
        }
        else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv();
        }
    }

    //iit will bind the data from our data from todos list, to the view
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var curTodo = todos[position]
        holder.itemView.apply {
            tvTodoTitle.text = curTodo.title;
            cbDone.isChecked = curTodo.isChecked;
            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)
            //also we want to call the function above when checkBox is toggled
            cbDone.setOnCheckedChangeListener { _, isChecked ->
               toggleStrikeThrough(tvTodoTitle, isChecked)
                curTodo.isChecked = isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}

