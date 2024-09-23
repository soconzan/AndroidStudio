package com.example.bookki.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient {
    companion object {
        var gson = GsonBuilder().setLenient().create()

        private val client = Retrofit.Builder()
            .baseUrl("https://apis.data.go.kr/6270000/dgsmartlib/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val bestBookApi: SmartLibraryApi = client.create(SmartLibraryApi::class.java)
        val libraryPlaceApi: SmartLibraryApi = client.create(SmartLibraryApi::class.java)
    }
}