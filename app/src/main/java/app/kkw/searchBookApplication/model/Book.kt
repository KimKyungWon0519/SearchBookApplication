package app.kkw.searchBookApplication.model

data class Book(
    val title: String,
    val author: String,
) {
    companion object {
        fun empty(): Book = Book("", "",)
    }
}

fun Book.isEmpty(): Boolean = title.isEmpty() && author.isEmpty()
