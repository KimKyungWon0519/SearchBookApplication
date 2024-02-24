import app.kkw.searchBookApplication.model.BookList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("book.json")
    suspend fun searchBook(
        @Query("query") bookName: String
    ): Response<BookList>
}