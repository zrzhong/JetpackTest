package com.zzr.jetpacktest.activity.transition

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.transition.TransitionInflater
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.activity.BaseActivity
import com.zzr.jetpacktest.databinding.ActivityTransitionABinding

class TransitionAActivity : BaseActivity<ActivityTransitionABinding>() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            exitTransition = Fade()
//            enterTransition=
//            returnTransition
//            reenterTransition
            sharedElementExitTransition = ChangeBounds()
        }
        super.onCreate(savedInstanceState)

//        val inflater = TransitionInflater.from(this)
//        window.exitTransition = inflater.inflateTransition(R.transition.fade)


        binding.gotoBActivity.setOnClickListener {
            val options =
                ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    binding.imageA,
                    binding.imageA.transitionName
                )
            startActivity(Intent(this, TransitionBActivity::class.java), options.toBundle())
        }
    }
}