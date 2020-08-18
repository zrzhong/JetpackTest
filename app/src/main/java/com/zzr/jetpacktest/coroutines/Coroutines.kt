package com.zzr.jetpacktest.coroutines

import kotlinx.coroutines.*

fun main() {
//    GlobalScope.launch {
//        println("codes run in coroutine scope")
//    }
    runBlocking {
        val result = async {
            5 + 3
        }.await()
        println(result)
        //等同上面的写法
        val result2 = withContext(Dispatchers.Default) {
            5 + 3
        }
        println(result2)

        val start = System.currentTimeMillis()
        val deferred1 = async {
            delay(1000)
            5 + 5
        }
        val deferred2 = async {
            delay(1000)
            6 + 4
        }
        println("result is ${deferred1.await() + deferred2.await()}")
        val end = System.currentTimeMillis()
        println("cost ${end - start} millis")
    }

//    runBlocking {
//        coroutineScope {
//            launch {
//                for (i in 1..10) {
//                    println(i)
//                    delay(1000)
//                }
//            }
//        }
//        println("coroutineScope finished")
//    }
//    println("runBlocking finished")

//    val start = System.currentTimeMillis()
//    runBlocking {
//        repeat(100000) {
//            launch {
//                println(".")
//            }
//        }
//    }
//    val end = System.currentTimeMillis()
//    println(end - start)

    suspend fun printDot2() {
        println(".")
        delay(1000)
    }

    suspend fun printDot() = coroutineScope {
        launch {
            println(".")
            delay(1000)
        }
    }
}