package compose.project.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        WindowCompat.setDecorFitsSystemWindows(window, false) // Enable edge-to-edge content

        setContent {
            val darkTheme = isSystemInDarkTheme()
            val view = LocalView.current
            val window = (view.context as? ComponentActivity)?.window ?: return@setContent

            // Set system bars colors to match the Composables
            val systemBarColor = if (darkTheme) Color.Black else Color.White

            // Set status bar color
            window.statusBarColor = systemBarColor.toArgb()

            // Set navigation bar color
            window.navigationBarColor = systemBarColor.toArgb()

            // Modify the insets appearance
            WindowInsetsControllerCompat(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}