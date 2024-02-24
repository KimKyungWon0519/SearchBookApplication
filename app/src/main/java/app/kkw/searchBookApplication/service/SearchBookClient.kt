package app.kkw.searchBookApplication.service

import okhttp3.OkHttpClient

object SearchBookClient {
    private val client: OkHttpClient

    init {
        val url = "https://openapi.naver.com/v1/search/book.json"

        client = OkHttpClient()
    }
}