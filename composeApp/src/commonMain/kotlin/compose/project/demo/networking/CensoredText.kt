package compose.project.demo.networking

import kotlinx.serialization.Serializable

@Serializable
data class CensoredText(
    val result: String
)