package com.example.trackit.data.models

data class Profile(
    val id: String,
    val firstName: String,
    val lastName: String
) {
    companion object {
        fun fromMap(data: Map<String, Any>): Profile {
            return Profile(
                    id = data["id"].toString(),
                    firstName = data["firstName"].toString(),
                    lastName = data["lastName"].toString()
                )
        }
    }
}