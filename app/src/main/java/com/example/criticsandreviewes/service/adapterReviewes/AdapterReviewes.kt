package com.example.criticsandreviewes.service.adapterReviewes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.criticsandreviewes.databinding.ItemReviewesBinding
import com.example.criticsandreviewes.entity.reviewes.Review

class AdapterReviewes: PagingDataAdapter<Review, ReviewesViewHolder>(ReviewesDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewesViewHolder {
        return ReviewesViewHolder(ItemReviewesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ReviewesViewHolder, position: Int) {
        val item=getItem(position)
        with(holder.binding){
            txtTitleReview.text=item?.displayTitle
            txtDescriptionReview.text=item?.summaryShort
            val date=item?.dateUpdated?.split(" ")?.get(0)?.split("-")
            val time=item?.dateUpdated?.split(" ")?.get(1)
            txtDateReviewes.text="${date?.get(0)}/${date?.get(1)}/${date?.get(2)}"
            txtTimeReviewwes.text=time
            txtNameReviewes.text=item?.byline
            Glide.with(imgReviewes.context).load(item?.multimedia?.src).centerCrop().into(imgReviewes)
        }
    }
}

class ReviewesViewHolder(val binding: ItemReviewesBinding): RecyclerView.ViewHolder(binding.root)

class ReviewesDiffUtil: DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.displayTitle == newItem.displayTitle
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}