package app.kkw.searchBookApplication.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kkw.searchBookApplication.model.BookList
import app.kkw.searchBookApplication.service.bookService
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val booksLiveData: MutableLiveData<BookList> = MutableLiveData()

    fun searchBook(bookName: String) {
        viewModelScope.launch {
            val response = bookService.searchBook(bookName)

            if (response.isSuccessful) {
                booksLiveData.postValue(response.body())
            } else {
                throw Exception("검색 결과가 없습니다.")
            }
        }
    }
}