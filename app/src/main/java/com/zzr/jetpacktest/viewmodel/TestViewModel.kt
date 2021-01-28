package com.zzr.jetpacktest.viewmodel

import androidx.lifecycle.*
import com.zzr.jetpacktest.entity.User
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * @Author zzr
 * @Desc
 * @Date 2020/9/19
 */
class TestViewModel : ViewModel() {

    private val _netData = MutableLiveData<String>()
    val netData: LiveData<String>
        get() = _netData

    fun getNetData() {
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO) {
                delay(2000)
                "Get Data"
            }
            _netData.value = data
        }
    }

    suspend fun testFlow() {
        flow {
            for (i in 1..5) {
                delay(100)
                emit(i)
            }
        }.collect {
            print(it)
        }
        viewModelScope.launch {
            supervisorScope {

            }
            coroutineScope {

            }
        }
    }

    val user: LiveData<User> = liveData {
        val data = loadUser("1")
        emit(data)
    }

    private suspend fun loadUser(userId: String): User {
        delay(1000)
        return User("jack", 20)
    }

    private val userId: LiveData<String> = MutableLiveData()
    val userData: LiveData<User> = userId.switchMap { id ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(loadUser(id))
        }
    }
}