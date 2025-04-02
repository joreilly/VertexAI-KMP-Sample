package dev.johnoreilly.vertexai

import com.google.firebase.Firebase
import com.google.firebase.vertexai.type.PublicPreviewAPI
import com.google.firebase.vertexai.type.Schema
import com.google.firebase.vertexai.type.generationConfig
import com.google.firebase.vertexai.vertexAI


class GenerativeModelAndroid : GenerativeModel {
    private val jsonSchema = Schema.array(
        Schema.obj(
            mapOf(
                "name" to Schema.string(),
                "country" to Schema.string()
            )
        )
    )

    override suspend fun generateTextContent(prompt: String): String? {
        val generativeModel = Firebase.vertexAI.generativeModel(
            modelName = "gemini-1.5-flash"
        )

        return generativeModel.generateContent(prompt).text
    }

    override suspend fun generateJsonContent(prompt: String): String? {
        val generativeModel = Firebase.vertexAI.generativeModel(
            modelName = "gemini-1.5-flash",
            generationConfig = generationConfig {
                responseMimeType = "application/json"
                responseSchema = jsonSchema
            }
        )

        return generativeModel.generateContent(prompt).text
    }

    @OptIn(PublicPreviewAPI::class)
    override suspend fun generateImage(prompt: String): ByteArray? {
        val imageModel = Firebase.vertexAI.imagenModel(
            modelName = "imagen-3.0-generate-002"
        )
        val imageResponse = imageModel.generateImages(prompt)
        return if (imageResponse.images.isNotEmpty()) {
            imageResponse.images.first().data
        } else {
            null
        }
    }
}

