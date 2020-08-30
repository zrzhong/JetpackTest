package com.zzr.jetpacktest.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.bean.User
import com.zzr.jetpacktest.kotlin_test.gotoActivity
import com.zzr.jetpacktest.kotlin_test.open
import com.zzr.jetpacktest.kotlin_test.showToast
import com.zzr.jetpacktest.module_logic.ui.ArticleActivity
import com.zzr.jetpacktest.repository.AppDatabase
import com.zzr.jetpacktest.sputils.SpBase
import com.zzr.jetpacktest.viewmodel.MainViewModel
import com.zzr.jetpacktest.viewmodel.MainViewModelFactory
import com.zzr.jetpacktest.workmanager.SimpleWorker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val counter = 10
        viewModel =
            ViewModelProvider(this, MainViewModelFactory(counter)).get(MainViewModel::class.java)

        tv_msg.text = "我是消息内容"

        tv_msg.text = SpBase.contentSomething

        tv_msg.setOnClickListener {
//            Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show()
            "点击了".showToast(this)
            R.string.app_name.showToast(this)
            gotoActivity<TestActivity>(this) {
                putExtra("param1", "hello")
                putExtra("param2", "kotlin")
            }

            SpBase.contentSomething = "hello world"
        }
        getSharedPreferences("data", Context.MODE_PRIVATE).open {
            putString("name", "Tome")
            putInt("age", 28)
        }


        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom", 50)
        val user2 = User("Jack", 40)
        addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }
        updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }
        deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByName("Tom")
            }
        }
        queryDataBtn.setOnClickListener {
            thread {
                userDao.loadAllUsers().run {
                    for (user in this) {
                        Log.i(TAG, user.toString())
                    }
                }
            }
        }

        doWorkBtn.setOnClickListener {
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build()
//            val request = PeriodicWorkRequest.Builder(SimpleWorker::class.java,15,TimeUnit.MINUTES).build()
            WorkManager.getInstance(this).enqueue(request)
        }
        gotoArticle.setOnClickListener {
            gotoActivity<ArticleActivity>(this)
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}