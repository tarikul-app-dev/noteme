package com.rokomari.noteme.utils

import android.content.Context
import android.content.SharedPreferences
import java.io.File
import java.util.prefs.Preferences

class SessionManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)



    var email: String?
        get() = sharedPreferences.getString("email", "")
        set(email) = sharedPreferences.edit().putString("email", email).apply()


    var mobile: String?
        get() = sharedPreferences.getString("mobile", "")
        set(mobile) = sharedPreferences.edit().putString("mobile", mobile).apply()

    var url: String?
        get() = sharedPreferences.getString("url", "")
        set(url) = sharedPreferences.edit().putString("url", url).apply()

}