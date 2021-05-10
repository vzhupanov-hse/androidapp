package com.example.androidapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidapp.R
import com.example.androidapp.databinding.FragmentProfileBinding
import com.example.androidapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var bind: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentSearchBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    //TODO Тут пишем код
    //TODO если нужно найти какой-то елемент нужно использовать bind.id

    }

}