package com.zzr.jetpacktest.module_logic.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.zzr.jetpacktest.module_logic.Repository
import com.zzr.jetpacktest.module_logic.model.Article
import com.zzr.jetpacktest.module_logic.model.BaseResponse
import com.zzr.jetpacktest.module_logic.model.LoadState
import com.zzr.jetpacktest.module_logic.model.PageBean
import com.zzr.jetpacktest.module_logic.network.ApiServiceManager
import kotlinx.coroutines.*
import retrofit2.await
import java.lang.Exception

class HomeViewModel : ViewModel() {
    val msg = MutableLiveData<String>()

    fun getMsg() {
        msg.value = "hello kotlin"
    }

    private val _articleData = MutableLiveData<BaseResponse<PageBean<ArrayList<Article>>>>()
    val articleData: LiveData<BaseResponse<PageBean<ArrayList<Article>>>> = _articleData

    val loadState = MutableLiveData<LoadState>()

    fun articleList(page: Int) {
//        viewModelScope.launch {
//            val articleList = withContext(Dispatchers.IO) {
//                ApiServiceManager.apiService.articleList(page)
//            }
//            _articleData.value = articleList
//        }

//        val scope = CoroutineScope(Job())
//        scope.launch {
//
//        }
        launch({
            loadState.value = LoadState.Loading()
//            val result = async { ApiServiceManager.apiService.articleList(page) }
//            _articleData.value = result.await()
            _articleData.value = withContext(Dispatchers.IO) {
                ApiServiceManager.apiService.articleList(page)
//                ApiServiceManager.apiService.articleList2(page).await()
            }
            loadState.value = LoadState.Success()
        }, {
            loadState.value = LoadState.Fail(it.message ?: "获取数据失败")
            Log.e("TAG", "articleList: ${it.message}", it)
        }, {
            Log.e("TAG", "articleList: completing")
        })
    }

    private val pageLiveData = MutableLiveData<Int>()
    fun getArticle(page: Int) {
        pageLiveData.value = page
    }

    val articleLiveData = Transformations.switchMap(pageLiveData) { page ->
        Repository.articleList2(page)
    }
}