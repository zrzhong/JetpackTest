package com.zzr.jetpacktest.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.databinding.FragmentPage2Binding
import com.zzr.jetpacktest.utils.inflate

/**
 * @Author zzr
 * @Desc
 * @Date 2021/1/8
 */
class Page2Fragment : Fragment() {
    private val args: Page2FragmentArgs by navArgs()

    private val binding by inflate<FragmentPage2Binding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGotoPage1.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnGotoPage2.setOnClickListener {
           val action =  Page2FragmentDirections.actionFragmentPage2ToFragmentPage3()
//            findNavController().navigate(R.id.action_fragment_page_2_to_fragment_page_3)
            findNavController().navigate(action)
        }

        binding.tvDesc.text = arguments?.getString("key") ?: "Hello Navigation"
//        binding.tvDesc.text = "${args.myString} and ${args.myInteger}"
    }

}