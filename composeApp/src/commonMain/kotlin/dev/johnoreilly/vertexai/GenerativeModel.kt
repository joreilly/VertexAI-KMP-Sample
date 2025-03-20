package dev.johnoreilly.vertexai

interface GenerativeModel {
    suspend fun generateTextContent(prompt: String): String?
    suspend fun generateJsonContent(prompt: String): String?
    suspend fun generateImage(prompt: String): ByteArray?
}
