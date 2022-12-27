package com.furkancosgun.livedatakullanm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    var sonuc = MutableLiveData<String>()

    init {
        sonuc = MutableLiveData("0")
    }

    fun addOne() {
        sonuc.value = (sonuc.value!!.toInt() + 1).toString()
    }

    fun subtractOne() {
        sonuc.value = (sonuc.value!!.toInt() - 1).toString()
    }
}