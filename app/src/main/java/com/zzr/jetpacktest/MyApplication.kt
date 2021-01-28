package com.zzr.jetpacktest

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.drake.net.cacheEnabled
import com.drake.net.initNet
import com.drake.net.logEnabled
import com.zzr.jetpacktest.module_logic.network.GsonConvert
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        //Net全局配置
        initNet("https://jyapi2-test.aylives.cn/wy-api/"){
            converter(GsonConvert()) // 转换器
            cacheEnabled() // 开启缓存
            setLogRecord(BuildConfig.DEBUG) // 日志记录器
            logEnabled = BuildConfig.DEBUG // LogCat异常日志
            addHeader("键" ,"值") // 全局请求头
            setHeader("覆盖键" ,"值") // 全局请求头, 键相同会覆盖
            addParam("键" ,"值") // 全局参数

        }
    }
}