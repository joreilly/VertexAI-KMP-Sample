package dev.johnoreilly.vertexai

import dev.johnoreilly.vertexai.di.generativeModelIOS

class GenerativeModelIOS: GenerativeModel {
    override suspend fun generateContent(prompt: String): String? {
        return generativeModelIOS?.generateContent(prompt)
    }
}