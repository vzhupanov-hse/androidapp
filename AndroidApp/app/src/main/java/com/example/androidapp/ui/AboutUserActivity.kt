package com.example.androidapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.androidapp.R
import com.example.androidapp.db.*
import com.example.androidapp.util.initUser
import kotlinx.android.synthetic.main.activity_about_user.*
import kotlinx.android.synthetic.main.activity_registration.*

class AboutUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_user)
        //переключение на следующий экран
        nextDisplay2.setOnClickListener {
            if (aboutUser.text.isNotEmpty()) {
                //запись информации о пользователе в базу данных
                val uid = AUTH.currentUser?.uid.toString()
                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).child(CHILD_ABOUT).setValue(aboutUser.text.toString())
                //переход на следующий экран
                val changeScreen = Intent(this, UserActivity::class.java)
                initUser { startActivity(changeScreen) }
                this.finish()
            //вывод ошибки о пустой строке
            }else Toast.makeText(this, "Напишите про себя что-то", Toast.LENGTH_SHORT).show()
        }
    }
}