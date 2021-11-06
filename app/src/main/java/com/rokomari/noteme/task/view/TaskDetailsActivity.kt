package com.rokomari.noteme.task.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rokomari.noteme.R
import com.rokomari.noteme.databinding.ActivityAddTaskBinding
import com.rokomari.noteme.databinding.ActivityTaskDetailsBinding

class TaskDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityTaskDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    fun initView(){
        val id = intent.getIntExtra("id",0)
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



    }
}