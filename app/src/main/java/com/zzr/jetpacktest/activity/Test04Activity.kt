package com.zzr.jetpacktest.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.zzr.jetpacktest.databinding.ActivityTest04Binding
import com.zzr.jetpacktest.viewmodel.TestViewModel
import kotlinx.coroutines.*

class Test04Activity : BaseActivity<ActivityTest04Binding>() {

    private val testViewModel: TestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test04)
        testViewModel.netData.observe(this) {
            binding.tv1.text = it
        }
        lifecycleScope.launchWhenCreated {

        }
    }

    private fun fetchDocs(){
        lifecycleScope.launchWhenResumed {

        }
    }

    private fun launchFromMainScope() {
        launch {
            val deferred = async {

            }
            val data = withContext(Dispatchers.IO) {
                delay(2000)
                "Get It"
            }

        }
    }
}