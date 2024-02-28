package app.kkw.searchBookApplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kkw.searchBookApplication.model.Book
import app.kkw.searchBookApplication.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val _bookRepository: BookRepository = BookRepository()
    private val _booksLiveData: MutableLiveData<Result<List<Book>>> = MutableLiveData()
    private var _start = 1
    private var _bookName = ""
    val bookList: LiveData<Result<List<Book>>> = _booksLiveData

    fun searchBook(bookName: String) {
        _start = 1;
        _bookName = bookName
        _booksLiveData.postValue(Result.success(emptyList()))

        updateBookResult()
    }

    fun getMoreResult() {
        if (_start == 1000) return

        _start += 100

        updateBookResult()
    }

    private fun updateBookResult() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val resultBooks = _bookRepository.searchBook(_bookName, _start)
                val originBooks = bookList.value?.getOrNull()?.toMutableList() ?: mutableListOf()

                originBooks.addAll(resultBooks)

                _booksLiveData.postValue(Result.success(originBooks))
            } catch (e: Exception) {
                _booksLiveData.postValue(Result.failure(e))
            }
        }
    }
}