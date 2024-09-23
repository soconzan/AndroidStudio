package com.example.bookki.model

data class BestBookRes(
    val response: Response
)

data class Response (
    val header: Header,
    val body: Body
)

data class Header(
    val resultCode: String,
    val resultMsg: String
)

data class Body(
    val items: Items,
    val dataType: String,
    val pageNo: Int,
    val numOfRows: Int,
    val totalCount: Int
)

data class Items(
    val item: List<Item>
)

data class Item(
    val RANK: String,
    val BOOK_TYPE: String,
    val REG_NO: String,
    val TITLE: String,
    val AUTHOR: String,
    val PUBLISHER: String,
    val CALL_NO: String,
    val ISBN: String,
    val WORKING_STATUS: String,
    val LOAN_CHECK: String,
    val RESERVATION_STATUS: String,
    val USE_LIMIT_CODE: String,
    val LIB_RESERVATION_YN: String,
    val RESERVATION_NUMBER: String,
    val RESERVATION_CNT: String,
    val LILL_YN: String,
    val SHELF_LOC_CODE: String,
    val SHELF_LOC_NAME: String,
    val CNT: String,
    val PUBLISH_YEAR: String,
    val MANAGE_CODE: String,
    val REG_CODE: String,
    val SUBJECT_CODE: String,
    val LIB_NAME: String,
    val BOOK_KEY: String,
    val LIB_RESERVATION_YN2: String,
    val RESTRICTED_BY_CODE_RESERVE: String,
    val RESTRICTED_BY_LOAN: String,
    val RESTRICTED_BY_NONCONTACTLOAN: String,
    val LOAN_CNT: String,
    val ALREADY_NON_CONTACT_LOAN: String,
    val BOOK_STATUS: String,
    val RETURN_PLAN_DATE: String,
    val RESERVE_CODE: String,
    val LOAN_CODE: String,
    val KBILL_LILL_YN: String,
    val NON_CONTACT_YN: String,
    val IMAGE: String?
)
