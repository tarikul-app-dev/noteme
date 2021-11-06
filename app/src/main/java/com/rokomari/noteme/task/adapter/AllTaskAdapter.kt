package com.rokomari.noteme.task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rokomari.noteme.R
import com.rokomari.noteme.databinding.ItemTaskListBinding
import com.rokomari.noteme.task.model.TaskModel

class AllTaskAdapter(val context: Context, private val taskList: List<TaskModel>) :
    RecyclerView.Adapter<AllTaskAdapter.ViewHolder>() {


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

        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}