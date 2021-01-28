package com.zzr.jetpacktest.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zzr.jetpacktest.entity.DiffAdapter
import com.zzr.jetpacktest.entity.DiffBean
import com.zzr.jetpacktest.entity.DiffCallBack
import com.zzr.jetpacktest.databinding.ActivityTest06Binding
import com.zzr.jetpacktest.viewmodel.SavedStateViewModel

class Test06Activity : AppCompatActivity() {

    private val viewModel: SavedStateViewModel by viewModels() {
        SavedStateViewModelFactory(application, this)
    }

    private lateinit var binding: ActivityTest06Binding
    private val mData = mutableListOf<DiffBean>()
    private val diffAdapter: DiffAdapter by lazy {
        DiffAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test06)
        binding = ActivityTest06Binding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.rvRefresh.layoutManager = LinearLayoutManager(this)
        initData()
        diffAdapter.setNewInstance(mData)
        binding.rvRefresh.adapter = diffAdapter
        binding.rvRefresh.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))

        binding.btnRefresh.setOnClickListener(onRefresh)
    }

    private val onRefresh = { _: View ->
        //clone一遍旧数据 ，模拟刷新操作
        val newData = ArrayList<DiffBean>(mData.map { it.copy() })

//        //模拟新增数据
//        newData.add(DiffBean("ASs", "窘境好看"))
//        //模拟修改数据
//        newData[0].desc = "Android+"
//        newData[0].name = "hello"
//        //模拟数据位移
        val diffBean = newData[1];
        newData.remove(diffBean);
        newData.add(diffBean);

        val diffResult = DiffUtil.calculateDiff(DiffCallBack(mData, newData), true)
        diffResult.dispatchUpdatesTo(diffAdapter)

        //别忘了将新数据给Adapter
//        mData = newData;
        diffAdapter.setList(newData)
        //以前我们大多数情况下只能这样
//        diffAdapter.notifyDataSetChanged();
    }

    private fun initData() {

        mData.add(DiffBean("zzr", "Android"))
        mData.add(DiffBean("zzr", "IOS"))
        mData.add(DiffBean("zzr", "测试"))
        mData.add(DiffBean("zzr", "产品"))
        mData.add(DiffBean("zzr", "Java"))
        mData.add(DiffBean("zzr", "Python"))
    }
}