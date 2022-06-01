package com.example.pruebatecnicabkool.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebatecnicabkool.adapter.CrewAdapter
import com.example.pruebatecnicabkool.adapter.ShipAdapter
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

    private lateinit var crewAdapter: CrewAdapter
    private lateinit var shipAdapter: ShipAdapter

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentLaunchDetailBinding.inflate(layoutInflater)
        setUpRecyclerView()
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
                crewAdapter.submitList(it.crew)
                shipAdapter.submitList(it.ships)
            }
        }
    }

    private fun setUpRecyclerView(){
        binding.rcvCrew.apply {
            crewAdapter = CrewAdapter()
            adapter = crewAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.rcvShips.apply {
            shipAdapter = ShipAdapter()
            adapter = shipAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

}