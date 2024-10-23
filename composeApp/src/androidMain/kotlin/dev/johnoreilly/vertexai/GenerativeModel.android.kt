package dev.johnoreilly.vertexai

import com.google.firebase.Firebase
import com.google.firebase.vertexai.vertexAI


class GenerativeModelAndroid: GenerativeModel {
    val generativeModel = Firebase.vertexAI.generativeModel("gemini-1.5-flash")

    override suspend fun generateContent(prompt: String): String? {
        return generativeModel.generateContent(prompt).text
    }

}