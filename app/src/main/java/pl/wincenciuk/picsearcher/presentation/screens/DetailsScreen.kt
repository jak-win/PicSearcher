package pl.wincenciuk.picsearcher.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import pl.wincenciuk.picsearcher.presentation.viewmodel.ImageViewModel
import pl.wincenciuk.picsearcher.utils.Constants

@Composable
fun DetailsScreen(viewModel: ImageViewModel) {
    val context = LocalContext.current
    val selectedItem by viewModel.selectedItem.collectAsState(null)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Constants.backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(context)
                        .data(selectedItem?.largeImageURL)
                        .crossfade(true).build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Surface(
                modifier = Modifier
                    .padding(9.dp)
                    .padding(top = 5.dp, bottom = 15.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                color = Constants.backgroundColor2,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(3.dp, Color.White),
                shadowElevation = 15.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Text(
                        text = "Photo details",
                        modifier = Modifier
                            .padding(4.dp)
                            .align(Alignment.CenterHorizontally),
                        color = Color.White,
                        fontSize = 33.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Username: ${selectedItem?.user}",
                        fontSize = 17.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Tags: ${selectedItem?.tags}",
                        fontSize = 17.sp,
                        color = Color.White
                    )
                    Spacer(Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Icon(Icons.Outlined.Favorite, contentDescription = null)
                        Text(text = "${selectedItem?.likes}")
                        Spacer(modifier = Modifier.width(40.dp))
                        Icon(Icons.Default.Edit, contentDescription = null)
                        Text(text = "${selectedItem?.comments}")
                        Spacer(modifier = Modifier.width(40.dp))
                        Icon(Icons.Default.Share, contentDescription = null)
                        Text(text = "${selectedItem?.downloads}")
                    }
                }
            }
        }
    }
}