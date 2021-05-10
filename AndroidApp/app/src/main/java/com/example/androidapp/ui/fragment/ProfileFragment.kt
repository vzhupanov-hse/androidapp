package com.example.androidapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidapp.R
import com.example.androidapp.databinding.FragmentProfileBinding
import com.example.androidapp.db.*
import com.example.androidapp.model.UserModel
import com.example.androidapp.ui.fragment.auth.UserAddInfoFragment
import com.example.androidapp.util.replaceFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class ProfileFragment : Fragment() {

    private lateinit var bind: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentProfileBinding.inflate(inflater, container, false)
        return bind.root
    }

    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (USER.add_info == "no") replaceFragment(UserAddInfoFragment(), false)

        bind.textViewEmail.text = (getString(R.string.TextViewEmail, USER.email))
        bind.textViewName.text = (getString(R.string.TextViewName, USER.name))
        bind.textViewSurname.text = (getString(R.string.TextViewSurname, USER.surname))
        bind.textViewAbout.text = (getString(R.string.TextViewAbout, USER.about))

        if (USER.interes.android == "Android") bind.txtAndroid.visibility = View.VISIBLE
        if (USER.interes.java == "Java") bind.txtJava.visibility = View.VISIBLE
        if (USER.interes.python == "Python") bind.txtPython.visibility = View.VISIBLE
        if (USER.interes.cplus == "CPlus") bind.cplus.visibility = View.VISIBLE
        if (USER.interes.csharp == "CSharp") bind.txtCsharp.visibility = View.VISIBLE
        if (USER.interes.kotlin == "Kotlin") bind.txtKotlin.visibility = View.VISIBLE
        if (USER.interes.web == "Web") bind.txtWeb.visibility = View.VISIBLE
        if (USER.interes.javascript == "JavaScript") bind.txtJS.visibility = View.VISIBLE
        if (USER.interes.html == "HTML") bind.txtHtml.visibility = View.VISIBLE
        if (USER.interes.css == "CSS") bind.css.visibility = View.VISIBLE


    }
}