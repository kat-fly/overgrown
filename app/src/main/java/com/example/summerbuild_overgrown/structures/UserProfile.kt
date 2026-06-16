package com.example.summerbuild_overgrown.structures

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val user_id: String,
    val created_at: String? = null,
    val username: String,
    val friend_id: Long,
    val current_streak: Int = 0,
    val total_achieved: Int = 0,
    val equipped_cosmetic: String = "default"
)