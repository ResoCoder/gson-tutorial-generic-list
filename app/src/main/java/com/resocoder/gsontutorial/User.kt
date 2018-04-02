package com.resocoder.gsontutorial


data class User(val name: String,
                val likesOranges: Boolean) {
    override fun toString() = "$name - $likesOranges"
}