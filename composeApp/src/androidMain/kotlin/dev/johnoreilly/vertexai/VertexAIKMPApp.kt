package dev.johnoreilly.vertexai

import android.app.Application
import dev.johnoreilly.vertexai.di.initKoin

class VertexAIKMPApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
