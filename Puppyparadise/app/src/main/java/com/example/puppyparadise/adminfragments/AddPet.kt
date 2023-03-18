package com.example.puppyparadise.adminfragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.puppyparadise.AppDatabase
import com.example.puppyparadise.PetDetails
import com.example.puppyparadise.R
import com.example.puppyparadise.databinding.FragmentAddPetBinding
import kotlinx.coroutines.launch

class AddPet : Fragment() {
    var imageUri: Uri? = null
    lateinit var imageview:ImageView
    private lateinit var appDatabase: AppDatabase
 override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding = DataBindingUtil.inflate<FragmentAddPetBinding>(inflater,R.layout.fragment_add_pet,container,false)
     appDatabase= AppDatabase.getDatabase(requireContext())
//     imageview = binding.petimagetoadd
//       binding.petimagetoadd.setOnClickListener {
//          pickimage()
//       }
     binding.addthispetbtn.setOnClickListener { view:View->
        val breed = binding.breedtext.text.toString()
         val food = binding.foodtext.text.toString()
         val category = binding.categorytext.text.toString()
         val price = binding.pricetext.text.toString()
         val url = binding.urltext.text.toString()
         if(breed.isNotEmpty() && food.isNotEmpty() && category.isNotEmpty() && price.isNotEmpty() && url!=null)
         {
             lifecycleScope.launch{
                 val newpet = PetDetails(null,category.toInt(),url,price.toDouble(),food,breed)
                 appDatabase.customerdao().insertpetdetails(newpet)
                 view.findNavController().navigate(AddPetDirections.actionAddPetToAdminoptionspage())
             }
         }
         else
         {
             Toast.makeText(requireContext(),"Please fill all the details",Toast.LENGTH_SHORT).show()
         }
     }
     return binding.root
 }

//    private fun pickimage()
//    {
//        val intent = Intent()
//        intent.type = "image/*"
//        intent.action = Intent.ACTION_GET_CONTENT
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
//    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when { requestCode == 1 && resultCode == Activity.RESULT_OK -> {
//                imageUri = data!!.data
//                imageview.setImageURI(imageUri)
////            val uriPathHelper = URIPathHelper()
////            val filePath = uriPathHelper.getPath(requireContext(), imageUri!!)
//
//            }
//        }
    }
