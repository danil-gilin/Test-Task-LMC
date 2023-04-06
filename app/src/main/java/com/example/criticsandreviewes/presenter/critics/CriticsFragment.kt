package com.example.criticsandreviewes.presenter.critics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.criticsandreviewes.R
import com.example.criticsandreviewes.databinding.FragmentCriticsBinding
import com.example.criticsandreviewes.entity.Constance
import com.example.criticsandreviewes.entity.critics.Crtitcs
import com.example.criticsandreviewes.service.adapterCritics.AdapterCritics
import com.example.criticsandreviewes.service.adapterReviewes.ReviewesLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class CriticsFragment : Fragment() {

    companion object {
        fun newInstance() = CriticsFragment()
    }

    @Inject
    lateinit var factory:CriticsFactory

    private val viewModel: CriticsViewModel by viewModels { factory }
    lateinit var binding:FragmentCriticsBinding
    private val adapter=AdapterCritics(){ crtitcs -> clickItem(crtitcs) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCriticsBinding.inflate(inflater)
        binding.rcCrititcs.adapter=adapter.withLoadStateFooter(ReviewesLoadStateAdapter())

       viewModel.getCritics("")
        binding.rcCrititcs.layoutManager=GridLayoutManager(requireContext(),2)

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getCritics("")
            binding.swipeRefresh.isRefreshing = false
            binding.searchEditCritics.text?.clear()
        }

        adapter.loadStateFlow.onEach {
            binding.swipeRefresh.isRefreshing = it.refresh == LoadState.Loading
        }.launchIn(viewLifecycleOwner.lifecycleScope)



        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.state.collect{
                when(it){
                    is CriticsState.Loading ->{

                    }
                    is CriticsState.Success ->{
                        it.listCrititcs?.onEach { data->
                            adapter.submitData( data)
                        }?.launchIn(viewLifecycleOwner.lifecycleScope)
                    }
                }
            }
        }

        binding.searchEditCritics.setOnEditorActionListener { searchText, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.getCritics(searchText.text.toString())
                true
            } else {
                false
            }
        }

        return binding.root
    }

    private fun clickItem(crtitcs: Crtitcs) {
        val bundle=Bundle()
        bundle.putString(Constance.CRITIC_IMG,crtitcs.multimedia?.resource?.src ?: "")
        bundle.putString(Constance.CRITIC_NAME,crtitcs.displayName)
        bundle.putString(Constance.CRITIC_BIO,crtitcs.bio)
        bundle.putString(Constance.CRITIC_STATUS ,crtitcs.status)
        findNavController().navigate(R.id.action_criticsFragment_to_criticInfoFragment,bundle)
    }

}