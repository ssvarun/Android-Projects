package com.example.puppyparadise

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.example.puppyparadise.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {
    lateinit var appdatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater,R.layout.fragment_login,container,false)

        val animation = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation

        appdatabase= AppDatabase.getDatabase(requireContext())
        binding.realloginbtn.setOnClickListener { view:View->
            val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.btn_animation)
            binding.realloginbtn.startAnimation(animation)
            val email = binding.loginemail.text
            val password = binding.loginpassword.text
            lifecycleScope.launch {
                 val correctpassword = appdatabase.customerdao().getpasswordofemail(email.toString())
                 if(email.isNotEmpty() && password.isNotEmpty())
                 {
                     if(password.toString()==correctpassword )
                     {
                         val bundle = Bundle()
                         bundle.putString("email", email.toString())
                         bundle.putString("password",password.toString())
                         val extras = FragmentNavigatorExtras(binding.lottieAnimationView to "large_image")
                         view.findNavController().navigate(R.id.action_loginFragment_to_profileFragment,bundle,null,extras)
                     }
                     else
                     {
                         Toast.makeText(requireContext(),"Wrong Credentials",Toast.LENGTH_SHORT).show()
                     }
                 }
                else
                {
                    Toast.makeText(requireContext(),"Enter all the Credentials",Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }


}