package com.example.puppyparadise

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyparadise.databinding.FragmentOrderBinding
import com.example.puppyparadise.databinding.FragmentProfileBinding
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class OrderFragment : Fragment() {
    lateinit var appdatabase:AppDatabase
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: OrderAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentOrderBinding>(
            inflater,
            R.layout.fragment_order,
            container,
            false
        )
        appdatabase = AppDatabase.getDatabase(requireContext())
        val username = arguments?.getString("email")
        recyclerView = binding.orderrecyclerview
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launch {
            val customerid = appdatabase.customerdao().returncustomerid(username.toString())
            Log.d("Order fragment",customerid.toString())
//            val cartid = appdatabase.customerdao().getcartid(customerid)
            val orderlist = appdatabase.customerdao().getorderdetailseithid(customerid)
            if(orderlist.isEmpty())
            {
                binding.noorderanim.visibility=View.VISIBLE
            }
            else {
                Log.d("Order fragment", orderlist.toString())
                adapter = OrderAdapter(requireContext(), orderlist)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

        }

        return binding.root
    }

}