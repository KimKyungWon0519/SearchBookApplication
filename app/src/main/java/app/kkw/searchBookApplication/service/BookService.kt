import app.kkw.searchBookApplication.model.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("book.json?display=100")
    suspend fun searchBook(
        @Query("query") bookName: String,
        @Query("start") start: Int
    ): Response<BookResponse>
}