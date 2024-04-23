package com.example.testairtickets.screens.searchScreen

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.testairtickets.R
import com.example.testairtickets.databinding.FragmentSearchBinding
import com.example.testairtickets.screens.ticketsScreen.TicketsFragment
import com.example.testairtickets.util.formatDate
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.util.Calendar

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchScreenVM: SearchScreenViewModel by viewModel()

    private var flyTime: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupListeners()
        loadData()
        getTodayDate()
    }

    private fun setupUI() {
        binding.fromtv.setText(arguments?.getString(ARG_FROM_CITY))
        binding.wheretv.setText(arguments?.getString(ARG_DESTINATION))
    }

    private fun setupListeners() {
        binding.textInputLayout.setEndIconOnClickListener {
            val fromText = binding.fromtv.text
            binding.fromtv.text = binding.wheretv.text
            binding.wheretv.text = fromText
        }

        binding.textInputLayout2.setEndIconOnClickListener {
            binding.wheretv.text?.clear()
        }

        binding.buttonBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.dateButton.setOnClickListener { showDatePickerDialog() }

        binding.dateBackButton.setOnClickListener { showDatePickerDialog(isReturnDate = true) }

        binding.roadToTicketsButton.setOnClickListener { ticketsFragmentNavigate() }
    }

    private fun showDatePickerDialog(isReturnDate: Boolean = false) {
        val calendar = if (isReturnDate) Calendar.getInstance() else Calendar.getInstance()
        val selectedDate = Calendar.getInstance()
        val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            selectedDate.set(year, month, dayOfMonth)
            if (!isReturnDate) {
                binding.dateButton.text = formatDate(selectedDate)
                flyTime = formatDate(selectedDate)
            } else {
                binding.dateBackButton.text = formatDate(selectedDate)
            }
        }
        DatePickerDialog(
            requireContext(),
            dateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun loadData() {
        viewLifecycleOwner.lifecycleScope.launch {
            searchScreenVM.searchScreenUiState.collect { uiState ->
                uiState.error?.let {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
                uiState.directFlights.takeIf { it.isNotEmpty() }?.let { directFlights ->
                    binding.apply {
                        directFlights.take(3).forEachIndexed { index, flight ->
                            val textViewCompany = when (index) {
                                0 -> company
                                1 -> company2
                                else -> company3
                            }
                            val textViewPrice = when (index) {
                                0 -> price
                                1 -> price2
                                else -> price3
                            }
                            val textViewTimeFly = when (index) {
                                0 -> timeFly
                                1 -> timeFly2
                                else -> timeFly3
                            }
                            textViewCompany.text = flight.title
                            textViewPrice.text = NumberFormat.getInstance().format(flight.price.value)
                            textViewTimeFly.text = flight.timeRange.joinToString(separator = " ")
                        }
                    }
                }
            }
        }
    }

    private fun getTodayDate() {
        val calendar = Calendar.getInstance()
        binding.dateButton.text = formatDate(calendar)
        flyTime = formatDate(calendar)
    }

    private fun ticketsFragmentNavigate() {
        val fragment = TicketsFragment()
        val parameters = Bundle().apply {
            if (binding.fromtv.text.toString() != "" && binding.wheretv.text.toString() != "") {
                putString(ARG_DESTINATION, "${binding.fromtv.text}-${binding.wheretv.text}")
            } else {
                putString(
                    ARG_DESTINATION, "${arguments?.getString(ARG_FROM_CITY)}-${arguments?.getString(ARG_DESTINATION)}"
                )
            }
            putString(ARG_FLY_TIME, "$flyTime, 1 пассажир")
        }
        fragment.arguments = parameters

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.mainContent, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_FROM_CITY = "fromCity"
        const val ARG_DESTINATION = "destination"
        const val ARG_FLY_TIME = "flyTime"
    }
}
