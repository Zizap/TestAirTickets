package com.example.testairtickets.screens.mainScreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testairtickets.adapters.OfferAdapter
import com.example.testairtickets.databinding.FragmentMainBinding
import com.example.testairtickets.screens.searchBottomScreen.SearchBottomSheetFragment
import com.example.testairtickets.util.CyrillicInputFilter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainScreenVM: MainScreenViewModel by viewModel()
    private lateinit var offerAdapter: OfferAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        initRecyclerView()
        loadData()
    }

    private fun setupListeners() {
        val filters = arrayOf(CyrillicInputFilter())

        binding.wheretv.filters = filters
        binding.fromtv.filters = filters


        binding.fromtv.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mainScreenVM.savePrefs(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.wheretv.setOnClickListener {
            openPanelSearch()
        }
    }

    private fun initRecyclerView() {
        binding.recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        offerAdapter = OfferAdapter()
        binding.recycler.adapter = offerAdapter
    }

    private fun loadData() {
        viewLifecycleOwner.lifecycleScope.launch {
            mainScreenVM.mainScreenUiState.collect {
                binding.fromtv.setText(it.city)
                offerAdapter.setList(it.offer)
                if (it.error != null) {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun openPanelSearch() {
        val panelSearch = SearchBottomSheetFragment()
        val parameters = Bundle()

        parameters.putString(ARG_FROM_CITY, binding.fromtv.text.toString())
        panelSearch.arguments = parameters

        panelSearch.show(requireActivity().supportFragmentManager, "panelSearch")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_FROM_CITY = "fromCity"
    }
}