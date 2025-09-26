package net.cocotea.cyreneagent.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.hutool.core.util.IdUtil
import com.agentsflex.llm.ollama.OllamaLlm
import com.agentsflex.llm.ollama.OllamaLlmConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.cocotea.cyreneagent.enums.Role

class ChatViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppState())
    val uiState: StateFlow<AppState> = _uiState.asStateFlow()
    val llm: OllamaLlm = initChat()

    fun chat(prompt: String) {
        addMessage(prompt, Role.PLAYER)
        var msgId: Long? = null
        llm.chatStream(prompt) { _, response ->
            run {
                if (msgId == null) {
                    msgId = addMessage("", Role.MODEL)
                }
                appendMessage(msgId, response.message.content)
            }
        }
    }

    fun addMessage(msg: String, role: Role): Long {
        val id = IdUtil.getSnowflakeNextId()
        _uiState.value = _uiState.value.copy(
            chatMessageList = _uiState.value.chatMessageList + Message(id, role = role, content = msg)
        )
        return id
    }

    fun appendMessage(messageId: Long, partialText: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                val updatedList = currentState.chatMessageList.map { message ->
                    if (message.id == messageId) {
                        val newMsg = message.content + partialText
                        message.copy(content = newMsg)
                    } else {
                        message
                    }
                }
                currentState.copy(chatMessageList = updatedList)
            }
        }
    }

    private fun initChat(): OllamaLlm {
        val llmConfig = OllamaLlmConfig()
        llmConfig.endpoint = "http://127.0.0.1:11434"
        llmConfig.model = "qwen3"
//        llmConfig.enableThinking = false
        return OllamaLlm(llmConfig)
    }
}