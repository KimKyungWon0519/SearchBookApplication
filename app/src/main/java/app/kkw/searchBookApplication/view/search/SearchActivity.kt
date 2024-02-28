package app.kkw.searchBookApplication.view.search

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
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
        bookRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    searchViewModel.getMoreResult()
                }
            }
        })
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

            val inputMethodManager: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(searchField.windowToken, 0)

            searchViewModel.searchBook(bookName)
        }
    }
}