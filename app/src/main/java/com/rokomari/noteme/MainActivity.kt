package com.rokomari.noteme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.rokomari.noteme.databinding.ActivityMainBinding
import com.rokomari.noteme.task.view.HomeFragment
import com.rokomari.noteme.utils.CommonMethods
import com.rokomari.noteme.utils.OnClickAlertDialog

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    fun initView() {
        openFragment(HomeFragment())


        binding.bottomAppBar.setOnNavigationItemSelectedListener {
            clearBackStack()
            var returnType = false
            when (it.itemId) {
                R.id.openId -> {
                    openFragment(HomeFragment())
                    returnType = true
                }
                R.id.inprogressId -> {
                    openFragment(HomeFragment())
                    returnType = true
                }
                R.id.testId -> {
                    openFragment(HomeFragment())
                    returnType = true
                }

                R.id.doneId -> {
                    openFragment(HomeFragment())
                    returnType = true
                }
                else -> returnType = false
            }

            return@setOnNavigationItemSelectedListener returnType
        }

    }

    private fun clearBackStack() {
        for (i in 0..supportFragmentManager.backStackEntryCount) {
            supportFragmentManager.popBackStack()
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.homeFrameID, fragment)
            .addToBackStack(null)
            .commit()
    }


    override fun onBackPressed() {
        val appName = getAppName()
        if (supportFragmentManager.backStackEntryCount == 1) {
            CommonMethods.alertDialog(
                this,
                appName,
                "Are you sure you want to close this app?",
                "Yes", object : OnClickAlertDialog {
                    override fun positiveClick() {
                        finish()
                    }

                    override fun negativeClick() {

                    }

                })
        }
        if (supportFragmentManager.backStackEntryCount != 0 && supportFragmentManager.backStackEntryCount != 1)
            super.onBackPressed()
    }

    fun getAppName(): String {
        var appName: String = ""
        val applicationInfo = this.getApplicationInfo()
        val stringId = applicationInfo.labelRes
        appName = if (stringId == 0) {
            applicationInfo.nonLocalizedLabel.toString()
        } else {
            this.getString(stringId)
        }

        return appName
    }
}