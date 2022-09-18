package com.example.android.guesstheword.screens.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class scoreviewmodelfactory(private val finalScore: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Scoreviewmodel::class.java)) {
            return Scoreviewmodel(finalScore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}