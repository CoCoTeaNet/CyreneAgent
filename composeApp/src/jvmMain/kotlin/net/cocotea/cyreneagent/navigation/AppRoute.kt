package net.cocotea.cyreneagent.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoute {
    @Serializable
    object Chat : AppRoute()

    @Serializable
    object Setting : AppRoute()
}