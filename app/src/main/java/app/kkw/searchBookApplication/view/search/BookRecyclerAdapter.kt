package app.kkw.searchBookApplication.view.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.kkw.searchBookApplication.R
import app.kkw.searchBookApplication.model.Book

class BookRecyclerAdapter(private val books: List<Book>) :
    RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val thumbnail: ImageView
        private val title: TextView
        private val author: TextView

        init {
            thumbnail = view.findViewById(R.id.thumbnail)
            title = view.findViewById(R.id.title)
            author = view.findViewById(R.id.author)
        }

        fun bind(book: Book) {
            title.text = book.title
            author.text = book.author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return books.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(books[position])
    }
}