package com.rokomari.noteme.task.viewmodel

import android.app.Activity
import android.app.Application
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.rokomari.noteme.MainActivity
import com.rokomari.noteme.R
import com.rokomari.noteme.task.data.TaskDatabase
import com.rokomari.noteme.task.model.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val db = TaskDatabase.getAppDataBase(application)!!
    internal val allData: LiveData<List<TaskModel>> = db.taskDao().getAllData()


    fun createTask(taskModel: TaskModel,activity: Activity) {
        var result: Long = -1
        GlobalScope.launch(Dispatchers.IO) {
            result = db.taskDao().createTask(taskModel)

            withContext(Dispatchers.Main) {
                if (result > 0) {
                    dialog(activity)
                } else {
                    Toast.makeText(activity,"Failed",Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    fun updateTask(id: String,
                   name: String,
                   description: String,
                   create_at: String,
                   deadline: String,
                   status: String,
                   email: String,
                   phone: String,
                   url: String,activity: Activity) {
        var result: Int = -1
        GlobalScope.launch(Dispatchers.IO) {
            result = db.taskDao().updateTask(id,name,description,create_at,deadline,status,email,phone,url)

            withContext(Dispatchers.Main) {

                if (result > 0) {
                    dialog(activity)
                } else {
                    Toast.makeText(activity,"Failed",Toast.LENGTH_LONG).show()
                }

            }
        }

    }

    fun deleteItemById(itemId: Int, activity: Activity) {
        var result: Int = -1
        GlobalScope.launch(Dispatchers.IO) {
            result = db.taskDao().deleteBYItemId(itemId)
            withContext(Dispatchers.Main) {
                if (result > 0) {
                    dialogRemove(activity)
                } else {
                    Toast.makeText(activity,"Failed",Toast.LENGTH_LONG).show()
                }

            }
        }

    }

    fun dialog(
        activity: Activity
    ) {
        val dialog = MaterialAlertDialogBuilder(activity)
            .setView(R.layout.layout_dialog)
            .show()

        val okBtn: MaterialButton = dialog.findViewById(R.id.okBtnId)!!
        okBtn.setOnClickListener {
            activity.finish()
            val intent = Intent(activity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            activity.startActivity(intent)
        }

    }

    fun dialogRemove(
        activity: Activity
    ) {
        val dialog = MaterialAlertDialogBuilder(activity)
            .setView(R.layout.layout_dialog_delete)
            .show()

        val okBtn: MaterialButton = dialog.findViewById(R.id.okBtnId)!!
        okBtn.setOnClickListener {

            dialog.dismiss()
        }

    }

    fun showDobDatePicker(context: Context, dateTvId: TextView, dafDate: String) {
        val c = Calendar.getInstance()
        val mYear = c[Calendar.YEAR]
        val mMonth = c[Calendar.MONTH]
        val mDay = c[Calendar.DAY_OF_MONTH]
        var month: Int = 0
        var year: Int = 2021
        var day: Int = 11

        try {
            val sdf = SimpleDateFormat("dd.MM.yyyy")
            val d: Date = sdf.parse(dafDate)
            val cal = Calendar.getInstance()
            cal.time = d
            month = cal[Calendar.MONTH] //YOUR MONTH IN INTEGER
            year = cal[Calendar.YEAR]
            day = cal[Calendar.DAY_OF_MONTH]
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val datePickerDialog = DatePickerDialog(
            context,android.R.style.Theme_Holo_Dialog,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth -> //
                val month = monthOfYear + 1
                var fm = "" + month
                var fd = "" + dayOfMonth
                if (month < 10) {
                    fm = "0$month"
                }
                if (dayOfMonth < 10) {
                    fd = "0$dayOfMonth"
                }
                val date = "$fd.$fm.$year"
                dateTvId.text = date

            }, mYear, mMonth, mDay
        )
        datePickerDialog.updateDate(year, month, day);

        datePickerDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
      //  datePickerDialog.datePicker.maxDate = System.currentTimeMillis();
        datePickerDialog.show()

    }


}