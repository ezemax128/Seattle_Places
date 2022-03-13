package pumpkin.app.seattleplaces.presentation.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timeToStart()
    }

    private fun timeToStart() {
        object : CountDownTimer(1000, 1000) {
            override fun onTick(p0: Long) {
                //nothing to do
            }

            override fun onFinish() {
                startActivity(Intent(this@SplashScreen, MainActivity::class.java)).apply { }
            }

        }.start()
    }
}