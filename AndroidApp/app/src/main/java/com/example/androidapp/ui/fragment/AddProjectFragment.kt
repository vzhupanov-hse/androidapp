package com.example.androidapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidapp.databinding.FragmentProjectAddBinding
import com.example.androidapp.db.*
import com.example.androidapp.util.getRandomString
import com.example.androidapp.util.replaceFragment
import com.example.androidapp.util.showToast
import java.util.ArrayList

class AddProjectFragment : Fragment() {

    private val interests = ArrayList<String>()
    private lateinit var bind: FragmentProjectAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentProjectAddBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.button.setOnClickListener { checkText() }
    }


    fun checkText() {
        if (bind.editTextTextPersonName.text.isEmpty() || bind.editTextTextPersonName2.text.isEmpty()) {
            showToast("Все поля должны быть заполнены или пароли не совпадают")
        }else addCheckBoxDB()
    }

    private fun addCheckBoxDB() {
        //добавление интересов в список интересов пользователя
        if (bind.checkAndroid2.isChecked) interests.add(CHILD_ANDROID)
        if (bind.checkCSS2.isChecked) interests.add(CHILD_CSS)
        if (bind.checkCpp2.isChecked) interests.add(CHILD_CPLUS)
        if (bind.checkCsh2.isChecked) interests.add(CHILD_CSHARP)
        if (bind.checkHtml2.isChecked) interests.add(CHILD_HTML)
        if (bind.checkWeb2.isChecked) interests.add(CHILD_WEB)
        if (bind.checkPython2.isChecked) interests.add(CHILD_PYTHON)
        if (bind.checkJS2.isChecked) interests.add(CHILD_JAVASCRIPT)
        if (bind.checkJava2.isChecked) interests.add(CHILD_JAVA)
        if (bind.checkKotlin2.isChecked) interests.add(CHILD_KOTLIN)
        //проверка на количество выбранных пунктов
        when (interests.size) {
            //вывод сообщения об ошибке при выборе недостаточного кокличества интересов
            0 -> {
                showToast("Нужно выбрать хотя-бы один пункт")
                clearCheck()
            }
            //переключение на следующий экран при выборе досточного количества пунктов
            in 1..9 -> {
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
        bind.checkAndroid2.isChecked = false
        bind.checkCSS2.isChecked = false
        bind.checkCpp2.isChecked = false
        bind.checkCsh2.isChecked = false
        bind.checkHtml2.isChecked = false
        bind.checkWeb2.isChecked = false
        bind.checkPython2.isChecked = false
        bind.checkJS2.isChecked = false
        bind.checkJava2.isChecked = false
        bind.checkKotlin2.isChecked = false
    }

    private fun addDB(){
        val randomString = getRandomString(10)
        val update = mutableMapOf<String, Any>()
        update[CHILD_EMAIL] = USER.email
        update[CHILD_NAME_PROJECT] = bind.editTextTextPersonName.text.toString()
        update[CHILD_ABOUT] = bind.editTextTextPersonName2.text.toString()
        REF_DATABASE_ROOT.child(NODE_PROJECT).child(randomString).updateChildren(update).addOnCompleteListener { task1 ->
            if (task1.isSuccessful) {
                showToast("Добавленно")
                for (interes in interests) REF_DATABASE_ROOT.child(NODE_PROJECT).child(randomString).child(interes).setValue("$interes ")
                replaceFragment(ProfileFragment(), false)
            }
        }
    }
}