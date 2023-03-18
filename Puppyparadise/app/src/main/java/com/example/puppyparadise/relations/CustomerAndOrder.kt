package com.example.puppyparadise.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.puppyparadise.Customer
import com.example.puppyparadise.Order

data class CustomerAndOrder(
    @Embedded val customer: Customer,
    @Relation(
        parentColumn = "customerid",
        entityColumn = "customerid"
    )
    val orderlist: List<Order>
)
