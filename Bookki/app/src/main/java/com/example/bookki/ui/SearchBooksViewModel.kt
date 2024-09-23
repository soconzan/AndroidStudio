package com.example.bookki.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookki.api.RetrofitClient
import com.example.bookki.api.SearchBookRes
import com.example.bookki.api.SrchItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchBooksViewModel : ViewModel() {
    val searchBooks = MutableLiveData<List<SrchItem>>()

    fun getSearchBooks(code: String, searchText: String) = viewModelScope.launch(Dispatchers.IO) {
        RetrofitClient.libraryPlaceApi.getSearchBook(code, searchText)
            .enqueue(object : Callback<SearchBookRes> {
                override fun onResponse(call: Call<SearchBookRes>, res: Response<SearchBookRes>) {
                    Log.d("https", "Response code: ${res.body()}")
                    if (res.isSuccessful) {
                        val bookRes = res.body()
                        bookRes?.response?.body.let {
                            val items = it?.items?.item
                            items?.let {
                                searchBooks.postValue(it)
                            }
                        }
                    }
                }

                override fun onFailure(p0: Call<SearchBookRes>, t: Throwable) {
                    Log.e("https", "Error: ${t.message}", t)
                }
            })
    }
}