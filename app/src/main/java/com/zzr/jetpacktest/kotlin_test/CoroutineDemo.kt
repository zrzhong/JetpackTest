package com.zzr.jetpacktest.kotlin_test

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.*

/**
 * @Author zzr
 * @Desc
 * @Date 2020/11/30
 */

fun main() {
    runBlocking(Dispatchers.Unconfined) {

//            testFlow1()

//        testFlow2()

//        testFlow3()

        channelTest()


//        testCon()

    }

}

private suspend fun testCon() {
    supervisorScope {

//            val a = async {
//                delay(1000)
//                println("1")
//            }
//            val b = async {
//                delay(100)
//                println("2")
//            }
//            a.await()
//            b.await()

        val result = withContext(Dispatchers.Default) {
            delay(200)
            println("1: ${System.currentTimeMillis()}")
            "hello"
        }
        val result2 = withContext(Dispatchers.Default) {
            delay(200)
            println("2: ${System.currentTimeMillis()}")
            "world"
        }
        println("result=$result ,result2=$result2")
    }
}

private fun CoroutineScope.channelTest() {
    //1.生成 channel
    val channel = Channel<Int>()

    //2.channel 发送数据
    launch {
        for (i in 1..5) {
            delay(200)
            channel.send(i * i)
        }
        channel.close()
    }

    val channel2 = produce<Int> {
        for (i in 1..5) {
            delay(200)
            send(i * i)
        }
        close()
    }

    //3.channel接收数据
    launch {
//                channel.receive()
        for (y in channel2) {
            println("get $y")
        }
    }
}

private suspend fun testFlow3() {
    //取消flow
    val f = flow {
        for (i in 1..3) {
            delay(500)
            println("emit $i")
            emit(i)
        }
    }
    withTimeoutOrNull(1600) {
        f.collect {
            delay(500)
            println("consume $it")
        }
    }
    println("cancel")
}

private suspend fun testFlow2() {
    flow {
        for (i in 1..3) {
            println("$i emit")
            emit(i)
        }
    }.filter {
        println("$it filter")
        it % 2 != 0
    }.map {
        println("$it map")
        "${it * it} money"
    }.collect {
        println("i got $it")
    }
}

private suspend fun testFlow1() {
    createFlow()
        .flowOn(Dispatchers.IO)
        .catch { e ->
            println(e.printStackTrace())
        }
        .onCompletion { t: Throwable? ->
            println("completion${t}")
        }
        .collect {
            println("数据消费 thread==${Thread.currentThread().name}")
            println(it)
        }
}

fun buffer() {
    flow {
        List(100) {
            emit(it)
        }
    }.buffer()
}

private fun createFlow2(): Flow<Int> {
    return channelFlow {
        send(1)
        withContext(Dispatchers.IO) {
            send(2)
        }
    }
}

private fun createFlow(): Flow<Int> {
//    return listOf("1","3").asFlow()
//    return setOf("5,"7).asFlow()
//    return (1..10).asFlow()
    println("数据产生 thread==${Thread.currentThread().name}")
//    return flowOf(1, 2, 3, 4, 5, 6)
    return flow {
        for (i in 1..10) {
            emit(i)
        }
    }

}

private suspend fun getResult(num: Int): Int {
    return withContext(Dispatchers.IO) {
        num * num
    }
}