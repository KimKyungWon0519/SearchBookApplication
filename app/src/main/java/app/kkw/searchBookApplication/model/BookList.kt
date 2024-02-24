package app.kkw.searchBookApplication.model

import com.google.gson.annotations.SerializedName

data class BookList(
    @SerializedName("items") val books: List<Book>
)