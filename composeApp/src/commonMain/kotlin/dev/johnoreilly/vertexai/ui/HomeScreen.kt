package dev.johnoreilly.vertexai.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import com.mikepenz.markdown.m3.Markdown
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import vertexai_kmp_sample.composeapp.generated.resources.Res
import vertexai_kmp_sample.composeapp.generated.resources.generate_content
import vertexai_kmp_sample.composeapp.generated.resources.generate_image

@Composable
fun HomeScreen() {
    val viewModel = koinViewModel<GenerativeModelViewModel>()

    var prompt by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    val generateJson = remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(modifier = Modifier.padding(8.dp)) {
        BasicTextField(
            value = prompt,
            onValueChange = { prompt = it },
            textStyle = TextStyle(fontSize = 24.sp),
            modifier = Modifier
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                .padding(8.dp)
                .focusRequester(focusRequester)
                .fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Generate JSON")
            Checkbox(checked = generateJson.value, onCheckedChange = { generateJson.value = it })
        }

        Row {
            OutlinedButton(
                onClick = {
                    if (prompt.text.isNotBlank()) {
                        keyboardController?.hide()
                        viewModel.generateContent(prompt.text, generateJson = generateJson.value)
                    }
                },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(stringResource(Res.string.generate_content))
            }

            Spacer(Modifier.width(16.dp))
            OutlinedButton(
                onClick = {
                    if (prompt.text.isNotBlank()) {
                        keyboardController?.hide()
                        viewModel.generateImage(prompt.text)
                    }
                },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(stringResource(Res.string.generate_image))
            }

        }

        ResponseView(uiState, prompt.text)
    }
}

@Composable
fun ResponseView(uiState: GenerativeModelUIState, prompt: String) {
    val scrollState = rememberScrollState()

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
                            headlineContent = { Text(item.name) },
                            supportingContent = { Text(item.country) }
                        )
                    }
                }
            } else if (uiState.textContent != null) {
                Column(modifier = Modifier.verticalScroll(scrollState)) {
                    Markdown(uiState.textContent)
                }
            } else if (uiState.imageData != null) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalPlatformContext.current)
                        .data(uiState.imageData)
                        .build(),
                    contentDescription = prompt,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        is GenerativeModelUIState.Error -> {
            Text(uiState.message)
        }
    }
}
