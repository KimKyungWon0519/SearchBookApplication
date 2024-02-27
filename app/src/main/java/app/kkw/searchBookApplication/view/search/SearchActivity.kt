package app.kkw.searchBookApplication.view.search

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import app.kkw.searchBookApplication.R
import app.kkw.searchBookApplication.model.Book
import app.kkw.searchBookApplication.view.detailedInfo.DetailedInfoActivity
import app.kkw.searchBookApplication.viewModel.SearchViewModel
import com.google.android.material.textfield.TextInputLayout

class SearchActivity : AppCompatActivity() {
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var bookRecyclerView: RecyclerView
    private lateinit var bookAdapter: BookRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        bookRecyclerView = findViewById(app.kkw.searchBookApplication.R.id.books_recyclerview)
        bookAdapter = BookRecyclerAdapter(::onClickBookItem)

        initializeRecyclerView()

        onClickSearchIcon()

        searchViewModel.bookList.observe(this) {
            val guideText: TextView = findViewById(R.id.guide_text)

            if (it.isFailure) {
                guideText.visibility = View.VISIBLE
                bookRecyclerView.visibility = View.GONE

                guideText.text = it.exceptionOrNull()?.message ?: ""
            } else if (it.isSuccess) {
                guideText.visibility = View.GONE
                bookRecyclerView.visibility = View.VISIBLE
                bookAdapter.update(it.getOrNull() ?: emptyList())
            }
        }
    }

    private fun initializeRecyclerView() {
        bookRecyclerView.adapter = bookAdapter
    }

    private fun onClickBookItem(book: Book) {
        val intent = Intent(applicationContext, DetailedInfoActivity::class.java).apply {
            putExtra("book", book)
        }

        startActivity(intent)
    }

    private fun onClickSearchIcon() {
        val searchIcon: ImageView = findViewById(R.id.search_icon)

        searchIcon.setOnClickListener {
            val searchField: TextInputLayout = findViewById(R.id.search_field)
            val bookName = searchField.editText?.text?.toString() ?: ""

            searchViewModel.searchBook(bookName)
        }
    }
}