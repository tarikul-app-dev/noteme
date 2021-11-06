package com.rokomari.noteme.task.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rokomari.noteme.R
import com.rokomari.noteme.databinding.FragmentDoneBinding
import com.rokomari.noteme.databinding.FragmentInProgressBinding
import com.rokomari.noteme.task.adapter.AllTaskAdapter
import com.rokomari.noteme.task.model.TaskModel
import com.rokomari.noteme.task.viewmodel.TaskViewModel

class DoneFragment : Fragment() ,AllTaskAdapter.OnItemClickListener {
    lateinit var binding: FragmentDoneBinding
    private lateinit var viewModel: TaskViewModel
    private var linearLayoutManager: LinearLayoutManager? = null
    private var allTaskAdapter: AllTaskAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDoneBinding.inflate(layoutInflater)

        initView()
        return binding.root
    }
    fun initView() {
        allTaskAdapter = AllTaskAdapter(requireActivity(),this)

        binding.addTaskId.setOnClickListener {
            startActivity(Intent(requireActivity(), AddTaskActivity::class.java))
        }

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerview.layoutManager = linearLayoutManager


        liveDataListener()
    }

    fun liveDataListener() {
        viewModel.allData.observe(requireActivity()) {
            val openTaskList = mutableListOf<TaskModel>()
            for (i in it){
                if(i.status == "Done") {
                    val taskModel = TaskModel(i.id,i.name,i.description,i.create_at,i.deadline,i.status,i.email,i.phone,i.url)
                    openTaskList.add(taskModel)
                }
            }
            allTaskAdapter!!.clearData();
            allTaskAdapter!!.addData(openTaskList)
            binding.recyclerview.adapter = allTaskAdapter

        }

    }

    override fun onItemClick(
        itemId: Int,
        name: String,
        createAt: String,
        status: String,
        description: String,
        deadline: String
    ) {
        val intent = Intent(requireActivity(),TaskDetailsActivity::class.java)
        intent.putExtra("id",id)
        intent.putExtra("name",name)
        intent.putExtra("create_at",createAt)
        intent.putExtra("status",status)
        intent.putExtra("dsecription",description)
        intent.putExtra("deadline",deadline)
        startActivity(intent)

    }

}