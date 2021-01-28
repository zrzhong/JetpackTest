package com.zzr.jetpacktest.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.databinding.FragmentPage3Binding
import com.zzr.jetpacktest.utils.inflate

/**
 * @Author zzr
 * @Desc
 * @Date 2021/1/8
 */
class Page3Fragment : Fragment() {
    private val binding by inflate<FragmentPage3Binding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGotoPage2.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnGotoActivity.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_page_3_to_coordinatorDemo2Activity)
        }

        binding.gotoNestedFragment.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_page_3_to_navigation)
        }
    }

}