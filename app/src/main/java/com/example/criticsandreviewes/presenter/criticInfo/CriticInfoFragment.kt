package com.example.criticsandreviewes.presenter.criticInfo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.bumptech.glide.Glide
import com.example.criticsandreviewes.databinding.FragmentCriticInfoBinding
import com.example.criticsandreviewes.entity.Constance
import com.example.criticsandreviewes.service.adapterReviewes.AdapterReviewes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class CriticInfoFragment : Fragment() {

    companion object {
        fun newInstance() = CriticInfoFragment()
    }

    @Inject
    lateinit var factory: CriticInfoFactory

    private val viewModel: CriticInfoViewModel by viewModels { factory }
    lateinit var binding: FragmentCriticInfoBinding
    private val adapter= AdapterReviewes()
    var nameCritic=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCriticInfoBinding.inflate(inflater)
        binding.rcCriticReviewes.adapter = adapter

        arguments.let {
             val img=it?.getString(Constance.CRITIC_IMG,"")!!
            val name= it.getString(Constance.CRITIC_NAME,"")!!
            val bio= it.getString(Constance.CRITIC_BIO,"")!!
            val status= it.getString(Constance.CRITIC_STATUS,"")!!

            nameCritic=name

            init(img,name,bio,status)
        }


        binding.swipeRefreshCritic.setOnRefreshListener {
            viewModel.getCriticReviewes(nameCritic)
            binding.swipeRefreshCritic.isRefreshing = false
        }

        adapter.loadStateFlow.onEach {
            binding.swipeRefreshCritic.isRefreshing = it.refresh == LoadState.Loading
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.state.collect{
                when(it){
                    CriticInfoState.Loading -> {
                        //------------
                    }
                    is CriticInfoState.Success -> {
                        it.listCriticReviewes?.onEach { pagingData ->
                            adapter.submitData(pagingData)
                        }?.launchIn(viewLifecycleOwner.lifecycleScope)
                    }
                }
            }
        }



        return binding.root
    }

    fun init(img: String, name: String, bio: String, status: String) {
        binding.toolbarTitleCritic.text = name
        binding.txtCriticFullName.text =name
        if (status == "") {
            binding.txtCriticTimeWork.visibility = View.GONE
        } else {
            binding.txtCriticTimeWork.text = status
        }
        if (bio == "") {
            binding.txtCriticDecrtiption.visibility = View.GONE
        } else {
            val biotemp = bio.replace("\n<br/><br/>", "")
            binding.txtCriticDecrtiption.text =biotemp
        }
        if(img != ""){
            Glide.with(requireContext()).load(img).centerCrop().into(binding.imgCriticFullInfo)
        }
        viewModel.getCriticReviewes(name)
    }


}