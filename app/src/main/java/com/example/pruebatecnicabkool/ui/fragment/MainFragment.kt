package com.example.pruebatecnicabkool.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebatecnicabkool.adapter.LaunchAdapter
import com.example.pruebatecnicabkool.databinding.FragmentMainBinding
import com.example.pruebatecnicabkool.ui.viewmodel.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel : MainFragmentViewModel by viewModels()

    private lateinit var launchAdapter: LaunchAdapter

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        setUpRecyclerView()
        subscribeToObservables()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpRecyclerView(){
        binding.rcvLaunches.apply {
            launchAdapter = LaunchAdapter()
            adapter = launchAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun subscribeToObservables(){
        lifecycleScope.launchWhenStarted {
            mainViewModel.launchEntries.collectLatest {
                launchAdapter.submitList(it)
            }
        }
    }

}