package com.example.kullanicietkilesim

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun AlertDialogUsageView() {
    var isOpenAlertDialog by remember {
        mutableStateOf(false)
    }
    var isCustomOpenAlertDialog by remember {
        mutableStateOf(false)
    }
    var alertText by remember {
        mutableStateOf("")
    }
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it), contentAlignment = Alignment.Center
        ) { Column {
            Button(onClick = { isOpenAlertDialog=!isOpenAlertDialog }) {
                Text(text = "Show Alert Dialog View")
            }
            Button(onClick = { isCustomOpenAlertDialog=!isCustomOpenAlertDialog }) {
                Text(text = "Show Custom Alert Dialog View")
            }
        }
            if (isOpenAlertDialog) {
                AlertDialog(
                    onDismissRequest = { isOpenAlertDialog = !isOpenAlertDialog },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_alert),
                            contentDescription = "Alert"
                        )
                    },
                    title = { Text(text = "Title Area") },
                    text = { Text(text = "Text Are") },
                    dismissButton = {
                        OutlinedButton(onClick = { isOpenAlertDialog = !isOpenAlertDialog }) {
                            Text(text = "Dismiss Button")
                        }
                    },
                    confirmButton = {
                        OutlinedButton(onClick = { isOpenAlertDialog = !isOpenAlertDialog }) {
                            Text(text = "Confirm Button")
                        }
                    }
                )
            }
            if (isCustomOpenAlertDialog){
                AlertDialog(
                    onDismissRequest = { isCustomOpenAlertDialog = !isCustomOpenAlertDialog },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_alert),
                            contentDescription = "Alert"
                        )
                    },
                    title = { Text(text = "Title Area") },
                    text = {
                           TextField(value = alertText, onValueChange = {text->alertText=text})
                    },
                    dismissButton = {
                        OutlinedButton(onClick = { isCustomOpenAlertDialog = !isCustomOpenAlertDialog }) {
                            Text(text = "Dismiss Button")
                        }
                    },
                    confirmButton = {
                        OutlinedButton(onClick = { isCustomOpenAlertDialog = !isCustomOpenAlertDialog }) {
                            Text(text = "Confirm Button")
                        }
                    }
                )
            }
        }
    }
}
