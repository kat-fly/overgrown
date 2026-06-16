package com.example.summerbuild_overgrown.database

import com.example.summerbuild_overgrown.database.SupabaseClient
import com.example.summerbuild_overgrown.structures.Friends
import com.example.summerbuild_overgrown.structures.UserProfile
import io.github.jan.supabase.postgrest.from

class FriendFunctions {

    suspend fun removeFriend(
        userId: String,
        friendId: Long
    ) {
        // Remove the friendship entry
        SupabaseClient.client.from("Friends")
            .delete {
                filter {
                    eq("user_id", userId)
                    eq("friend_id", friendId)
                }
            }

        // Remove the reverse entry (bidirectional unfriending)
        val friendProfile = SupabaseClient.client.from("UserProfiles")
            .select {
                filter { eq("friend_id", friendId) }
            }
            .decodeSingleOrNull<UserProfile>()

        val currentUserProfile = SupabaseClient.client.from("UserProfiles")
            .select {
                filter { eq("user_id", userId) }
            }
            .decodeSingleOrNull<UserProfile>()

        if (friendProfile != null && currentUserProfile != null) {
            SupabaseClient.client.from("Friends")
                .delete {
                    filter {
                        eq("user_id", friendProfile.user_id)
                        eq("friend_id", currentUserProfile.friend_id)
                    }
                }
        }
    }

    suspend fun getFriends(
        userId: String
    ): List<Friends> {
        return SupabaseClient.client.from("Friends")
            .select {
                filter { eq("user_id", userId) }
            }
            .decodeList<Friends>()
    }
}
