package app.kkw.searchBookApplication.view.detailedInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import app.kkw.searchBookApplication.R

class DetailedInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_info)

        showNavigationIcon()
        onClickNavigationIcon()
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
}