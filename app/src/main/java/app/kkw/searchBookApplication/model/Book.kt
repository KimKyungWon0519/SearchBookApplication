package app.kkw.searchBookApplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    @SerializedName("title") val title: String,
    @SerializedName("author") val author: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("pubdate") val publishDate: String,
    @SerializedName("discount") val discount: Int
) : Parcelable {
    companion object {
        fun empty(): Book = Book("", "", "", "", 0)
    }
}

fun Book.isEmpty(): Boolean = title.isEmpty() && author.isEmpty()
