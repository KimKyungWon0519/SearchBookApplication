package app.kkw.searchBookApplication.service

import BookService
import app.kkw.searchBookApplication.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
    .addInterceptor {
        val request =
            it.request().newBuilder().addHeader("X-Naver-Client-Id", BuildConfig.NAVER_CLIENT_ID)
                .addHeader("X-Naver-Client-Secret", BuildConfig.NAVER_CLIENT_SECRET).build()
        
        it.proceed(request)
    }
    .build()

private val retrofit = Retrofit.Builder().baseUrl("https://openapi.naver.com/v1/search/")
    .addConverterFactory(GsonConverterFactory.create()).client(client).build()

val bookService: BookService = retrofit.create<BookService>()