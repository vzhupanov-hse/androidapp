package com.example.androidapp.util

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.androidapp.R
import com.example.androidapp.ui.activity.MainActivity


lateinit var APP_ACTIVITY: MainActivity

fun Activity.replaceActivity(activity: AppCompatActivity){
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}

fun getRandomString(length: Int) : String {
    val allowedChars = ('A'..'Z') + ('a'..'z')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}

fun replaceFragment(fragment: Fragment, addStack: Boolean = true) {
    if (addStack) { APP_ACTIVITY.supportFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(R.id.MainContainer, fragment).commit()
    } else {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .replace(R.id.MainContainer, fragment).commit()
    }
}

fun Fragment.showToast(message:String){
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}