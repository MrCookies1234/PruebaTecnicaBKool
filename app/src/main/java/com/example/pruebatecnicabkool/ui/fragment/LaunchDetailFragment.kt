package com.example.pruebatecnicabkool.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.pruebatecnicabkool.databinding.FragmentLaunchDetailBinding
import com.example.pruebatecnicabkool.ui.viewmodel.FragmentDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchDetailFragment : Fragment() {

    private var _binding : FragmentLaunchDetailBinding? = null
    private val binding get() = _binding!!

    private val args : LaunchDetailFragmentArgs by navArgs()

    private val fragmentDetailViewModel : FragmentDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentLaunchDetailBinding.inflate(layoutInflater)
        binding.txvId.text = args.launchId
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}