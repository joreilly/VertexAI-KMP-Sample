package dev.johnoreilly.vertexai.di

import dev.johnoreilly.vertexai.ui.GenerativeModelViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val commonModule = module {
    factoryOf(::GenerativeModelViewModel)
}

