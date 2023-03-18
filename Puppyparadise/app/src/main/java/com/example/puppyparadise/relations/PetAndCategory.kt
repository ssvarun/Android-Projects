package com.example.puppyparadise.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.puppyparadise.Category
import com.example.puppyparadise.PetDetails
import java.util.*

data class PetAndCategory(
    @Embedded val category: Category,
    @Relation
        (
        parentColumn = "categoryid",
        entityColumn = "categoryid"
        )
    val petlist:List<PetDetails>
)
