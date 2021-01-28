package com.zzr.jetpacktest.kotlin_test

/**
 * @Author zzr
 * @Desc
 * @Date 2020/9/30
 */
fun main() {
//    listTest()
    sequenceTest()
    val list = 5
    for (i in 0 until list){

    }
}

fun listTest() {
    val list = listOf(1, 2, 3, 4, 5, 6)
    val result = list
        .map {
            println("In Map $it")
            it * 2
        }.filter {
            println("In Filter $it")
            it % 3 == 0
        }
    println(result.first())
}

fun sequenceTest() {
    val sequence = sequenceOf(1, 2, 3, 4, 5, 6)
    val result = sequence
        .map {
            println("In Map $it")
            it * 2
        }.filter {
            println("In Filter $it")
            it % 3 == 0
        }
    println(result.first())
}