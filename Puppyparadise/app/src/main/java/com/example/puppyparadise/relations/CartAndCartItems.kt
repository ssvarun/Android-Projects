package com.example.puppyparadise.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.puppyparadise.Cart
import com.example.puppyparadise.CartItems
import com.example.puppyparadise.PetDetails

data class CartAndCartItems(
    @Embedded val cart: Cart,
    @Relation(
        parentColumn = "cartid",
        entityColumn = "cartid"
    )
    val cartitemlist:List<CartItems>)
