package app.kkw.searchBookApplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kkw.searchBookApplication.model.BookList
import app.kkw.searchBookApplication.service.bookService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val _booksLiveData: MutableLiveData<Result<BookList>> = MutableLiveData()
    val bookList: LiveData<Result<BookList>> = _booksLiveData

    fun searchBook(bookName: String) {
        Log.d("bookName", "BookName: $bookName")

        viewModelScope.launch(Dispatchers.IO) {
            val response = bookService.searchBook(bookName)

            if (response.isSuccessful) {
                val bookList = response.body()

                if (bookList != null) {
                    _booksLiveData.postValue(Result.success(bookList))

                    return@launch
                }
            }

            _booksLiveData.postValue(Result.failure(Exception("검색 결과가 없습니다.")))
        }
    }
}