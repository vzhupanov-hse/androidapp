package com.example.androidapp.ui.fragment.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidapp.databinding.FragmentRegBinding
import com.example.androidapp.db.*
import com.example.androidapp.util.replaceFragment
import com.example.androidapp.util.showToast

class RegFragment : Fragment() {

    private lateinit var bind: FragmentRegBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentRegBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.StartRegistration.setOnClickListener { registration() }
    }

    //регистрация пользователя в базе данных
    private fun registration(){
        //проверка заполнения полей
        if (bind.inputE.text.isEmpty() || bind.InputName.text.isEmpty() ||
            bind.InputSurname.text.isEmpty() || bind.inputPass.text.isEmpty() ||
            bind.repeatPass.text.toString() != bind.inputPass.text.toString()) {
            showToast("Все поля должны быть заполнены или пароли не совпадают")
        }else{
            //добавление пользователя в базу данных и заполнение его полей
            AUTH.createUserWithEmailAndPassword(
                bind.inputE.text?.toString()?.trim() ?: "",
                bind.inputPass.text?.toString()?.trim() ?: ""
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    val uid = AUTH.currentUser?.uid.toString()
                    val dateMap = mutableMapOf<String, Any>()
                    dateMap[CHILD_ID] = uid
                    dateMap[CHILD_EMAIL] = bind.inputE.text.toString().trim()
                    dateMap[CHILD_NAME] = bind.InputName.text.toString().trim()
                    dateMap[CHILD_SURNAME] = bind.InputSurname.text.toString().trim()
                    dateMap[CHILD_ADD_INFO] = "no"
                    REF_DATABASE_ROOT.child(NODE_USERS).child(uid)
                        .updateChildren(dateMap).addOnCompleteListener { task2 ->
                            if (task2.isSuccessful) {
                                showToast("Вы успешно зарегистрировались!")
                                replaceFragment(UserAddInfoFragment(), false)
                            } else showToast(task2.exception?.message.toString())
                        }
                } else showToast(it.exception?.message.toString())
            }
        }
    }
}