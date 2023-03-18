package com.example.puppyparadise

import android.app.AlertDialog
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.puppyparadise.databinding.FragmentLoginBinding
import com.example.puppyparadise.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    lateinit var appdatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val username = arguments?.getString("email")
        val password = arguments?.getString("password")


        val animation = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation

        appdatabase= AppDatabase.getDatabase(requireContext())
        val binding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater,R.layout.fragment_profile,container,false)

       lifecycleScope.launch {
           val customerid = appdatabase.customerdao().returncustomerid(username!!)
           val check = appdatabase.customerdao().iscartexists(customerid)
           if (!check) {
               val cart = Cart(null, customerid!!)
               appdatabase.customerdao().insertcart(cart)
           }
       }


        lifecycleScope.launch {
            val customerprofile = appdatabase.customerdao().getcustomerprofiledescrition(username.toString())
            binding.profilename.text="Name : "+customerprofile.customername
            binding.profileaddress.text="Address : "+customerprofile.customeradress
            binding.profilephone.text="Mobile Number : "+customerprofile.customernumber
            val puppypoints = (10..200).shuffled().last().toString()
            binding.puppypoints.text="Puppy Points : "+puppypoints
        }
        binding.profileorderbtn.setOnClickListener { view:View->
            val bundle = Bundle()
            bundle.putString("email",username)
            view.findNavController().navigate(R.id.action_profileFragment_to_orderFragment,bundle)
        }
        binding.profileshopbtn.setOnClickListener { view:View->
            val bundle = Bundle()
            bundle.putString("email",username)
            view.findNavController().navigate(R.id.action_profileFragment_to_shoppingFragment,bundle)
        }
        binding.gotocartprofilebtn.setOnClickListener { view:View->
            lifecycleScope.launch {
                val bundle = Bundle()
                val customerid = appdatabase.customerdao().returncustomerid(username!!)
                bundle.putInt("customerid",customerid)
                view.findNavController().navigate(R.id.action_profileFragment_to_cartFragment,bundle)
            }

        }

        binding.editprofilebtn.setOnClickListener {
            val alertdialogview = LayoutInflater.from(requireContext()).inflate(R.layout.alert_layout,null)
            val dialogbuilder = AlertDialog.Builder(requireContext()).setView(alertdialogview).setTitle("Update Your Profile")
            val alertDialog = dialogbuilder.show()
            alertDialog!!.window!!.attributes.windowAnimations = R.style.DialogFragmentAnimation

            alertdialogview.findViewById<Button>(R.id.alertupdate).setOnClickListener {
                lifecycleScope.launch {
                    val name =  alertdialogview.findViewById<EditText>(R.id.alertname).text.toString()
                    val adress = alertdialogview.findViewById<EditText>(R.id.alertadress).text.toString()
                    val phno = alertdialogview.findViewById<EditText>(R.id.alertphno).text.toString()
                    val pincode = alertdialogview.findViewById<EditText>(R.id.alertpin).text.toString()
                    if(name.isNotEmpty() && adress.isNotEmpty() && phno.isNotEmpty() && pincode.isNotEmpty()) {
                        val customerid = appdatabase.customerdao().returncustomerid(username!!)
                        appdatabase.customerdao().updatecustomerdetails(
                            customerid,
                            name,
                            adress,
                            phno.toInt(),
                            pincode.toInt()
                        )
                        alertDialog.dismiss()
                        binding.profilename.text = "Name : " + name
                        binding.profileaddress.text = "Address : " + adress
                        binding.profilephone.text = "Mobile Number : " + phno
                    }
                    else
                    {
                        Toast.makeText(requireContext(),"Enter all details to update",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            alertdialogview.findViewById<Button>(R.id.alertcancel).setOnClickListener{
                alertDialog.dismiss()
            }
        }

        return binding.root
    }

}