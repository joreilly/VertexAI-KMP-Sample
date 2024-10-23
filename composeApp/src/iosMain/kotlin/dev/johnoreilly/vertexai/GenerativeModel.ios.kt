package dev.johnoreilly.vertexai

class GenerativeModelIOS: GenerativeModel {
    override suspend fun generateContent(prompt: String): String? {

        return generativeModelIOS?.generateContent(prompt)
    }

}