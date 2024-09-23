package com.example.bookki.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookki.api.RetrofitClient
import com.example.bookki.model.BestBookRes
import com.example.bookki.model.Item
import com.example.bookki.model.LibItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LibraryInfoViewModel : ViewModel() {
    val bestBooks = MutableLiveData<List<Item>>()

    fun getBestBooks(code: String) = viewModelScope.launch(Dispatchers.IO) {
        RetrofitClient.bestBookApi.getBestBooks(code).enqueue(object : Callback<BestBookRes> {
            override fun onResponse(call: Call<BestBookRes>, res: Response<BestBookRes>) {
                Log.d("https", "Response code: ${res.body()}")
                if (res.isSuccessful) {
                    val bestBookResponse = res.body()
                    bestBookResponse?.response?.body.let {
                        val items = it?.items?.item
                        items?.let {
                            bestBooks.postValue(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<BestBookRes>, t: Throwable) {
                Log.e("https", "Error: ${t.message}", t)
            }
        })
    }

    val libraryPlace = MutableLiveData<LibItem>()

    fun getLibraryPlace(code: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = RetrofitClient.libraryPlaceApi.getLibraryPlace(code)
            response.response.body.items.item.firstOrNull()?.let {
                libraryPlace.postValue(it)
            }
        } catch (e: Exception) {
            Log.e("https", "Exception: ${e.message}", e)
        }
    }
}