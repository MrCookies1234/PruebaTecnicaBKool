package com.example.pruebatecnicabkool.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.pruebatecnicabkool.databinding.FragmentLaunchDetailBinding
import com.example.pruebatecnicabkool.ui.viewmodel.FragmentDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LaunchDetailFragment : Fragment() {

    private var _binding: FragmentLaunchDetailBinding? = null
    private val binding get() = _binding!!

    private val fragmentDetailViewModel: FragmentDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentLaunchDetailBinding.inflate(layoutInflater)

        subscribeToObservables()
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun subscribeToObservables() {
        lifecycleScope.launch {
            fragmentDetailViewModel.launchDetail.collectLatest {
                binding.txvLaunchDetailName.text = it.name
                Glide.with(requireContext()).load(it.img).into(binding.imvLaunchDetailImg)
            }
        }
        lifecycleScope.launch {
            fragmentDetailViewModel.youtubeIdState.collectLatest { youtubeId ->
                binding.btnPlay.setOnClickListener {
                    findNavController().navigate(LaunchDetailFragmentDirections.actionLaunchDetailFragmentToYoutubeFragment(youtubeId))
                }
            }
        }
    }

}