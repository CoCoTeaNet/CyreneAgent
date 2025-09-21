package net.cocotea.cyreneagent.view.component.chat

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import net.cocotea.cyreneagent.navigation.Component

class ChatComponent(
    private val componentContext: ComponentContext,
) : Component, ComponentContext by componentContext{
    @Composable
    override fun render() {
        ChatView(

        )
    }
}