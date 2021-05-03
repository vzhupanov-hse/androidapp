package com.example.androidapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidapp.R
import com.example.androidapp.db.*
import com.example.androidapp.model.UserInteres
import com.example.androidapp.util.AppValueEventListener
import com.example.androidapp.util.getUserInteresModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    private var adapter: UserAdapter? = null
    private var refBarcodes: DatabaseReference? = null
    private var barcodesListener: ValueEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        textViewEmail.text = (getString(R.string.TextViewEmail, USER.email))
        textView_name.text = (getString(R.string.TextViewName, USER.name))
        textViewSurname.text = (getString(R.string.TextViewSurname, USER.surname))
        textViewAbout.text = (getString(R.string.TextViewAbout, USER.about))

    }

    fun setAdapter(bonus: MutableList<UserInteres>) {
        adapter = UserAdapter(bonus)
        item_user.layoutManager = LinearLayoutManager(this)
        item_user.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        refBarcodes = REF_DATABASE_ROOT.child(NODE_INTERES).child(CURRENT_UID)
        barcodesListener = AppValueEventListener { dataSnapshot ->
            val list = dataSnapshot.children.map { it.getValue(UserInteres::class.java) ?: UserInteres() }.toMutableList()
            setAdapter(list)
            println(list)
        }
        barcodesListener?.let { refBarcodes?.addValueEventListener(it) }
    }

    override fun onPause() {
        super.onPause()
        barcodesListener?.let { refBarcodes?.removeEventListener(it) }
    }

    override fun onBackPressed() {
            Snackbar.make(findViewById(R.id.item_user), "Выйти?", Snackbar.LENGTH_LONG)
                .setAction("Да") { finish() }.show()
    }
}