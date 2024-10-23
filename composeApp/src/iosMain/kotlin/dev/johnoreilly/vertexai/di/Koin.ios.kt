package dev.johnoreilly.vertexai.di

import dev.johnoreilly.vertexai.GenerativeModel
import dev.johnoreilly.vertexai.GenerativeModelIOS
import org.koin.core.module.Module
import org.koin.dsl.module

var generativeModelIOS: GenerativeModel? = null

actual fun platformModule(): Module = module {
    single<GenerativeModel> { GenerativeModelIOS() }
}
