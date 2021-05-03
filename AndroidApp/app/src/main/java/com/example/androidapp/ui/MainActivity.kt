package com.example.androidapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidapp.R
import com.example.androidapp.db.*
import com.example.androidapp.util.initFirebase
import com.example.androidapp.util.initUser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //инициализация базы данных и текущего пользователя из базы данных
        initFirebase()
        if (AUTH.currentUser != null) initUser { changeToUserActivity() }
        setContentView(R.layout.activity_main)
        LoginButton.setOnClickListener {
            auth()
        }
    }

    //проверка данных авторизации пользователя в базе данных
    fun auth() {
        AUTH.signInWithEmailAndPassword(
            inputEm.text?.toString()?.trim() ?: "",
            inputPassw.text?.toString()?.trim() ?: ""
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_EMAIL] = inputEm.text.toString().trim() ?: ""
                REF_DATABASE_ROOT.child(NODE_USERS).child(uid)
                        .updateChildren(dateMap).addOnCompleteListener { task2 ->
                            if (task2.isSuccessful) {
                                Toast.makeText(this, "Добро пожаловать", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, task2.exception?.localizedMessage.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
            } else {
                Toast.makeText(this, it.exception?.localizedMessage.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    //переход на экран ЛК
    private fun changeToUserActivity() {
        val changeScreen = Intent(this, UserActivity::class.java)
        startActivity(changeScreen)
        this.finish()
    }

    //переход на экран регистрации
    fun changeToRegistrationActivity(view: View) {
        val changeScreen = Intent(this, RegistrationActivity::class.java)
        startActivity(changeScreen)
        this.finish()
    }
}