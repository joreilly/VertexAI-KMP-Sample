package dev.johnoreilly.vertexai.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
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
fun GenerativeTextScreen() {
    val viewModel = koinViewModel<GenerativeModelViewModel>()

    var prompt by rememberSaveable { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = prompt,
            label = { Text(stringResource(Res.string.prompt)) },
            onValueChange = { prompt = it },
            modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth()
        )

        OutlinedButton(
            onClick = {
                if (prompt.isNotBlank()) {
                    keyboardController?.hide()
                    viewModel.generateContent(prompt)
                }
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(stringResource(Res.string.generate_content))
        }


        when (uiState) {
            GenerativeModelUIState.Initial -> {
            }

            GenerativeModelUIState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    CircularProgressIndicator()
                }
            }

            is GenerativeModelUIState.Success -> {
                Markdown((uiState as GenerativeModelUIState.Success).content)
            }
        }
    }
}
