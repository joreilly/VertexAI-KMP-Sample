package dev.johnoreilly.vertexai.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.johnoreilly.vertexai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GenerativeModelViewModel(val generativeModel: GenerativeModel) : ViewModel() {

    val content = MutableStateFlow("")

    fun generateContent(prompt: String) {
        viewModelScope.launch {
            content.value = generativeModel.generateContent(prompt) ?: ""
        }
    }
}
