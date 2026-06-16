package com.example.summerbuild_overgrown.structures

import kotlinx.serialization.Serializable

@Serializable
data class Friends(
    val user_id: String,
    val friends_since: String? = null,
    val friend_id: Long
)