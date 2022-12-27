package com.furkancosgun.viewmodelyaps

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    var sonuc = "0"

    fun topla(s1: String, s2: String) {
        val sayi1 = s1.toInt()
        val sayi2 = s2.toInt()
        sonuc = (sayi1 + sayi2).toString()
    }
}