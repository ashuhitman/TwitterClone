package com.example.twitterclone.util

data class User(
    val email: String? = "",
    val username: String = "",
    val imageUrl: String = "",
    val followHashtags: ArrayList<String>? = arrayListOf(),
    val followUsers: ArrayList<String>? = arrayListOf()
)