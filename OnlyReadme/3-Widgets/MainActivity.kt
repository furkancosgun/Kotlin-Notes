package com.example.widgets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.widgets.ui.theme.WidgetsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WidgetsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    /*
                    WidgetScreen {
                        Button_Text_TextField()
                        OutlinedButton_OutlinedTextField()
                    }
                     */
                    //HomeScreen()
                    WidgetScreen {
                        /*
                          SwitchUsage()
                          CheckBoxUsage()
                          ClickableUsage()
                          GestureDetectorUsage()
                        RadioButtonUsage()
                        ProgressIndicatorUsage()
                        SliderUsage()
                        WebViewUsage()
                         */
                        DropDownMenuUsage()
                    }
                }
            }
        }
    }
}

/* Standard Android Ozelliklerini Kullanmak İçin Aşagıdaki Şekilde Yapmalıyız
AndroidView(factory = {
    WebView(it).apply {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        webViewClient = WebViewClient()
        loadUrl(url)
    }
}, update = {
    it.loadUrl(url)
})

 */


@Composable
fun WidgetScreen(widgets: @Composable () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        widgets()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WidgetsTheme {
        WidgetScreen {
            /*
                SwitchUsage()
               CheckBoxUsage()
               ClickableUsage()
               GestureDetectorUsage()
            RadioButtonUsage()
            ProgressIndicatorUsage()
            SliderUsage()
            WebViewUsage()
            ImageUsage()
             */
            DropDownMenuUsage()
        }
        //HomeScreen()
    }
}