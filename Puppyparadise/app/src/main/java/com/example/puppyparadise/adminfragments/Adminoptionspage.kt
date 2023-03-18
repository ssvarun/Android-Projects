package com.example.puppyparadise.adminfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.puppyparadise.R
import com.example.puppyparadise.databinding.FragmentAdminoptionspageBinding


class Adminoptionspage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val binding = DataBindingUtil.inflate<FragmentAdminoptionspageBinding>(inflater,R.layout.fragment_adminoptionspage,container,false)
         binding.adminpetaddbtn.setOnClickListener { view:View->
             view.findNavController().navigate(AdminoptionspageDirections.actionAdminoptionspageToAddPet())
         }
        binding.admincustomerbtn.setOnClickListener { view:View->
            view.findNavController().navigate(AdminoptionspageDirections.actionAdminoptionspageToAdmincustomerFragment())
        }
        return binding.root
    }

}