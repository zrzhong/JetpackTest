package com.zzr.jetpacktest.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.zzr.jetpacktest.databinding.ActivityBottomSheetBinding

class BottomSheetActivity : BaseActivity<ActivityBottomSheetBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val behavior = BottomSheetBehavior.from(binding.bottomSheetLayout)
        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) binding.bottomSheetHeading.text =
                    "下拉关闭"
                else if (newState == BottomSheetBehavior.STATE_COLLAPSED) binding.bottomSheetHeading.text =
                    "上拉展开"

                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> LogUtils.i("STATE_EXPANDED")
                    BottomSheetBehavior.STATE_COLLAPSED -> LogUtils.i("STATE_COLLAPSED")
                    BottomSheetBehavior.STATE_DRAGGING -> LogUtils.i("STATE_DRAGGING")
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> LogUtils.i("STATE_HALF_EXPANDED")
                    BottomSheetBehavior.STATE_HIDDEN -> LogUtils.i("STATE_HIDDEN")
                    BottomSheetBehavior.STATE_SETTLING -> LogUtils.i("STATE_SETTLING")
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                LogUtils.i("slideOffset: $slideOffset")
            }
        })

        binding.expandBottomSheetButton.setOnClickListener {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        binding.collapseBottomSheetButton.setOnClickListener {
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        binding.showBottomSheetDialogButton.setOnClickListener {
            behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        }
        binding.hideBottomSheetButton.setOnClickListener {
            behavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }
}