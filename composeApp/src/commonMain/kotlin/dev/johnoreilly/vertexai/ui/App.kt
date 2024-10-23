package dev.johnoreilly.vertexai.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    MaterialTheme() {
        Surface {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(title = {
                        Text("VertexAIKMP")
                    })
                }
            ) {
                Column(modifier = Modifier.padding(it)) {
                    GenerativeTextScreen()
                }
            }
        }
    }
}
