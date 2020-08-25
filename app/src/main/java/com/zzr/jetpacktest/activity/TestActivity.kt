package com.zzr.jetpacktest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.bean.User
import com.zzr.jetpacktest.kotlin_test.later
import com.zzr.jetpacktest.repository.AppDatabase
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val intent = intent
        val content = "value==${intent.getStringExtra("param1")},${intent.getStringExtra("param2")}"
        tv_content.text = content

//        val db = Room.databaseBuilder(this.applicationContext, AppDatabase::class.java,
//                "app_database")
//                .build()
        val p: User by later {
            val user = User("jack", 10)
            user
        }
        val user by lazy {
            val user = User("Tom", 20)
            user
        }
    }
}