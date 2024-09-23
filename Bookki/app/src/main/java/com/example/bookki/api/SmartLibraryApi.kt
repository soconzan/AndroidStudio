package com.example.bookki.api

import com.example.bookki.model.BestBookRes
import com.example.bookki.model.LibraryPlaceRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SmartLibraryApi {

    companion object {
        const val SERVICE_KEY: String = "my-api-key"
    }

    @GET("bestBookList")
    fun getBestBooks(
        @Query("code") code: String,
        @Query("pageNo") pageNo: Int = 1,
        @Query("numOfRows") numOfRows: Int = 10,
        @Query("serviceKey") serviceKey: String = SERVICE_KEY
    ): Call<BestBookRes>

    @GET("smartLibPlace")
    suspend fun getLibraryPlace(
        @Query("code") code: String,
        @Query("serviceKey") serviceKey: String = SERVICE_KEY
    ): LibraryPlaceRes

    @GET("bookSearch")
    fun getSearchBook(
        @Query("code") code: String,
        @Query("search_text") searchText: String,
        @Query("pageNo") pageNo: Int = 1,
        @Query("numOfRows") numOfRows: Int = 40,
        @Query("serviceKey") serviceKey: String = SERVICE_KEY
    ): Call<SearchBookRes>

}