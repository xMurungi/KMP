package compose.project.demo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.Navigation
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import compose.project.demo.networking.InsultCensorClient
import compose.project.demo.screens.details.DetailsScreen
import compose.project.demo.screens.home.HomeScreen
import compose.project.demo.tab.home.HomeTab
import compose.project.demo.tab.profile.ProfileTab
import compose.project.demo.tab.settings.SettingsTab
import composedemo.composeapp.generated.resources.compose_multiplatform
import composedemo.composeapp.generated.resources.eg
import composedemo.composeapp.generated.resources.fr
import composedemo.composeapp.generated.resources.id
import composedemo.composeapp.generated.resources.jp
import composedemo.composeapp.generated.resources.ke
import composedemo.composeapp.generated.resources.mx
import composedemo.composeapp.generated.resources.student
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import composedemo.composeapp.generated.resources.Res
import composedemo.composeapp.generated.resources.compose_multiplatform

data class Country(
    val name: String,
    val zone: TimeZone,
    val image: DrawableResource
)

fun currentTimeAt(
    location: String,
    zone: TimeZone
): String {
    fun LocalTime.formatted() = "$hour:$minute:$second"

    val time = Clock.System.now()
    val localTime = time.toLocalDateTime(zone).time

    return "The time in $location is ${localTime.formatted()}"
}

val defaultCountries = listOf(
    Country("Japan", TimeZone.of("Asia/Tokyo"), Res.drawable.jp),
    Country("France", TimeZone.of("Europe/Paris"), Res.drawable.fr),
    Country("Mexico", TimeZone.of("America/Mexico_City"), Res.drawable.mx),
    Country("Indonesia", TimeZone.of("Asia/Jakarta"), Res.drawable.id),
    Country("Egypt", TimeZone.of("Africa/Cairo"), Res.drawable.eg),
    Country("Kenya", TimeZone.of("Africa/Nairobi"), Res.drawable.ke)
)

val LocalInsultCensorClient = compositionLocalOf<InsultCensorClient> { error("No client provided") }

@Composable
@Preview
fun App(
    client: InsultCensorClient,
    countries: List<Country> = defaultCountries
) {
    CompositionLocalProvider(LocalInsultCensorClient provides client) {
        MaterialTheme {
            val navigator = LocalNavigator.current

            // Track if the current screen should hide the bottom bar
            val isBottomBarVisible = remember {
                mutableStateOf(true) // Initially, the bottom bar is visible
            }

            var isVisible by remember { mutableStateOf(true) }
            val homeTab = remember {
                HomeTab(
                    onNavigator = { isVisible = it }
                )
            }

            TabNavigator(
                tab = homeTab
            ) { tabNavigator ->
                // Access the current tab
                val currentTab = tabNavigator.current

//            val currentScreen = LocalNavigator.current?.lastItem

                // Update isBottomBarVisible based on current screen type
                if (navigator?.lastItem is DetailsScreen) {
                    isBottomBarVisible.value = false
                } else {
                    isBottomBarVisible.value = true
                }

                Scaffold(
                    bottomBar = {
                        AnimatedVisibility(
                            visible = isVisible,
                            enter = slideInVertically { height ->
                                height
                            },
                            exit = slideOutVertically { height ->
                                height
                            }
                        ) {
                            BottomNavigation {
                                TabNavigationItem(homeTab)
                                TabNavigationItem(SettingsTab)
                                TabNavigationItem(ProfileTab)
                            }
                        }
                    }
                ) { paddingValues ->
                    // Apply padding to the content
                    Surface(
                        modifier = Modifier
                            .padding(paddingValues)
                    ) {
                        CurrentTab()
                    }
                }
            }

        }
    }
}

@Composable
fun RowScope.TabNavigationItem(
    tab: Tab
) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        label = { Text(tab.options.title) },
        icon = {  }
    )

}

@Composable
fun EntryScreen(
    modifier: Modifier = Modifier,
    countries: List<Country> = defaultCountries
) {
    var showCountries by remember { mutableStateOf(false) }
    var timeAtLocation by remember { mutableStateOf("No location selected") }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text(
                        "My App",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {},
//                    scrollBehavior = scrollBehavior,
            )
        }
    ) {
        LazyColumn {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = timeAtLocation,
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )

                    Row(
                        modifier = Modifier
                            .padding(start = 20.dp, top = 10.dp)
                    ) {
                        DropdownMenu(
                            expanded = showCountries,
                            onDismissRequest = { showCountries = !showCountries }
                        ) {
                            countries.forEach { (name, zone, image) ->
                                DropdownMenuItem(
                                    onClick = {
                                        timeAtLocation = currentTimeAt(name, zone)
                                        showCountries = false
                                    }
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painterResource(image),
                                            modifier = Modifier
                                                .size(50.dp)
                                                .padding(end = 10.dp),
                                            contentDescription = "$name flag"
                                        )
                                        Text(name)
                                    }

                                }
                            }

                        }
                    }

                    Button(
                        onClick = { showCountries = !showCountries },
                    ) {
                        Text("Select location")
                    }

//                    PostView()
//                    PostView()
//                    PostView()
//                    PostView()
//                    PostView()
                }
            }
        }
    }

}

@Composable
fun PostView(
    modifier: Modifier = Modifier,
    navigator: Navigator?,
    onNavigator: (isRoot: Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable{ navigator?.push(DetailsScreen(id = -1, onNavigator)) },
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Image(
                painterResource(Res.drawable.student),
                contentDescription = "flag",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            )
            Text(
                "Card composable"
            )
            Text(
                "This is a text about me. This is a text about me. This is a text about me. This is a text about me."
            )
        }
    }
}
