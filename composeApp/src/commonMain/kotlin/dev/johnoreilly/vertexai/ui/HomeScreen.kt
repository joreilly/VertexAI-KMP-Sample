package dev.johnoreilly.vertexai.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.mikepenz.markdown.m3.Markdown
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import vertexai_kmp_sample.composeapp.generated.resources.Res
import vertexai_kmp_sample.composeapp.generated.resources.generate_content
import vertexai_kmp_sample.composeapp.generated.resources.prompt

@Composable
fun HomeScreen() {
    val viewModel = koinViewModel<GenerativeModelViewModel>()

    var prompt by rememberSaveable { mutableStateOf("Who are the top 10 football players?") }
    val generateJson = remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsState()
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = prompt,
            label = { Text(stringResource(Res.string.prompt)) },
            onValueChange = { prompt = it },
            modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Genreate JSON")
            Checkbox(checked = generateJson.value, onCheckedChange = { generateJson.value = it })
        }

        OutlinedButton(
            onClick = {
                if (prompt.isNotBlank()) {
                    keyboardController?.hide()
                    viewModel.generateContent(prompt, generateJson = generateJson.value)
                }
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(stringResource(Res.string.generate_content))
        }

        ResponseView(uiState)
    }
}

@Composable
fun ResponseView(uiState: GenerativeModelUIState) {
    when (uiState) {
        GenerativeModelUIState.Initial -> {}

        GenerativeModelUIState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is GenerativeModelUIState.Success -> {
            if (uiState.entityContent != null) {
                LazyColumn {
                    items(uiState.entityContent) { item ->
                        ListItem(
                            headlineContent = { Text(item.name)},
                            supportingContent = { Text(item.country) }
                        )
                    }
                }
            } else if (uiState.textContent != null) {
                Markdown(uiState.textContent)
            }
        }

        is GenerativeModelUIState.Error -> {
            Text(uiState.message)
        }
    }
}
