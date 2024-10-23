package dev.johnoreilly.vertexai.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.johnoreilly.vertexai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


sealed class GenerativeModelUIState {
    data object Initial: GenerativeModelUIState()
    data object Loading : GenerativeModelUIState()
    data class Success(val content: String): GenerativeModelUIState()
}


class GenerativeModelViewModel(val generativeModel: GenerativeModel) : ViewModel() {
    val uiState = MutableStateFlow<GenerativeModelUIState>(GenerativeModelUIState.Initial)

    fun generateContent(prompt: String) {
        viewModelScope.launch {
            uiState.value = GenerativeModelUIState.Loading
            val content = generativeModel.generateContent(prompt) ?: ""
            uiState.value = GenerativeModelUIState.Success(content)
        }
    }
}
