package compose.project.demo.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import compose.project.demo.BottomBarConfig
import compose.project.demo.LocalInsultCensorClient
import compose.project.demo.PostView
import compose.project.demo.networking.InsultCensorClient
import compose.project.demo.screens.details.DetailsScreen
import compose.project.demo.screens.details.DetailsScreenView
import kotlin.jvm.Transient

class HomeScreen(
    @Transient
    val onNavigator : (isRoot : Boolean) -> Unit,
) : Screen, BottomBarConfig {

    override val shouldShowBottomBar = false

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        HomeScreenView(
            navigator = navigator,
            onNavigator = onNavigator
        )
        PostView(
            navigator = navigator,
            onNavigator = onNavigator
        )
    }

}

@Composable
fun HomeScreenView(
    modifier: Modifier = Modifier,
    navigator: Navigator?,
    onNavigator: (isRoot: Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    navigator?.push(DetailsScreen(id = 21, onNavigator))
                }
            ) {
                Text(
                    text = "Go to next screen"
                )
            }
            CensorView(
                client = LocalInsultCensorClient.current
            )
        }
    }
}
