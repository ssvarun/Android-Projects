package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
private val NO_BUZZ_PATTERN = longArrayOf(0)


class GameViewModel : ViewModel() {
    private val  _word = MutableLiveData<String>()
    val word : LiveData<String>
    get() = _word
    // The current score
    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int>
    get() = _score

    private val _eventgamefinished= MutableLiveData<Boolean>()
    val eventgamefinished :MutableLiveData<Boolean>
    get() = _eventgamefinished

    private val tcurrentime=MutableLiveData<Long>()
    val currentime :MutableLiveData<Long>
        get()=tcurrentime
    val currenttimestring = Transformations.map(tcurrentime){ time->
        DateUtils.formatElapsedTime(time)
    }

    private val _eventBuzz = MutableLiveData<BuzzType>()
    val eventBuzz: LiveData<BuzzType>
        get() = _eventBuzz

    enum class BuzzType(val pattern: LongArray) {
        CORRECT(CORRECT_BUZZ_PATTERN),
        GAME_OVER(GAME_OVER_BUZZ_PATTERN),
        COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
        NO_BUZZ(NO_BUZZ_PATTERN)
    }

    companion object
    {
        const val DONE = 0L
        const val ONE_SECOND = 1000L
        const val COUNTDOWN_TIME = 60000L
        private const val COUNTDOWN_PANIC_SECONDS = 10L
    }
    private var timer:CountDownTimer
    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>
    init {
        Log.i("GameViewmodel","Created!!")
        resetList()
        nextWord()
        _score.value=0
        _word.value="password"
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND)
        {
            override fun onTick(millisUntilFinished: Long) {
               tcurrentime.value=(millisUntilFinished/ ONE_SECOND)
                if (millisUntilFinished / ONE_SECOND <= COUNTDOWN_PANIC_SECONDS) {
                    _eventBuzz.value = BuzzType.COUNTDOWN_PANIC
                }
            }

            override fun onFinish() {
                tcurrentime.value= DONE
                _eventgamefinished.value=true
                _eventBuzz.value = BuzzType.GAME_OVER
            }
        }
        timer.start()
    }
     fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }
     fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            resetList()
        } else {
            _word.value = wordList.removeAt(0)
        }

    }
     fun onSkip() {
        _score.value=score.value?.minus(1)
        nextWord()
    }

     fun onCorrect() {
       _score.value=score.value?.plus(1)
         _eventBuzz.value = BuzzType.CORRECT
        nextWord()
    }
    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
    fun gamefinished()
    {
        _eventgamefinished.value=false
    }
    fun onBuzzComplete() {
        _eventBuzz.value = BuzzType.NO_BUZZ
    }

}