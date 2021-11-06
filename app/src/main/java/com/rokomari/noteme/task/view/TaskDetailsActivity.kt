package com.rokomari.noteme.task.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rokomari.noteme.R
import com.rokomari.noteme.databinding.ActivityAddTaskBinding
import com.rokomari.noteme.databinding.ActivityTaskDetailsBinding
import com.rokomari.noteme.task.viewmodel.TaskViewModel

class TaskDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityTaskDetailsBinding
    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    fun initView(){
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val createDate = intent.getStringExtra("create_at")
        val status = intent.getStringExtra("status")
        val description = intent.getStringExtra("dsecription")
        val deadline = intent.getStringExtra("deadline")

        binding.taskTitleId.text = name
        binding.descriptionValueId.text = description
        binding.createDateId.text = createDate
        binding.statusId.text = status
        binding.deadlineValueId.text = deadline

        binding.editTaskId.setOnClickListener {
            val intent = Intent(this,EditTaskActivity::class.java)
            intent.putExtra("id",id)
            intent.putExtra("name",name)
            intent.putExtra("create_at",createDate)
            intent.putExtra("status",status)
            intent.putExtra("dsecription",description)
            intent.putExtra("deadline",deadline)
            startActivity(intent)
        }

        binding.deleteIconId.setOnClickListener{
            viewModel.deleteItemById(id!!.toInt(),this)
        }

    }
}