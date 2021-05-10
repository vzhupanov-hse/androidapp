package com.example.androidapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidapp.R
import com.example.androidapp.databinding.FragmentProjectsListBinding
import com.example.androidapp.db.*
import com.example.androidapp.model.ProjectModel
import com.example.androidapp.ui.adapter.ProjectAdapter
import com.example.androidapp.util.AppValueEventListener
import com.example.androidapp.util.getProjectModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class ProjectListFragment : Fragment() {

    private lateinit var bind: FragmentProjectsListBinding
    private var adapter: ProjectAdapter? = null
    private var refProject: DatabaseReference? = null
    private var projectListener: ValueEventListener? = null

    var list: MutableList<ProjectModel> = mutableListOf()
    private var listBackup: MutableList<ProjectModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View{
        bind = FragmentProjectsListBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.all -> {
                    refProject = REF_DATABASE_ROOT.child(NODE_PROJECT)
                    projectListener = AppValueEventListener { dataSnapshot ->
                        list = dataSnapshot.children.map { it.getProjectModel() }.toMutableList()
                        setAdapter(list)
                    }
                    projectListener?.let { refProject?.addValueEventListener(it) }
                        true
                }
                R.id.my_fav -> {

                    listBackup = checkExpiredGoods(list)
                    setAdapter(listBackup)

                    true
                }
                else -> false
            }
        }
    }

    fun checkExpiredGoods(list: MutableList<ProjectModel>): MutableList<ProjectModel> {
        val result = mutableListOf<ProjectModel>()
        for (i in 0 until list.size) {
            if (USER.interes.android == "Android"){
                val left = list[i].android
                if (left.startsWith("Android")) result.add(list[i])
            }
            if (USER.interes.java == "Java") {
                val left = list[i].java
                if (left.startsWith("Java")) result.add(list[i])
            }
            if (USER.interes.python == "Python") {
                val left = list[i].python
                if (left.startsWith("Python")) result.add(list[i])
            }
            if (USER.interes.cplus == "CPlus") {
                val left = list[i].cplus
                if (left.startsWith("CPlus")) result.add(list[i])
            }
            if (USER.interes.csharp == "CSharp") {
                val left = list[i].csharp
                if (left.startsWith("CSharp")) result.add(list[i])
            }
            if (USER.interes.kotlin == "Kotlin") {
                val left = list[i].kotlin
                if (left.startsWith("Kotlin")) result.add(list[i])
            }
            if (USER.interes.web == "Web") {
                val left = list[i].web
                if (left.startsWith("Web")) result.add(list[i])
            }
            if (USER.interes.javascript == "JavaScript") {
                val left = list[i].javascript
                if (left.startsWith("JavaScript")) result.add(list[i])
            }
            if (USER.interes.html == "HTML") {
                val left = list[i].html
                if (left.startsWith("HTML")) result.add(list[i])
            }
            if (USER.interes.css == "CSS") {
                val left = list[i].css
                if (left.startsWith("CSS")) result.add(list[i])
            }
        }
        //Удаление дубликатов
        val set: HashSet<ProjectModel> = HashSet(result)
        result.clear()
        result.addAll(set)
        //Возрат результата
        return result
    }

    private fun setAdapter(list: MutableList<ProjectModel>) {
        adapter = ProjectAdapter(list)
        bind.ItemProject.layoutManager = LinearLayoutManager((requireActivity()))
        bind.ItemProject.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        refProject = REF_DATABASE_ROOT.child(NODE_PROJECT)
        projectListener = AppValueEventListener { dataSnapshot ->
            list = dataSnapshot.children.map { it.getProjectModel() }.toMutableList()
            setAdapter(list)
        }
        projectListener?.let { refProject?.addValueEventListener(it) }

    }

    override fun onPause() {
        super.onPause()
        projectListener?.let { refProject?.removeEventListener(it) }
    }




}