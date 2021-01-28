package com.zzr.jetpacktest.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.zzr.jetpacktest.utils.SimpleStatusBar
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import java.lang.reflect.ParameterizedType

/**
 * @Author zzr
 * @Desc 协程作用域 ViewBinding
 * @Date 2020/9/2
 */
open class BaseActivity<VB : ViewBinding> : AppCompatActivity(), CoroutineScope by MainScope() {
    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //利用反射，ViewBinding 再调用指定ViewBinding中的inflate方法填充视图
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        binding = method.invoke(null, layoutInflater) as VB
        setContentView(binding.root)
//        SimpleStatusBar.setStatusBar(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        //取消协程
        cancel()
    }

    /**
     * 定义View 只能点一次的扩展方法
     */
    fun View.setOnceClick(block: (view: View) -> Unit) {
        /**
         * 定义 协程 的一个消费者模式
         */
        val eventActor = actor<View>(Dispatchers.Main) {
            // 这里注意，协程 channel 若没有数据，会处于 挂起 状态。直到有数过来才会执行
            for (view in channel) {
                block(view)
                // 500 毫秒 才能接受下一次的点击
                delay(500)
            }
        }
        setOnClickListener {
            /**
             * 发送输出,若消费者,没有消费等待数据,发送数据就会失败
             */
            eventActor.offer(it)
        }
    }
}