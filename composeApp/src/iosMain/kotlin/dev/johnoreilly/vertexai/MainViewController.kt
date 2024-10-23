package dev.johnoreilly.vertexai

import androidx.compose.ui.window.ComposeUIViewController
import dev.johnoreilly.vertexai.ui.App


var generativeModelIOS: GenerativeModel? = null

fun MainViewController(generativeModel: GenerativeModel) = ComposeUIViewController() {
    println("JFOR")
    println(generativeModel)
    generativeModelIOS = generativeModel
    App()
}
