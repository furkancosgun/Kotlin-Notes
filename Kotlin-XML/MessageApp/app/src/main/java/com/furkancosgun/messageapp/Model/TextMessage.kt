package com.furkancosgun.messageapp.Model


data class TextMessage(
    val from: String? = "",
    val to: String? = "",
    val text: String? = "",
    val time: String? = ""
)