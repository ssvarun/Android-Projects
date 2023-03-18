package com.example.puppyparadise.adminfragments

import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyparadise.Customer
import com.example.puppyparadise.R

class AdminCustomerAdpater(val context:Context,val customerslist:List<Customer>) : RecyclerView.Adapter<AdminCustomerAdpater.ViewHolder>(), Adapter
{
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val customername:TextView = itemView.findViewById(R.id.customernametxt)
        val customerpincode:TextView=itemView.findViewById(R.id.customerpincodetxt)
        val deletebtn:ImageView=itemView.findViewById(R.id.deletebtn)
        init {
            customername.text = "Name : Varun"
            customerpincode.text = "Name : 577004"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.admincustomer_design,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentitem = customerslist[position]
        holder.customername.text=currentitem.customername
        holder.customerpincode.text=currentitem.customerpincode.toString()
        holder.deletebtn.setOnClickListener {
            Toast.makeText(context,currentitem.customerid.toString(),Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
       return customerslist.size
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