package com.example.criticsandreviewes.service.adapterReviewes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.criticsandreviewes.databinding.LoadStateBinding

class ReviewesLoadStateAdapter:LoadStateAdapter<ReviewesLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: ReviewesLoadStateViewHolder, loadState: LoadState) {
        Unit
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ReviewesLoadStateViewHolder {
        val binding = LoadStateBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ReviewesLoadStateViewHolder(binding)
    }

}


class ReviewesLoadStateViewHolder(binding: LoadStateBinding):RecyclerView.ViewHolder(binding.root)