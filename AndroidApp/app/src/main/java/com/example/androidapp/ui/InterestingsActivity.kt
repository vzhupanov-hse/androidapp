package com.example.androidapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidapp.R
import com.example.androidapp.db.*
import kotlinx.android.synthetic.main.activity_interestings.*
import java.util.*

class InterestingsActivity : AppCompatActivity() {

    val interests = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interestings)

        nextDisplay.setOnClickListener {
            //добавление интересов в список интересов пользователя
            if (checkAndroid.isChecked) interests.add(CHILD_ANDROID)
            if (checkCSS.isChecked) interests.add(CHILD_CSS)
            if (checkCpp.isChecked) interests.add(CHILD_CPLUS)
            if (checkCsh.isChecked) interests.add(CHILD_CSHARP)
            if (checkHtml.isChecked) interests.add(CHILD_HTML)
            if (checkWeb.isChecked) interests.add(CHILD_WEB)
            if (checkPython.isChecked) interests.add(CHILD_PYTHON)
            if (checkJS.isChecked) interests.add(CHILD_JAVASCRIPT)
            if (checkJava.isChecked) interests.add(CHILD_JAVA)
            if (checkKotlin.isChecked) interests.add(CHILD_KOTLIN)
            //проверка на количество выбранных пунктов
            when(interests.size) {
                //вывод сообщения об ошибке при выборе недостаточного окличества интересов
                0 -> {
                    Toast.makeText(this, "Нужно выбрать хотя-бы один пункт", Toast.LENGTH_SHORT).show()
                    clearCheck()
                }
                //переключение на следующий экран при выборе досточного количества пунктов
                in 1..50 -> {
                    addDB()
                    val changeScreen = Intent(this, AboutUserActivity::class.java)
                    startActivity(changeScreen)
                    this.finish()
                }
                else -> Toast.makeText(this, "Что-то пошло не так", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //очищение checkBox'ов интересов
    fun clearCheck(){
        interests.clear()
        checkAndroid.isChecked = false
        checkCSS.isChecked = false
        checkCpp.isChecked = false
        checkCsh.isChecked = false
        checkHtml.isChecked = false
        checkWeb.isChecked = false
        checkPython.isChecked = false
        checkJS.isChecked = false
        checkJava.isChecked = false
        checkKotlin.isChecked = false
    }

    //добавление интересов пользователя в базу данных
    fun addDB(){
        CURRENT_UID = AUTH.currentUser?.uid.toString()
        for (interes in interests) REF_DATABASE_ROOT.child(NODE_INTERES).child(CURRENT_UID).child(CURRENT_UID).child(interes).setValue(interes)
    }
}