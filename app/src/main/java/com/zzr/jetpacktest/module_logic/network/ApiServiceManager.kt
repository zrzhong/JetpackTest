package com.zzr.jetpacktest.module_logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 统一的网络请求入口
 */
object ApiServiceManager {
    //    private val placeService = ServiceCreator.create(PlaceService::class.java)
    val apiService = ServiceCreator.create<ApiService>()

    suspend fun articleList(page: Int) = apiService.articleList2(page).await()

    /**
     * Retrofit自己就有一个await的拓展函数
     */
   private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }
            })
        }
    }

}