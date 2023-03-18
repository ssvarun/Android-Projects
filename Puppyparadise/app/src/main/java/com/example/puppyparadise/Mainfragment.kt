package com.example.puppyparadise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.puppyparadise.databinding.FragmentMainfragmentBinding
import com.example.puppyparadise.relations.PetAndCategory
import kotlinx.coroutines.launch


class Mainfragment : Fragment() {
    lateinit var appdatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMainfragmentBinding>(inflater,R.layout.fragment_mainfragment,container,false)
         appdatabase= AppDatabase.getDatabase(requireContext())



         binding.loginbtn.setOnClickListener { view : View->
             val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.btn_animation)
              binding.loginbtn.startAnimation(animation)
             view.findNavController().navigate(MainfragmentDirections.actionMainfragmentToLoginFragment())
         }
        binding.registerbtn.setOnClickListener { view:View->
            val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.btn_animation)
            binding.registerbtn.startAnimation(animation)
            view.findNavController().navigate(MainfragmentDirections.actionMainfragmentToRegisterFragment())
        }

        return binding.root
    }


}