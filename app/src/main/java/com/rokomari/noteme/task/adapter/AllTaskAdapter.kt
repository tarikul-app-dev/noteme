package com.rokomari.noteme.task.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rokomari.noteme.R
import com.rokomari.noteme.databinding.ItemTaskListBinding
import com.rokomari.noteme.task.model.TaskModel
import com.rokomari.noteme.task.view.EditTaskActivity
import com.rokomari.noteme.task.view.TaskDetailsActivity
import com.rokomari.noteme.task.viewmodel.TaskViewModel

class AllTaskAdapter(
    val context: Context,
    val listener: OnItemClickListener
) :
    RecyclerView.Adapter<AllTaskAdapter.ViewHolder>() {

    var taskList: MutableList<TaskModel> = mutableListOf()


    @SuppressLint("NotifyDataSetChanged")
    fun addData(task: MutableList<TaskModel>) {
        taskList.addAll(task)
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun clearData() {
        taskList.clear()
        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemTaskListBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val taskItem = taskList[position]
        with(holder) {
            binding.taskValueId.text = taskItem.name
            binding.createDateValueId.text = taskItem.create_at
            binding.deadlineValueId.text = taskItem.deadline

            binding.edtTaskId.setOnClickListener {
                val name = taskItem.name
                val status = taskItem.status
                val description = taskItem.description
                val deadline = taskItem.deadline
                val createAt = taskItem.create_at
                val itemId = taskItem.id.toString()
                val intent = Intent(context, EditTaskActivity::class.java)
                intent.putExtra("id",itemId)
                intent.putExtra("name",name)
                intent.putExtra("create_at",createAt)
                intent.putExtra("status",status)
                intent.putExtra("dsecription",description)
                intent.putExtra("deadline",deadline)
                context.startActivity(intent)

            }

            binding.deleteId.setOnClickListener {
                val itemId = taskItem.id
                listener.onClickDeleteItem(itemId)
            }

        }
        holder.itemView.setOnClickListener {
            val name = taskItem.name
            val status = taskItem.status
            val description = taskItem.description
            val deadline = taskItem.deadline
            val createAt = taskItem.create_at
            val itemId = taskItem.id.toString()
            listener.onItemClick(itemId,name,createAt,status,description,deadline)
        }

    }


    override fun getItemCount(): Int {
        return taskList.size
    }


    interface OnItemClickListener {
        fun onItemClick(
            itemId: String,
            name: String,
            createAt: String,
            status: String,
            description: String,
            deadline: String
        )

        fun onClickDeleteItem(itemId: Int)
    }

}