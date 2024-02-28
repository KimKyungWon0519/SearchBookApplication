package app.kkw.searchBookApplication.model

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("items") val books: List<Book>
)