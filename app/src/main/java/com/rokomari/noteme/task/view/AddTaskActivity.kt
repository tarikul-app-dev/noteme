package com.rokomari.noteme.task.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.rokomari.noteme.R
import com.rokomari.noteme.databinding.ActivityAddTaskBinding
import com.rokomari.noteme.databinding.ActivityMainBinding
import com.rokomari.noteme.task.model.TaskModel
import com.rokomari.noteme.task.viewmodel.TaskViewModel
import com.rokomari.noteme.utils.CommonMethods

class AddTaskActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddTaskBinding
    private lateinit var viewModel: TaskViewModel
    var statusOptions = arrayOf("Open", "In-Progress", "Test", "Done")
    private lateinit var taskModel: TaskModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    fun initView() {
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        val adapter1 =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, statusOptions)
        binding.statusSpinnerId.threshold =
            1 //start searching for values after typing first character
        binding.statusSpinnerId.setAdapter(adapter1)

        binding.statusSpinnerId.setOnClickListener {
            binding.statusSpinnerId.showDropDown()
        }

        binding.datePickerId.setOnClickListener {
            val defDate = binding.dateId.text.toString()
            viewModel.showDobDatePicker(this, binding.dateId, defDate)
        }


        binding.submitBtnId.setOnClickListener {
            val taskName = binding.nameILId.editText!!.text.toString()
            val description = binding.descriptionILId.editText!!.text.toString()
            val deadline = binding.dateId.text.toString()
            val status = binding.statusSpinnerId.text.toString()
            val createAt = CommonMethods.getCurrentDate().toString()

            if (taskName.isEmpty()) {
                binding.nameIEId.error = "Field can not be empty"
                return@setOnClickListener
            }

            if (description.isEmpty()) {
                binding.descriptionIEId.error = "Field can not be empty"
                return@setOnClickListener
            }

            if (status.isEmpty()) {
                CommonMethods.showMatDialog(this, "Warning", "Select status")
                return@setOnClickListener
            }


            taskModel = TaskModel(
                0, taskName, description, createAt, deadline, status, "", "", ""
            )

            viewModel.createTask(taskModel, this)

        }


    }

}