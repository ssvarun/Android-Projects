package com.example.puppyparadise.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.puppyparadise.Cart
import com.example.puppyparadise.Customer

data class CustomerAndCart(
    @Embedded val customer: Customer,  //It includes all the fields in customer to this class
    @Relation(
        parentColumn = "customerid",
        entityColumn = "customerid"
    )
    val cart: Cart

)
