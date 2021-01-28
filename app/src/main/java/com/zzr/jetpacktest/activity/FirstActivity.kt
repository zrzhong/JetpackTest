package com.zzr.jetpacktest.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.zzr.jetpacktest.R

class FirstActivity : AppCompatActivity() {
    private lateinit var tvMsg: TextView

    private val myActivityLauncher =
        registerForActivityResult(MyActivityResultContract()) { result ->
            Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
            tvMsg.text = "回传数据：$result"
        }

    private val myActivityLauncher2 =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val result = activityResult.data?.getStringExtra("result")
                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                tvMsg.text = "回传数据：$result"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        tvMsg = findViewById(R.id.tvMsg)

        findViewById<View>(R.id.gotoSecond).setOnClickListener {
            myActivityLauncher.launch("hello")
        }

        findViewById<View>(R.id.gotoSecond2).setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("name", "Hello,技术最TOP")
            }
            myActivityLauncher2.launch(intent)
        }

    }
}