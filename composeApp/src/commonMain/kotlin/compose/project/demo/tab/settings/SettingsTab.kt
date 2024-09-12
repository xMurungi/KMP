package compose.project.demo.tab.settings

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import compose.project.demo.BottomBarConfig

object SettingsTab : Tab, BottomBarConfig {

    override val shouldShowBottomBar: Boolean = true

    @Composable
    override fun Content() {
        Text("Settings")
    }

    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 2u,
                title = "Settings",
                icon = null
            )
        }

}

@Composable
fun Tab(
    modifier: Modifier = Modifier
) {

}