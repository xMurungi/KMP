package compose.project.demo.tab.home

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import compose.project.demo.BottomBarConfig
import compose.project.demo.screens.home.HomeScreen
import kotlin.jvm.Transient

class HomeTab(
    @Transient
    val onNavigator: (isRoot: Boolean) -> Unit,
) : Tab, BottomBarConfig {

    override val shouldShowBottomBar: Boolean = true

    @Composable
    override fun Content() {
        Navigator(
            HomeScreen(onNavigator = onNavigator)
        ) { navigator ->
            LaunchedEffect(navigator.lastItem){
                println("qsddqsdsqdsqdsqdsq"+navigator.lastItem)
                onNavigator(navigator.lastItem is HomeScreen)
            }
            SlideTransition(navigator)
        }

//        Text("Home")
    }

    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 0u,
                title = "Home",
                icon = null
            )
        }

}
