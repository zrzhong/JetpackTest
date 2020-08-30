package com.zzr.jetpacktest.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.zzr.jetpacktest.bean.User
import com.zzr.jetpacktest.repository.Repository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainViewModel(countReserved: Int) : ViewModel() {

    fun test(

    ) {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            Log.i("TAG", "test: ${e.message}")
        }) {

        }
    }

    private val userLiveData: MutableLiveData<User> = MutableLiveData()
    val userName: LiveData<String> = Transformations.map(userLiveData) {
        "${it.name} ${it.age}"
    }

    private val userIdLiveData: MutableLiveData<String> = MutableLiveData()
    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) { userId ->
        Repository.getUser(userId)
    }

    fun getUser(userId: String) {
        userIdLiveData.value = userId
        viewModelScope.launch {

        }
    }

    val counter: LiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }
}