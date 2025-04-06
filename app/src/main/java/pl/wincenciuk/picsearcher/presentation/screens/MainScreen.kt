package pl.wincenciuk.picsearcher.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import pl.wincenciuk.picsearcher.presentation.viewmodel.ImageViewModel
import pl.wincenciuk.picsearcher.utils.Constants

@Composable
fun MainScreen(navController: NavController, viewModel: ImageViewModel) {
    var searchText by remember { mutableStateOf("fruits") }
//    val viewModel = getViewModel<ImageViewModel>()
    val imageData = viewModel.imagesData.collectAsState(emptyList())
    val selectedItem = remember { mutableStateOf<Hit?>(null) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val showDialog = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = searchText) {
        viewModel.getImages(searchText)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Constants.backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 20.dp)
                .verticalScroll(rememberScrollState()),
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
                            value = searchText,
                            onValueChange = { searchText = it },
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
                                    keyboardController?.hide()
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

            ) {
                imageData.value.forEach { response ->
                    response.hits.forEach { item ->
                        ImageItemCard(
                            item = item
                        )
                        {
                            selectedItem.value = item
                            viewModel.setSelectedItem(item)
                            showDialog.value = true
                        }
                    }
                }
            }
        }
        if (showDialog.value) {
            ConfirmationDialog(
                showDialog,
                onConfirm = {
                    selectedItem.value?.let { viewModel.setSelectedItem(it) }
                    navController.navigate(AppScreens.DetailsScreen.name)
                },
                onDismiss = {
                    showDialog.value = false
                }
            )
        }
    }
}

@Composable
fun ImageItemCard(
    item: Hit,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Box(modifier = Modifier
        .padding(6.dp)
        .height(200.dp)
        .clickable { onClick() }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmationDialog(
    showDialog: MutableState<Boolean>,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = {
            showDialog.value = false
        },
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation,
            color = Constants.backgroundColor2,
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Do you want to see details of this image?", color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.End) {
                    TextButton(
                        onClick = {
                            showDialog.value = false
                            onConfirm()
                        }
                    ) {
                        Text(text = "Yes")
                    }
                    TextButton(
                        onClick = {
                            showDialog.value = false
                            onDismiss()
                        }) {
                        Text(text = "No")
                    }
                }
            }
        }
    }
}
