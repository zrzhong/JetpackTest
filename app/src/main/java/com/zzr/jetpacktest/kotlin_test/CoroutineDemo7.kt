package com.zzr.jetpacktest.kotlin_test

import com.drake.net.utils.scopeNet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/30
 */
fun main() {
    scopeNet(Dispatchers.Default) {
        val a = async {
            LogUtil.log("111")
            delay(500)
        }.await()

//        scopeNet(Dispatchers.Default) {
//            LogUtil.log("222")
//            2 / 0
//        }.catch {
//            LogUtil.log("111: ${it.message}")
//        }

        launch {
            LogUtil.log("222")
            2 / 0
        }.invokeOnCompletion {
            LogUtil.log("222: ${it?.message}")
        }

    }.catch {
        LogUtil.log("111: ${it.message}")
    }

    Thread.sleep(2000)
}