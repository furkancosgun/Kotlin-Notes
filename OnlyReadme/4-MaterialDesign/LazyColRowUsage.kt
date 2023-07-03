package com.example.materialdesign

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun LazyColumnStaticList() {
    /*
        Item ne kadar kopyalanırsa o kadar uzunlukta liste oluşur
     */
    Column(Modifier.fillMaxSize()) {
        LazyColumn {
            item {
                CardUsage()
            }
            item {
                CardUsage()
            }
            item {
                CardUsage()
            }
        }
    }
}

@Composable
fun LazyRowStaticList() {
    /*
        Item ne kadar kopyalanırsa o kadar uzunlukta liste oluşur
     */
    Box(Modifier.fillMaxSize()) {
        LazyRow {
            item {
                CardUsage()
            }
            item {
                CardUsage()
            }
            item {
                CardUsage()
            }
        }
    }
}

@Composable
fun LazyColDynamicList() {
    val ulkeler =
        mutableListOf<String>("Turkiye", "Almanya", "Kanada", "Norvec", "Amerika", "Hollanda")
    Box(Modifier.fillMaxSize()) {
        LazyColumn {
            items(count = ulkeler.size) {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(modifier = Modifier.padding(16.dp), text = ulkeler[it])
                }
            }
        }
    }
}


@Composable
fun LazyColDynamicListClickable() {
    val context = LocalContext.current
    val ulkeler =
        mutableListOf<String>("Turkiye", "Almanya", "Kanada", "Norvec", "Amerika", "Hollanda")
    Box(Modifier.fillMaxSize()) {
        LazyColumn {
            items(count = ulkeler.size) {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            Toast
                                .makeText(context, "Tiklandı: ${ulkeler[it]}", Toast.LENGTH_LONG)
                                .show()
                        }
                ) {
                    Text(modifier = Modifier.padding(16.dp), text = ulkeler[it])
                }
            }
        }
    }
}


@Composable
@Preview(showSystemUi = true)
fun LazyColDynamicListClickableWidget() {
    val context = LocalContext.current
    val ulkeler =
        mutableListOf<String>("Turkiye", "Almanya", "Kanada", "Norvec", "Amerika", "Hollanda")
    Box(Modifier.fillMaxSize()) {
        LazyColumn {
            items(count = ulkeler.size) {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = ulkeler[it])
                        ElevatedButton(onClick = {
                            Toast.makeText(context,"Clicked: ${ulkeler[it]}",Toast.LENGTH_LONG).show()
                        }) {
                            Text(text = "Click")
                        }
                    }
                }
            }
        }
    }
}

