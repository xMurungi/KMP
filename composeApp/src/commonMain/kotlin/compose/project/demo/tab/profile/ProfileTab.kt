package compose.project.demo.tab.profile

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import compose.project.demo.BottomBarConfig

object ProfileTab : Tab, BottomBarConfig {

    override val shouldShowBottomBar: Boolean = true

    @Composable
    override fun Content() {
        Text("Profile")
    }

    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 1u,
                title = "Profile",
                icon = null
            )
        }

}

@Composable
fun Tab(
    modifier: Modifier = Modifier
) {

}