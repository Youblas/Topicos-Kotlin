package com.example.demotipcalculatorkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform