package net.cocotea.cyreneagent.navigation

import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailDefaults
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun Navigation(
    onGoClicked: (route : AppRoute) -> Unit
) {
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf(
        NavItem(AppRoute.Chat, Icons.Filled.Home, "首页"),
        NavItem(AppRoute.Setting, Icons.Filled.Settings, "设置"),
    )

    NavigationRail(windowInsets = NavigationRailDefaults.windowInsets) {
        items.forEachIndexed { index, item ->
            NavigationRailItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onGoClicked(item.route)
                },
            )
        }
    }
}

data class NavItem(
    val route: AppRoute,
    val icon: ImageVector,
    val label: String
)