package com.example.criticsandreviewes.service.adapterCritics

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.criticsandreviewes.R
import com.example.criticsandreviewes.databinding.CriticItemBinding
import com.example.criticsandreviewes.entity.critics.Crtitcs

class AdapterCritics(val click: (Crtitcs)->Unit) : PagingDataAdapter<Crtitcs, CriticsViewHolder>(CriticsDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CriticsViewHolder {
        return CriticsViewHolder(CriticItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CriticsViewHolder, position: Int) {
        val item=getItem(position)
        with(holder.binding){
            Log.d("imgCritic", "onBindViewHolder: ${item?.multimedia?.resource?.src}")

            if(item?.multimedia !=null){
                Glide.with(imgCritic).load(item?.multimedia?.resource?.src).centerCrop().into(imgCritic)
            }
            txtCriticName.text=item?.displayName

            root.setOnClickListener {
                click(item!!)
            }
        }
    }

}


class CriticsViewHolder(val binding:CriticItemBinding): RecyclerView.ViewHolder(binding.root)

class CriticsDiffUtil: DiffUtil.ItemCallback<Crtitcs>() {
    override fun areItemsTheSame(oldItem: Crtitcs, newItem: Crtitcs): Boolean {
        return oldItem.displayName == newItem.displayName
    }

    override fun areContentsTheSame(oldItem: Crtitcs, newItem: Crtitcs): Boolean {
        return oldItem == newItem
    }
}