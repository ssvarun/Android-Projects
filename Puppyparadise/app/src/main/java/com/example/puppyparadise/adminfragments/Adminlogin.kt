package com.example.puppyparadise.adminfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.puppyparadise.R
import com.example.puppyparadise.databinding.FragmentAdminloginBinding
import com.example.puppyparadise.databinding.FragmentAdminloginBindingImpl

class Adminlogin : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAdminloginBinding>(inflater,R.layout.fragment_adminlogin,container,false)
        val adminemail = binding.adminemailorno.text
        val password = binding.adminpass.text
        binding.adminloginbtn.setOnClickListener { view:View->
            if(adminemail.isNotEmpty() && password.isNotEmpty())
            {
                if(adminemail.toString()=="ssvarunlm10@gmail.com" && password.toString()=="Smvs")
                {
                    view.findNavController().navigate(AdminloginDirections.actionAdminloginToAdminoptionspage())
                }
                else
                {
                    Toast.makeText(requireContext(),"Wrong Credentials", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(requireContext(),"Enter all the Credentials",Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

}