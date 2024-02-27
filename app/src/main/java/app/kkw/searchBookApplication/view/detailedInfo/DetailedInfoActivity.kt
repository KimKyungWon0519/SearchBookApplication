package app.kkw.searchBookApplication.view.detailedInfo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import app.kkw.searchBookApplication.R
import app.kkw.searchBookApplication.model.Book
import com.bumptech.glide.Glide

class DetailedInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_info)

        showNavigationIcon()
        onClickNavigationIcon()
        initializeDetailedInfoOfBook()
    }


    private fun showNavigationIcon() {
        val toolbar: Toolbar = findViewById(R.id.book_name_toolbar)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
    }

    private fun onClickNavigationIcon() {
        val toolbar: Toolbar = findViewById(R.id.book_name_toolbar)

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun getBookFromIntent(): Book {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("book", Book::class.java) as Book
        } else {
            intent.getParcelableExtra("book") as? Book ?: Book.empty()
        }
    }

    private fun initializeDetailedInfoOfBook() {
        val book: Book = getBookFromIntent()

        val toolbar: Toolbar = findViewById(R.id.book_name_toolbar)
        val authorTextView: TextView = findViewById(R.id.author)
        val publisherTextView: TextView = findViewById(R.id.publisher)
        val publishDateTextView: TextView = findViewById(R.id.publish_date)
        val discountTextView: TextView = findViewById(R.id.discount)
        val bookImage: ImageView = findViewById(R.id.book_image)

        with(book) {
            toolbar.title = title
            authorTextView.text = author
            publisherTextView.text = publisher
            publishDateTextView.text = publishDate
            discountTextView.text = discount.toString()
            Glide.with(this@DetailedInfoActivity).load(book.imagePath).into(bookImage)
        }
    }
}