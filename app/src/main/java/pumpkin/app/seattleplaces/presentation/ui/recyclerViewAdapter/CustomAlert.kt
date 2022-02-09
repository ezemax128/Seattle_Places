package pumpkin.app.seattleplaces.presentation.ui.recyclerViewAdapter

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView
import pumpkin.app.seattleplaces.R

class CustomAlert {
    companion object {
        fun createAlert(context: Context, view: View): AlertDialog? {
            val message = AlertDialog.Builder(context)
            message.setView(view)
            view.findViewById<LottieAnimationView>(R.id.exitLottie).setAnimation(R.raw.exit)
            return message.create()
        }
    }

}