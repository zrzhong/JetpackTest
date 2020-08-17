package com.zzr.jetpacktest.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

object Repository {

    fun searchPlaces(query: String): LiveData<String> = liveData(Dispatchers.IO) {

    }
}