package net.cocotea.cyreneagent.view.component.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.cocotea.cyreneagent.store.AppViewModel

@Composable
fun ChatView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Text(
                text = "Body content",
            )
        }
        Row(modifier = Modifier.fillMaxWidth().weight(7f)) {
            ChatScreen()
        }
        Row(modifier = Modifier.fillMaxWidth().weight(2f)) {
            MessagePanel()
        }
    }
}

@Composable
fun ChatScreen(appViewModel: AppViewModel = viewModel()) {
    val uiState by appViewModel.uiState.collectAsState()
    LazyColumn(reverseLayout = true) {
        items(items = uiState.chatMessageList, key = { message -> message.hashCode() }) { message ->
            Text(text = "Item: $message")
        }
    }
}

@Composable
fun MessagePanel(appViewModel: AppViewModel = viewModel()) {
    val state = rememberTextFieldState()
    TextField(
        state = state,
        lineLimits = TextFieldLineLimits.SingleLine,
        label = { Text("按Enter发送消息") },
        modifier = Modifier.fillMaxSize().onPreviewKeyEvent { event ->
            if (event.key == Key.Enter && event.type == KeyEventType.KeyUp) {
                if (state.text.isNotBlank()) {
                    appViewModel.addChatMessage("""Me: ${state.text}""")
                    state.clearText()
                }
                true
            } else {
                false
            }
        },
    )
}