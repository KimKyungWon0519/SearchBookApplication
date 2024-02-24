package app.kkw.searchBookApplication.view.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.kkw.searchBookApplication.R
import app.kkw.searchBookApplication.model.Book
import app.kkw.searchBookApplication.model.isEmpty
import okhttp3.internal.notify

class BookRecyclerAdapter(private val onClickItem: (book: Book) -> Unit) :
    RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder>() {
    private var books: List<Book> = emptyList()

    class ViewHolder(view: View, onClickItem: (book: Book) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val thumbnail: ImageView
        private val title: TextView
        private val author: TextView
        private var currentBook = Book.empty()

        init {
            thumbnail = view.findViewById(R.id.thumbnail)
            title = view.findViewById(R.id.title)
            author = view.findViewById(R.id.author)

            itemView.setOnClickListener {
                if (currentBook.isEmpty()) return@setOnClickListener

                onClickItem(currentBook)
            }
        }

        fun bind(book: Book) {
            title.text = book.title
            author.text = book.author

            currentBook = book
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return ViewHolder(view, onClickItem)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(books[position])
    }

    fun update(books: List<Book>) {
        this.books = books

        notifyDataSetChanged()
    }
}