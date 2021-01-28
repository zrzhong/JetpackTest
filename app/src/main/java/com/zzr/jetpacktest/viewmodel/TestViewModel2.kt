package com.zzr.jetpacktest.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.blankj.utilcode.util.CollectionUtils.isNotEmpty
import com.zzr.jetpacktest.module_logic.model.Article
import com.zzr.jetpacktest.repository.TestRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/28
 */
@ExperimentalCoroutinesApi
@FlowPreview
class TestViewModel2 @ViewModelInject constructor(val repository: TestRepository) : ViewModel() {

    val article: LiveData<List<Article>> = liveData {
        repository.articleList()
            .catch {
                emit(listOf<Article>())
            }
            .collect {
                emit(it.data.datas)
            }
    }

    private val testDocs by lazy {
        MutableLiveData<String>().also {
            fetchDocs()
        }
    }

    fun getTestDocs(): LiveData<String> = testDocs

    fun fetchDocs() {
        viewModelScope.launch {
            testDocs.value = repository.fetchDocs()
        }
    }

    val searchFlow = MutableStateFlow("")

    val searchResult: LiveData<List<Article>>

    init {
        // 2
        searchResult = searchFlow
            .debounce(500)
            .filter {
                it.isNotEmpty()
            }
            .flatMapLatest {
                repository.articleList2(it)
            }
            .catch {
                emit(listOf())
            }
            .asLiveData()
    }
}