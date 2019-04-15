package com.example.communicationfragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val liveCounter = MutableLiveData<Int>()

}