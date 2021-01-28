package com.zzr.jetpacktest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.adapter.TestAdapter
import com.zzr.jetpacktest.databinding.FragmentDemoBinding

/**
 * @Author zzr
 * @Desc
 * @Date 2020/11/23
 */
class DemoFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    companion object {
        fun newInstance() = DemoFragment()
    }

    private lateinit var binding: FragmentDemoBinding
    private val testAdapter: TestAdapter by lazy {
        TestAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvContent.layoutManager = LinearLayoutManager(context)
        binding.rvContent.adapter = testAdapter
        val data = mutableListOf<String>()
        repeat(50) {
            data.add("Item-${it + 1}")
        }
        testAdapter.setNewInstance(data)
    }
}