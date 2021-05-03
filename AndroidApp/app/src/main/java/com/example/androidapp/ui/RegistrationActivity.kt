package com.example.androidapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidapp.R
import com.example.androidapp.db.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        StartRegistration.setOnClickListener {
            registration()
        }
    }

    //регистрация пользователя в базе данных
    fun registration(){
        //проверка заполнения полей
        if (inputE.text.isEmpty() || InputName.text.isEmpty() || InputSurname.text.isEmpty() || inputPass.text.isEmpty() || repeatPass.text.toString() != inputPass.text.toString()) {
            Toast.makeText(this, "Все поля должны быть заполнены или пароли не совпадают", Toast.LENGTH_SHORT).show()
        }else{
            //добавление пользователя в базу данных и заполнение его полей
            AUTH.createUserWithEmailAndPassword(
                inputE.text?.toString()?.trim() ?: "",
                inputPass.text?.toString()?.trim() ?: ""
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    val uid = AUTH.currentUser?.uid.toString()
                    val dateMap = mutableMapOf<String, Any>()
                    dateMap[CHILD_ID] = uid
                    dateMap[CHILD_EMAIL] = inputE.text.toString().trim() ?: ""
                    dateMap[CHILD_NAME] = InputName.text.toString().trim() ?: ""
                    dateMap[CHILD_SURNAME] = InputSurname.text.toString().trim() ?: ""
                    REF_DATABASE_ROOT.child(NODE_USERS).child(uid)
                        .updateChildren(dateMap).addOnCompleteListener { task2 ->
                            if (task2.isSuccessful) {
                                Toast.makeText(this, "Вы успешно зарегистрировались!", Toast.LENGTH_SHORT).show()
                                val changeScreen = Intent(this, InterestingsActivity::class.java)
                                startActivity(changeScreen)
                                this.finish()
                            } else Toast.makeText(this, task2.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                } else Toast.makeText(this, it.exception?.message.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }
}