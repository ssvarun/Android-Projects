package com.example.puppyparadise

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
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
import com.example.puppyparadise.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch


class RegisterFragment : Fragment() {
    lateinit var appdatabase: AppDatabase
    private val mToastDuration = 7000
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(
            inflater,
            R.layout.fragment_register,
            container,
            false
        )
        appdatabase = AppDatabase.getDatabase(requireContext())
        binding.realregisterbtn.setOnClickListener { view: View ->
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_animation)
            binding.realregisterbtn.startAnimation(animation)
            val name = binding.name.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val address = binding.address.text.toString()
            val phonenumber = binding.phonenumber.text.toString()
            val pincode = binding.pincode.text.toString()
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && address.isNotEmpty() && phonenumber.isNotEmpty() && pincode.isNotEmpty()) {
                lifecycleScope.launch {
                    val length= appdatabase.customerdao().checkemail(email)
                    if (length!=null) {
                        val toast = Toast.makeText(
                            requireContext(),
                            "Email Entered already exists !!",
                            Toast.LENGTH_SHORT)
                        mDisplayToast(toast)
                    }
                    else if (TextUtils.isDigitsOnly(name))
                    {
                        val toast = Toast.makeText(
                            requireContext(),
                            "Name should be alphanumeric",
                            Toast.LENGTH_SHORT)
                        mDisplayToast(toast)
                    }
                    else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            val toast = Toast.makeText(
                                requireContext(),
                                "Email a valid email",
                                Toast.LENGTH_SHORT)
                            mDisplayToast(toast)
                        }
                        else if (password.length <= 5) {
                                val toast = Toast.makeText(
                                    requireContext(),
                                    "Enter a strong password",
                                    Toast.LENGTH_SHORT)
                                mDisplayToast(toast)
                            }
                            else if (phonenumber.length!=10) {
                                val toast = Toast.makeText(
                                    requireContext(),
                                    "Enter a valid Phone Number",
                                    Toast.LENGTH_SHORT)
                                mDisplayToast(toast)
                            }
                            else if (pincode.length!=6) {
                                val toast = Toast.makeText(
                                    requireContext(),
                                    "Enter a valid Pin Code",
                                    Toast.LENGTH_SHORT)
                                mDisplayToast(toast)
                            }
                            else
                             {
                                lifecycleScope.launch {
                                    appdatabase.customerdao().insertcustomer(
                                        Customer(
                                            null,
                                            name,
                                            email,
                                            password,
                                            address,
                                            phonenumber.toLong(),
                                            pincode.toInt()
                                        )
                                    )

                                }
                                 val extras = FragmentNavigatorExtras(binding.realregisterbtn to "small_image")
                                view.findNavController()
                                    .navigate(R.id.action_registerFragment_to_loginFragment,null,null,extras)
                            }
                        }
                    }
            else {
                val toast = Toast.makeText(
                    requireContext(),
                    "Enter all the Credentials",
                    Toast.LENGTH_SHORT
                )
                mDisplayToast(toast)
            }
        }
        return binding.root
    }

    private fun mDisplayToast(toast: Toast) {
        Thread {
            for (i in 1..mToastDuration / 2000) {
                toast.show()
                Thread.sleep(2000)
                toast.cancel()
            }
        }.start()
    }
}