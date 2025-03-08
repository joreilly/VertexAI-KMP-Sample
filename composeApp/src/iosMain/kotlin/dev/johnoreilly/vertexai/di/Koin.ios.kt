package dev.johnoreilly.vertexai.di

import dev.johnoreilly.vertexai.GenerativeModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


fun initialiseKoin(generativeModel: GenerativeModel) {
    startKoin {
        modules(
            commonModule,
            module { single<GenerativeModel> { generativeModel } }
        )
    }

}

