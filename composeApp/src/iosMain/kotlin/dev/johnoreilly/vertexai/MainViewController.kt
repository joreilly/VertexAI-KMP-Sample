package dev.johnoreilly.vertexai

import androidx.compose.ui.window.ComposeUIViewController
import dev.johnoreilly.vertexai.di.generativeModelIOS
import dev.johnoreilly.vertexai.ui.App


fun MainViewController(generativeModel: GenerativeModel) = ComposeUIViewController() {
    generativeModelIOS = generativeModel
    App()
}
