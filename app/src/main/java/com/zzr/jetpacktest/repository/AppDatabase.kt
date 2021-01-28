package com.zzr.jetpacktest.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zzr.jetpacktest.entity.Student
import com.zzr.jetpacktest.entity.User

@Database(version = 1, entities = [User::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    //创建数据库非常消耗资源 采用单例
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext
                    , AppDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
//        private var instance: AppDatabase? = null
//
//        @Synchronized
//        fun getDatabase(context: Context): AppDatabase {
//            instance?.let {
//                return it
//            }
//
//            return Room.databaseBuilder(
//                context.applicationContext,
//                AppDatabase::class.java,
//                "app_database"
//            )
//                .build().apply {
//                    instance = this
//                }
//        }


//        fun getInstance(context:Context):AppDatabase{
//            return instance?: synchronized(this){
//                instance?:buildDataBase(context)
//                    .also {
//                        instance = it
//                    }
//            }
//        }

//        private fun buildDataBase(context: Context):AppDatabase{
//            return Room
//                .databaseBuilder(context,AppDatabase::class.java,"jetPackDemo-database")
//                .build()
//        }
    }
}