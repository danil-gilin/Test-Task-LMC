package com.example.criticsandreviewes.presenter.reviewes

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.criticsandreviewes.R
import com.example.criticsandreviewes.databinding.FragmentReviewesBinding
import com.example.criticsandreviewes.service.adapterReviewes.AdapterReviewes
import com.example.criticsandreviewes.service.adapterReviewes.ReviewesLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class ReviewesFragment : Fragment() {

    companion object {
        fun newInstance() = ReviewesFragment()
    }

    @Inject
    lateinit var factory: ReviewesFactory


    private val viewModel: ReviewesViewModel by viewModels { factory }
    lateinit var binding: FragmentReviewesBinding
    private val adapterReviewes = AdapterReviewes()
    private val calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewesBinding.inflate(inflater)
        binding.rcReviewes.adapter = adapterReviewes.withLoadStateFooter(ReviewesLoadStateAdapter())
        setDate(year, month, dayOfMonth)
        viewModel.getReviewesSearch(binding.searchEdit.text.toString(), binding.txtDate.text.toString())

      binding.swipeRefresh.setOnRefreshListener {
          viewModel.getReviewesSearch(binding.searchEdit.text.toString(), binding.txtDate.text.toString())
            binding.swipeRefresh.isRefreshing = false
        }

        adapterReviewes.loadStateFlow.onEach {
            binding.swipeRefresh.isRefreshing = it.refresh == LoadState.Loading
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated{
            viewModel.state.collect {
                when (it) {
                    is ReviewesState.Loading -> {

                    }
                    is ReviewesState.Success -> {
                        it.listSearchPaggin?.onEach {data->
                            adapterReviewes.submitData(data)
                        }?.launchIn(viewLifecycleOwner.lifecycleScope)
                    }
                    is ReviewesState.Error -> {
                        Log.d("ReviewesFragment", "onCreateView: ${it.error}")
                    }
                }
            }
        }

        binding.dateLinear.setOnClickListener {
            val date=binding.txtDate.text.toString().split("/")
            val yearlast=date[0].trim().toInt()
            val monthlast=date[1].trim().toInt()-1
            val dayOfMonthlast=date[2].trim().toInt()
            val datePicker = DatePickerDialog(
                requireContext(),
                R.style.OrangeDatePickerDialogTheme,
                { view, year, month, dayOfMonth ->
                    setDate(year, month, dayOfMonth)
                },
                yearlast,
                monthlast,
                dayOfMonthlast
            )
            datePicker.show()
        }

        binding.searchEdit.setOnEditorActionListener { searchText, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.getReviewesSearch(
                    searchText.text.toString(),
                    binding.txtDate.text.toString()
                )
                true
            } else {
                false
            }
        }


        return binding.root
    }

    private fun setDate(year: Int, month: Int, dayOfMonth: Int) {
        if (month < 10 && dayOfMonth > 10)
            binding.txtDate.text = "$year / 0${month + 1} / $dayOfMonth"
        else if (dayOfMonth < 10 && month > 10)
            binding.txtDate.text = "$year / ${month + 1} / 0$dayOfMonth"
        else if (month < 10 && dayOfMonth < 10)
            binding.txtDate.text = "$year / 0${month + 1} / 0$dayOfMonth"
        else
            binding.txtDate.text = "$year / ${month + 1} / $dayOfMonth"
    }
}