package compose.project.demo.screens.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import compose.project.demo.BottomBarConfig
import compose.project.demo.TabNavigationItem
import compose.project.demo.tab.home.HomeTab
import compose.project.demo.tab.profile.ProfileTab
import compose.project.demo.tab.settings.SettingsTab
import kotlin.jvm.Transient

data class DetailsScreen(
    val id: Int,
    @Transient
    val onNavigator: (isRoot: Boolean) -> Unit,
) : Screen {

//    override val shouldShowBottomBar = false

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        DetailsScreenView(
            id = id,
            navigator = navigator,
            onNavigator = onNavigator
        )
    }

}

@Composable
fun DetailsScreenView(
    modifier: Modifier = Modifier,
    id: Int,
    navigator: Navigator?,
    onNavigator: (isRoot: Boolean) -> Unit,
) {
    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically { height ->
                    height
                },
                exit = slideOutVertically { height ->
                    height
                }
            ) {
                TopAppBar(
                    title = { Text(text = "Details") },
                    navigationIcon = {
                        IconButton(
                            onClick = { navigator?.pop() }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back button"
                            )
                        }
                    }
                )
            }
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Details screen id: $id"
            )
        }

    }
}

/*
*
* Comment test signing
*waeawe
* */