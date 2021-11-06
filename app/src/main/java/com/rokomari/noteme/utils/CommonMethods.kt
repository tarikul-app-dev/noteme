package com.rokomari.noteme.utils

import android.content.Context
import android.widget.TextView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rokomari.noteme.R
import java.text.SimpleDateFormat
import java.util.*

class CommonMethods {
    companion object {
        fun getCurrentDate(): String? {
            val format = SimpleDateFormat("dd.MM.yyyy")
            val todayDate = Date()
            return format.format(todayDate)
        }


        fun showMatDialog(
            c: Context?, title: String?,
            message: String?
        ) {
            MaterialAlertDialogBuilder(c!!)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok") { dialogInterface, i -> }
                .show()
        }

        fun alertDialog(
            context: Context, title: String, message: String,
            buttonTitle: String, onClickAlertDialog: OnClickAlertDialog?
        ) {

            val dialog = MaterialAlertDialogBuilder(context, R.style.MaterialDialogStyle)
                .setTitle(title)
                .setView(R.layout.custom_dialog_text)
                .setPositiveButton(buttonTitle) { _, _ ->
                    onClickAlertDialog?.positiveClick()
                }
                .setNegativeButton("No") { _, _ ->
                    onClickAlertDialog?.negativeClick()
                }
                .show()
            val msgText: TextView = dialog.findViewById(R.id.message)!!
            msgText.text = message
        }
    }
}