package com.zzr.jetpacktest.activity

import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zzr.jetpacktest.adapter.TestAdapter
import com.zzr.jetpacktest.databinding.ActivityNestedScroll2Binding

/**
 * @Author zzr
 * @Desc
 * @Date 2020/11/17
 */
class NestedScrollActivity2 : BaseActivity<ActivityNestedScroll2Binding>() {
    private val testAdapter: TestAdapter by lazy {
        TestAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvContent.layoutManager = LinearLayoutManager(this)
        binding.rvContent.adapter = testAdapter
        binding.rvContent.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        val data = mutableListOf<String>()
        repeat(20) {
            data.add("Item-${it + 1}")
        }
        testAdapter.setNewInstance(data)
    }
}