package pl.wincenciuk.picsearcher.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import pl.wincenciuk.picsearcher.data.model.Hit
import pl.wincenciuk.picsearcher.presentation.navigation.AppScreens
import pl.wincenciuk.picsearcher.utils.Constants

@Composable
fun MainScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Constants.backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .padding(9.dp)
                    .padding(top = 5.dp, bottom = 2.dp),
                color = Constants.backgroundColor2,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(3.dp, Color.White),
                shadowElevation = 15.dp
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Type in any word you want and I will display the images you are interested in!",
                        modifier = Modifier
                            .padding(4.dp)
                            .padding(top = 5.dp, bottom = 5.dp),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        color = Color.White
                    ) {
                        OutlinedTextField(
                            value = "Search for images...",
                            onValueChange = {},
                            modifier = Modifier
                                .padding(10.dp),
                            shape = RoundedCornerShape(16.dp),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null
                                )
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Search
                            ),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    // Handle search action
                                }
                            )
                        )
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                ImageItemCard(item = Hit(
                    largeImageURL = "https://pixabay.com/get/gdf5fe04048b0cf022e5a5ed73874c688c86a3dee099e3275f5e48e8fc43d86ea40d665e25aa9ae587425c5fe453ff33cb60fe80a787e23fff26b8469585ca606_1280.jpg",
                    tags = "blossom, bloom, flower",
                    user = "Josch13")) {
                    navController.navigate(AppScreens.DetailsScreen.name)
                }
            }
        }
    }
}

@Composable
fun ImageItemCard(
    modifier: Modifier = Modifier,
    item: Hit,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Box(modifier = Modifier
        .height(200.dp)
        .clickable{ onClick() }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context).data(item.largeImageURL)
                .crossfade(true).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent, Color.Black
                        ), startY = 350f
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.BottomStart
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "User: ${item.user}",
                    style = TextStyle(color = Color.White, fontSize = 15.sp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Tags: ${item.tags}",
                    style = TextStyle(color = Color.White, fontSize = 15.sp)
                )
            }
        }

    }

}