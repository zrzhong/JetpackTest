package com.zzr.jetpacktest.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zzr.jetpacktest.entity.User

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUsers(): List<User>

    /**
     * 配合liveData
     */
    @Query("select * from User")
    fun loadAllUsers2(): LiveData<List<User>>

    @Delete
    fun deleteUser(user: User)

    @Query("select * from User where age >:age")
    fun loadUserOlderThan(age: Int): List<User>

    @Query("delete from User where name = :name")
    fun deleteUserByName(name: String):Int
}