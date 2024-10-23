package dev.johnoreilly.vertexai.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mikepenz.markdown.m3.Markdown
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GenerativeTextScreen() {
    val viewModel = koinViewModel<GenerativeModelViewModel>()

    var prompt by rememberSaveable { mutableStateOf("") }
    val content by viewModel.content.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        ElevatedCard(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.large
        ) {
            OutlinedTextField(
                value = prompt,
//                label = { Text(stringResource(R.string.summarize_label)) },
//                placeholder = { Text(stringResource(R.string.summarize_hint)) },
                onValueChange = { prompt = it },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            TextButton(
                onClick = {
                    if (prompt.isNotBlank()) {
                        viewModel.generateContent(prompt)
                    }
                },
                modifier = Modifier.padding(end = 16.dp, bottom = 16.dp)
            ) {
                Text("Go") //stringResource(R.string.action_go))
            }
        }

        Markdown(content)
    }
}
