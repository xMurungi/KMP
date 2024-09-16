package compose.project.demo

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import compose.project.demo.networking.InsultCensorClient
import compose.project.demo.networking.createHttpClient
import io.ktor.client.engine.darwin.Darwin

fun MainViewController() =
    ComposeUIViewController {
        App(
            client = remember {
                InsultCensorClient(
                    createHttpClient(
                        Darwin.create()
                    )
                )
            }
        )
    }