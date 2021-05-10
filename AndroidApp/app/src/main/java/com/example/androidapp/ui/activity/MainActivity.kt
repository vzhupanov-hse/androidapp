package com.example.androidapp.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidapp.R
import com.example.androidapp.databinding.ActivityMainBinding
import com.example.androidapp.db.AUTH
import com.example.androidapp.ui.fragment.AddProjectFragment
import com.example.androidapp.ui.fragment.auth.AuthFragment
import com.example.androidapp.ui.fragment.ProfileFragment
import com.example.androidapp.ui.fragment.ProjectListFragment
import com.example.androidapp.util.APP_ACTIVITY
import com.example.androidapp.util.initFirebase
import com.example.androidapp.util.initUser
import com.example.androidapp.util.replaceFragment
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)

        APP_ACTIVITY = this
        //инициализация базы данных и текущего пользователя из базы данных
        initFirebase()
        if (AUTH.currentUser != null)
            initUser {
                initBottomMenu()
                replaceFragment(ProfileFragment(), false)
            }
        else replaceFragment(AuthFragment(), false)
    }

    override fun onBackPressed() {
        Snackbar.make(findViewById(R.id.MainContainer), "Выйти?", Snackbar.LENGTH_LONG)
            .setAction("Да") { finish() }.show()
    }


    private fun initBottomMenu(){
        bind.bnMain.visibility = View.VISIBLE
        bind.bnMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.ic_rec -> {
                    replaceFragment(ProjectListFragment())
                    true
                }
                R.id.ic_add -> {
                    replaceFragment(AddProjectFragment())
                    true
                }
                R.id.ic_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }
}