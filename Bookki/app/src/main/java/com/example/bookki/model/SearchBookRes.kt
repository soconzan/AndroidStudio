package com.example.bookki.api

data class SearchBookRes(
    val response: SrchResponse
)

data class SrchResponse(
    val body: SrchBody
)

data class SrchBody(
    val items: SrchItems,
    val dataType: String,
    val pageNo: Int,
    val numOfRows: Int,
    val totalCount: Int
)

data class SrchItems(
    val item: List<SrchItem>
)

data class SrchItem(
    val BOOK_CODE: String,
    val SPECIES_KEY: String,
    val PUBLISHER: String,
    val ACQ_YEAR: String,
    val LIB_RESERVATION_YN: String,
    val KEYWORD: String,
    val VOL_SORT_NO: String,
    val PUB_YEAR: String,
    val BOOK_STATUS: String,
    val RESERVATION_NUMBER: String,
    val IMAGE: String?,
    val TITLE_INFO: String,
    val SUBJECT_CODE: String,
    val PAGE: String,
    val REG_NO: String,
    val FORM_CODE: String,
    val CALL_NO: String,
    val LOAN_CNT: String,
    val RESERVATION_STATUS: String,
    val AUTHOR: String,
    val ISBN: String,
    val DESCRIPTION: String,
    val SHELF_LOC_CODE: String,
    val LIB_NAME: String,
    val BOOK_KEY: String,
    val ST_CODE: String,
    val CLASS_NO: String,
    val WORKNO: String,
    val CONTENTS_YN: String,
    val CONTROL_NO: String,
    val RESERVE_CODE: String,
    val RESTRICTED_BY_NONCONTACTLOAN: String,
    val LOAN_CHECK: String,
    val LIB_CODE: String
)
