package com.zzr.jetpacktest.activity.transition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.TransitionManager
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.activity.BaseActivity
import com.zzr.jetpacktest.databinding.ActivitySceneBinding

class SceneActivity : BaseActivity<ActivitySceneBinding>() {
    private var togger = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scene1 = Scene.getSceneForLayout(binding.viewContainer, R.layout.scene_layout_1, this)
        val scene2 = Scene.getSceneForLayout(binding.viewContainer, R.layout.scene_layout_2, this)

        binding.btnToggle.setOnClickListener {
            if (togger) {
                TransitionManager.go(scene1,ChangeBounds())
            } else {
                TransitionManager.go(scene2,ChangeBounds())
            }
            togger = !togger
        }
    }
}