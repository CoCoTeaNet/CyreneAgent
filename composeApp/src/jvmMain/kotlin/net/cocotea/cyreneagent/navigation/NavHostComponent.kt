package net.cocotea.cyreneagent.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceCurrent
import net.cocotea.cyreneagent.view.component.chat.ChatComponent
import net.cocotea.cyreneagent.view.component.setting.SettingComponent

class NavHostComponent(componentContext: ComponentContext) : Component, ComponentContext by componentContext {
    private val navigation = StackNavigation<AppRoute>()
    private val stack = childStack(
        source = navigation,
        serializer = AppRoute.serializer(),
        initialConfiguration = AppRoute.Chat,
        childFactory = ::createScreenComponent
    )

    /**
     * Factory function to create screen from given AppRoute
     */
    private fun createScreenComponent(
        appRoute: AppRoute,
        componentContext: ComponentContext
    ): Component {
        return when (appRoute) {
            is AppRoute.Chat -> ChatComponent(
                componentContext,
            )
            is AppRoute.Setting -> SettingComponent(
                componentContext,
            )
        }
    }

    @OptIn(DelicateDecomposeApi::class)
    @Composable
    override fun render() {
        Navigation {
            navigation.replaceCurrent(it)
        }
        Children(
            stack = stack,
        ) {
            it.instance.render()
        }
    }

}