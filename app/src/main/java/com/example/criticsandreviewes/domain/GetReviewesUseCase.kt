package com.example.criticsandreviewes.domain

import com.example.criticsandreviewes.data.repositry.ReviewesRepository
import javax.inject.Inject

class GetReviewesUseCase @Inject constructor(private val reviewesRepository: ReviewesRepository) {



    suspend fun getReviewesSearch(query: String, publication_date: String, offset: Int) = reviewesRepository.getReviewesSearch(query, publication_date, offset)

    suspend fun getReviewesCritic(criticName: String, offset: Int) = reviewesRepository.getReviewesCritic(criticName, offset)
}
