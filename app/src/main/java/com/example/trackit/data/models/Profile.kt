package com.example.trackit.data.models

data class Profile(
    val id: String,
    val firstName: String,
    val lastName: String,
    val state: ProfileState = ProfileState.Blank,
) {
    companion object {
        fun fromMap(data: Map<String, Any>): Profile {
            return Profile(
                    id = data["id"].toString(),
                    firstName = data["firstName"].toString(),
                    lastName = data["lastName"].toString(),
                    state = ProfileState.Loaded,
                )
        }
    }

    sealed class ProfileState {
        data object Blank: ProfileState()
        data object Loaded: ProfileState()
    }
}