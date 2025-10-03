package net.cocotea.cyreneagent.view.component.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberOverscrollEffect
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import net.cocotea.cyreneagent.enums.Role
import net.cocotea.cyreneagent.store.ChatViewModel

@Composable
fun ChatView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().weight(0.5f)) {
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
                Text(text = "Power by kotlin multiplatform")
            }
        }
        Row(modifier = Modifier.fillMaxWidth().weight(7.5f)) {
            ChatScreen()
        }
        Row(modifier = Modifier.fillMaxWidth().weight(2f)) {
            MessagePanel()
        }
    }
}

@Composable
fun ChatScreen(chatViewModel: ChatViewModel = viewModel()) {
    val uiState by chatViewModel.uiState.collectAsState()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        state = listState,
        overscrollEffect = rememberOverscrollEffect(),
    ) {
        items(
            items = uiState.chatMessageList,
            key = { message -> message.id },
        ) { message ->
            var lr = Arrangement.Start
            var color = Color.White
            if (message.role == Role.PLAYER) {
                lr = Arrangement.End
                color = Color(0xFFf3f3f3)
            }
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .background(color),
            ) {
                Row(modifier = Modifier.fillMaxSize(),horizontalArrangement = lr) {
                    Text(text = message.content)
                }
            }
            coroutineScope.launch {
                listState.animateScrollToItem(index = uiState.chatMessageList.size - 1)
            }
        }
    }
}

@Composable
fun MessagePanel(chatViewModel: ChatViewModel = viewModel()) {
    val state = rememberTextFieldState()
    TextField(
        state = state,
        lineLimits = TextFieldLineLimits.SingleLine,
        label = { Text("按Enter发送消息") },
        modifier = Modifier.fillMaxSize().onPreviewKeyEvent { event ->
            if (event.key == Key.Enter && event.type == KeyEventType.KeyUp) {
                if (state.text.isNotBlank()) {
                    chatViewModel.chat("""${state.text}""")
                    state.clearText()
                }
                true
            } else {
                false
            }
        },
    )
}