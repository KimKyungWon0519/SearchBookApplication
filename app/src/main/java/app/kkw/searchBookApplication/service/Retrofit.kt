package app.kkw.searchBookApplication.service

import BookService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


private val retrofit = Retrofit.Builder().baseUrl("https://openapi.naver.com/v1/search/book.json")
    .addConverterFactory(GsonConverterFactory.create()).build()

val bookService: BookService = retrofit.create<BookService>()