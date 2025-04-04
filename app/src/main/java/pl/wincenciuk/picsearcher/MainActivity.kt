package pl.wincenciuk.picsearcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.wincenciuk.picsearcher.presentation.navigation.AppNavigation
import pl.wincenciuk.picsearcher.presentation.screens.DetailsScreen
import pl.wincenciuk.picsearcher.presentation.screens.MainScreen
import pl.wincenciuk.picsearcher.ui.theme.PicSearcherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}
