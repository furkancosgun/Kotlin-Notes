package com.furkancosgun.dropdown

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkancosgun.dropdown.ui.theme.DropDownTheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DropDownTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(modifier = Modifier.background(Color.Black).padding(16.dp)){
                        DropDown(modifier = Modifier, text = "DropDown") {
                            Text(
                                text = "Item",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color=Color.Black,
                                modifier = Modifier.fillMaxWidth()
                                    .background(Color.Green)
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DropDown(
    modifier: Modifier,
    text: String,
    initiallyOpened: Boolean = false,
    content : @Composable ()-> Unit
){
    var isOpen by remember {
        mutableStateOf(initiallyOpened)
    }

    //Katlanma Hareketi
    val alpha = animateFloatAsState(
        targetValue = if(isOpen) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300
        )
    )


    //Gorunmezlik
    val rotateX = animateFloatAsState(
        targetValue = if(isOpen) 0f else -90f,
        animationSpec = tween(
            durationMillis = 300
        )
    )

    Column(modifier = modifier.fillMaxHeight()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = modifier.fillMaxWidth(),
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp
            )
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = "DropDown" ,
                tint = Color.White,
                modifier = modifier
                    .clickable {
                        isOpen = !isOpen
                    }
                    .scale(1f, if (isOpen) -1f else 1f)
            )
        }
        Spacer(modifier = modifier.height(30.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxWidth()
                .graphicsLayer {
                    rotationX = rotateX.value //Katlanma Hareketi
                    transformOrigin = TransformOrigin(0.5f, 0f)
                }
                .alpha(alpha.value)//Daha sonra gorunmezlik
        ){
            content()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DropDownTheme {
        Box(modifier = Modifier.background(Color.Black).padding(16.dp)){
            DropDown(modifier = Modifier, text = "DropDown") {
                Text(
                    text = "Item",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color=Color.White,
                    modifier = Modifier.fillMaxWidth()
                        .background(Color.Green)
                )
            }
        }
    }
}