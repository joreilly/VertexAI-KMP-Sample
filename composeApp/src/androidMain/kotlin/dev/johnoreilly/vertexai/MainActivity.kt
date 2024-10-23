package dev.johnoreilly.vertexai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.vertexai.vertexAI
import dev.johnoreilly.vertexai.ui.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        // Initialize the Vertex AI service and the generative model
        // Specify a model that supports your use case
        // Gemini 1.5 models are versatile and can be used with all API capabilities
        val generativeModel = Firebase.vertexAI.generativeModel("gemini-1.5-flash")

        // Provide a prompt that contains text
        val prompt = "Write a story about a magic backpack."

//        lifecycleScope.launch {
//            // To generate text output, call generateContent with the text input
//            val response = generativeModel.generateContent(prompt)
//            print(response.text)
//
//        }

        enableEdgeToEdge()
        setContent {
            // Remove when https://issuetracker.google.com/issues/364713509 is fixed
            LaunchedEffect(isSystemInDarkTheme()) {
                enableEdgeToEdge()
            }
            App()
        }
    }
}
