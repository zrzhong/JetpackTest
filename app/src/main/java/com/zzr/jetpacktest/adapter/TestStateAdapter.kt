package com.zzr.jetpacktest.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zzr.jetpacktest.fragment.DemoFragment

/**
 * @Author zzr
 * @Desc
 * @Date 2020/11/23
 */
class TestStateAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return DemoFragment.newInstance()
    }
}