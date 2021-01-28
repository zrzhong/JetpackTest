package com.zzr.jetpacktest.activity

import android.os.Bundle
import android.webkit.WebViewClient
import com.zzr.jetpacktest.databinding.ActivityWebTestBinding
import com.zzr.jetpacktest.service.MyIntentService

class WebTestActivity : BaseActivity<ActivityWebTestBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_web_test)
        binding.webView.loadUrl("https://www.baidu.com/")

        binding.webView.webViewClient = WebViewClient()

        binding.btnReLoad.setOnClickListener {
            binding.webView.reload()
        }
    }
}