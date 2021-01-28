package com.zzr.jetpacktest.activity

import android.os.Bundle
import com.zzr.jetpacktest.adapter.TestAdapter
import com.zzr.jetpacktest.databinding.ActivityLayoutManagerTestBinding
import com.zzr.mylibrary.widget.MaxLineLinearLayoutManager

class LayoutManagerTestActivity : BaseActivity<ActivityLayoutManagerTestBinding>() {
    private val layoutAdapter: TestAdapter by lazy {
        TestAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_layout_manager_test)

        binding.rv.run {
            layoutManager = MaxLineLinearLayoutManager(this@LayoutManagerTestActivity, 5)
            adapter = layoutAdapter
            layoutAdapter.setNewInstance(generateData())
        }
    }

    private fun generateData(): MutableList<String> {
        val data = mutableListOf<String>()
        repeat(50) {
            data.add("Item-$it")
        }
        return data
    }
}