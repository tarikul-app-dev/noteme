package com.rokomari.noteme.task.viewmodel

import android.R
import android.app.Activity
import android.app.Application
import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.material.snackbar.Snackbar
import com.rokomari.noteme.task.data.TaskDatabase
import com.rokomari.noteme.task.model.TaskModel
import com.rokomari.noteme.task.view.TaskDetailsActivity
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
                    showSuccessDialog(activity)
                } else {
                    showErrorDialog(activity)
                }
            }
        }

    }



    fun showSuccessDialog(activity: Activity) {
        SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("Task Saved")
            .setContentText("Successfully")
            .setConfirmClickListener(SweetAlertDialog.OnSweetClickListener {
                activity.finish()
            })
            .show()

    }

    fun showErrorDialog(context: Context) {
        SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("Oops...")
            .setContentText("Something went wrong!")
            .show()
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