package com.zzr.jetpacktest.kotlin_test

import androidx.lifecycle.liveData
import com.zzr.jetpacktest.module_logic.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * @Author zzr
 * @Desc
 * @Date 2020/9/28
 */
fun main() {
    runBlocking {
//        flowTest1()
        flowTest4()

    }
}

suspend fun flowTest1() {
    flow {
        for (i in 1..5) {
            delay(100)
            emit(i)
        }
    }.map {
        it*it
    }.flowOn(Dispatchers.IO)
        .collect {
        println(it)
    }
}

suspend fun flowTest2() {
    flowOf(1, 2, 3, 4, 5).onEach {
        delay(100)
    }.collect {
        println(it)
    }
}

suspend fun flowTest3() {
    listOf(1, 2, 3, 4, 5).asFlow()
        .onEach {
            delay(100)
        }.collect {
            println(it)
        }
}

suspend fun flowTest4() {
    val time = measureTimeMillis {
        channelFlow {
            for (i in 1..5) {
                delay(100)
                send(i)
            }
        }.collect {
            delay(100)
            println(it)
        }
    }
    println("cost $time")
}

fun fectchPokemonInfo() = liveData<String> {
    Repository.featchPokemonInfo()
}


