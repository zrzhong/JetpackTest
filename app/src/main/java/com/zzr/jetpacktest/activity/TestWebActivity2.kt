package com.zzr.jetpacktest.activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zzr.jetpacktest.databinding.ActivityTestWeb2Binding
import com.zzr.jetpacktest.entity.AndroidtoJs

class TestWebActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test_web2)
        val binding = ActivityTestWeb2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val webSettings = binding.webView2.settings
        webSettings.javaScriptEnabled = true
        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：Java对象名
        binding.webView2.addJavascriptInterface(AndroidtoJs(), "test")

        // 加载JS代码
        // 格式规定为:file:///android_asset/文件名.html
//        binding.webView2.loadUrl("file:///android_asset/TestJavascript2.html");
        binding.webView2.loadUrl("file:///android_asset/TestJavascript3.html");


        //另一种方式 js调用Android代码
        binding.webView2.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // 步骤2：根据协议的参数，判断是否是所需要的url
                // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）
                val uri = Uri.parse(url)
                // 如果url的协议 等于 预先约定的 js 协议
                // 就解析往下解析参数
                if (uri.scheme == "js") {
                    // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                    // 所以拦截url,下面JS开始调用Android需要的方法
                    if (uri.authority == "webview") {
                        //  步骤3：
                        // 执行JS所需要调用的逻辑
                        Toast.makeText(this@TestWebActivity2, "js调用了Android的方法", Toast.LENGTH_SHORT)
                            .show()

                        // 可以在协议上带有参数并传递到Android上
                        val params = mutableMapOf<String, String?>()
                        val collection = uri.queryParameterNames
                        for (name in collection) {
                            params[name] = uri.getQueryParameter(name)
                        }
                        //打印信息
                        params.forEach {
                            Log.i(
                                "TestWebActivity2",
                                "key==${it.key}  value==${it.value}"
                            )
                        }
                    }
                    return true
                }

                return super.shouldOverrideUrlLoading(view, url)
            }
        }
    }
}