package com.zzr.jetpacktest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.activity.transition.SceneActivity
import com.zzr.jetpacktest.activity.transition.TransitionAActivity
import com.zzr.jetpacktest.entity.User
import com.zzr.jetpacktest.databinding.ActivityMainBinding
import com.zzr.jetpacktest.kotlin_test.*
import com.zzr.jetpacktest.module_logic.ui.ArticleActivity
import com.zzr.jetpacktest.repository.AppDatabase
import com.zzr.jetpacktest.sputils.SpBase
import com.zzr.jetpacktest.viewmodel.MainViewModel
import com.zzr.jetpacktest.viewmodel.MainViewModelFactory
import com.zzr.jetpacktest.workmanager.SimpleWorker
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.properties.Delegates

class MainActivity : BaseActivity<ActivityMainBinding>() {
    lateinit var viewModel: MainViewModel
    val model: MainViewModel by viewModels()

//    var isVisible by binding.tvMsg.isVisible(keepBounds = false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        setContentView(R.layout.activity_main)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val counter = 10
        viewModel =
            ViewModelProvider(this, MainViewModelFactory(counter)).get(MainViewModel::class.java)

        binding.tvMsg.text = "我是消息内容"

        binding.tvMsg.text = SpBase.contentSomething

        binding.tvMsg.setOnClickListener {
//            Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show()
            "点击了".showToast(this)
            R.string.app_name.showToast(this)
            gotoActivity<TestActivity>(this) {
                putExtra("param1", "hello")
                putExtra("param2", "kotlin")
            }

            SpBase.contentSomething = "hello world"
        }
        binding.gotoTest02Btn.setOnClickListener {
            gotoActivity<Test02Activity<*>>(this)
        }

        getSharedPreferences("data", Context.MODE_PRIVATE).open {
            putString("name", "Tome")
            putInt("age", 28)
        }

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom", 50)
        val user2 = User("Jack", 40)
        binding.addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }
        binding.updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }
        binding.deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByName("Tom")
            }
        }
        binding.queryDataBtn.setOnClickListener {
            thread {
                userDao.loadAllUsers().run {
                    for (user in this) {
                        Log.i(TAG, user.toString())
                    }
                }
            }
        }

        binding.doWorkBtn.setOnClickListener {
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build()
//            val request = PeriodicWorkRequest.Builder(SimpleWorker::class.java,15,TimeUnit.MINUTES).build()
            WorkManager.getInstance(this).enqueue(request)
        }
        binding.gotoArticle.setOnClickListener {
            gotoActivity<ArticleActivity>(this)
        }

        binding.gotoMaterial.setOnClickListener {
            gotoActivity<Test03Activity>(this)
        }

        binding.gotoConstraint.setOnClickListener {
            gotoActivity<Test04Activity>(this)
        }

        binding.gotoItemDecoration.setOnClickListener {
            gotoActivity<Test05Activity>(this)
        }

        binding.gotoDiff.setOnClickListener {
            gotoActivity<Test06Activity>(this)
        }

        binding.gotoWeb.setOnClickListener {
            gotoActivity<TestWebActivity>(this)
        }

        binding.gotoWeb2.setOnClickListener {
            gotoActivity<TestWebActivity2>(this)
        }

        binding.firstA.setOnClickListener {
            gotoActivity<FirstActivity>(this)
        }

        binding.watermark.setOnClickListener {
            gotoActivity<WatermarkActivity>(this)
        }

        binding.camera.setOnClickListener {
            gotoActivity<CameraActivity>(this)
        }

        binding.Staggered.setOnClickListener {
            gotoActivity<StaggeredActivity>(this)
        }

        binding.flexBox.setOnClickListener {
            gotoActivity<FlexBoxActivity>(this)
        }

        binding.nestedScroll.setOnClickListener {
            gotoActivity<NestedScrollActivity>(this)
        }

        binding.nestedScroll2.setOnClickListener {
            gotoActivity<NestedScrollActivity2>(this)
        }

        binding.nestedScroll3.setOnClickListener {
            gotoActivity<NestedScrollActivity3>(this)
        }

        binding.nestedScroll4.setOnClickListener {
            gotoActivity<NestedScrollingActivity4>(this)
        }

        binding.coordinator.setOnClickListener {
            gotoActivity<CoordinatorDemoActivity>(this)
        }

        binding.coroutineDemo.setOnClickListener {
            gotoActivity<CoroutineDemoActivity>(this)
        }

        binding.BottomSheet.setOnClickListener {
            gotoActivity<BottomSheetActivity>(this)
        }

        binding.webReload.setOnClickListener {
            gotoActivity<WebTestActivity>(this)
        }

        binding.serviceTest.setOnClickListener {
            gotoActivity<ServiceActivity>(this)
        }

        binding.fragmentTest.setOnClickListener {
            gotoActivity<FragmentTestActivity>(this)
        }

        binding.navigationDemo.setOnClickListener {
            gotoActivity<NavigationActivity>(this)
        }

        binding.transitionTest.setOnClickListener {
            gotoActivity<TransitionAActivity>(this)
        }

        binding.sceneTransition.setOnClickListener {
            gotoActivity<SceneActivity>(this)
        }

        binding.watermark2.setOnClickListener {
            gotoActivity<WatermarkActivity2>(this)
        }

        binding.netDemo.setOnClickListener {
            gotoActivity<NetDemoActivity>(this)
        }

        binding.navigationDemo2.setOnClickListener {
            gotoActivity<NavigationDemoActivity2>(this)
        }

        binding.layoutManager.setOnClickListener {
            gotoActivity<LayoutManagerTestActivity>(this)
        }

        binding.permissionX.setOnClickListener {
            gotoActivity<PermissionXActivity>(this)
        }

        binding.navigationDemo3.setOnClickListener {
            gotoActivity<NavigationDemoActivity>(this)
        }


    }

    companion object {
        const val TAG = "MainActivity"
    }

    /**
     * 利用Delegates类的委托
     */
    private var backPressedTime by Delegates.observable(0L) { property, oldValue, newValue ->
        //2 次的时间间隔小于2秒就退出了
        if (newValue - oldValue < 2000) {
            finish()
        } else {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onBackPressed() {
        //直接赋值就可以啦
        backPressedTime = System.currentTimeMillis()
    }
}