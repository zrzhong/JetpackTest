package com.zzr.jetpacktest.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.databinding.ActivityFragmentTestBinding
import com.zzr.jetpacktest.fragment.DemoFragment
import com.zzr.jetpacktest.fragment.TestFragment
import com.zzr.jetpacktest.fragment.TestFragment2
import com.zzr.jetpacktest.fragment.TestViewModel

class FragmentTestActivity : BaseActivity<ActivityFragmentTestBinding>() {

    private val viewModel: TestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val bundle = bundleOf("param" to "hello param")
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<TestFragment>(R.id.container, tag = "TestFragment", args = bundle)
            }
        }
        val testFragment = TestFragment.newInstance()
        binding.tvSetValue.setOnClickListener {
            val demoFragment = TestFragment2.newInstance("","")
            supportFragmentManager.commit {
//                setCustomAnimations(
//                     R.anim.slide_in,
//                     R.anim.fade_out,
//                     R.anim.fade_in,
//                     R.anim.slide_out
//                )
//                addSharedElement(testFragment)
                replace(R.id.container, demoFragment)
                addToBackStack(null)
            }
        }
        viewModel.selectedData.observe(this) { newValue ->
            binding.tvSetValue.text = newValue
        }
    }
}