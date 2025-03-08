//package dev.johnoreilly.vertexai
//
//import dev.johnoreilly.vertexai.di.generativeModelIOS
//
//class GenerativeModelIOS: GenerativeModel {
//    override suspend fun generateTextContent(prompt: String): String? {
//        return generativeModelIOS?.generateTextContent(prompt)
//    }
//
//    override suspend fun generateJsonContent(prompt: String): String? {
//        return generativeModelIOS?.generateJsonContent(prompt)
//    }
//}