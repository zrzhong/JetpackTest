package com.zzr.jetpacktest.activity.transition

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import androidx.annotation.RequiresApi
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.activity.BaseActivity
import com.zzr.jetpacktest.databinding.ActivityTransitionABinding
import com.zzr.jetpacktest.databinding.ActivityTransitionBBinding

class TransitionBActivity : BaseActivity<ActivityTransitionBBinding>() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        with(window){
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            enterTransition = Slide().apply {
                slideEdge = Gravity.RIGHT
            }
            sharedElementEnterTransition = ChangeBounds()
        }
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_transition_b)

    }
}