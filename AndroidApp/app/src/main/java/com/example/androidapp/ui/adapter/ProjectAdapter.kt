package com.example.androidapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapp.databinding.ItemProjectBinding
import com.example.androidapp.model.ProjectModel
import com.example.androidapp.model.UserModel


class ProjectAdapter(var list: MutableList<ProjectModel> = mutableListOf()
    ) :RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val bind = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectViewHolder(bind)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val item = list[position]
        holder.projectName.text = item.ProjectName
        holder.Author.text = "Автор: ${item.email}"
        holder.lng.text = "Технологии: ${item.android}${item.cplus}${item.csharp}${item.css}${item.web}${item.html}${item.java}${item.javascript}${item.kotlin}${item.python}"
        holder.about.text = "О проекте: ${item.about}"
    }

    class ProjectViewHolder(item: ItemProjectBinding) : RecyclerView.ViewHolder(item.root) {
        val Author: TextView = item.txtAuthor
        val projectName: TextView = item.txtProjectName
        val lng: TextView = item.txtLang
        val about: TextView = item.txtAbout
    }

    override fun getItemCount(): Int = list.size
}