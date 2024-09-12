package compose.project.demo

import cafe.adriel.voyager.core.screen.Screen

interface BottomBarConfig {
    val shouldShowBottomBar: Boolean
}

// Or as an extension property
val Screen.shouldShowBottomBar: Boolean
    get() = true // Default to true