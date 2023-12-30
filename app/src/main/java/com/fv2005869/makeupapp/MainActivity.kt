package com.fv2005869.makeupapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.fv2005869.makeupapp.model.ProductItem
import com.fv2005869.makeupapp.ui.screens.MakeUpUiState
import com.fv2005869.makeupapp.ui.screens.MakeUpViewModel
import com.fv2005869.makeupapp.ui.theme.MakeUpAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MakeUpAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
                ) {
                    val makeupViewModel: MakeUpViewModel =
                        viewModel(factory = MakeUpViewModel.Factory)
                    MakeUpAppPreview()
                }
            }
        }
    }
}


@Composable
fun MakeUpApp(
    makeUpUiState: MakeUpUiState,
    modifier: Modifier = Modifier
) {
    when (makeUpUiState) {
        is MakeUpUiState.Success -> SuccessAction(products = makeUpUiState.products)
        is MakeUpUiState.Loading -> LoadingAction()
        is MakeUpUiState.Error -> ErrorAction()
    }
}


@Composable
fun SuccessAction(products: List<ProductItem>, modifier: Modifier = Modifier) {
////fun SuccessAction(products: List<Product>, modifier: Modifier = Modifier) {


    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Brush
                .verticalGradient(
                    colors = listOf(
                        Color(0xFFE06666), // Cor do topo
                        Color(0xFF6A5ACD)  // Cor da base
                    ),
                    startY = 0.0f,
                    endY = 500.0f
                ))
    ) {
        item {
            Text(
                text = "MakeUp App\n\n",
                fontWeight = FontWeight.Bold,
                color = Color(0xFFe67e22),
                fontSize = 36.sp
            )
        }

        items(products) { product ->
            val imageModifier = Modifier
                .size(175.dp, 200.dp)
                .border(BorderStroke(.5.dp, Color.LightGray))
                .background(Color.Transparent)
//                .clip(RoundedCornerShape(60.dp))

            AsyncImage(
                model = product.imageLink,
                error = painterResource(id = R.drawable.baseline_broken_image_24),
                placeholder = painterResource(id = R.drawable.baseline_cloud_download_24),
                contentDescription = product.description,
                contentScale = ContentScale.Fit,
                modifier = imageModifier
            )
            Text(text = "" +
                    "${product.name.replaceFirstChar { it.uppercase() }} - ${
                product.brand.toString().replaceFirstChar { it.uppercase() }}\n" +
                    "${product.priceSign} ${product.price}\n",
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
//    Column {
//        for (item in products){
//            Text(text=item.brand.toString())
//            Text(text=item.name.toString())
//            Text(text="${item.priceSign} ${item.price}")
//            Text(text = "${item.imageLink.toString()}\n")
//        }
//    }
}


@Composable
fun LoadingAction(modifier: Modifier = Modifier) {
    Text(text = "Loading...")
}

@Composable
fun ErrorAction(modifier: Modifier = Modifier) {
    Text(text = "Error...")
}

@Preview
@Composable
fun MakeUpAppPreview() {
    val makeupViewModel: MakeUpViewModel = viewModel()
    MakeUpApp(makeUpUiState = makeupViewModel.makeupUiState)
}