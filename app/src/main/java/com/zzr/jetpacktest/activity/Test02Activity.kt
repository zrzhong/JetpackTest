package com.zzr.jetpacktest.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zzr.jetpacktest.R
import kotlinx.android.synthetic.main.activity_test02.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class Test02Activity<T : ViewModel?> : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test02)
        val str = "急啊急啊就"
        val id = 11
        val list = listOf<String>()
        saveBtn.setOnClickListener {
            if (validate("名称不能为空") { str.isEmpty() }) return@setOnClickListener
            if (validate("请选择图标") { id == -1 }) return@setOnClickListener
            if (validate("请添加最少一个情景设备") { list.isEmpty() }) return@setOnClickListener
            Log.i("Test02Activity", "onCreate: ")
        }

        createViewModel()
    }

    @SuppressWarnings("unchecked")
    private fun createViewModel() {
        val type: Type? = javaClass.genericSuperclass ?: return
        val types = (type as ParameterizedType).actualTypeArguments
        val tClass: Class<T> = types[0] as Class<T>
        val viewModel: T = ViewModelProvider(this).get(tClass)
    }

    private fun validate(msg: String, block: () -> Boolean): Boolean {
        return if (block()) {
            Log.i("Test02Activity", "validate: $msg")
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            true
        } else false
    }
}