package com.zzr.jetpacktest.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.rxLifeScope
import com.drake.net.utils.scopeNet
import com.zzr.jetpacktest.databinding.ActivityNetDemoBinding
import kotlinx.coroutines.launch
import rxhttp.toStr
import rxhttp.wrapper.param.RxHttp

class NetDemoActivity : BaseActivity<ActivityNetDemoBinding>() {
    companion object {
        const val TAG = "NetDemoActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_net_demo)

        scopeNet {
//            val task = Post<String>("api0").await()
            Log.i(TAG, "AAAA")

            scopeNet {
                Log.i(TAG, "BBBB")
//                val task = Post<String>("api0").await() // 此时发生请求错误
                val value = 2 / 0
            }.catch {
                // A
                Log.i(TAG, "BBBB: ${it.message}")
            }
        }.catch {
            // B
            Log.i(TAG, "AAAA: ${it.message}")
        }

        rxLifeScope.launch {
            val data = RxHttp.get("http://www.baidu.com/")
                .toStr()
                .await()
            binding.tvContent.text = data
        }
    }
}