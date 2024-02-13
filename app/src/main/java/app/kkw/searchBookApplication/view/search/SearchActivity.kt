package app.kkw.searchBookApplication.view.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import app.kkw.searchBookApplication.R
import app.kkw.searchBookApplication.model.Book

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initializeRecyclerView();
    }

    private fun initializeRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.books_recyclerview)
        val books: List<Book> = (1..100).map {
            Book(
                title = "책 이름 - $it",
                author = "저자 이름 - $it",
            )
        }

        recyclerView.adapter = BookRecyclerAdapter(books)
    }
}