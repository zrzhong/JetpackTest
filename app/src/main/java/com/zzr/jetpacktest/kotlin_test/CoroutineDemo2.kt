package com.zzr.jetpacktest.kotlin_test

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlin.properties.Delegates

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/1
 */

fun main() {
    runBlocking {
        //背压 因为生产速度大于消费速度
        // conflate 新数据覆盖老数据
//        conflateTest()

        //collectLatest 只处理最新的数据
//        collectLatest()

//        mapTest()
        mapTest2()

        var name: String by Delegates.notNull<String>()

        val name2: String by Delegates.observable("初始化值") { property, old, new ->
            println("以前的值$old 新设置的值$new")
        }
        val name3: String by Delegates.vetoable("初始化值") { pre, old, new ->
            // 如果设置的新值是a开头的就设置新值，返回ture就修改，false就不修改
            new.startsWith("a")
        }
    }
}

private suspend fun mapTest() {
    flow {
        List(5) {
            emit(it)
        }
    }.map {
        flow { List(it) { emit(it) } }
    }.collect {
        it.collect { num ->
            println("flow $num")
        }
    }
}

private suspend fun mapTest2() {
    //flattenConcat 拼接flow
    flow {
        for (i in 1..5) {
            emit(i)
        }
//        List(5) { emit(it) }
    }
        .map {
            flow { List(it) { emit(it) } }
        }
        .flattenConcat()
//        .flattenMerge()
        .collect {
            println(it)
        }
}

private suspend fun collectLatest() {
    flow {
        List(100) {
            emit(it)
        }
    }.collectLatest {
        println("Collecting value $it")
        delay(100)
        println("$it collected")
    }
}

private suspend fun conflateTest() {
    flow {
        List(100) {
            emit(it)
        }
    }.conflate()
        .collect {
            println("Collecting value $it")
            delay(100)
            println("$it collected")
        }
}