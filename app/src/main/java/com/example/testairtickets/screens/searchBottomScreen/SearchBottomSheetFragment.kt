package com.example.testairtickets.screens.searchBottomScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.example.testairtickets.R
import com.example.testairtickets.databinding.SearchBottomSheetFragmentBinding
import com.example.testairtickets.screens.searchScreen.SearchFragment
import com.example.testairtickets.screens.stubScreen.StubFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SearchBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: SearchBottomSheetFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchBottomSheetFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        if (arguments?.getString(ARG_FROM_CITY) == "") {
            binding.fromtv.setText("Москва")
        } else {
            binding.fromtv.setText(arguments?.getString(ARG_FROM_CITY))
        }

    }

    private fun setupListeners() {
        binding.wheretv.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                if (binding.wheretv.text?.isNotEmpty() != false) {
                    searchFragmentNavigate()
                    dismiss()
                }

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        clickToolButton(
            listOf(
                binding.toolButton1,
                binding.toolButton2,
                binding.toolButton3,
                binding.toolButton4
            )
        )

        binding.place1.setOnClickListener {
            binding.wheretv.setText(binding.placeName1.text)
            searchFragmentNavigate()
            dismiss()
        }

        binding.place2.setOnClickListener {
            binding.wheretv.setText(binding.placeName2.text)
            searchFragmentNavigate()
            dismiss()
        }

        binding.place3.setOnClickListener {
            binding.wheretv.setText(binding.placeName3.text)
            searchFragmentNavigate()
            dismiss()
        }
    }

    private fun clickToolButton(listView: List<View>) {
        listView.forEach { view ->
            when (view) {
                binding.toolButton2 -> {
                    view.setOnClickListener {
                        binding.wheretv.setText(getString(R.string.anywhere))
                        searchFragmentNavigate()
                        dismiss()
                    }

                }

                else -> {
                    view.setOnClickListener {
                        stubNavigate()
                        dismiss()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun searchFragmentNavigate() {
        val fragment = SearchFragment()
        val parameters = Bundle()

        parameters.putString(ARG_FROM_CITY, binding.fromtv.text.toString())
        parameters.putString(ARG_DESTINATION, binding.wheretv.text.toString())
        fragment.arguments = parameters

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainContent, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun stubNavigate() {
        val fragment = StubFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainContent, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        const val ARG_DESTINATION = "destination"
        const val ARG_FROM_CITY = "fromCity"
    }

}