package net.cocotea.cyreneagent.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppState())
    val uiState: StateFlow<AppState> = _uiState.asStateFlow()

    fun addChatMessage(msg: String) {
        // 在主线程安全地更新状态
        _uiState.value = _uiState.value.copy(
            chatMessageList = _uiState.value.chatMessageList + msg
        )
    }

    fun addChatMessageAsync(msg: String) {
        // 如果需要异步操作（比如保存到数据库）
        viewModelScope.launch {
            // 模拟异步操作
            _uiState.value = _uiState.value.copy(isLoading = true)

            // 这里可以调用 repository 或其他异步操作
            // delay(1000) // 模拟网络请求

            _uiState.value = _uiState.value.copy(
                chatMessageList = _uiState.value.chatMessageList + msg,
                isLoading = false
            )
        }
    }

    fun clearMessages() {
        _uiState.value = _uiState.value.copy(chatMessageList = emptyList())
    }
}