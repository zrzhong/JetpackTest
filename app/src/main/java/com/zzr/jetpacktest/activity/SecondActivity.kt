package com.zzr.jetpacktest.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zzr.jetpacktest.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            val intent = Intent().apply {
                putExtra("result", "Hello，依然范特西稀，我是回传的数据！")
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        intent.getStringExtra("name")?.run {
            findViewById<TextView>(R.id.tvMsg).text = this
        }
    }
}