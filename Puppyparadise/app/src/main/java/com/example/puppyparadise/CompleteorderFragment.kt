package com.example.puppyparadise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.puppyparadise.databinding.FragmentCompleteorderBinding
import com.example.puppyparadise.databinding.FragmentProfileBinding


class CompleteorderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCompleteorderBinding>(inflater,R.layout.fragment_completeorder,container,false)
        return binding.root
    }

}