package com.example.androidapp.ui.fragment.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidapp.databinding.FragmentAuthBinding
import com.example.androidapp.db.*
import com.example.androidapp.ui.fragment.ProfileFragment
import com.example.androidapp.util.replaceFragment
import com.example.androidapp.util.showToast

class AuthFragment : Fragment() {

    private lateinit var bind: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentAuthBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.LoginButton.setOnClickListener { auth() }
        bind.RegistrationButton.setOnClickListener { replaceFragment(RegFragment()) }
    }

    //проверка данных авторизации пользователя в базе данных
    private fun auth() {
        AUTH.signInWithEmailAndPassword(
            bind.inputEm.text?.toString()?.trim() ?: "",
            bind.inputPassw.text?.toString()?.trim() ?: ""
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_EMAIL] = bind.inputEm.text.toString().trim()
                REF_DATABASE_ROOT.child(NODE_USERS).child(uid)
                    .updateChildren(dateMap).addOnCompleteListener { task2 ->
                        if (task2.isSuccessful) {
                            replaceFragment(ProfileFragment(), false)
                            showToast("Добро пожаловать")
                        } else showToast(task2.exception?.localizedMessage.toString())
                    }
            } else showToast(it.exception?.localizedMessage.toString())
        }
    }
}