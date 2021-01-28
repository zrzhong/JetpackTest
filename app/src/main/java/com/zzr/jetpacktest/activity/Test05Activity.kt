package com.zzr.jetpacktest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.adapter.MyAdapter
import com.zzr.jetpacktest.entity.CityBean
import com.zzr.jetpacktest.widget.MyItemDecoration

class Test05Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test05)
//        val data = mutableListOf<String>()
//        for (i in 0..30) {
//            data.add("Item-$i")
//        }
        val data = generateData()
        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter()
        adapter.setNewInstance(data)
        rv.adapter = adapter
        rv.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        rv.addItemDecoration(MyItemDecoration(data))
    }

    private fun generateData(): MutableList<CityBean> {

        return mutableListOf<CityBean>().apply {
            add(CityBean("A", "安徽"))
            add(CityBean("A", "啊啊"))
            add(CityBean("A", "阿萨"))
            add(CityBean("A", "阿达"))
            add(CityBean("A", "安防"))
            add(CityBean("A", "爱国"))

            add(CityBean("B", "北京"))
            add(CityBean("B", "版本"))
            add(CityBean("B", "不行"))
            add(CityBean("B", "不是"))
            add(CityBean("B", "帮我"))

            add(CityBean("C", "北京"))
            add(CityBean("C", "版本"))
            add(CityBean("C", "不行"))
            add(CityBean("C", "不是"))
            add(CityBean("C", "帮我"))

            add(CityBean("D", "北京"))
            add(CityBean("D", "版本"))
            add(CityBean("D", "不行"))
            add(CityBean("D", "不是"))
            add(CityBean("D", "帮我"))

            add(CityBean("E", "北京"))
            add(CityBean("E", "版本"))
            add(CityBean("E", "不行"))
            add(CityBean("E", "不是"))
            add(CityBean("E", "帮我"))

            add(CityBean("F", "北京"))
            add(CityBean("F", "版本"))
            add(CityBean("F", "不行"))
            add(CityBean("F", "不是"))
            add(CityBean("F", "帮我"))

            add(CityBean("G", "北京"))
            add(CityBean("G", "版本"))
            add(CityBean("G", "不行"))
            add(CityBean("G", "不是"))
            add(CityBean("G", "帮我"))

        }
    }
}