package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Scoreviewmodel(finalscore : Int) : ViewModel() {
    private val _evetnfinished = MutableLiveData<Boolean>()
    val eventfinshed : LiveData<Boolean>
    get() = _evetnfinished
    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int>
    get() = _score
    init {
    Log.i("Scoreviewmodel","Viewmodel is called")
        _score.value=finalscore
        _evetnfinished.value=false
    }
  fun onPlayAgain()
  {
      _evetnfinished.value = true
  }
    fun onPlayAgainComplete()
    {
        _evetnfinished.value = false
    }
}