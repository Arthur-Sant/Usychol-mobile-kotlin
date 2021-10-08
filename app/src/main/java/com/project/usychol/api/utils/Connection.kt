package com.project.usychol.api.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Connection {
    companion object {
        const val BASE_URL = "https://6155212b2473940017efb080.mockapi.io/usychol/api/v1/"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}