package dev.johnoreilly.vertexai

import android.app.Application
import com.google.firebase.FirebaseApp
import dev.johnoreilly.vertexai.di.initKoin

class VertexAIKMPApp : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
        initKoin()
    }
}
