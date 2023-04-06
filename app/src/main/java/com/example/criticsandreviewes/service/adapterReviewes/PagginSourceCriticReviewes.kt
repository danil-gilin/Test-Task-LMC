package com.example.criticsandreviewes.service.adapterReviewes

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.criticsandreviewes.domain.GetReviewesUseCase
import com.example.criticsandreviewes.entity.reviewes.Review
import javax.inject.Inject

class PagginSourceCriticReviewes (val criticName:String, private val getReviewesUseCase: GetReviewesUseCase) : PagingSource<Int, Review>() {


    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        val offset = params.key ?: INITIAL_KEY
        val limit = params.loadSize

        val rezult= kotlin.runCatching {
            getReviewesUseCase.getReviewesCritic(criticName, offset)
        }.fold(
            onSuccess = {reviews->
                LoadResult.Page(
                    data = reviews.results ?: emptyList(),
                    prevKey = if (offset == 0) null else offset - limit,
                    nextKey = if (reviews.results.isNullOrEmpty() || !reviews.hasMore) null else offset + limit
                )
            },
            onFailure = {error->
                Log.d("PagginSourceCriticReviewes", "onCreateView: ${error.message}")
                LoadResult.Error(error)
            }
        )

        return rezult
    }

    companion object{
        const val  INITIAL_KEY=0
    }
}