package compose.project.demo.tab.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import compose.project.demo.BottomBarConfig

object PaymentsTab : Tab, BottomBarConfig {

    override val shouldShowBottomBar: Boolean = true

    @Composable
    override fun Content() {
        PaymentsList()
    }

    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 2u,
                title = "Payments",
                icon = null
            )
        }

}

@Composable
fun PaymentsList(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(14) {
            PastPayments()
        }
    }
}

@Composable
fun PastPayments(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
//            .clickable{ navigator?.push(DetailsScreen(id = -1, onNavigator)) },
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp
    ) {
        Column(
//            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier
            ) {
                Text(
                    text = "Date"
                )
                Text(
                    text = "27th October 2024"
                )
            }
            Row(
                modifier = modifier
            ) {
                Text(
                    text = "Amount"
                )
                Text(
                    text = "14,000 Ksh."
                )
            }
            Row(
                modifier = modifier
            ) {
                Text(
                    text = "Paid to: "
                )
                Text(
                    text = "Amani Apartments"
                )
            }
            Button(
                onClick = {},
                content = {
                    Text(
                        text = "Click to view Invoice"
                    )
                }
            )
        }
    }
}