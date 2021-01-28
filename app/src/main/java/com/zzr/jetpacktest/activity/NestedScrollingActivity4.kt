package com.zzr.jetpacktest.activity

import android.animation.ArgbEvaluator
import android.graphics.Color
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.adapter.TestStateAdapter
import com.zzr.jetpacktest.databinding.ActivityNestedScrolling4Binding
import com.zzr.jetpacktest.widget.StickyNavLayout


class NestedScrollingActivity4 : BaseActivity<ActivityNestedScrolling4Binding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tabs = listOf("首页", "我的", "新闻", "消息", "娱乐")
        val inspectionAdapter = TestStateAdapter(this)
        binding.vpDemo.adapter = inspectionAdapter
        TabLayoutMediator(binding.tabLayout, binding.vpDemo) { tab, position ->
            tab.text = tabs[position]
        }.attach()

        binding.sickLayout.setScrollChangeListener(object : StickyNavLayout.ScrollChangeListener {
            override fun onScroll(moveRatio: Float) {
                initToolBar(R.drawable.back, moveRatio)
            }
        })
    }

    private fun initToolBar(@DrawableRes backResId: Int, moveRatio: Float) {
        binding.ivBack.setOnClickListener {
            finish()
        }
        val argbEvaluator = ArgbEvaluator()
        val color = argbEvaluator.evaluate(moveRatio, Color.WHITE, Color.BLACK) as Int
        ContextCompat.getDrawable(this, backResId)?.run {
            val wrapDrawable = DrawableCompat.wrap(this)
            DrawableCompat.setTint(wrapDrawable, color)
            binding.ivBack.setImageDrawable(wrapDrawable)
        }

        binding.tvTitle.alpha = moveRatio
        binding.toolbar.alpha = moveRatio
    }
}