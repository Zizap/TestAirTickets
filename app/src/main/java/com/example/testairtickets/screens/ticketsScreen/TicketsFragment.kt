package com.example.testairtickets.screens.ticketsScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testairtickets.R
import com.example.testairtickets.adapters.OfferAdapter
import com.example.testairtickets.adapters.TicketsAdapter
import com.example.testairtickets.databinding.FragmentStubBinding
import com.example.testairtickets.databinding.FragmentTicketsBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class TicketsFragment : Fragment() {

    private var _binding: FragmentTicketsBinding? = null
    private val binding get() = _binding!!

    private val ticketsScreenViewModel: TicketsScreenViewModel by viewModel()

    private lateinit var ticketsAdapter: TicketsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        initRecyclerView()
        loadData()
        setupUI()
    }

    private fun setupUI() {
        binding.cruise.text = arguments?.getString(ARG_DESTINATION)
        binding.dateCruise.text = arguments?.getString(ARG_FLY_TIME)
    }

    private fun setupListeners() {
        binding.buttonBack.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            if (fragmentManager.backStackEntryCount > 0) {
                fragmentManager.popBackStack()
                onDestroy()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView() {
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        ticketsAdapter = TicketsAdapter()
        binding.recycler.adapter = ticketsAdapter
    }

    private fun loadData() {
        viewLifecycleOwner.lifecycleScope.launch {
            ticketsScreenViewModel.ticketsScreenUiState.collect {
                if (it.error != null) {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                } else {
                    ticketsAdapter.setList(it.ticketsList)
                }
            }
        }
    }

    companion object {
        const val ARG_DESTINATION = "destination"
        const val ARG_FLY_TIME = "flyTime"
    }

}