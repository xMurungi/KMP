package compose.project.demo.tab.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import compose.project.demo.BottomBarConfig
import composedemo.composeapp.generated.resources.Res
import composedemo.composeapp.generated.resources.ke
import org.jetbrains.compose.resources.painterResource

object ProfileTab : Tab, BottomBarConfig {

    override val shouldShowBottomBar: Boolean = true

    @Composable
    override fun Content() {
        ProfileScreen()
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
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        item {
            LandLordDetails(
                modifier = Modifier
                    .padding(20.dp)
            )
            Properties(
                modifier = Modifier
                    .padding(20.dp)
            )
            Properties(
                modifier = Modifier
                    .padding(20.dp)
            )
            Properties(
                modifier = Modifier
                    .padding(20.dp)
            )
            Properties(
                modifier = Modifier
                    .padding(20.dp)
            )
        }

    }
}

@Composable
fun LandLordDetails(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp
    ) {
        Row(
            modifier = modifier
        ) {
            Column {
                Image(
                    painter = painterResource(Res.drawable.ke),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = "John Doe"
                )
            }
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Row {
                    Text(
                        text = "Properties: "
                    )
                    Text(
                        text = "2"
                    )
                }

            }
        }
    }
}

@Composable
fun Properties(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp
    ) {
        Column {
            Image(
                painter = painterResource(Res.drawable.ke),
                contentDescription = null,
                modifier = Modifier
//                    .size(100.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
//                        .clip(CircleShape)
            )
            Text(
                text = "Amani Apartment"
            )
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Row {
                    Text(
                        text = "Units: "
                    )
                    Text(
                        text = "14"
                    )
                }
                Row {
                    Text(
                        text = "PayBill: "
                    )
                    Text(
                        text = "247247"
                    )
                }
                Row {
                    Text(
                        text = "Accnt. No.: "
                    )
                    Text(
                        text = "221053"
                    )
                }
            }
        }
    }
}