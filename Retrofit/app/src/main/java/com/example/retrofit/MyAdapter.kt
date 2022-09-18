package com.example.retrofit

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(val context: Context, val userlist:List<Article>) :
    RecyclerView.Adapter<MyAdapter.Viewholder>() {
    class Viewholder(itemview:View):RecyclerView.ViewHolder(itemview)
    {
       val image:ImageView
       val title:TextView
       val description:TextView
       init {
           image=itemview.findViewById(R.id.image)
           title=itemview.findViewById(R.id.title)
           description=itemview.findViewById(R.id.des)
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(context).inflate(R.layout.display_style,parent,false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.title.text=userlist[position].title
        holder.description.text=userlist[position].description
        // to download image using url and to set it to imageview
        Glide.with(context).load(userlist[position].urlToImage).into(holder.image)

        holder.itemView.setOnClickListener{
             val intent= Intent(context,detailedactivity::class.java)
            intent.putExtra("url",userlist[position].url)
            context.startActivity(intent)
//            Toast.makeText(context,userlist[position].title,Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return userlist.size
    }
}