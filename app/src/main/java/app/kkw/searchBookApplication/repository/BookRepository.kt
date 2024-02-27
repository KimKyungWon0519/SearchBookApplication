package app.kkw.searchBookApplication.repository

import app.kkw.searchBookApplication.model.Book
import app.kkw.searchBookApplication.service.bookService

class BookRepository {
    suspend fun searchBook(bookName: String): List<Book> {
        val response = bookService.searchBook(bookName)

        if (response.isSuccessful) {
            val bookList = response.body()

            if (bookList != null) {
                return bookList.books.map {
                    it.copy(author = it.author.replace("^", ", "))
                }
            }
        }

        throw Exception("검색 결과가 없습니다.")
    }
}