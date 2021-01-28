package com.zzr.jetpacktest.activity

import android.os.Bundle
import com.zzr.jetpacktest.databinding.ActivityServiceBinding
import com.zzr.jetpacktest.service.MyIntentService

class ServiceActivity : BaseActivity<ActivityServiceBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_service)
        binding.btnStartService.setOnClickListener {
            MyIntentService.startActionFoo(this, "a", "b")
        }
    }
}