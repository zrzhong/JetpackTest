package com.zzr.jetpacktest.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.databinding.FragmentPage1Binding
import com.zzr.jetpacktest.utils.inflate

/**
 * @Author zzr
 * @Desc
 * @Date 2021/1/8
 */
class Page1Fragment : Fragment() {

    private val binding by inflate<FragmentPage1Binding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val action = Page1FragmentDirections.actionFragmentPage1ToFragmentPage2("hello", 1)
        binding.btnGotoPage2.setOnClickListener {
//            findNavController().navigate(R.id.action_fragment_page_1_to_fragment_page_2)
            findNavController().navigate(action)
        }
        binding.btnBundle.setOnClickListener {
            val bundle = bundleOf("key" to "I am form page1")
            findNavController().navigate(R.id.action_fragment_page_1_to_fragment_page_2, bundle)
        }

        binding.gotoIncludeGraph.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_page_1_to_include_graph)
        }

        binding.gotoGameModule.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_page_1_to_game_module)
        }

    }
}