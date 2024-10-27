package dev.johnoreilly.vertexai

import android.app.Application
import com.google.firebase.FirebaseApp


class VertexAIKMPApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
