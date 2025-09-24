package net.cocotea.cyreneagent.store

data class AppState(
    val chatMessageList: List<String> = emptyList(),
    val isLoading: Boolean = false
)
