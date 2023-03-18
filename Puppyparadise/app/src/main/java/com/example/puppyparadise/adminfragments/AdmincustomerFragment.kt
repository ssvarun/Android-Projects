package com.example.puppyparadise.adminfragments

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
import com.example.puppyparadise.AppDatabase
import com.example.puppyparadise.DataBinderMapperImpl
import com.example.puppyparadise.R
import com.example.puppyparadise.databinding.FragmentAdmincustomerBinding
import com.example.puppyparadise.databinding.FragmentAdmincustomerBindingImpl
import kotlinx.coroutines.launch

class AdmincustomerFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adpater: AdminCustomerAdpater
    lateinit var appDatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding = DataBindingUtil.inflate<FragmentAdmincustomerBinding>(inflater,R.layout.fragment_admincustomer,container,false)
        appDatabase= AppDatabase.getDatabase(requireContext())
        recyclerView=binding.adminCustomerRecyclerview
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        lifecycleScope.launch {
            val customerlist = appDatabase.customerdao().getallcustomerslist()
            Log.d("adminfragment",customerlist.toString())
            adpater= AdminCustomerAdpater(requireContext(),customerlist)
            recyclerView.adapter=adpater
            adpater.notifyDataSetChanged()
        }
        return binding.root
    }

}