package com.example.puppyparadise

import android.content.Context
import android.content.Intent
import android.database.DataSetObserver
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class MyAdapter(val context: Context,val petslist:List<PetDetails>,val customerid:Int) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>(), Adapter {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pet_display,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val currentitem=petslist[position]
        CoroutineScope(Dispatchers.Main).launch {
            coroutineScope {
            val finish = async {
                Picasso.get().load(currentitem.petimage).into(holder.petImage)
            }
            finish.await()
            }
    }
         holder.breed.text=currentitem.petbreed
         holder.rate.text=currentitem.petrate.toString()
         holder.food.text=currentitem.petfood
//        holder.petImage.setImageBitmap(BitmapFactory.decodeFile(currentitem.petimage))
//         holder.petImage.setImageURI(currentitem.petimage.toUri())
         holder.itemView.setOnClickListener{ view:View->
            val bundle = Bundle()
            bundle.putInt("Petid", currentitem.Petid!!)
            bundle.putInt("customerid",customerid)
            view.findNavController().navigate(R.id.action_shoppingFragment_to_petDetailedFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
        return petslist.size
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val petImage: ImageView
        val breed: TextView
        val food: TextView
        val rate:TextView
        init {
            petImage=itemView.findViewById(R.id.petimageview)
            breed=itemView.findViewById(R.id.petbreedtxt)
            food=itemView.findViewById(R.id.petfoodtxt)
            rate=itemView.findViewById(R.id.petratetxt)
        }
    }

    override fun registerDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun unregisterDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }


}