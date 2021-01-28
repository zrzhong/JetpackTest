package com.zzr.jetpacktest.repository

import com.zzr.jetpacktest.module_logic.model.Article
import com.zzr.jetpacktest.module_logic.model.BaseResponse
import com.zzr.jetpacktest.module_logic.model.PageBean
import com.zzr.jetpacktest.module_logic.network.ApiServiceManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/28
 */
class TestRepository @Inject constructor() {

    suspend fun fetchDocs(): String {
        delay(1000)
        return "Hello Hilt"
    }

    fun articleList():Flow<BaseResponse<PageBean<ArrayList<Article>>>>{
        return flow {
            emit(ApiServiceManager.apiService.articleList(0))
        }
    }

    fun articleList2(param:String):Flow<List<Article>>{
        return flow {
            emit(ApiServiceManager.apiService.articleList(0).data.datas)
        }
    }
}