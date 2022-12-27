package com.example.roomkullanimi

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Kisiler::class], version = 1)
abstract class Veritabani : RoomDatabase() {
    abstract fun getKisilerDao() : KisilerDao

    companion object {
        var INSTANCE:Veritabani? = null

        fun veritabaniErisim(context: Context) : Veritabani? {
            if(INSTANCE == null){
                synchronized(Veritabani::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        Veritabani::class.java,
                        "rehber.sqlite")//Assets Dosyasını okur
                        .createFromAsset("rehber.sqlite").build()
                }
            }
            return INSTANCE
        }
    }
}

