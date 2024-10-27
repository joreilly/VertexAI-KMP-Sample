package dev.johnoreilly.vertexai.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.johnoreilly.vertexai.di.commonModule
import dev.johnoreilly.vertexai.di.platformModule
import org.koin.compose.KoinApplication


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    KoinApplication(application = {
        modules(commonModule, platformModule())
    }) {
        MaterialTheme() {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(title = {
                        Text("VertexAI-KMP")
                    })
                }
            ) {
                Column(modifier = Modifier.padding(it)) {
                    HomeScreen()
                }
            }
        }
    }
}
