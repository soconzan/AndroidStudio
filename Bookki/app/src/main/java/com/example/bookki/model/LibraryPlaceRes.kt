package com.example.bookki.model

data class LibraryPlaceRes(
    val response: LibResponse
)

data class LibResponse(
    val body: LibBody
)

data class LibBody(
    val items: LibItems
)

data class LibItems(
    val item: List<LibItem>
)

data class LibItem(
    val LOCATION: String,
    val CODE: String,
    val LONGITUDE: String,
    val LATITUDE: String,
    val LIBRARY: String
)