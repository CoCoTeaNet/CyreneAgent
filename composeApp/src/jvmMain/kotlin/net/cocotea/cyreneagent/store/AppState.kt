package net.cocotea.cyreneagent.store

import net.cocotea.cyreneagent.enums.Role

data class AppState(
    val chatMessageList: List<Message> = emptyList(),
    val isLoading: Boolean = false
)

data class Message (
    val id: Long,
    val role: Role,
    var content: String = ""
)