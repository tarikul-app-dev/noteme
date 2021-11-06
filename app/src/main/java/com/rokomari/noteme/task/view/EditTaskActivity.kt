package com.rokomari.noteme.task.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.rokomari.noteme.R
import com.rokomari.noteme.databinding.ActivityEditTaskBinding
import com.rokomari.noteme.databinding.ActivityTaskDetailsBinding
import com.rokomari.noteme.task.model.TaskModel
import com.rokomari.noteme.task.viewmodel.TaskViewModel
import com.rokomari.noteme.utils.CommonMethods
import com.rokomari.noteme.utils.SessionManager

class EditTaskActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditTaskBinding
    private lateinit var viewModel: TaskViewModel
    var statusOptions = arrayOf("Open", "In-Progress", "Test", "Done")
    private lateinit var taskModel: TaskModel
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    fun initView() {
        sessionManager = SessionManager(this)
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)


        binding.statusSpinnerId.setOnClickListener {
            binding.statusSpinnerId.showDropDown()
        }

        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val createDate = intent.getStringExtra("create_at")
        val status = intent.getStringExtra("status")
        val description = intent.getStringExtra("dsecription")
        val deadline = intent.getStringExtra("deadline")


        binding.nameILId.editText!!.setText(name)
        binding.descriptionILId.editText!!.setText(description)
        binding.dateId.text = deadline
        binding.statusSpinnerId.setText(status)

        val adapter1 =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, statusOptions)
        binding.statusSpinnerId.threshold = 1
        binding.statusSpinnerId.setAdapter(adapter1)


        binding.datePickerId.setOnClickListener {
            val defDate = binding.dateId.text.toString()
            viewModel.showDobDatePicker(this, binding.dateId, defDate)
        }

        binding.submitBtnId.setOnClickListener {
            val taskName = binding.nameILId.editText!!.text.toString()
            val description = binding.descriptionILId.editText!!.text.toString()
            val deadline = binding.dateId.text.toString()
            val status = binding.statusSpinnerId.text.toString()
            //val createAt = CommonMethods.getCurrentDate().toString()

            if (taskName.isEmpty()) {
                binding.nameIEId.error = "Field can not be empty"
                return@setOnClickListener
            }

            if (description.isEmpty()) {
                binding.descriptionIEId.error = "Field can not be empty"
                return@setOnClickListener
            }

            if (deadline.isEmpty()) {
                CommonMethods.showMatDialog(this, "Warning", "deadline date can not be empty")
                return@setOnClickListener
            }


            if (status.isEmpty()) {
                CommonMethods.showMatDialog(this, "Warning", "Select status")
                return@setOnClickListener
            }

            val email = sessionManager.email
            val phone = sessionManager.mobile
            val url = sessionManager.url

            viewModel.updateTask(
                id.toString(),
                name!!,
                description,
                createDate!!,
                deadline,
                status,
                email!!,
                phone!!,
                url!!,
                this
            )

        }

        binding.linEmailId.setOnClickListener {
            dialogEmail(this)
        }

        binding.linPhoneId.setOnClickListener {
            dialogPhone(this)
        }

        binding.linUrlId.setOnClickListener {
            dialogUrl(this)
        }
    }

    fun dialogEmail(
        activity: Activity
    ) {
        val dialog = MaterialAlertDialogBuilder(activity)
            .setView(R.layout.layout_dialog_email)
            .show()

        val submitBtnId: MaterialButton = dialog.findViewById(R.id.saveEmailBtnId)!!
        val emailILId: TextInputLayout = dialog.findViewById(R.id.emailILId)!!
        val emailIEId: TextInputEditText = dialog.findViewById(R.id.emailIEId)!!
        submitBtnId.setOnClickListener {
            val email = emailILId.editText!!.text.toString()
            if (email.isEmpty()) {
                emailIEId.error = "Field can not be empty"
                return@setOnClickListener
            }
            sessionManager.email = email
            Toast.makeText(activity, "Email Save Success", Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }

    }

    fun dialogPhone(
        activity: Activity
    ) {
        val dialog = MaterialAlertDialogBuilder(activity)
            .setView(R.layout.layout_dialog_phone)
            .show()

        val submitBtnId: MaterialButton = dialog.findViewById(R.id.savePhoneBtnId)!!
        val phoneILId: TextInputLayout = dialog.findViewById(R.id.phoneILId)!!
        val phoneIEId: TextInputEditText = dialog.findViewById(R.id.phoneIEId)!!
        submitBtnId.setOnClickListener {
            val phone = phoneILId.editText!!.text.toString()
            if (phone.isEmpty()) {
                phoneIEId.error = "Field can not be empty"
                return@setOnClickListener
            }
            sessionManager.mobile = phone
            Toast.makeText(activity, "Phone Save Success", Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }

    }

    fun dialogUrl(
        activity: Activity
    ) {
        val dialog = MaterialAlertDialogBuilder(activity)
            .setView(R.layout.layout_dialog_url)
            .show()

        val submitBtnId: MaterialButton = dialog.findViewById(R.id.saveUrlBtnId)!!
        val urlILId: TextInputLayout = dialog.findViewById(R.id.urlILId)!!
        val urlIEId: TextInputEditText = dialog.findViewById(R.id.urlIEId)!!
        submitBtnId.setOnClickListener {
            val url = urlILId.editText!!.text.toString()
            if (url.isEmpty()) {
                urlIEId.error = "Field can not be empty"
                return@setOnClickListener
            }
            sessionManager.url = url
            Toast.makeText(activity, "Url Save Success", Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }

    }
}