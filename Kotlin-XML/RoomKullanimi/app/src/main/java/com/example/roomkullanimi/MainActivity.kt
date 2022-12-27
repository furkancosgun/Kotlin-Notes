package com.example.roomkullanimi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var vt:Veritabani
    private lateinit var kdao:KisilerDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vt = Veritabani.veritabaniErisim(this)!!
        kdao = vt.getKisilerDao()

        //ekle()
        //guncelle()
        //sil()
        //kisileriYukle()
        //rasgele()
        //ara()
        //getir()
        kontrol()
    }

    fun kisileriYukle(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val gelenListe = kdao.tumKisiler()

            for(k in gelenListe){
                Log.e("Kişi id",k.kisi_id.toString())
                Log.e("Kişi ad",k.kisi_ad)
                Log.e("Kişi yaş",k.kisi_yas.toString())
            }
        }
    }

    fun ekle(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val yeniKisi = Kisiler(0,"Ahmet",40)
            kdao.kisiEkle(yeniKisi)
        }
    }

    fun guncelle(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val guncellenenKisi = Kisiler(3,"Yeni Ahmet",50)
            kdao.kisiGuncelle(guncellenenKisi)
        }
    }

    fun sil(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val silinenKisi = Kisiler(3,"Yeni Ahmet",50)
            kdao.kisiSil(silinenKisi)
        }
    }

    fun rasgele(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val gelenListe = kdao.rasgele1KisiGetir()

            for(k in gelenListe){
                Log.e("Kişi id",k.kisi_id.toString())
                Log.e("Kişi ad",k.kisi_ad)
                Log.e("Kişi yaş",k.kisi_yas.toString())
            }
        }
    }

    fun ara(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val gelenListe = kdao.kisiAra("ec")

            for(k in gelenListe){
                Log.e("Kişi id",k.kisi_id.toString())
                Log.e("Kişi ad",k.kisi_ad)
                Log.e("Kişi yaş",k.kisi_yas.toString())
            }
        }
    }

    fun getir(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val gelenKisi = kdao.kisiGetir(2)

            Log.e("Kişi id",gelenKisi.kisi_id.toString())
            Log.e("Kişi ad",gelenKisi.kisi_ad)
            Log.e("Kişi yaş",gelenKisi.kisi_yas.toString())
        }
    }

    fun kontrol(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val gelenSonuc = kdao.kayitKontrol("Ecex")

            Log.e("Kişi kontrol sonucu",gelenSonuc.toString())
        }
    }
}

