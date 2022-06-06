package com.example.pruebatecnicabkool.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.pruebatecnicabkool.R
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

                var dateText = "${resources.getText(R.string.date_launched)} "

                if(it.success!!){
                    Glide.with(requireContext()).load(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_check_24)).into(binding.imvLaunchDetailSuccess)
                }else{
                    Glide.with(requireContext()).load(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_close_24)).into(binding.imvLaunchDetailSuccess)
                }

                dateText += it.static_fire_date_unix

                binding.txvLaunchDetailDate.text = dateText

                if(it.details == "" || it.details == null){
                    binding.txvLaunchDetailInfo.text = resources.getText(R.string.details_not_available)
                }else {
                    binding.txvLaunchDetailInfo.text = it.details
                }

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