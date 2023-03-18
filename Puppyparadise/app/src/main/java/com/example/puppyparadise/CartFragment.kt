package com.example.puppyparadise

import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyparadise.databinding.FragmentCartBinding
import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class CartFragment : Fragment() {
    lateinit var appdatabase:AppDatabase
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CartAdapter
    var checkorder:Boolean=true
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentCartBinding>(inflater,R.layout.fragment_cart,container,false)
        recyclerView=binding.cartrecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(requireContext())

        var totalprice = 0
        val customerid = arguments?.getInt("customerid")
//        val quantity = arguments?.getInt("quantity")
        appdatabase= AppDatabase.getDatabase(requireContext())

        lifecycleScope.launch {
            val cartid = appdatabase.customerdao().getcartid(customerid!!)
            val cartlist: List<CartItems> = appdatabase.customerdao().getallcartitems(cartid)

            if(cartlist.isEmpty())
            {
                binding.emptycartanim.visibility = View.VISIBLE
                checkorder=false
            }
            else {
                checkorder=true
                val cartitemlist: ArrayList<PetDetails> = ArrayList()
                coroutineScope {
                    val finish = async {
                        for (i in cartlist) {
                            cartitemlist.add(appdatabase.customerdao().getpetdetailswithid(i.Petid))
                            totalprice += appdatabase.customerdao()
                                .getpetdetailswithid(i.Petid).petrate.toInt()
                        }
                    }
                    finish.await()
                    adapter = CartAdapter(requireContext(), cartitemlist!!)
                    recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        }

            binding.placeorderbtn.setOnClickListener { view:View->
                val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.btn_animation)
                binding.placeorderbtn.startAnimation(animation)
                if (checkorder)
                {
                    lifecycleScope.launch {
                        val cartid = appdatabase.customerdao().getcartid(customerid!!)
                        val current = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                        val formatted = current.format(formatter)
                        val deliveryday = (1..10).shuffled().last().toString()
                        val order = Order(null,customerid,cartid,deliveryday+" days",formatted,totalprice)
                        appdatabase.customerdao().insertorder(order)
                        appdatabase.customerdao().deleteallwithcartid(cartid)
                        view.findNavController().navigate(R.id.action_cartFragment_to_completeorderFragment)
                    }
                }
                else
                {
                    Toast.makeText(requireContext(),"Add any pet to Order",Toast.LENGTH_SHORT).show()
                }
            }

        return binding.root
    }
}