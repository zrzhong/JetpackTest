package com.zzr.jetpacktest.module_logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.zzr.jetpacktest.module_logic.model.Article
import com.zzr.jetpacktest.module_logic.network.ApiServiceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow
import kotlin.Exception
import kotlin.coroutines.CoroutineContext

/**
 * 仓库层 数据的获取和返回 直接和viewModel通信
 */
object Repository {

    suspend fun featchPokemonInfo() =
        flow {
            emit("hello")
        }.collect {
            println(it)
        }


    fun searchPlaces(query: String): LiveData<String> = liveData(Dispatchers.IO) {

    }

    fun articleList(page: Int) = liveData(Dispatchers.IO) {
        val result = try {
            val response = ApiServiceManager.articleList(page)
            if (response.errorCode == 0) {
                val articles = response.data.datas
                Result.success(articles)
            } else {
                Result.failure(RuntimeException("response status is ${response.errorMsg}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Article>>(e)
        }
        emit(result)
    }

    fun articleList2(page: Int) = fire(Dispatchers.IO) {

        val response = ApiServiceManager.articleList(page)
        if (response.errorCode == 0) {
            val articles = response.data.datas
            Result.success(articles)
        } else {
            Result.failure(RuntimeException("response status is ${response.errorMsg}"))
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }

}