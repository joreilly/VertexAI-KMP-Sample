package dev.johnoreilly.vertexai

interface GenerativeModel {
    suspend fun generateContent(prompt: String): String?
}