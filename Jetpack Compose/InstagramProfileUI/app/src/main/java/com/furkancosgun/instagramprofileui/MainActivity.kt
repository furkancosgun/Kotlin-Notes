package com.furkancosgun.instagramprofileui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkancosgun.instagramprofileui.ui.theme.InstagramProfileUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramProfileUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier) {
    Column {
        TopBar(modifier = modifier)
        ProfilePrewiew(modifier = modifier)
        ProfileInfo(modifier = modifier)
        ActionButtons(modifier = modifier)
        StoryList(modifier = modifier)
    }
}

@Composable
fun TopBar(modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            Icons.Default.ArrowBack,
            contentDescription = "Back Button",
            modifier
                .padding(16.dp)
                .size(30.dp)
        )
        Text(
            text = "furkancosgun",
            modifier = modifier
                .padding(16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
        )

        Icon(
            Icons.Default.Notifications, contentDescription = "Notify Button", modifier
                .padding(16.dp)
                .size(30.dp)
        )
        Icon(
            Icons.Default.Menu, contentDescription = "Menu Button", modifier
                .padding(16.dp)
                .size(30.dp)
        )
    }
}

@Composable
fun ProfilePrewiew(
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Profile",
            contentScale = ContentScale.Fit,
            modifier = modifier
                .size(110.dp)
                .clip(CircleShape),
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "100", fontWeight = FontWeight.Bold, fontSize = 28.sp)
            Text(text = "posts", fontWeight = FontWeight.Medium, fontSize = 16.sp)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "99.9K", fontWeight = FontWeight.Bold, fontSize = 28.sp)
            Text(text = "Followers", fontWeight = FontWeight.Medium, fontSize = 16.sp)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "1", fontWeight = FontWeight.Bold, fontSize = 28.sp)
            Text(text = "Following", fontWeight = FontWeight.Medium, fontSize = 16.sp)
        }
    }
}

@Composable
fun ProfileInfo(modifier: Modifier) {
    Box(modifier = modifier.fillMaxWidth()) {
        Column(modifier = modifier.padding(start = 16.dp)) {
            Text(text = "Junior develpoer", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = "Lorem Ipsum Dolor QASD", fontSize = 16.sp)
            Text(
                text = buildAnnotatedString {//Text oluşturulur ve karaktere veya kelimeye gore font biçimlendirlir
                    append("Followed By")
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    ) {
                        append(" furkangobrr ")//name degerinin ilk karakteri farklı stilde
                    }
                    append("and")//Geriye kalanı aşagıdaki stilde
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    ) {
                        append(" 1K Others ")//name degerinin ilk karakteri farklı stilde
                    }
                },
                fontSize = 16.sp,
            )
        }
    }
}

@Composable
fun ActionButtons(modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                Color.White
            ),
            onClick = { /*TODO*/ },
        ) {
            Text(text = "Following")
            Icon(Icons.Default.ArrowDropDown, contentDescription = "DropDown")
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                Color.White
            ),
            onClick = { /*TODO*/ },

            ) {
            Text(text = "Message")
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                Color.White
            ),
            onClick = { /*TODO*/ },
        ) {
            Text(text = "Email")
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                Color.White
            ),
            onClick = { /*TODO*/ },

            ) {
            Icon(Icons.Default.ArrowDropDown, contentDescription = "DropDown")
        }
    }
}


@Composable
fun StoryList(modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(modifier = modifier) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Text(text = "First S.", modifier = modifier.align(Alignment.Bottom))
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    InstagramProfileUITheme {
        HomeScreen(modifier = Modifier)
    }
}