package com.example.puppyparadise

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.puppyparadise.databinding.FragmentPetDetailedBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class PetDetailedFragment : Fragment() {
    lateinit var appdatabase:AppDatabase
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding = DataBindingUtil.inflate<FragmentPetDetailedBinding>(inflater,R.layout.fragment_pet_detailed,container,false)
       val petid = arguments?.getInt("Petid")
        val customerid=arguments?.getInt("customerid")
        Log.i("iii",customerid.toString())
        appdatabase= AppDatabase.getDatabase(requireContext())
        lifecycleScope.launch {
            val petlist = appdatabase.customerdao().getpetdetailswithid(petid!!)
            binding.detailedbreedtxt.text = "Breed : " + petlist.petbreed
            binding.detailedfoodtxt.text = "Food : " + petlist.petfood
            binding.detailedratetxt.text = "Price : " + petlist.petrate.toString()
            Picasso.get().load(petlist.petimage).into(binding.imageView)
//            Glide.with(context).load(petlist.petimage).into(binding.imageView)
        }
        var totalprice=0
        var quantity = 1
            binding.quantitytxt.text=quantity.toString()
            binding.qaddbtn.setOnClickListener {
                quantity++
                binding.quantitytxt.text=quantity.toString()
            }
            binding.qsubbtn.setOnClickListener {
                if(quantity>1)
                {   quantity-- }
                binding.quantitytxt.text=quantity.toString()
            }


        binding.addtocartbtton.setOnClickListener { view:View->
            val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.btn_animation)
            binding.addtocartbtton.startAnimation(animation)
            lifecycleScope.launch {
                val cartid = appdatabase.customerdao().getcartid(customerid!!)
                val caritem = CartItems(null,cartid,petid!!,quantity)
                appdatabase.customerdao().insertcartitem(caritem)
            }

        }
        binding.gotocartbtn.setOnClickListener {view:View->
            val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.btn_animation)
            binding.addtocartbtton.startAnimation(animation)
            val bundle = Bundle()
            if (customerid != null) {
                bundle.putInt("customerid",customerid)
            }
            view.findNavController().navigate(R.id.action_petDetailedFragment_to_cartFragment,bundle)
        }


        return binding.root
    }
}