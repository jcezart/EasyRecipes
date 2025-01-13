package com.devspace.myapplication.list.data

import com.devspace.myapplication.BuildConfig
import com.devspace.myapplication.common.model.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ListService {

    @GET("recipes/random?number=20&apiKey=${BuildConfig.API_KEY}")
    suspend fun getRecipesList(): Response<RecipeResponse>
}