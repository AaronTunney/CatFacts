package com.aarontunney.catfacts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// The view model calls the Retrofit interface and holds the list of cat facts.
class MainViewModel: ViewModel() {
    // mutableStateOf causes the Jetpack Compose user interface to redraw when it receives
    // new data - very clever and very useful for network calls!
    var factListResponse: List<Fact> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getFactList() {
        // Sets up the asynchronous network request
        viewModelScope.launch {
            val apiService = ApiInterface.create()
            try {
                val factList = apiService.getFacts()
                factListResponse = factList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}