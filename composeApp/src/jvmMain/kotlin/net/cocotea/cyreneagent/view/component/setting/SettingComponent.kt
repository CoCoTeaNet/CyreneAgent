package net.cocotea.cyreneagent.view.component.setting

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import net.cocotea.cyreneagent.navigation.Component

class SettingComponent(
    private val componentContext: ComponentContext,
) : Component, ComponentContext by componentContext {

    @Composable
    override fun render() {
        SettingView(

        )
    }

}