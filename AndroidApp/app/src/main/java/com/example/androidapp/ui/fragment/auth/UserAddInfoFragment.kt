package com.example.androidapp.ui.fragment.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidapp.databinding.FragmentUserAddInfoBinding
import com.example.androidapp.db.*
import com.example.androidapp.ui.activity.MainActivity
import com.example.androidapp.util.*
import java.util.*

class UserAddInfoFragment : Fragment() {

    private val interests = ArrayList<String>()
    private lateinit var bind: FragmentUserAddInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        bind = FragmentUserAddInfoBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.nextDisplay.setOnClickListener {
            if (bind.aboutUser.text.isEmpty()) showToast("Напишите про себя что-то")
            else addCheckBoxDB()
        }
    }

    private fun addCheckBoxDB() {
        //добавление интересов в список интересов пользователя
        if (bind.checkAndroid.isChecked) interests.add(CHILD_ANDROID)
        if (bind.checkCSS.isChecked) interests.add(CHILD_CSS)
        if (bind.checkCpp.isChecked) interests.add(CHILD_CPLUS)
        if (bind.checkCsh.isChecked) interests.add(CHILD_CSHARP)
        if (bind.checkHtml.isChecked) interests.add(CHILD_HTML)
        if (bind.checkWeb.isChecked) interests.add(CHILD_WEB)
        if (bind.checkPython.isChecked) interests.add(CHILD_PYTHON)
        if (bind.checkJS.isChecked) interests.add(CHILD_JAVASCRIPT)
        if (bind.checkJava.isChecked) interests.add(CHILD_JAVA)
        if (bind.checkKotlin.isChecked) interests.add(CHILD_KOTLIN)
        //проверка на количество выбранных пунктов
        when (interests.size) {
            //вывод сообщения об ошибке при выборе недостаточного кокличества интересов
            0 -> {
                showToast("Нужно выбрать хотя-бы один пункт")
                clearCheck()
            }
            //переключение на следующий экран при выборе досточного количества пунктов
            in 1..5 -> {
                //добавление интересов пользователя в базу данных
                addDB()
            }
            else -> {
                clearCheck()
                showToast("Что-то пошло не так")
            }
        }
    }

    //очищение checkBox ов интересов
    private fun clearCheck(){
        interests.clear()
        bind.checkAndroid.isChecked = false
        bind.checkCSS.isChecked = false
        bind.checkCpp.isChecked = false
        bind.checkCsh.isChecked = false
        bind.checkHtml.isChecked = false
        bind.checkWeb.isChecked = false
        bind.checkPython.isChecked = false
        bind.checkJS.isChecked = false
        bind.checkJava.isChecked = false
        bind.checkKotlin.isChecked = false
    }

    private fun addDB(){
        val uid = AUTH.currentUser?.uid.toString()
        REF_DATABASE_ROOT.child(NODE_USERS).child(uid).child(CHILD_ADD_INFO).setValue("yes")
        REF_DATABASE_ROOT.child(NODE_USERS).child(uid).child(CHILD_ABOUT).setValue(bind.aboutUser.text.toString())
        for (interes in interests) REF_DATABASE_ROOT.child(NODE_USERS).child(uid).child(NODE_INTERES).child(interes).setValue(interes)
        APP_ACTIVITY.replaceActivity(MainActivity())
    }
}