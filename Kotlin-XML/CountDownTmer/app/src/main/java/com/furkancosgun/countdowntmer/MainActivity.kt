package com.furkancosgun.countdowntmer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startTimer(view:View){
        val time : Int = edtCount.text.toString().toInt()
        println("DEB0G:tıkalndı")
        val timer = object : CountDownTimer((time * 1000).toLong(),1000) {
            override fun onTick(p0: Long) {
                try {
                    txtTimer.text = (p0 / 1000).toString()
                } catch (e: Exception) {
                    Snackbar.make(this@MainActivity.txtTimer, e.toString(), Snackbar.LENGTH_LONG)
                        .show()
                }
            }

            override fun onFinish() {
                txtTimer.text = "Timer Finished"
            }
        }.start()




    }
}