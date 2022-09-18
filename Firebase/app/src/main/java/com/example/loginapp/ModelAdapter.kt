package com.example.loginapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ModelAdapter(private var Modellist:ArrayList<Model>) :
    RecyclerView.Adapter<ModelAdapter.ModelViewHolder>() {
    class ModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val nametxt:TextView=itemView.findViewById(R.id.cardnametxt)
        val emailtxt:TextView=itemView.findViewById(R.id.cardemailtxt)
        val passtxt:TextView=itemView.findViewById(R.id.cardpasstxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.displaystyle,parent,false)
        return ModelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        val model=Modellist[position]
        holder.emailtxt.text=model.email.toString()
        holder.nametxt.text=model.name.toString()
        holder.passtxt.text=model.password.toString()
    }

    override fun getItemCount(): Int {
        return Modellist.size
    }
}