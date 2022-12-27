package com.furkancosgun.listinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.furkancosgun.listinapp.ui.theme.ListInAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListInAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    LazyColumn() {
        for(i in 1 until 100) {
            item { //Item olması gerekli scroll işlemi yapılcaksa lazycolumn ile aksi takdirde hata alcaktır
                MessageCard(msg = Message(author = "Furkan Coşgun", body = stringResource(id = R.string.lorem)))
            }
        }
    }

}

data class Message(val author: String, val body: String)//Message Class


@Composable
fun MessageCard(msg: Message) {
    //Modifier ile herturlu şekillendirme boyutlandırm işlemleri yapılır
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)//Daire içinde gorsel işlemi
                .border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))//8 dp genişlikl boşluk bırakır

        //state tanımlanır genişletme/daraltma işlemi için
        var isExpanded by remember { mutableStateOf(false) }

        // genişleme durumalrında renk degişimi için ayrı bir state
        val surfaceColor by animateColorAsState(//genişleyince renk değişimi için state
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )

        // Colona basıldıgı durumda state durumunu tersi yonde tetikler
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                // Değişimden donen state rengi
                color = surfaceColor,
                // Animasyonlu içeri boyu eklendigi için daha rahat bi gorunum ile daralma işlemi olcak
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    //Eğer Genişletilmiş durumda ise satır sayısı maxa cıkar dar konumda ise 1e düşer
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ListInAppTheme {
       MainScreen()
    }
}