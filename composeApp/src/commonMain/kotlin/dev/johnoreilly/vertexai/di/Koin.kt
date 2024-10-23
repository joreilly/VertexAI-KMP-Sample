package dev.johnoreilly.vertexai.di

import dev.johnoreilly.vertexai.ui.GenerativeModelViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(commonModule, platformModule())
    }
}

val commonModule = module {
    factoryOf(::GenerativeModelViewModel)
}


expect fun platformModule(): Module
