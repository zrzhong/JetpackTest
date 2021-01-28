package com.zzr.jetpacktest.activity

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.zzr.jetpacktest.databinding.ActivityTestWebBinding

/**
 * android调用js代码
 */
class TestWebActivity : AppCompatActivity() {
    companion object {
        const val TAG = "TestWebActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test_web)
        val binding = ActivityTestWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val webSettings = binding.webView.settings
        // 设置与Js交互的权限
        webSettings.javaScriptEnabled = true
        // 设置允许JS弹窗
        webSettings.javaScriptCanOpenWindowsAutomatically = true

        // 先载入JS代码
        // 格式规定为:file:///android_asset/文件名.html
        binding.webView.loadUrl("file:///android_asset/TestJavascript.html")

        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                //要加载完h5后才能调用js的方法 可以在这里调用

            }
        }
        binding.button.setOnClickListener {
            binding.webView.post {
                // 注意调用的JS方法名要对应上
                // 调用javascript的callJS()方法
                // Android4.4以下用loadUrl
//                binding.webView.loadUrl("javascript:callJS()")
                binding.webView.evaluateJavascript("javascript:callJSWithParam(\" + from Android + \")") { value: String ->
                    //此处为 js 返回的结果
                    Log.i(TAG, "value==$value")
                }
            }
        }

        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(
                view: WebView?,
                url: String?,
                message: String?,
                result: JsResult?
            ): Boolean {
                val b: AlertDialog.Builder = AlertDialog.Builder(this@TestWebActivity)
                b.setTitle("Alert")
                b.setMessage(message)
                b.setPositiveButton(android.R.string.ok) { _, _ -> result!!.confirm() }
                b.setCancelable(false)
                b.create().show()
                return true
            }
        }
    }
}