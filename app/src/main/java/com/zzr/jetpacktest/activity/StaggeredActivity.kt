package com.zzr.jetpacktest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.adapter.StaggerAdapter

class StaggeredActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staggered)

        val rvStagger = findViewById<RecyclerView>(R.id.rvStagger)
        val adapter = StaggerAdapter()
        val manager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL)
        rvStagger.layoutManager = manager
        rvStagger.adapter = adapter

        val data = mutableListOf<String>()
        for (i in 0..60) {
            data.add("Item-${i + 1}")
        }
        adapter.setNewInstance(data)
    }
}