package com.example.lemonade2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade2.ui.theme.Lemonade2Theme
import java.security.spec.EllipticCurve

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lemonade2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
//                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Lemonade()
                }
            }
        }
    }
}

@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    var currentImage by remember { mutableStateOf(R.drawable.lemon_tree) }
    var currentText by remember { mutableStateOf(R.string.tap_lemon_tree) }
    var clickCount by remember { mutableStateOf(0) }

    Column(modifier = modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Image(
            painter = painterResource(id = currentImage),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .background(
                    color = Color(200, 232, 209),
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(16.dp)
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable { /* Handle image click here */
                    clickCount++
                    currentImage = when (currentImage) {
                        R.drawable.lemon_tree -> R.drawable.lemon_squeeze
                        R.drawable.lemon_squeeze -> lemonSqueezerImage(clickCount)
                        R.drawable.lemon_drink -> R.drawable.lemon_restart
                        else -> {
                            clickCount = 0
                            R.drawable.lemon_tree
                        }
                    }

                    currentText = when (currentText) {
                        R.string.tap_lemon_tree -> R.string.tap_lemon
                        R.string.tap_lemon -> lemonSqueezerText(currentImage)
                        R.string.tap_lemonade -> R.string.tap_empty_glass
                        else -> {
                            clickCount = 0
                            R.string.tap_lemon_tree
                        }
                    }
                }
        )
        Spacer(modifier = modifier.padding(8.dp))
        Text(
            text = stringResource(id = currentText),
            fontSize = 16.sp
        )
    }

}

fun lemonSqueezerText(currentImage : Int): Int {
    return if(currentImage == R.drawable.lemon_drink){
        R.string.tap_lemonade
    }
    else{
        R.string.tap_lemon
    }
}

fun lemonSqueezerImage(clickCount : Int): Int {
    val random = (5..13).random()
    return if(clickCount == random) {
        R.drawable.lemon_drink
    } else R.drawable.lemon_squeeze
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    Lemonade2Theme {
        Lemonade()
    }
}