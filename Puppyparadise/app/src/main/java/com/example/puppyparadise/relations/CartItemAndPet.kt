package com.example.puppyparadise.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.puppyparadise.Cart
import com.example.puppyparadise.Order
import com.example.puppyparadise.PetDetails

data class CartItemAndPet(
    @Embedded val cart: Cart,
    @Relation(
        parentColumn = "Petid",
        entityColumn = "Petid"
    )
    val petdetail: PetDetails
)