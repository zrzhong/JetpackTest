package com.zzr.jetpacktest.coroutines

import kotlinx.coroutines.*

fun main() {

    //方法一顶层协程，与app生命周期一致，无法取消
    GlobalScope.launch {
        println("codes run in coroutine scope")
    }

    //方法二 阻塞线程 多用在单元测试
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

        //方法三 推荐 只能在协程作用域或挂起函数中调用
        val job = coroutineScope {
            launch {

            }
            val deferred = async {

            }
            val result = withContext(Dispatchers.IO) {

            }

        }

        //方法四 用CoroutineScope函数生产scope对象，用scope对象的launch方法开启一个协程
        val job2 = Job()
        val scope = CoroutineScope(job2)
        scope.launch(Dispatchers.Main) {
            val deferred = async {
                //获取标题
//                api.getTitle()
            }
            val deferred2 = async {
                //获取内容
//                api.getContent()
            }
            //上面两个任务是并行的，提高了效率
            //await方法获取返回值，合并结果
            val result1 = deferred.await()
            val result2 = deferred2.await()

        }
    }

    suspend fun printDot2() {
        coroutineScope {

        }
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