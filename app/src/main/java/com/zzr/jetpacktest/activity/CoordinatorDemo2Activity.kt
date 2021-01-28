package com.zzr.jetpacktest.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zzr.jetpacktest.adapter.MyAdapter
import com.zzr.jetpacktest.adapter.TestAdapter
import com.zzr.jetpacktest.databinding.ActivityCoordinatorDemo2Binding

class CoordinatorDemo2Activity : BaseActivity<ActivityCoordinatorDemo2Binding>() {
    private val myAdapter by lazy {
        TestAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvDisplay.layoutManager = LinearLayoutManager(this)
        binding.rvDisplay.adapter = myAdapter

        val data = mutableListOf<String>()
        repeat(50) {
            data.add("Item-${it}")
        }
        myAdapter.setNewInstance(data)
    }
}