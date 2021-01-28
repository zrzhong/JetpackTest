package com.zzr.jetpacktest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.transition.TransitionInflater
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.databinding.TestFragmentBinding

class TestFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    companion object {
        fun newInstance() = TestFragment()
    }

    //activity范围内共享的viewModel
    private val viewModel: TestViewModel by activityViewModels()
    private lateinit var binding: TestFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TestFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val param = requireArguments().getString("param")
        param?.let {
            binding.btnSetValue.text = it
        }

        binding.btnSetValue.setOnClickListener {
            viewModel.setNewValue()
        }

        val imageView = view.findViewById<ImageView>(R.id.item_image)
        ViewCompat.setTransitionName(imageView, "item_image")
    }

}