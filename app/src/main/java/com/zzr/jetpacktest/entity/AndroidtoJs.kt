package com.zzr.jetpacktest.entity

import android.util.Log
import android.webkit.JavascriptInterface

/**
 * @Author zzr
 * @Desc
 * @Date 2020/10/15
 */
class AndroidtoJs {

    //定义js需要调用的方法
    @JavascriptInterface
    public fun hello(msg: String) {
        Log.i("TAG", "hello==$msg")
    }
}