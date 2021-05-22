package com.goldball.app.lang

inline fun <T> tryOrNull(block : () -> T): T? =
    try {
        block.invoke()
    } catch (e: Exception){
        e.printStackTrace()
        null
    }