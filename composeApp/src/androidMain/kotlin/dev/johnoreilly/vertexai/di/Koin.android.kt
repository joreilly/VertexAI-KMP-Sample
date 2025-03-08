package dev.johnoreilly.vertexai.di

import dev.johnoreilly.vertexai.GenerativeModel
import dev.johnoreilly.vertexai.GenerativeModelAndroid
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initialiseKoin() {
    startKoin {
        modules(
            commonModule,
            module { single<GenerativeModel> { GenerativeModelAndroid() } }
        )
    }
}
