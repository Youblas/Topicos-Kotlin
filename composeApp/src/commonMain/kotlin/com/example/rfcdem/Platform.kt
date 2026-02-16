package com.example.rfcdem

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform