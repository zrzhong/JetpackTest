package com.zzr.jetpacktest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.adapter.FlowAdapter

class FlexBoxActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flex_box)
        val rvFlex = findViewById<RecyclerView>(R.id.rvFlex)
        val flowAdapter = FlowAdapter()
    }
}