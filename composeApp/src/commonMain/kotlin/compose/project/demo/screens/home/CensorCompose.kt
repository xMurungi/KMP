package compose.project.demo.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import compose.project.demo.networking.InsultCensorClient
import compose.project.demo.screens.details.DetailsScreen
import compose.project.demo.util.NetworkError
import compose.project.demo.util.onError
import compose.project.demo.util.onSuccess
import kotlinx.coroutines.launch

@Composable
fun CensorView(
    client: InsultCensorClient,
    modifier: Modifier = Modifier
) {

    val coroutineScope = rememberCoroutineScope()

    var censoredText by remember {
        mutableStateOf<String?>(null)
    }

    var uncensoredText by remember {
        mutableStateOf("")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    var errorMessage by remember {
        mutableStateOf<NetworkError?>(null)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable{  },
//        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = uncensoredText,
                onValueChange = { uncensoredText = it },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Uncensored Text"
                    )
                }
            )

            Button(
                onClick = {
                    coroutineScope.launch {
                        isLoading = true
                        errorMessage = null

                        client.censorWords(uncensoredText)
                            .onSuccess { censoredText = it }
                            .onError { errorMessage = it }

                        isLoading = false
                    }
                }
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(15.dp),
                        strokeWidth = 1.dp,
                        color = Color.White
                    )
                } else {
                    Text(text = "Censor Words")
                }

            }

            censoredText?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            errorMessage?.let {
                Text(
                    text = it.name,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.Red
                )
            }
        }
    }

}