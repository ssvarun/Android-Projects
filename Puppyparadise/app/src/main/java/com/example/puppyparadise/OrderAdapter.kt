package com.example.puppyparadise

import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(val context:Context,val orderlist:List<Order>) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() , Adapter{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.orderdisplay,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentitem = orderlist[position]
        holder.orderdate.text=currentitem.orderdate
        holder.amount.text=currentitem.orderamount.toString()
        holder.deliverydate.text=currentitem.deliverydate
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val orderdate:TextView
       val amount:TextView
       val deliverydate:TextView
       init {
           orderdate=itemView.findViewById(R.id.orderdatetxt)
           amount=itemView.findViewById(R.id.amounttxt)
           deliverydate=itemView.findViewById(R.id.deliverydatetxt)
       }
    }

    override fun getItemCount(): Int {
       return orderlist.size
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