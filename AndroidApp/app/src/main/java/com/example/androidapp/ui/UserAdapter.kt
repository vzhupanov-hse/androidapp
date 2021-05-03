package com.example.androidapp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapp.R
import com.example.androidapp.model.UserInteres
import kotlinx.android.synthetic.main.interes_item.view.*


class UserAdapter(
        var list: MutableList<UserInteres> = mutableListOf()
) :RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val androids: TextView = view.txtAndroid
        val cplus: TextView = view.cplus
        val css: TextView = view.css
        val csharp: TextView = view.txtCsharp
        val htmls: TextView = view.txtHtml
        val webs: TextView = view.txtWeb
        val python: TextView = view.txtPython
        val js: TextView = view.txtJS
        val javas: TextView = view.txtJava
        val kotlins: TextView = view.txtKotlin
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.interes_item, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = list[position]
        if (item.android.isEmpty()) holder.androids.visibility = View.GONE
        else holder.androids.text = item.android
        if (item.java.isEmpty()) holder.javas.visibility = View.GONE
        else holder.javas.text = item.java
        if (item.python.isEmpty()) holder.python.visibility = View.GONE
        else holder.python.text = item.python
        if (item.cplus.isEmpty()) holder.cplus.visibility = View.GONE
        else holder.cplus.text = item.cplus
        if (item.csharp.isEmpty()) holder.csharp.visibility = View.GONE
        else holder.csharp.text = item.csharp
        if (item.kotlin.isEmpty()) holder.kotlins.visibility = View.GONE
        else holder.kotlins.text = item.kotlin
        if (item.web.isEmpty()) holder.webs.visibility = View.GONE
        else holder.webs.text = item.web
        if (item.javascript.isEmpty()) holder.js.visibility = View.GONE
        else holder.js.text = item.javascript
        if (item.html.isEmpty()) holder.htmls.visibility = View.GONE
        else holder.htmls.text = item.html
        if (item.css.isEmpty()) holder.css.visibility = View.GONE
        else holder.css.text = item.css

     }
}