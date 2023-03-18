package com.example.puppyparadise

import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CartAdapter(val context:Context,val cartitemlist:ArrayList<PetDetails>) : RecyclerView.Adapter<CartAdapter.ViewHolder>(),Adapter
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cart_display,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentitem= cartitemlist[position]
        holder.cartbreddtxt.text=currentitem.petbreed
        Picasso.get().load(currentitem.petimage).into(holder.cartpetimage)
    }

    override fun getItemCount(): Int {
        return cartitemlist.size
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
    {
        val cartpetimage:ImageView
        val cartbreddtxt:TextView
        init {
            cartpetimage=itemView.findViewById(R.id.cartimageview)
            cartbreddtxt=itemView.findViewById(R.id.breedtxtincart)
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