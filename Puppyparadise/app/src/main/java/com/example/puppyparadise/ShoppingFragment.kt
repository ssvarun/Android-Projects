package com.example.puppyparadise

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyparadise.databinding.FragmentShoppingBinding
import com.example.puppyparadise.databinding.FragmentShoppingBindingImpl
import kotlinx.coroutines.launch


class ShoppingFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var appdatabase:AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentShoppingBinding>(inflater,R.layout.fragment_shopping,container,false)
        val username = arguments?.getString("email")
        appdatabase= AppDatabase.getDatabase(requireContext())

        recyclerView=binding.recyclerview
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        lifecycleScope.launch {
            val petlist: List<PetDetails> = appdatabase.customerdao().getallpetslist()
            val customerid=appdatabase.customerdao().returncustomerid(username.toString())

//            val permissionCheck = ActivityCompat.requestPermissions(requireActivity(),Manifest.permission.R)
            recyclerView.adapter= MyAdapter(requireContext(),petlist,customerid)
        }


        return binding.root
    }

}