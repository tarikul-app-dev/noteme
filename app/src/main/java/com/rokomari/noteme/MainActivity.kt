package com.rokomari.noteme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rokomari.noteme.databinding.ActivityMainBinding
import com.rokomari.noteme.task.view.AddTaskActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    fun initView(){
        binding.inCludeLayoutId.addTaskId.setOnClickListener {
             startActivity(Intent(this,AddTaskActivity::class.java))
        }
    }
}