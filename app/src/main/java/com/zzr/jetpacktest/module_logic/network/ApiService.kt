package com.zzr.jetpacktest.module_logic.network

import com.zzr.jetpacktest.module_logic.model.Article
import com.zzr.jetpacktest.module_logic.model.BaseResponse
import com.zzr.jetpacktest.module_logic.model.PageBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    /**
     * 首页文章列表 page页码 0开始
     */
    @GET("article/list/{page}/json")
    suspend fun articleList(@Path("page") page: Int): BaseResponse<PageBean<ArrayList<Article>>>

    @GET("article/list/{page}/json")
    fun articleList2(@Path("page") page: Int): Call<BaseResponse<PageBean<ArrayList<Article>>>>

}