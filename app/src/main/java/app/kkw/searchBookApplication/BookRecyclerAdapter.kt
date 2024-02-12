package app.kkw.searchBookApplication

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class BookRecyclerAdapter(private val dataSet: Array<String>) :
    RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbnail: ImageView
        val title: TextView
        val author: TextView
        val publishDate: TextView

        init {
            thumbnail = view.findViewById(R.id.thumbnail)
            title = view.findViewById(R.id.title)
            author = view.findViewById(R.id.author)
            publishDate = view.findViewById(R.id.publish_date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}