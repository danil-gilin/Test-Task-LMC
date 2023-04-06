package com.example.criticsandreviewes.data.repositry


import android.util.Log
import com.example.criticsandreviewes.data.api.RetrofitReviewAndCritics
import com.example.criticsandreviewes.entity.reviewes.Review
import com.example.criticsandreviewes.entity.reviewes.ReviewesRezult
import kotlinx.coroutines.delay
import javax.inject.Inject

class ReviewesRepository @Inject constructor() {

    suspend fun getReviewesSearch(query: String, publicationDate: String, offset: Int): ReviewesRezult {
        try {
            val rezult= if (query==""){
                RetrofitReviewAndCritics.reviewesApi.getReviewesSearch(query, publicationDate, offset)
            }else{
                RetrofitReviewAndCritics.reviewesApi.getReviewesSearchNotEmpty(query, publicationDate, offset)
            }
            return rezult
        }catch (e:Exception){
            Log.d("ReviewesRepository", "getReviewesSearch: ${e.message}")
            delay(DELEY_FOR_THE_REQUEST)
            val rezult= if (query==""){
                RetrofitReviewAndCritics.reviewesApi.getReviewesSearch(query, publicationDate, offset)
            }else{
                RetrofitReviewAndCritics.reviewesApi.getReviewesSearchNotEmpty(query, publicationDate, offset)
            }
            return rezult
        }

    }

   suspend fun getReviewesCritic(criticName: String, offset: Int): ReviewesRezult {
        try {
            val rezult= RetrofitReviewAndCritics.reviewesApi.getReviewesCritic(criticName, offset)
            return rezult
        }catch (e:Exception){
            Log.d("ReviewesRepository", "getReviewesCritic: ${e.message}")
            delay(DELEY_FOR_THE_REQUEST)
            return RetrofitReviewAndCritics.reviewesApi.getReviewesCritic(criticName, offset)
        }

    }

    companion object{
        const val DELEY_FOR_THE_REQUEST=60000L
    }


}