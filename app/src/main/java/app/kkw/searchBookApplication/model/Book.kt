package app.kkw.searchBookApplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val title: String,
    val author: String,
    val publisher: String,
    val publishDate: String,
    val discount: Int
) : Parcelable {
    companion object {
        fun empty(): Book = Book("", "", "", "", 0)
    }
}

fun Book.isEmpty(): Boolean = title.isEmpty() && author.isEmpty()
