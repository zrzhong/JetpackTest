package com.zzr.jetpacktest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zzr.jetpacktest.bean.User

object Repository {

    fun getUser(userId: String): LiveData<User> {
        return MutableLiveData<User>().also {
            //模拟服务器或数据库获取数据
            it.value = User(userId, 20)
        }
    }
}