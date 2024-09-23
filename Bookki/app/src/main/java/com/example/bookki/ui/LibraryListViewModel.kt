package com.example.bookki.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookki.api.RetrofitClient
import com.example.bookki.model.LibItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class LibraryListViewModel : ViewModel() {
    val libraries = MutableLiveData<List<LibItem>>()
    val codes = listOf(
        "CB08", "CB10", "BA08", "AH17", "BT10", "BR08", "CA08", "BN13",
        "BA23", "AA51", "AB35", "AD39", "AD40", "AG40", "BA22", "BD10",
        "BE18", "FG07", "FS02"
    )

    fun getLibraries() = viewModelScope.launch(Dispatchers.IO) {
        val libItems = MutableList<LibItem?>(codes.size) { null }
        val deferreds = codes.mapIndexed { index, code ->
            async {
                try {
                    val response = RetrofitClient.libraryPlaceApi.getLibraryPlace(code)
                    response.response.body.items.item.firstOrNull()?.let {
                        libItems[index] = it
                    }
                } catch (e: Exception) {
                    Log.e("https", "Exception: ${e.message}", e)
                }
            }
        }
        deferreds.awaitAll()
        libraries.postValue(libItems.filterNotNull())
        Log.d("libraries", "${libItems.filterNotNull()}")
    }
}