package app.kkw.searchBookApplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kkw.searchBookApplication.model.Book
import app.kkw.searchBookApplication.model.BookList
import app.kkw.searchBookApplication.repository.BookRepository
import app.kkw.searchBookApplication.service.bookService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val _bookRepository: BookRepository = BookRepository()
    private val _booksLiveData: MutableLiveData<Result<List<Book>>> = MutableLiveData()
    val bookList: LiveData<Result<List<Book>>> = _booksLiveData

    fun searchBook(bookName: String) {
        Log.d("bookName", "BookName: $bookName")

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val resultBooks = _bookRepository.searchBook(bookName)

                _booksLiveData.postValue(Result.success(resultBooks))
            } catch (e: Exception) {
                _booksLiveData.postValue(Result.failure(e))
            }
        }
    }
}