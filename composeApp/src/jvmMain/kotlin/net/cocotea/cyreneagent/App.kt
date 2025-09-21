package net.cocotea.cyreneagent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.window.WindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import net.cocotea.cyreneagent.navigation.NavHostComponent
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val windowState = WindowState()
    val lifecycle = LifecycleRegistry()
    val componentContext = DefaultComponentContext(lifecycle)
    val root = NavHostComponent(componentContext)
    LifecycleController(lifecycle, windowState)

    MaterialTheme {
        Column {
            Row {
                root.render()
            }
        }
    }
}
